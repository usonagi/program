#include<iostream>
#include<chrono>  // 时间库
//#include<format>  // 格式化库

/* C++ 基础学习 */

using std::cout;
using std::endl;

#define ENDLINE std::cout<<"***************   ***************\r\n\n"

// GCC中C++名称修饰规则(程序员的自我修养-P109)：_ZN命名空间
namespace MyS{
    int var = 999;
}
#ifdef __cplusplus
extern "C"{  // 使用extern "C"表示告诉C++编译器将其内容当作C代码处理
#endif
//long int _ZN3MyS3varE;  // 编译错误，此变量与命名空间MyS中的var同名
;
#ifdef __cplusplus
}
#endif

extern "C" long int _ZN3MyS3varE;  // 对单个变量进行extern "C"

/* 不求值表达式：sizeof(),typeid应用到多态类类型的泛左值以外的表达式,noexcept(),decltype()*/

// noexcept 运算符进行编译时检查，如果表达式不会抛出任何异常则返回true。noexcept(表达式)不会对表达式求值！
// noexcept说明符--指定函数是否抛出异常 noexcept与noexcept(true)含义相同，声明函数不抛出异常。
// noexcept说明不是函数类型的一部分,它不能在 typedef 或类型别名声明中出现。
void test_func_1() noexcept  // 与void test_func_1()的函数签名一样
{
    cout << "call " << __FUNCTION__ << endl;
}

void test_func_2() noexcept(0)
{
    cout << "call " << __FUNCTION__ << endl;
}

// 1、使用noexcept运算符求test_func_1() 2、将结果传递给noexcept说明符
void noexcept_func() noexcept(noexcept(test_func_1()))
{
    cout << "call " << __FUNCTION__ << endl;
}

// 用户自定义字面量:通过定义用户定义的后缀，允许整数、浮点数、字符及字符串字面量产生用户定义类型的对象
// 由字面量运算符或字面量运算符模板声明引入,所有程序引入的 用户定义后缀 必须以下划线字符 _ 开始。标准库的 用户定义后缀 不以下划线开始。
// 字面量运算符 "" 对形参有规定，部分类型不能作为形参 如：int,float...
std::string operator ""_str(const char* s, size_t size)
{
    return std::string(s, size);
}


/*struct A {  // 字符串字面量运算符模板 
    constexpr A(const char* s) noexcept : _str(s);
    const char _str;
};
template<A a>
constexpr auto operator ""_f(){
    // 变参模板--C++20
    return [=]<typename... T>(T... Args) {
        return std::format(a._str, Args...);
    };
}*/

// 成员指针声明格式：Type Class::* P; 使用成员指针的格式：实例对象.*P，实例对象指针->*P
struct MS {
    int m;
    double db;
    int getm(){ return m; }
    int getm(int m){ return this->m + m; }

    double getdb() const{ return db; }  // 相当于传递const this指针给成员函数，return (const this)->db;
    /*int func()  // CV限定符会影响函数签名，而引用不影响；故此函数与带引用限定符的同名函数不构成重载。
    {
        cout << "call func\n";
        return m;
    }*/
    int func()& 
    { 
        cout << "call func &\n";
        return m; 
    }
    int func()&&
    {
        cout << "call func &&\n";
        return m;
    }
    int func() const&
    {
        cout << "call func const&\n";
        return m;
    }
};

template<typename dst_type, typename src_type>
dst_type union_cast(src_type src)
{   union{
        src_type s;
        dst_type d;
    }u;
    u.s = src;
    return u.d;
}

// main函数之前调用函数：全局初始化、链接选项、attribute、编译选项(#program section)
int before_main_execute()
{
    cout << "call " << __func__ << endl;
    return -1;
}
int gloabl_x = before_main_execute();
int global_lamb = [](){ cout << "lambda expression!\n"; return 0; }();
__attribute__((constructor)) void before_main()
{
    //cout << "call " << __func__ << endl;  // 运行错误--段错误
    printf("call %s\n", __func__);
}

struct MS1{
    mutable int n : 2;
    volatile double m;

    MS1() : n(0), m(0){}
    MS1(int x, double y) : n(x), m(y) {}
    void f();

    // C++20 三路比较运算符(gcc-9.2不支持)
    //auto operator<=>(const MS1&) const = default;
};

int main()
{
    static int static_lamb = [](){ cout << "lambda expression!\n"; return 0; }();
    cout << endl;
    cout << "extern \"C\" 声明的变量的值：" << _ZN3MyS3varE << '\t' << typeid(_ZN3MyS3varE).name() 
         << '\t' << typeid(MyS::var).name() << endl;
    cout << "当前函数的名称是：" << __FUNCTION__ << endl;  // 或__func__  
    cout << "本文件的名字是：" << __FILE__ << endl;
    cout << "本行代码的行号是：" << __LINE__ << endl;
    cout << endl;

    {
        // =====noexcept限定=====
        int var = 0x1;
        cout << std::boolalpha << "noexcept运算前后var的值：" << noexcept(++var) << " " << var << endl;
        cout << std::boolalpha << noexcept(test_func_1()) << endl;
        cout << std::boolalpha << noexcept(test_func_2()) << endl;
        cout << std::boolalpha << noexcept(noexcept_func) << endl;
        void (*fp)() = test_func_1;
        void func();  // 可能抛异常的函数
        //void (*ntfp)() noexcept = func;  // 错误，指向不抛异常的函数的指针不能指向可能抛出的函数
        ENDLINE;
    }
    {
        // =====自定义字面量=====
        using namespace std::literals::chrono_literals;  // 标准库
        auto hour = std::chrono::duration_cast<std::chrono::seconds>(1h);  // 转化为秒
        auto sec = 5s;
        auto msec = 10ms;
        printf("时间字面量输出：hour=%ld, sec=%ld, msec=%lf\n", hour, sec, msec);

        std::string str = "string"_str;
        cout << "自定义字面量："  << "12345"_str << "\t" << str << endl;

        //cout << "{},{}"_f("first", 0x1) << endl;
        ENDLINE;
    }
    {
        // =====成员指针=====
        MS mst{1};
        int MS::* mp = &MS::m;  // 数据成员指针,存储的是偏移量 指向MS的成员m
        double MS::* dbp = &MS::db;
        cout << *reinterpret_cast<int*>(&mp) << "\t" << *reinterpret_cast<int*>(&dbp) << endl;  // 输出数据成员的偏移量

        //int (*fp)() = &MS::getm;  // 错误，普通函数指针不能指向成员函数
        int (MS::* funp)() = &MS::getm;  // 成员函数指针 指向MS的成员函数
        int (MS::* funp_)(int) = &MS::getm;

        mst.*mp += 1;  // 通过成员指针修改数据成员 mst.mp错误
        printf("MS的数据成员m：addr=%p, %p; value=%d, %d\n", &mst.m, &(mst.*mp), mst.m, mst.*mp);
        cout << mst.getm() << '\t' << (mst.*funp)() << '\t' << (mst.*funp_)(10) << endl;  // 通过成员函数指针调用成员函数

        // 成员函数指针无法使用static_cast, dynamic_cast进行转换
        //cout << reinterpret_cast<const void *>(funp) << endl;  // 强制转换产生警告
        //cout << dynamic_cast<const void *>(funp) << endl;  // 动态转换错误
        // 对于重载函数需要指定类型，否则出现unresolved overloaded function type错误--实例化时无法匹配重载函数
        // 关于重载函数的函数名解析参考cppreference中overloaded_address
        cout << "MS的成员函数地址：" << union_cast<void*>(funp) << '\t' << union_cast<void*>(funp_) << endl;  // 使用成员函数指针实例化模板
        cout << "MS的成员函数地址：" << union_cast<void*, int(MS::*)()>(&MS::getm) << '\t' 
             << union_cast<void*, int(MS::*)(int)>(&MS::getm) << endl << endl;


        // =====成员函数=====
        mst = MS{0};
        const MS cmst{-1};
        MS& mst_ref = mst;
        cmst.getdb();
        //cmst.getm();  // 编译错误，cosnt对象不能调用非const成员函数
        mst.getdb();

        mst.func();  // 调用func() 或 func()&
        MS{0}.func();  // 调用func() 或 func()&& 或 func() const&
        cmst.func();  // 调用func() const&
        mst_ref.func();  // 左值引用同左值
        ENDLINE;
    }
    {
        // =====C++17 结构化绑定=====
        int A[] = {1,2};
        cout << "数组A的元素的地址：" << static_cast<void*>(A) << ", " << static_cast<void*>(A+1) << endl;
        auto [x,y] = A;
        cout << "绑定的对象的地址：" << static_cast<void*>(&x) << ", " << static_cast<void*>(&y) << endl;  // 创建新的局部变量x,y
        auto& [xr,yr] = A;
        cout << "绑定的引用的地址：" << static_cast<void*>(&xr) << ", " << static_cast<void*>(&yr) << endl;  // 创建引用，指向A的元素

        const auto [sx,sy] = MS1{1, 2.0};  // 各个标识符，按声明顺序依次成为指代各个成员的左值的名字
        cout << "绑定数据成员：" << sx << "," << sy << endl;
        sx = -1;  // sx是2位位域的 int 类型
        //sy = 3.0;  // sy是const volatile double类型
        cout << "绑定数据成员：" << sx << "," << sy << endl;

        MS1 ms{};
        auto& msr = ms;
        //auto& msnr = ms.n;  // 无法声明位域的引用
        auto& [msnr,msmr] = ms;  // 将引用类型msnr绑定到结构体的位域成员

        //cout << std::boolalpha << (MS1{} == MS1{}) << endl;
        //cout << std::boolalpha << (MS1{1,0} >= MS1{0,1.0}) << endl;
        //cout << std::boolalpha << (MS1{1,1.0} <= MS1{1,0.0}) << endl;
    }

    return 0;
}

