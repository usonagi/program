#include <iostream>

/*** C++ lambda表达式  要求c++20 ***/

/* lambda表达式是纯右值表达式，它的类型是独有的无名非联合非聚合类类型，被称为闭包类型(closure type)。
 *
 * lambda表达式——能够捕获作用域中的变量的无名函数对象(cppreference)。语法：
 * [捕获](形参) lambda说明符 约束(可选) {函数体}
 * 捕获：包含零或多个捕获符的逗号分隔列表。
 * 形参与函数体：与普通函数一样指形参列表与函数体。
 * lambda说明符由说明符、异常说明、属性和尾随返回类型组成。
 * 约束：C++20起向闭包类型的operator()添加约束。
 *
 * 说明符：
 *   mutable：允许body修改copy捕获的对象，并调用其非const成员函数
 *   constexpr：指定为常量表达式
 *
 * 变量捕获：
 * =：按值捕获所有本地变量。
 * &：按引用捕获所有本地变量。
 * local：本地变量标明对其按值捕获。[x]{...}表示按值捕获变量x。
 * &local：按引用捕获本地变量local。
 * this：按引用捕获外围对象(成员函数内捕获当前对象)。
 * *this：按值捕获外围对象(成员函数内捕获当前对象)。
 * 变量名=表达式：标明按值捕获表达式的结果。理解为auto 变量名=表达式。
 * &变量名=表达式：标明按引用捕获表达式的结果。理解为auto& 变量名=表达式。
 *
 * lambda表达式一般不用说明返回值，可以用后置->指定返回类型，返回lambda表达式需要使用auto，[]内放置需要捕获的本地变量。
 * 
 * 泛型lambda表达式：当以auto为形参类型或显式提供模板形参列表(C++20 起)时，该lambda是泛型lambda(C++14 起)。
 * 泛型lambda表达式实质是一个闭包类型的类模板。普通lambda表达式是一个闭包类型类。
 * */

struct X : decltype([](){ std::cout << "Lambda expression is Class type!\n"; })
{ };

int main()
{
    X x;
    x();  // X继承了lambda表达式，因此是个函数对象。

    unsigned int var = 0x1;

    // lambda表达式默认是const的，其捕获的变量默认是private
    //auto lamb = [m = 0](){ m = 1; };  // 错误，不能修改
    auto lamb = [m = 0]() mutable { m = -1; };  // 使用mutable修饰后，lambda是非const的

    // 以引用方式捕获本地变量var
    auto lamb_ = [&var](int n = -1){ var += n; };
    lamb_();
    std::cout << "捕获的本地变量var的值：" << var << std::endl;

    // 只有在lambda表达式的捕获符列表为空时才定义这个用户定义转换函数。
    //void (*funp_)() = [m = 0](){};  // 错误
    void (*funp)() = [](){ std::cout << "lambda expression.\n"; };  // 捕获列表为空，编译器提供转换函数
    std::cout << "函数指针funp的大小：" << sizeof(funp) << std::endl;

    auto lamb1 = []{ std::cout << "lambda!\r\n"; };  // 返回一个lambda表达式
    lamb1();  // 调用lambda表达式

    auto lamb2 = [](int n){ return n*n; }(10);  // 创建并调用lambda表达式
    auto lamb2_ = [](int n) -> int { return n*n; };  // 使用后置类型

    auto lamb3 = [](int x){return [x](int y){return x*y;};};  // 返回lambda表达式的lambda表达式
    std::cout << lamb3(2)(5) << std::endl;

    // 如果关键词constexpr用于lambda声明，那么它是constexpr的。
    auto lamb4 = [](int n, int m) constexpr { return (n-m); };  // 显式声明为constexpr
    constexpr int ret_lamb4 = lamb4(0x10, 0x1);
    constexpr auto ret_lamb4_ = [](auto n) { return n * n; }(-0x5); 

    // 泛型lambda示例
    auto lamb5 = []<typename T>(T x, T y) { return x>y ? x:y; };
    auto less_obj = [](auto&& x, auto&& y){
        return std::forward<decltype(x)>(x) < std::forward<decltype(y)>(y);
    };
    std::cout << "less_obj(x,y) is mean x less than y. result：" << less_obj(0x10, 0x01) << std::endl;

    
    return 0;
}
