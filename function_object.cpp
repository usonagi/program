#include <iostream>
#include <functional>

/*=== 函数对象 ===*/
/* 任何定义了函数调用操作符的对象都是函数对象。
 *
 * 类模板 std::function 是通用多态函数包装器。std::function 的实例能存储、复制及调用任何
 *   可复制构造的可调用 (Callable) 目标——函数、 lambda 表达式、 bind 表达式或其他函数对象，还有指向成员函数指针和指向数据成员指针。
 * 存储的可调用对象被称为 std::function 的目标。若 std::function 不含目标，则称它为空。
 * 调用空 std::function 的目标导致抛出 std::bad_function_call 异常。
 * */

struct FunObj{
    void operator () ()  // 重载函数调用操作符()
    { std::cout << "call FunObj class." << std::endl; }

};

class Obj{
public:
    int _count{};
    Obj(){};
    Obj(int n) :_count(n) {}

    void CountAddNum(int n) const  // const成员函数可以被const对象与非const对象调用，const对象不能调用非const函数
    { std::cout << "_count + n: " << _count + n << std::endl; }

    static void PrintClassName()  // static成员函数可以被对象直接调用，也可使用类名::函数名的方式
    { std::cout << "This is Obj class!\n"; }
};

void Hello() { std::cout << "Hello world!\n"; }

void PrintNumber(int n) 
{ std::cout << "the value of arg is: " << n << std::endl;}

int main()
{
    FunObj fo{};
    fo();  // 调用函数对象类

    // 定义空函数对象
    std::function<int()> f = nullptr;
    try{
        f();
    }
    catch(const std::bad_function_call& e){
        std::cout << e.what() << '\n';
    }
    
    // 存储函数指针
    void (*fp)();
    fp = Hello;
    std::function<void()> f_pointer = fp;
    f_pointer();

    // 存储普通函数
    std::function<void(int)> f_display = PrintNumber;
    f_display(-1);

    // 存储std::bind()的调用结果 
    std::function<void()> f_display_999 = std::bind(PrintNumber, 999);  // 注意无形参
    f_display_999();

    // 存储lambda表达式
    std:: function<void()> f_lambda = [](){ PrintNumber(0); };
    f_lambda();

    const Obj fobj(99);
    // 存储到成员函数的调用
    //std::function<void(int)> fobj_add = Obj::CountAddNum;  // 编译错误 不能直接对非静态成员函数使用
    std::function<void()> fobj_name = Obj::PrintClassName;
    fobj_name();
    std::function<void(const Obj&, int)> fobj_add = &Obj::CountAddNum;
    fobj_add(fobj, 1);

    // 存储到数据成员访问器的调用
    std::function<int(const Obj&)> fobj_count = &Obj::_count;
    std::cout << "the value of count is: " << fobj_count(fobj) << std::endl;

    // 存储到成员函数及对象的调用
    using std::placeholders::_1;
    std::function<void(int)> fobj_add2 = std::bind( &Obj::CountAddNum, fobj, _1 );
    fobj_add2(2);

    // 存储到函数对象的调用
    std::function<void()> funobj = FunObj();
    funobj();

    return 0;
}
