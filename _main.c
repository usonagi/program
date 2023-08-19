#include <stdio.h>
#include "_head.h"

#define NEWLINE printf("\r\n")

extern double ext_var;  // 引用未定义的外部变量
//extern glo_var_c = -1;  // 引用外部变量时赋值--链接时产生重定义错误
//extern glo_var_c;  // 使用extern不指定类型,类型默认为int
extern unsigned glo_var_c;
extern const char* headc;  // 引用const全局变量

// 同一编译单元出现同一标识符的内部链接与外部链接是未定义行为！
/*static int global_var;
int global_var;*/

// 与其他文件同名的非static全局变量--不同编译器实现不同 GCC：
// 1、全为或多个强符号，则链接时报重定义错误；2、全是弱符号，选择类型最大的；3、一个强符号，其余为弱符号，首选类型最大其次选强。
char same_name_var;  // 全为弱符号，链接时选择类型较大的
int same_name_uninit_var;  // 强符号的数据类型小于弱符号，链接时选择弱符号，但编译告警！(值是强符号的，类型是弱符号的)
//double same_name_init_var = 1.0L;  // 链接错误--重定义！
__attribute__((weak)) double same_name_init_var = 1.0L;  // 指定为弱符号，并初始化；不指定类型则默认int

//extern char * array_char;  // array_char是数组却使用指针引用--导致后续使用产生segment
extern char array_char[];  // 正确引用方式


double PI = 3.1415;  // 定义全局变量并初始化

void func_info()
{
    printf("func name is %s, located in %d\n\n", __func__, __LINE__);
}

int main()
{
    // 使用main.c中的全局变量
	printf("main函数中PI: %lf, %p\n", PI, &PI);
	printPI();
    NEWLINE;
    
    func_info();  // 调用本文件中定义的函数

    // 不同文件的同名非static变量
    printf("main函数中同名变量的sizeof,value：same_name_var=%d,%d; same_name_uninit_var=%d,%d\n",
            sizeof(same_name_var), same_name_var, sizeof(same_name_uninit_var), same_name_uninit_var);
    printf("main函数中显示指定为弱符号的同名变量sizeof,value：same_name_init_var=%d, %lf\n", 
            sizeof(same_name_init_var), same_name_init_var);
    NEWLINE;

    // 对于头文件中定义的static变量，不同的文件中不是同一变量
    printf("main函数中的sta_cnt_var_h: %s, %p\n", sta_cnt_var_h, &sta_cnt_var_h);
    print_static_head_h();
    NEWLINE;

    // 调用head.c中定义的函数
	hello();
	helloWorld();
    NEWLINE;

    // 使用head.c中的静态局部变量
    int ret = multiplyTen(5);
    printf("main函数调用.c文件中 返回静态局部变量的函数 的结果：first=%d, second=%d\n", ret, multiplyTen(2));
    NEWLINE;

    // 使用指针引用数组 array_char[i]被看作地址而非字符导致访问错误--段错误
    // 使用指针引用array_char时，指向的内容是array_char中的前4个字节，即指针array_char指向
    // array_char，并将其前4个字符被看作地址--即head的16进制ASCII：64616568(小端)
    //printf("(指针引用)字符数组的地址：%p, 首元素是：%c; 指针指向的内容：%p\n", 
    //        &array_char, array_char, array_char);  // 此时不能进行解引用或[]操作
    printf("(数组引用)字符数组的地址：%p, 首元素是：%c\n", array_char, array_char[0]);
    NEWLINE;

	return 0;
}

