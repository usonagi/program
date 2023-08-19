#include<iostream>

// 编译器C++11以上 使用参数-std=c++11

/* 编译器进行优化时 RVO与NRVO会直接把对象写到目标位置，避免拷贝带来的开销。
 * 条件：函数返回对象必须在编译时可确定才能优化.
 * */

class Obj{
public:
    int count;

    Obj():count(1) { std::cout << "call default construction." << std::endl; }
    Obj(int n):count(n) { std::cout << "call construction." << std::endl; }
    ~Obj(){ std::cout << "call destruction." << std::endl; }

    //Obj(Obj& obj)=default;
    //Obj(Obj&& obj)=delete;
    Obj(const Obj& obj) {
        std::cout << "call copy construction." << std::endl;
        this->count = obj.count;
    }
    Obj(Obj&& obj) noexcept { 
        std::cout << "call move construction." << std::endl;
        this->count = obj.count;
        obj.count = -1;
    }

    // 拷贝赋值——拷贝另一个对象
    /*Obj& operator= (const Obj& other) {
        std::cout << "call copy assignment." << std::endl;
        this->count = other.count;
        return *this;
    }
    // 移动赋值——占有另一个对象的资源
    Obj& operator= (Obj&& other) noexcept {
        std::cout << "call move assignment." << std::endl;
        this->count = other.count;
        other.count = -1;
        return *this;
    }*/

    // 更为安全的做法  通用的赋值运算符
    Obj& operator= (Obj other) {  // 依据实参类型调用拷贝构造或移动构造
        std::cout << "call general assignment." << std::endl;
        other.swap(*this);
        return *this;
    }
    void swap(Obj& rhs) noexcept{
        using std::swap;
        swap(this->count, rhs.count);
    }

};

// 禁止返回栈上对象的引用
int& ReturnLocalLRef();
int&& ReturnLocalRRef();

Obj ReturnClassObj();
void DeliverObj(Obj);  // 参数是值类型时，形参是实参的拷贝 即会调用拷贝构造
void DeliverObjPtr(Obj*);  // 参数是指针类型时，会对指针进行拷贝
void DeliverObjRhs(Obj&);  // 参数是引用类型时，不会发生拷贝

int main()
{
    std::cout << ("Hello") << " " << static_cast<const void*>("Hello") << std::endl;  // 输出字符串的地址

    // 函数返回局部变量的引用的行为是禁止的 栈上对象被释放后返回引用变为无所指的引用
    //int lret =  ReturnLocalLRef();
    //int&& rret = ReturnLocalRRef();
    //std::cout << "函数返回值是：" << lret << " " << rret << std::endl;
    
    // 使用-fno-elide-constructors关闭编译器优化
    Obj objx = ReturnClassObj();  // 优化时只进行一次构造 不调用移动/拷贝构造
    std::cout << "objx的属性count: " << objx.count << std::endl;

    Obj objy = Obj(2);  // 未优化时会调用移动构造
    objy = objx;  // 调用拷贝赋值
    objy = std::move(objx);  // 调用移动赋值
    //Obj obj();  // 看作函数声明

    DeliverObj(objx);  // 调用拷贝构造
    DeliverObj(std::move(objx));  // 调用移动构造
    DeliverObjPtr(&objx);  // 不调用任何构造
    DeliverObjRhs(objx);  // 不调用任何构造

    return 0;
}

int& ReturnLocalLRef()
{
    //int local = -1;
    static int local = 0;
    return local;  // 错误 编译器产生警告——返回局部变量的引用
}

int&& ReturnLocalRRef()
{
    int local = -1;
    return std::move(local);  // 错误 未定义行为
}

Obj ReturnClassObj()
{
    return Obj();  // 不具名返回值优化 RVO

    //Obj obj;
    //obj.count = -1;
    //return obj;  // 具名返回值优化 NRVO 不会调用但至少需要移动/拷贝构造其中之一

    //return std::move(obj);  // 会禁止NRVO
}

void DeliverObj(Obj obj)
{
    std::cout << "call deliver obj." << std::endl;
}
void DeliverObjPtr(Obj* obj)
{
    std::cout << "call deliver obj pointer." << std::endl;
}
void DeliverObjRhs(Obj& obj)
{
    std::cout << "call deliver obj reference." << std::endl;
}
