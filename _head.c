/*
 * 用于封装函数的头文件
 */
#include <stdio.h>
#include "_head.h"

extern double PI;  // 引用全局变量PI
unsigned int glo_var_c = 1;  // 定义全局变量
const char* headc = "head.c";

int same_name_var;
char same_name_uninit_var = 'c';
int same_name_init_var = 0x1;
char array_char[] = "head.c";

void hello()
{
	printf("this is the first function!\n");
}

void helloWorld()
{
	printf("Hello World! 这个文件用于实现函数功能!\n");
}

void printPI()
{
	printf("文件%s中的PI：%lf, %p\n", __FILE__, PI, &PI);
}

void print_static_head_h()
{
    printf(".c文件使用头文件中定义的静态变量sta_cnt_var_h: %s, %p\n", sta_cnt_var_h, &sta_cnt_var_h);
}

int multiplyTen(int x)
{
    static int var = 10;
    var *= x;
    return var;
}
