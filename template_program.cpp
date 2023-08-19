#include <iostream>
#include <array>

#include <climits>  // CHAR_BIT--定义字节的位数为8位  C数值极限接口
#include <cstdint>
#include <type_traits>  // make_unsigned
#include <utility>  // make_index_sequence 是std::integer_sequence(表示一个编译时的整数序列)的辅助模板

/*** 模板元编程(C++17) ***/


// 编译期阶乘
template<int n>
struct factorial{
    static_assert(n >= 0, "Arg must be non-negtive.");  // 静态断言，检查参数
    static const int value = n * factorial<n-1>::value;
};

template<>
struct factorial<0>{  // 特化0的时候值为1
    static const int value = 1;
};

// 类型推导工具 作用：把类型的值与类型进行映射，使用类型可以表示值。模板编程使用类型比值更方便。
template<class T, T v>
struct interage_constant{
    static const T value = v;  // 使用value可以取到值
    typedef T value_type;
    typedef interage_constant type;
};

template<bool B>
using bool_constant = interage_constant<bool, B>;  // 重命名结构体类型interage_constant<bool B>为bool_constant
typedef bool_constant<true>  true_type;  // 字面量true对应的类型为true_type,值是true
typedef bool_constant<false>  false_type;  // 字面量false对应的类型为false_type,值是false


// 使用示例：判断类型T是否是引用
template<class T>
struct is_reference : public false_type{};  // 对于类型T，默认不是引用类型
template<class T>
struct is_reference<T&> : public true_type{};  // 对于T&，认为是左值引用
template<class T>
struct is_reference<T&&> : public true_type{};  // 对于T&&，认为是右值引用

template<class T>  // inline变量需要C++17 去除inline修饰也可使用
inline constexpr bool is_reference_v = is_reference<T>::value;  // 为了方便使用

// 示例：去除const修饰
template<class T>
struct remove_const {
    typedef T type;
};  // 对于T类型保持不变

template<class T>
struct remove_const<const T> {  // 模板偏特化
    typedef T type;
};  // 对于const T类型可以去除const修饰

template<class _Tp>
using remove_const_t = typename remove_const<_Tp>::type;  // 为了方便使用


// 编译期条件语句  标准中的实现std::conditional
template <bool cond, typename Then, typename Else>
struct If;

template <typename Then, typename Else>
struct If<true, Then, Else>{
    typedef Then type;
};

template <typename Then, typename Else>
struct If<false, Then, Else>{
    typedef Else type;
};


// 模板形参包是接受零个或更多个模板实参（非类型、类型或模板）的模板形参。
// 函数形参包是接受零个或更多个函数实参的函数形参  sizeof...(形参包)--获取形参包中的元素数量
template<typename... Ts>
constexpr auto make_array(Ts&&... args)
{
    using CT = std::common_type_t<Ts...>;
    return std::array<CT, sizeof...(Ts)>{std::forward<CT>(args)...};
}

template<int...args>  // 有名字的非类型模板形参包
void print_args()
{
    ((std::cout << args << " "), ...);
    endl(std::cout);
}

template<typename... Ts>  // 有名字的类型模板形参包
void print_types(Ts...args)  // 函数参数包 形式：【包名... 包形参名(可选)】
{
    ((std::cout << args << " "), ...);  // 折叠表达式 一元右折叠
    endl(std::cout);
}

/*template<std::integral... Ts>  // 有名字的受约束(C++23)的类型模板形参包
void print_types_integral(Ts... args)
{
    ((std::cout << args << " "), ...);
    endl(std::cout);
}*/

template<template<class T, class U>class... Ts>  // 有名字的模板模板形参包
struct TMS{};

template<class... Ts>
void func(Ts... ts)
{
    ((std::cout << ts << " "), ...);
}
template<class... Us>
void gfunc(Us... args)
{
    //func(args...);  // 形参包展开 形式：【模式 ...】
    //func(&args...);  // 展开为&T1,&T2,...  Ts展开为T1*,T2*,...
    func(-1, ++args..., 0);  // 展开为 -1,++T1,++T2,...,0
    std::cout << std::endl;
}

void print_args()  // 模板出口
{
    endl(std::cout << __func__ << " Instantiated Completed!");
}
template<typename T, typename... Args>
void print_args(T arg0, Args... args)
{
    std::cout << "Call " << __func__ << " Arg: " << arg0 << "; ";
    print_args(args...);  // 使用其余形参继续实例化函数模板
}

// 折叠表达式--C++17  括号也是折叠表达式的一部分，且必须符合折叠表达式的形式，否则实例化失败。
/*
* 一元右折叠：【形参包 运算符 ...】  展开为【E1 运算符(E2 运算符(...运算符(EN)))】  类似:cout << ...
* 一元左折叠：【... 运算符 形参包】  展开为【(((E1 运算符 E2) 运算符 ...) 运算符 EN)】 类似:(a+b)+c...
* 二元右折叠：【形参包 运算符 ... 运算符 初值】  展开为【E1 运算符(E1 运算符(...(EN 运算符 I)))】  类似:a+=b+=c+=...x
* 二元左折叠：【初值 运算符 ... 运算符 形参包】  展开为【((((E1 运算符 I) 运算符 E2) 运算符 ...) 运算符 EN)】 类似:x && a && b && ...
*/
template<typename... Args>
void print_right_fold(Args... args)
{
    ((std::cout << args << "-->"), ...);
    endl(std::cout);
}

template<int... Args>
constexpr int sub_rfold = (Args - ...);  // E1-(E2-(...-(En)))

template<int... Args>
constexpr int sub_lfold = (... - Args);  // (((E1-E2)-...)-En)

template<int... Args>
constexpr int res_rfoldr = (Args - ... - 0x1);

template<int... Args>
constexpr int res_lfoldl = (0x1 - ... - Args);

template<class... Args>
void unfold_args(Args... args)
{
    //auto res = ('$' + args + ...);  // 错误，折叠表达式展开失败
    //auto res = ('$' + ... + args);  // 二元左折叠
    //auto res = ('$' + ... + args + '#');  // 错误
    //auto res = ('$' + (args...));  // 错误
    //auto res = ('$' + (args, ...));  // 【'$' + 一元右折叠】 逗号是运算符
    //auto res = ('$' + (args + ... + '#'));  // 【'$' + 二元右折叠】
    auto res = ('$' + (... + args) + '#');  // 【'$' + 一元左折叠 + '#'】
    endl(std::cout << "折叠表达式的结果：" << res);

    auto res_ = { '$' + args + '#' ... };  // 以模式【('$'+arg+'#')】展开形参包 即【('$'+arg0+'#'),('$'+arg1+'#'),...】
    std::cout << "形参包展开结果：";
    for(auto x : res_){
        std::cout << x << ' ';
    }
    std::cout << std::endl;
}

// C++reference折叠表达式示例：编译器端序交换  http://stackoverflow.com/a/36937049
template<class T, std::size_t... N>
constexpr T bswap_impl(T i, std::index_sequence<N...>)
{
    return (((i >> N*CHAR_BIT & std::uint8_t(-1)) << (sizeof(T)-1-N)*CHAR_BIT) | ...);
}
template<class T, class U = std::make_unsigned_t<T>>
constexpr U bswap(T i)
{
    return bswap_impl<U>(i, std::make_index_sequence<sizeof(T)>{});
}

int main()
{
    printf("编译期阶乘的结果(5)：%ld\n", factorial<5>::value);

    // 判断是否是引用类型
    std::cout << std::boolalpha << "int&& is refenrence type:" << is_reference_v<int&&> << std::endl;
    using Type = char&;
    std::cout << std::boolalpha << "char& is refenrence type:" << is_reference_v<Type> << std::endl;

    // 去除const修饰  标准中有const_cast<>()
    const char c = 'C';
    remove_const_t<decltype(c)> ch;
    std::cout << "(const char) removed const: " << typeid(ch).name() << std::endl;
    const char* cp = "hello";  // const char* 输出 PKc; char* 输出 Pc。
    std::cout << "(const char*) removed const: " << typeid(remove_const_t<decltype(cp)>).name() << std::endl;
    endl(std::cout);

    // 形参包
    std::array<double, 4ul> Arr = make_array(1, 3.14f, 2.5, 'C');
    std::cout << "Arr: ";
    /*for(auto s(Arr.size()); auto elem : Arr){  // C++23以前告警
        std::cout << elem << (--s ? ", " : "\n");
    }*/
    for(auto elem : Arr){
        std::cout << elem << " ";
    }
    endl(std::cout);

    print_args<1,2,3,4,5>();
    //print_args<1,2.0f,'A'>();  // 错误，形参包类型是int
    
    print_types(1, 3.14f, 2.0L, "C++", 'C');  // 接受任意个任意类型参数
    //print_types_integral(1, 2, 3, '*');  // 带有约束的形参包，只能接受同一类型的任意数量的参数

    //gfunc(1, 0.5f, 'A', "C");
    gfunc(1, 2, 3);

    print_args(1, 3.14f, 2.0L, "C++", 'C');  // 可变参数模板
    endl(std::cout);


    // 折叠表达式
    print_right_fold('S', "Hello", "World", 'E');
    std::cout << "((1-2)-3)-4=" << sub_rfold<1,2,3,4> << std::endl;
    std::cout << "1-(2-(3-4))=" << sub_lfold<1,2,3,4> << std::endl;
    std::cout << "1-(3-(5-1))=" << res_rfoldr<1,3,5> << std::endl;
    std::cout << "((1-1)-3)-5=" << res_lfoldl<1,3,5> << std::endl;

    unfold_args('A','B','C');

    // 端序交换 uint16_t是cstdint中的定宽整数类型，表示16位的无符号整数
    static_assert(bswap<std::uint16_t>(0x1234u) == 0x3412u);  // 注意2个数字占一个字节
    static_assert(bswap<std::uint64_t>(0x0123456789abcdefull) == 0xefcdab8967452301ULL);

    return 0;
}
