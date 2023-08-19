#include<stdio.h>

/* 判断大小端模式: 小端——低地址存储低位字节 */

union MyUnion{  // 联合体中的所有成员共享内存
    int x;
    char c;
};

void IsLittleEndian()
{
    int x = 0x04030201;  // 数据都是从低地址开始存储
    char *p = (char*)&x;  // p指向x，char占一个字节，因此p指向的是内存中x的第一个字节
    printf("整型变量x：%#x, 低位地址存储的值(字节)：%#x\n", x, *p);
    if(*p == 0x1){
        printf("小端模式！\n");
    }
    else{
        printf("大端模式！\n");
    }
}

int main()
{
    union MyUnion m = {0x0102};  // 整型成员x占4字节，char型成员c占1字节 和x的第一个字节共享内存
    if(m.c == 0x02){
        printf("小端模式！\n");
    }
    else{
        printf("大端模式！\n");
    }

    IsLittleEndian();

    return 0;
}

