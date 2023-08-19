#include<stdio.h>

/*** C语言格式输出 ***/

/* 字符串格式：%[flags][width][.prec][length]type
 * 前缀格式flags: -(负号), +(正号), space(空格), #, 0
 * 宽度width: 整数, *   --指定最小输出宽度
 * 精度.precision: 整数  --表示输出的小数位数或字符的个数
 * length:hh, h, l, ll, j, z, t, L  --h表示按短整型输出 l表示按长整型输出
 * 类型type: d/i(十进制输出整数), o(8进制输出无符号整数), u(十进制输出无符号整数), x/X(16进制输出无符号整数), f/lf(小数形式输出), 
 *     F, e/E(指数形式输出), g, G, c, s, S, p, n, %, m, a/A
 * 转义字符: \a, \b, \f, \n,\r, \t, \v, \\, \"
 * TIP: 小数点也占宽度。例6.2f表示最小宽度6位——小数2位，小数点1位，实数最小宽度为3.
 *
 * float占4个字节 指数位8位、尾数位23位、符号位1位 表示的数据范围：-3.4*10^38————3.4*10^38
 * 在计算机中浮点数采用科学计数法表示，(-1)^S*F*2^E  S表示符号位，F表示尾数(二进制),E表示指数
 * 因此当S=0，尾数全部为0 即F=1.0(二进制) E=128时，浮点数的值最大 为1.0*2^128 == 3.4*10^38(十进制)；反之S=1时取最小即-3.4*10^38
 * IEEE规定指数部分减去127才是真正的指数值，尾数决定精度，指数决定范围。
 * TIP:float的有效位数为6~7位，double的有效位数15~16。尾数的位数影响精度 float的尾数有23位，因此2^23=8388608共有7位 即float最多有7位有效数字。
 *     float不能使用==或!=进行比较，double可以，建议使用double而非float。
 *
 * C语言类型隐式转换：signed char(char是signed或unsigned 由编译器决定)、 unsigned char、short、 unshort -> int 
 *     -> unsigned int -> long -> unsigned long -> double   以及 float -> double
 * */

int main()
{
    float f = 3.1415926f;
    printf("不使用格式控制符输出浮点数：%f\n", f);  // 默认输出小数点后6位
    //printf("使用%%f输出浮点数：%f\n", f);
    printf("使用%%m.nf输出浮点数：%3.5f\n", f);
    printf("使用%%m.nf输出浮点数：%6.2lf\n", 12345.678);  // 整数全部输出
    printf("使用%%m.nf输出浮点数：%6.2lf\n", 123.45678);  // 整数全部输出
    printf("使用%%m.nf输出浮点数：%6.2lf\n", 12.45);  // 前面填充空格
    printf("使用%%m.nf输出浮点数：%4.6lf\n", 123.456789);  // 最小宽度小于精度:实数部分全部输出,小数部分按精度输出 相当于*.n
    printf("使用%%m.nf输出浮点数：%4.6lf\n", 12345.678);  // 小数不够后面补0
    printf("使用%%nd输出整型数字：%6d\n", 12345678);  // 全部输出
    printf("使用%%nd输出整型数字：%06d\n", 12345);  // 前面填充0

    double d = 12345.6789012345L;
    printf("不使用格式控制符输出double型数：%lf\n", d);  // double型不建议使用%f输入输出
    printf("使用%%m.nf输出double型数：%6.3lf\n", d);

    const char* str = "This is string. The world is still so wonderful!";
    printf("使用%%.ns输出n个字符：%.15s\n", str);  // %ns不起作用，%m.ns格式错误但生效。

    const int ix = 0x12345678;  // 小端模式：78 56 34 21
    printf("使用%%d,%%o,%%x输出整型数字：%d, %o, %x\n", ix, ix, ix);
    printf("使用%%i,%%#o,%%#x输出整型数字：%i, %#o, %#x\n", ix, ix, ix);  //%#o以8进制输出前面带0 %#x/X以16进制输出前面带0x/0X
    printf("使用%%hd输出整型数字：%hd, %#hx\n", ix, ix);  // 输出short型长度的数字

    // 浮点数使用注意事项
    float fx = 1000000.999f;  // float有效位6位 7位及以上的数无法准确表示
    float fy = 1.678f;
    float fres = fx + fy;  // 结果的小数部分不一定是预想的
    printf("浮点数fx:%e, %f, %lf\n", fx, fx, fx);
    printf("浮点数fy:%e, %f, %lf\n", fy, fy, fy);
    printf("fx+fy:%e, %f, %lf\n", fres, fres, fres);

    // 整型
    int intx = 010;  // 表示8进制数10 即10进制数字8
    int inty = 0x10;  // 表示16进制数10 即10进制数字16
    int intz = 10;  // 10进制数字10
    printf("整型数字：%#x, %#x, %#x\n", intx, inty, intz);

    return 0;
}


