#include <stdio.h>

/****** 宏定义与预处理器 *****/

/* 预处理器的作用：常量替换、类型定义、简化函数实现、头文件包含及防重复、预定义宏方便调试、
 *     注释代码、跨平台、特性功能开关配置、编译器编译选项配置
 * 控制行预处理指示符：#define #undef #include #line(用作行号控制，会影响预定义宏__LINE__的值) 
 *     #error(与条件编译一起使用，当缺少依赖时直接报错退出编译流程) #warning(用于警告不中断编译)
 * 条件段预处理符：#if #if defined() #elif #else #endif
 * 空指示符与注释：#，//，\/**\/
 * TIP: 条件编译使用条件组合时不能直接使用#ifdef,使用#if !defined().
 *
 * 编译器、编译平台、处理器架构都有对应的宏
 * 编译器：__GNUC__, __clang__, _MSC_VER
 * 编译平台：__linux__, _WIN32, _WIN64, __unix__
 * 处理器架构: __x86_64, __arm__, __LP64__
 * TIP：使用gcc -dM -E xxx.c 即可查看GCC所有预定义宏
 *
 *
 * C语言宏定义分为：C语言标准要求预定义的宏；环境宏——编译器可选支持；条件特征宏——编译器可选支持；编译器及平台预定义的宏。
 * 宏定义的基本用法：常量替换、类型定义、函数宏、头文件包含、可变参数宏定义(常用于重定义输出)
 * #操作符用于将宏参数转换为字符串字面量；##操作符用于把内容拼接在一起；msvc中有#@操作符表示一个字符。
 * TIP: 宏展开是同时进行的！且字符串中的宏不会被替换以及操作符也不会生效！ 
 *
 * */

#define TRUE 1
#define FALSE 0
#define STRING const char*
#define MAX(x,y) (((x)>(y))?(x):(y))
#define ADD(x,y) ({(x)+(y);})  // ({...; ...;})返回最后一个表达式的值

// 可变参数宏定义 常用于输出重定向; __VA_ARGS__可以接收多个参数，但输出取决于输出格式
#define PRINTSTR(content) printf(#content" = %s\r\n", content)
#define DEBUG(format, ...) printf(format "[func:%s line:%d].\r\n", ##__VA_ARGS__, __func__, __LINE__)
#define DEBUG_(content) printf("Info: %s [func:%s line:%d].\r\n", content, __func__, __LINE__)
#define debug0(...) printf(__VA_ARGS__)
#define debug1(x...) printf(x)
#define debug2(x, ...) printf(x,__VA_ARGS__)
#define debug3(x, ...) printf(x,##__VA_ARGS__ )

#define offset(Type, member) ((unsigned)&((Type*)0)->member)
#define ENDFLAG "***************"
#define ENDINFO(info) printf("%s %s %s\n", ENDFLAG, #info, ENDFLAG)

// #与##操作符使用演示
#define f(A,B) A##B    // 把参数A和B拼接起来
#define g(A) #A        // 把参数A转化为字符串
#define h(A) g(A)

// 宏的常见误区
#define ceil_div(x,y) ((x+y-1)/y)  // 运算优先级问题
#define Max(x,y) ((x)>(y)?(x):(y))  // 多次运算问题——若宏参数是函数调用，则会调用两次
//#define power_ (x) (x+1)  // 空格问题
#define power_(x) ((x)*(x))  // 同一个宏定义不一致！
// 宏内部使用的变量名太普通 可能导致修改变量值
#define get_sum(N)\
{\
    i = 0;\
    while(N > 0){\
        i += N;\
        N--;\
    }\
    N = i;\
}\
// 宏内部使用return 因为宏替换只是文本替换，所以return是退出宏所在的函数
#define FUNC(x) {\
    return (x)*(x);\
}\

int fun(int x)
{ 
    printf("call function: %s\n", __func__);
    return x * 2; 
}

int main()
{
#if !defined(__GNUC__) && (__linux__)  // 不满足条件则编译错误 
//#ifdef !(__GNUC__ && __linux__)  // 写法错误！编译失败
//#ifdef __linux__ && !__GNUC__  // 编译通过 但结果不符合预期！
#error Linux system and GNUC complier required.
#elif defined(__LP64__)  // 若数据模型是LP64(long与指针是8字节) 则产生警告
//#warning Data modle is LP64
#endif

    //printf("带有返回值的宏：0x0abc + 0x1111 = %#x\n", ADD(0x0abc, 0x1111));

    // 常用宏定义
    PRINTSTR(__DATE__);  // 输出源文件的编译日期
    PRINTSTR(__FILE__);  // 输出源文件名
    PRINTSTR(__FUNCTION__); // 输出所在函数
    PRINTSTR(__func__);  // 同上输出函数名
    debug0("__LINE__ = %d\r\n", __LINE__);  // 输出所在行号
    PRINTSTR(__TIME__);  // 输出源文件的编译时间
    printf("__STDC_VERSION__ = %d\r\n", __STDC_VERSION__);  // 输出编译器使用的C语言标准
    ENDINFO(This is end!);

    // 演示可变参数宏定义
    const char* info = "Here is an example.";
    debug0("%s\n", info);
    debug1("%s %s \r\n", "Linux", "C Program!");
    debug2("%s %s\r\n", "Hello!", info);
    //debug2(info);  // 编译失败 原因：多出个逗号
    debug3("Hello beautiful world!\r\n");
    //debug3("First", "Second");  // 展开后 printf("First","Second");输出只有"First"
    debug3("%s %s %u\n", "First", "Second", 0x2);

    //DEBUG();  // 输出函数名与行号
    //DEBUG(info);  // 错误 编译失败
    DEBUG("content: %s %s; value=%d ", info, "other string!", 0x1);  // 输出最后有函数名与行号
    ENDINFO(This is end!);

    // 演示 #和## 操作符
    PRINTSTR(h(f(1,0)));  // h与f是同时展开的 得到g(1,0)->输出10
    PRINTSTR(g(f(1,0)));  // g展开得到字符串"f(1,0)" 因此f(1,0)不会再被替换！
    ENDINFO(This is end!);

    // 宏常见问题演示
    int i = 0x3;
    int j = 0x7;
    printf("ceil_div(2,3)=%d\r\n", ceil_div(2,3));
    printf("ceil_div(i,i&j)=%d\r\n", ceil_div(i&j, i));  // ((i&j)+i-1)/i=1 实际是(i&j+i-1)/i=0
    printf("Max(j,fun(j))=%d\r\n", Max(j, fun(j)));  // fun会调用两次，若函数fun()是不可重入的函数(函数内修改全局或静态变量)则会产生逻辑错误
    get_sum(j);  // 影响i的值
    printf("after get_sum(j) j = %d; i = %d\r\n", j, i);
    //FUNC(i);  // 带有return语句的宏 从main函数返回
    ENDINFO(This is end!);


    return 0;
}
