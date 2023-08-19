#include<stdio.h>
#include<stdlib.h>
#include<stdarg.h>
#include<string.h>

/* 如果函数形参个数不确定，称为可变形参(前面至少有一个固定参数) 例：int fun(int a, ...);
 * C通过三个宏定义va_arg, va_start, va_end 以及一个类型 va_list 实现可变参数。
 * typedef char * va_list
 * #define va_start(ap,v)  将参数v之后的参数的地址存放到ap中
 * #define va_arg(ap,t)  从ap获取一个参数 参数类型为t 并且ap后移
 * #define va_end(ap)  关闭初始化列表 即置空ap
 * 1、声明va_list变量    2、使用va_start初始化va_list变量 
 * 3、使用va_arg获取参数 4、使用va_end关闭va_list变量
 * 使用va_arg获取参数时，参数类型必须匹配，否则会导致后面读取的参数错误！并且通常
 * 需要一个参数用于指定参数个数，防止发生内存越界。
 *
 * 注意：可变形参不是形参包展开！！！
 * */

#define MAXSIZE 1000

// 示例 拼接n个字符串
char* PrintString(const int n, ...)
{
    char* ret = (char*)malloc(sizeof(char) * MAXSIZE);
    if(ret == NULL){
        return NULL;
    }
    memset(ret, 0, MAXSIZE);

    va_list vlist;
    va_start(vlist, n);
    for(int i = 0; i < n; ++i){
        strcat(ret, va_arg(vlist, char*));
    }
    va_end(vlist);
    return ret;
}

// 示例 使用vsprintf()格式化输出
char* GetFormatString(char* format, ...)
{
    char* ret = (char*)malloc(sizeof(char) * MAXSIZE);
    if(ret == NULL){
        return NULL;
    }
    memset(ret, 0, MAXSIZE);
    va_list vl;
    va_start(vl, format);
    vsprintf(ret, format, vl);
    va_end(vl);
    return ret;
}

// 示例 获取n个整数之和
int SumInteger(const int n, ...)
{
    long long sum = 0;
    va_list vl;
    va_start(vl, n);
    for(int i = 0; i < n; i++){
        int tmp = va_arg(vl, int);
        if(tmp <= 0){  // 可用于结束参数读取
            break;
        }
        sum += tmp;
    }
    va_end(vl);
    return sum;
}

int main()
{
    char* ret;
    
    ret = PrintString(5, "Hello ", "World!", " variable", " formal", " parameter!");
    printf("Output: %s\n", ret);
    free(ret);
    
    ret = GetFormatString("My name is %s, %d years old!", "Tom", 15);
    printf("Output: %s\n", ret);
    free(ret);
    ret = NULL;

    int res = SumInteger(3, 12345, 6789, -2);
    printf("Output: %d\n", res);

    return 0;
}
