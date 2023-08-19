#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdalign.h>  // alignof()

// C 测试程序

#define ARRSIZE(a) sizeof(a)/sizeof(a[0])
#define ENDLINE "********************"
#define PRINTENDLINE(...) printf(__VA_ARGS__)

#define unix 1
#define PRINTSOMETHING printf(&unix["\021%six\12\0"], unix["have"] + "fun" - 0x60)

// 访问权限是public，结构体内不能定义函数但可以声明函数指针，不可以使用结构体名作为类型！
typedef struct{
    char name[10];
    int num;
    int (*funp)(int);
}__attribute__((aligned(4)))TStruct;  // 设置为4字节对齐

typedef struct{
    char *s;
    int A[];  // 不占结构体存储空间
}Mstring;

TStruct returnStruct()  // 结构体相当于自定义的变量类型 可以直接返回局部结构体变量
{
    //TStruct ts = (TStruct*)malloc(sizeof(TStruct));
    TStruct ts;
    strcpy(ts.name, "TStruct");
    ts.num = -1;
    return ts;
}

int fun(int);
int funArray(int[]);

/* main函数的参数(int argc, char* argv[], char* envp[])
 * int argc:表示参数的个数  规定argv[argc]为NULL，表示参数的结尾
 * char* argv[]:用于存储参数的字符串数组，argv[0]表示程序运行的全路径，argv[n]表示第n个参数
 * char* envp[]:保存用户环境的变量字符串
 * */
int main(int argc, char* argv[])
{
    int i, j;
    if(argc > 1){  // 为程序指定参数时
        printf("path of program: %s\n", argv[0]);
        for(i = 1; i < argc; i++){
            printf("args of argv[%d]: %s\n", i, argv[i]);
        }
        if(argv[argc] == NULL){
            printf("argc is the end of args!\n");
        }
    }

    // 条件编译 若不是linux下的GUNC编译器则输出警告信息。
#if !defined(__GUNC__) && (__linux__)
    printf("非指定平台与编译器，程序运行结果可能不符合预期。\r\n");
#endif

    PRINTSOMETHING;
    printf("%s===   ===%s\n", ENDLINE, ENDLINE);

    char ch = 'C';
    char ch_;  // 使用未初始化的局部变量，其值不确定！
    printf("字符变量的地址：&ch=%p; 未初始化的局部变量：ch_=%c\n", &ch, ch_);

    char* s = "0123456789";  // C中可以使用字符串初始化char*，C++中不行 必须是const char*。
    //s = "new string";  // C++中也不能使用字符串赋值给非const字符指针
    int* intp = (int*)s;
    // ++p的值为"4567"对应的16进制整数为37363534 字符0的ASCII为48即16进制3，Linux系统是小端存储 所以34353637输出为37363534
    printf("字符串s的值：%s; 指向s的int型指针(*++p)的值：%x\r\n", s, *++intp);

    // C语言中，以字符串字面量定义字符串时会将其分配到字面量池中(只读)
    //char chS[] = "this is string!";  // 字符数组中的字符串存储在栈内
    char chS[] = {"this is string!"};  // 与上一代码等价
    //char* s_ = "0123456789";  // 字符串字面量存储在字面量池中(静态存储区)
    char* s_ = {"0123456789"};  // 与上一代码等价
    //*(s_) = '9';  // 不能修改字符串字面量的值。gcc编译通过，运行发生段错误。
    printf("字符串常量的地址：%p; 字符指针的地址：%p; 字符数组的地址：%p\n", "string", s, chS);  // 只有字符数组在高地址
    printf("字符指针s与s_的地址与值：s=%p,%s; s_=%p,%s\n", s, s, s_, s_);  // 二者指向同一地址
    //printf("越界访问数组：%c, %c\n", chS[-1], chS[18]);  // 编译通过 但输出内容未知
    printf("%s===   ===%s\n", ENDLINE, ENDLINE);

    /*char c[10];  // 数组元素是连续存储的
    for(i = 0; i < 10; i++){
        printf("c[%d]的地址：%p\n", i, c+i);
    }
    char *p = NULL;  // 指针变量的分配的地址也是连续的
    p = (char*)malloc(sizeof(char) * 10);
    for(i = 0; i < 10; i++){
        printf("p+%d的地址：%p\n", i, p+i);
    }
    free(p);
    p = NULL;*/
    //printf("%s===   ===%s\n", ENDLINE, ENDLINE);

    char A[3][5];
    for(i = 0; i < 3; i++){  // 二维数组中的元素同样是连续存储的 A[0][4]的下一个地址存储的是A[1][0]
        for(j = 0; j < 5; j++){
            printf("A[%d][%d]的地址：%p\n", i, j, *(A+i)+j);
        }
    }
    // 使用指针创建的二维数组并不是真正的二维数组 P中的地址是连续的，但P[i]和P[i+1]的元素不一定
    char **P = NULL;
    P = (char**)malloc(sizeof(char*) * 3);
    for(i = 0; i < 3; i++){
        printf("指针P[%d]的地址：%p, %p\n", i, P+i, &(P));
        *(P+i) = (char*)malloc(sizeof(char) * 5);
    }
    for(i = 0; i < 3; i++){
        printf("指针P[%d]的地址：%p, 指向的地址：%p\n", i, &(P[i]), *(P+i));
        for(j = 0; j < 5; j++){
            printf("P[%d][%d]的地址：%p\n", i, j, *(P+i)+j);
        }
    }
    printf("地址偏移(&p+i) &p=%p, &p+1=%p, &p+4=%p. 指针的size=%d\n", &P, &P+1, &P+4, sizeof(char*));
    for(i = 0; i < 3; i++){
        free(*(P+i));
        *(P+i) = NULL;
    }
    P = NULL;
    printf("%s===   ===%s\n", ENDLINE, ENDLINE);

    /*int v = 0x11;
    const int *ptr_const = &v;  // 指向int型常量的指针
    int const *const_ptr = &v;  // 指向int型的常(量)指针
    int * const const_ptr_ = &v;  // 两种常指针的写法都行 这种是规范写法。
    printf("指向常量的指针与常量(常)指针：%d, %d, %d\n", *ptr_const, *const_ptr, const_ptr_);*/

    // 返回结构体
    TStruct ts = returnStruct();
    printf("输出返回的结构体的值：%s, %d\n", ts.name, ts.num);
    printf("结构体的内存：%d, 成员地址：%p, %p\n", sizeof(ts), &ts.name, &ts.num);
    ts.funp = fun;
    //printf("结构体中函数指针的返回值：%d\n", ts.funp(4));
    // 结构体初始化方式之一：指定成员初始化，不变顺序初始化成员。
    TStruct ts_ = { .funp = NULL, .name = "struct", .num=1,};
    printf("结构体ts_的成员：%s, %d\r\n", ts_.name, ts_.num);
    //printf("结构体TStruct与Mstring的对齐值：%ld, %ld\n", alignof(TStruct), alignof(Mstring));

    Mstring ms1, ms2;
    //printf("空结构体的大小：%d, 空结构体的不同变量的地址：%p, %p\n", sizeof(Mstring), &ms1, &ms2);  // 大小为0，所以不同的变量地址却相同
    ms1.s = (char*)malloc(sizeof(char) * 10);
    if(ms1.s == NULL){ exit(-1); }
    strcpy(ms1.s, "mstring-1");
    ms2 = ms1;  // 结构体成员的指针赋值--浅拷贝
    strcpy(ms2.s, "mstring-2");
    printf("结构体变量赋值是浅拷贝，修改ms2.s为：%s,%p，ms1.s的值：%s,%p\n", ms2.s, &ms2.s, ms1.s, &ms1.s);  // 对ms2.s的修改影响ms1.s
    free(ms1.s);
    ms1.s = NULL;
    ms2.s = NULL;
    printf("%s===   ===%s\n", ENDLINE, ENDLINE);

    // scanf()函数的参数 输入时需要在对应位置输入对应字符包括空格 即必须以指定格式输入
    /*int n, m;
    printf("输入整数n,m:\n");
    //scanf("%d, %d", &n, &m);  // 必须输入n, m的形式
    scanf("%d--%d", &n, &m);  // 以n--m的形式
    printf("n=%d, m=%d\n", n, m);*/
    //printf("%s===   ===%s\n", ENDLINE, ENDLINE);

    // 函数传参是值拷贝 函数内和外是不同的数据
    const int x = -0x1;
    printf("函数实参的地址是：%p, 值是：%d\n", &x, x);
    int ret = fun(x);
    printf("函数返回值的地址是：%p，值是：%d\n", &ret, ret);

    // 数组作为参数传递时会退化成指针
    int a[10] = { 1,2,3 };
    ret = funArray(a);
    printf("整型数组的大小：%d, %d\n", ARRSIZE(a), ret);  // 10  2
    printf("%s===   ===%s\n", ENDLINE, ENDLINE);

    return 0;
}

int fun(int x)
{
    printf("函数内参数的地址是：%p，值是：%d\n", &x, x);
    int y = x + 1;
    x++;  // 函数内修改x的值
    printf("函数内返回值的地址是：%p，值是：%d\n", &y, y);
    return y;  // 将临时对象拷贝到函数返回值中 释放临时对象，赋值时再将返回值拷贝到对象中 释放返回值。(编译器可能有优化)
}

int funArray(int A[])
{
    int size = ARRSIZE(A);  // 宏变成对指针求大小即sizeof(int*)/sizeof(int)  64位系统的指针占8字节 int占4字节
    return size;
}
