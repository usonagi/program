#include <iostream>
#include <algorithm>
#include <map>

/*===== C++ 模板基础 =====*/
/* 函数/类模板自身并不是类型、函数或任何其他实体.模板只有实例化才会出现代码。
 * 函数模板不能部分特化，只能完全特化；类模板可以部分特化或完全特化。
 *
 * 显式实例化定义强制实例化它所指代的函数或成员函数。它可以出现在程序中模板定义后的任何位置。
 * template 返回类型 函数名(形参列表);    template 返回类型 函数名<实参列表>(形参列表);
 *
 * 显式实例化声明（extern 模板）阻止隐式实例化。即本文件中不要实例化对应类型的模板。
 * extern template 返回类型 函数名(形参列表);    extern template 返回类型 函数名<实参列表>(形参列表);
 *
 * 解决模板实例化失败的方案：
 * ·直接添加支持 所需要操作 的代码(全局重载)——对成员函数无效
 * ·增加间接层，针对 所需要操作和对象 进行重载
 * ·增加间接层，针对所需要操作和对象进行特化
 *
 * !!!不要混合重载与特化，编译器在重载决议时(选择版本)不考虑特化版(只考虑普通函数和函数模板)，
 * 仅当编译器选择函数模板后在实例化的时候才考虑特化版本。关于函数模板特化可以参考：http://www.gotw.ca/publications/mill17.htm
 * 特化严格要求类型匹配，需要避免隐式类型转换时使用特化；需要隐式类型转换时使用重载。
 *
 * 二阶段名字查找：
 * ·对于不依赖模板参数的名字，编译时会在模板实例化之前查找。
 * ·对于依赖模板参数的名字，编译时会在模板实例化时查找。
 *
 * 变量模板(C++14起)格式：template < 形参列表 > 变量声明       
 * 变量模板可以通过处于命名空间作用域中的模板声明引入.
 * C++14之前的语法规则不允许使用模板声明的方式声明一个变量: template<typename T> T var;  var<char> = 'C';
 *
 * */


// 模板特化示例：早期静态断言——当表达式_Expr为false时，编译出现类型未定义错误
template<bool> struct compile_time_error;
template<> struct compile_time_error<true>{};  // 只特化bool模板的true，
#define STATIC_ASSERT(_Expr,_Msg)\
{\
    compile_time_error<((_Expr) != 0)> ERROR_##_MSG;\
    (void)ERROR_##_MSG;\
}

// 模板特化示例：通过错误报告对象类型
#define DISPLAY_TYPE(var) \
    type_display<decltype(var)> type_display_t
template<typename T>
class type_display;  // 模板类只有声明没有定义 上述宏在编译时报错


// 定义取模的模板函数
template<typename T>
T mod(T x, T y)
{
    return std::min(x, y);
}

// 取模函数的一种特化——用于实现float型的取模
template<>
float mod(float x, float y)
{
    auto ret = static_cast<int>(x) % static_cast<int>(y);
    return static_cast<float>(ret);
}

// 实现int型的取模函数的重载
int mod(int x, int y)
{
    return x % y;
}

template<typename T>
T GCD(T, T);  // greatest common divisor——最大公约数

template int GCD(int, int);  // 实例化函数模板

template<class T, int N>
void DisplayFun(T const(&)[N]);


// 二阶段名字查找示例
template<typename T>
struct Base{
    void fun() { std::cout << "Base function." << std::endl; }
};

template<typename T>
struct Derived : Base<T>{
    void func() 
    { 
        // 编译错误 不依赖模板参数，但在实例化前不能确认Base的实例化中是否存在此函数fun（即fun不被看做成员函数）
        //fun();
        
        // 使用this指针或显示调用(Base<T>::fun()) 依赖模板参数，延迟fun的查找时机到实例化的时候
        this->fun();
        //Base<T>::fun();

        std::cout << "Derive function." << std::endl;
    }
};

template<>
struct Base<int>{  // Base的int型特化
    void fun() { std::cout << "Base<int> function." << std::endl; }
};


// 变量模板
template<typename T>
constexpr T PI = T(3.14159265358979L);

template<typename T>
T GetCircleArea(T r)
{ return PI<T> * r * r; }  // 使用变量模板的实例化

template<class T>
constexpr T tv{};

template<class T>
constexpr int tv<const T> = -1;  // 部分特化--const特化

template<class T>
constexpr T* tv<T*> = static_cast<T*>("C++");  // 部分特化--指针特化

// 变量模板部分特化示例-- is_same_v()
template<typename T, typename U>
struct is_same {
  constexpr static bool value = false;
};

template<typename T>
struct is_same<T, T> {
  constexpr static bool value  = true;
};

template<typename T, typename U>
bool is_same_v = is_same<T, U>::value;


// 别名模板
template<typename T, typename U>
using MAP = std::map<T, U>;


// 演示类模板的部分特化与全特化
template<class T>
class TClass{ /*...*/ };  // 类模板

template<class T>
class TClass<T*>{ /*...*/ };  // 类模板部分特化

template<>
class TClass<char*>{ /*...*/ };  // 类模板全特化


// 编译器重载决议示例
#define printfunc(ID) printf("call %s; Info:%s\r\n",__func__,ID)
template<class T>
void template_fun(T x)
{ printfunc("Base-A"); }  // 函数模板a

template<>
void template_fun<>(int* p)
{ printfunc("Specialized-A(ptr)"); }  // 函数模板a的int*特化(p1)

template<>
void template_fun<int>(int x)
{ printfunc("Specialized-A"); }  // 函数模板a的int类型特化

template<class T>
void template_fun(int x, T y)
{ printfunc("Base-B"); }  // 函数模板b

template<class T>
void template_fun(T* p)
{ printfunc("Base-C"); }  // 函数模板c——而非模板a的特化(p2)

template<>
void template_fun<>(int* p)
{ printfunc("Specialized-C"); }  // 函数模板c的int型特化(p3)

void template_fun(double d)
{ printfunc("Normal-A"); }  // 普通函数a


int main()
{
    {
        // 自定义断言使用
        STATIC_ASSERT(sizeof(void*)==8, "The current system is not 64-bit system.");

        // 通过错误信息显示类型
        decltype(1+1) A = 0;
        //DISPLAY_TYPE(A);  // 显示错误信息：type_display<int> type_display_t’类型不完全，无法被定义

        std::map<int, char> mp;
        //DISPLAY_TYPE(mp.begin());
        std::cout << std::endl;
    }
    {
        std::cout << "GCD函数求得最大公约数：" << GCD(161, 63) << std::endl;

        //template double GCD<double>(double, double);  // c++11编译错误
        // 实例化并调用 
        float ft = 2.0f;
        std::cout << "浮点数使用GCD函数：" << GCD<float>(ft, 12.00) << std::endl;  // 若不指定实例化为float类型(GCD(ft,12.00))编译失败
        std::cout << std::endl;

    }
    {
        Derived<char>{}.func();
        Derived<int>{}.func();
        std::cout << std::endl;
    }
    {
        // 变量模板
        int circle_area_i = GetCircleArea(4);
        double circle_area_d = GetCircleArea(4.0);
        std::cout << "Circle Area : int(" << circle_area_i << "), double(" << circle_area_d << ")\n";
        printf("实例化变量：tv<double>=%lf, tv<const int>=%d, tv<const char*>=%s\n", tv<double>, tv<const int>, tv<const char*>);

        endl(std::cout << std::boolalpha << "is_same<int, int>: " << is_same_v<int, int>);
        endl(std::cout << std::boolalpha << "is_same<int, unsigned int>: " << is_same_v<int, unsigned int>);
        endl(std::cout << std::boolalpha << "is_same<char *, char[]>: " << is_same_v<char*, char[]>);

        //auto area = GetCircleArea(2);
        MAP<char, float> mp = {{'S', 1.0}};
        std::cout << "别名模板声明的变量的值：" << mp['S'] << std::endl << std::endl;
    }
    {
        // 慎用函数模板特化
        char ch = 'T';
        const char* cptr = "string";
        int x = 0x1;
        int* p = nullptr;
        double dl = 1.0L;
        template_fun(ch);  // Base A
        template_fun(x);  // Spec A
        template_fun(x, ch);  // Base B
        template_fun(&ch);  // Base C -- 参数为char*，只有模板函数C符合
        template_fun(dl);  // Normal -A
        template_fun(cptr); // Base C
        // 参数为int*时，此时满足形参列表的函数有： A的int*特化p1、模板C p2、模板C的特化p3
        // 编译器选择函数p2，然后发现p2有int的特化，所以使用特化即p3；若不存在对应的特化，则实例化p2。
        // 当不存在p2及p3时，编译器选择模板A，然后发现有int*的特化并使用。
        template_fun(p); // Spec C(exist) / Base C, 不会调用A的int*特化
    }

    return 0;
}

template<typename T>
T GCD(T a, T b)  // 模板函数的定义（通常模板的声明与定义(在同一翻译单元)放在头文件中）
{
    while((int)b != 0){
        //T c = a % b;  // 对于有些类型不支持%运算
        T c = mod(a, b);  // 增加间接层 使用取模的模板函数
        a = b;
        b = c;
    }
    return a;
}
