#include <stdio.h>
#include <stdlib.h>

/*** C语言程序内存布局 ***/

#define PRINTSTR(S) printf(#S"=%s, %p.\r\n",S,S)
#define PRINTADDR(INFO,VAR) printf("%s is at addr: %p.\r\n",INFO,&VAR)

int /*_bss_*/global_uninit_var;
int /*_rw data_*/global_init_var = 1;
int /*_bss_*/global_zero_init_var = 0;

static int /*_bss_*/static_global_uninit_var;
static int /*_rw data_*/static_global_init_var = 1;
static int /*_bss_*/static_global_zero_init_var = 0;

const int /*_bss_*/global_uninit_const_var;  // 未初始化全局常量，不安全
const int /*_ro data_*/global_init_const_var = 10;
const int /*_ro data_*/global_zero_init_const_var = 0;

static const int /*_bss_*/static_global_uninit_const_var;  // 未初始静态全局常量，不安全
static const char /*_ro data_*/static_global_init_const_var = 'G';
static const char /*_ro data_*/static_global_zero_init_const_var = 0;

// 指定变量所处的段 不能为局部变量指定！自定义段名不能以 . 作为前缀。
__attribute__((section(".data"))) const char* _str = "something";  // 指定_str位于data段
__attribute__((section("xxx"))) const char* _seg = "owner segement";  // 指定_seg位于自定义段xxx

void fun(){};

int main()
{
    // 栈向低地址增长
    //char _S[8] = "before!";
    //char S[8] = "string!";  // S的地址比_S的地址低
    //PRINTSTR(S);
    //PRINTSTR(_S);
    //printf("栈越界访问S：%s\n", S + 0x10);  // S越界后输出_S  为什么字符数组在栈中不连续？
    char _S[] = "before S!";
    char S[] = {'S'};
    printf("变量S和_S的地址：%p, %p\n", &S, &_S);
    printf("栈越界访问S：%s; 单个字符的地址：%p, c%\n\n", S, 's', 's');  // S越界后输出_S

    // 非静态局部变量全都存储在栈中
    char local_uninit_var;
    char local_init_var = 'I';
    char local_zero_init_var = 0;

    static int /*_bss_*/static_local_uninit_var;
    static int /*_rw data_*/static_local_init_var = -1;
    static int /*_bss_*/static_local_zero_init_var = 0;

    // 局部常量也是局部量，由编译器保证const性
    const char local_uninit_const_var;
    const char local_init_const_var = 'c';
    const char local_zero_init_const_var = 0;

    static const char /*_bss_*/static_local_uninit_const_var;  // 未初始化静态局部常量，不安全
    static const char /*_ro data_*/static_local_init_const_var = 'v';
    static const char /*_ro data_*/static_local_zero_init_const_var = 0;

    // 字符指针及字符数组都是局部变量存储在栈中，初始化字符指针的字符串存储在rodata段
    const char* /*_stack_*/local_init_const_char_ptr = /*_ro data_*/"c-string!";
    char* local_init_char_ptr = "string!";  // 字符指针指向字符串字面量
    char local_init_char_arr[] = /*_stack_*/"string.";  // 初始化字符数组的字符串存储在栈中


    // TEST段 -- 代码段
    printf("========== .Text Location: ==========\r\n");
    PRINTADDR("fun", fun);
    PRINTADDR("main", main);
    printf("==========     **********     ==========\r\n\n");
    
    // BSS段 -- 未初始化或初始化为0的 全局变量和静态变量
    printf("========== .Bss Location: ==========\r\n");
    PRINTADDR("global_uninit_var", global_uninit_var);
    PRINTADDR("global_zero_init_var", global_zero_init_var);  
    PRINTADDR("global_static_uninit_var", static_global_uninit_var);
    PRINTADDR("global_static_zero_init_var", static_global_zero_init_var);
    PRINTADDR("global_uninit_const_var", global_uninit_const_var);
    PRINTADDR("global_static_uninit_const_var", static_global_uninit_const_var); 
    PRINTADDR("local_static_uninit_var", static_local_uninit_var);
    PRINTADDR("local_static_zero_init_var", static_local_zero_init_var);
    PRINTADDR("local_static_uninit_const_var", static_local_uninit_const_var);
    printf("==========     **********     ==========\r\n\n");

    // RO-data段 -- 只读 常量
    printf("========== .RO Location: ==========\r\n");
    PRINTADDR("global_init_const_var", global_init_const_var);
    PRINTADDR("global_zero_init_const_var", global_zero_init_const_var);
    PRINTADDR("global_static_init_const_var", static_global_init_const_var);
    PRINTADDR("global_static_zero_init_const_var", static_global_zero_init_const_var);
    PRINTADDR("local_static_init_const_var", static_local_init_const_var);
    PRINTADDR("local_static_zero_init_const_var", static_local_zero_init_const_var);
    PRINTADDR("local_init_const_char*", *local_init_const_char_ptr);
    PRINTADDR("local_init_char*)", *local_init_char_ptr);
    printf("==========     **********     ==========\r\n\n");

    // RW-data段 -- 读写 初始化为非0的全局变量和静态变量
    printf("========== .RW Location: ==========\r\n");
    PRINTADDR("global_init_var", global_init_var);
    PRINTADDR("global_static_init_var", static_global_init_var);
    PRINTADDR("local_static_init_var", static_local_init_var);
    printf("==========     **********     ==========\r\n\n");

    // 栈
    printf("========== .Stack Location: ==========\r\n");
    PRINTADDR("local_uninit_var", local_uninit_var);
    PRINTADDR("local_init_var", local_init_var);
    PRINTADDR("local_zeor_init_var", local_zero_init_var);
    PRINTADDR("local_uninit_const_var", local_uninit_const_var);
    PRINTADDR("local_init_const_var", local_init_const_var);
    PRINTADDR("local_zero_init_const_var", local_zero_init_const_var);
    PRINTADDR("local_init_char_arr", local_init_char_arr);
    printf("==========     **********     ==========\r\n\n");

    // 堆
    printf("========== .Heap Location: ==========\r\n");
    //static char* static_local_ptr = (char*)malloc(sizeof(char) * 5);  // 非常量初始化错误，只能赋值
    static char* /*_bss_*/static_local_ptr;
    static_local_ptr = /*_heap_*/(char*)malloc(sizeof(char) * 5);
    PRINTADDR("local_static_ptr(to heap)", static_local_ptr);
    if(static_local_ptr){
        printf("静态局部指针变量指向动态分配的内存：%p\n", static_local_ptr);
    }
    int* /*_statck_*/local_ptr = /*_heap_*/(int*)malloc(sizeof(int) * 5);
    PRINTADDR("local_ptr(to heap)", local_ptr);
    if(local_ptr){
        printf("局部指针变量指向动态分配的内存：%p\n", local_ptr);
    }
    free(static_local_ptr);
    static_local_ptr = NULL;
    free(local_ptr);
    local_ptr = NULL;
    printf("==========     **********     ==========\r\n\n");

    // 自定义段
    //__attribute__((section(".rodata"))) int _roint = 0x0;  // 编译错误
    printf("自定义段中变量的地址：%p\r\n", _seg);

    return 0;
}
