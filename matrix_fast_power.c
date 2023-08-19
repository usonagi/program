#include<stdio.h>

/* 矩阵快速幂
 * 快速幂思想：任何一个正整数都可以使用2的幂次方之和表示 例如：117=64+32+16+4+1
 * 故m的n次方可以表示为：m*(m^2)*(m^4)*...*(m^k)  n=1+2+4+...+k
 * 斐波拉契数列的快速幂原理：f(n)=f(n-1)+f(n-2) 所以f(n-1)=0*f(n-1)+1*f(n-2)
 * 因此使用矩阵求[f(n),f(n-1)] = [[1,1][1,0]] * [f(n-1),f(n-2)] = [[1,1][1,0]]^(n-1) * [f(1),f(0)]
 * */

const int MOD = 1000000007;

typedef struct{
    int m[2][2];
}Matrix;

long long FastPower(int,int);
Matrix MatrixMulti(Matrix,Matrix);
long long MatrixFastPower(int);

int main()
{
    int m, n;
    long long ret;
    //printf("please enter m and n (Example:m,n):");
    //scanf("%d,%d", &m, &n);
    //ret = FastPower(m, n);
    //printf("%d to the power of %d is %lld\n", m, n, ret);

    printf("plaes enter the number of fibonacci:");
    scanf("%d", &n);
    ret = MatrixFastPower(n);
    printf("the %dth number of fabonacci is: %d\n", n, ret);

    return 0;
}

long long FastPower(int m, int n)
{
    long long res = 1;
    while(n > 0){
        if(n & 1){  // 判断当前的幂是否需要
            res *= m;
        }
        n >>= 1;  // 等价于n /= 2
        m *= m;  // m的幂次增加一倍
    }
    return res;
}

Matrix MatrixMulti(Matrix A,Matrix B)
{
    int i, j, k;
    int size = sizeof(A.m[0]) / sizeof(A.m[0][0]);
    Matrix res;
    for(i = 0; i < size; i++){
        for(j = 0; j < size; j++){
            res.m[i][j] = 0;
            for(k = 0; k < size; k++){
                res.m[i][j] += A.m[i][k] * B.m[k][j];
            }
        }
    }
    return res;
}

long long MatrixFastPower(int n)
{
    Matrix base, ans;
    base.m[0][0] = base.m[0][1] = base.m[1][0] = 1;
    base.m[1][1] = 0;  // 初始化矩阵
    ans.m[0][0] = ans.m[1][1] = 1;  // 初始化ans为单位矩阵
    ans.m[0][1] = ans.m[1][0] = 0;
    if(n <= 2){  // 斐波拉契数列前两个数为1
        return ans.m[0][0];
    }else{
        n--;
    }
    // 求第n个斐波拉契数  ans[[f(n),f(n-1)],[f(n-1),f(n-2)]]
    while(n > 0){
        if(n & 1){
            ans = MatrixMulti(ans, base);
        }
        n >>= 1;
        base = MatrixMulti(base, base);
    }
    return ans.m[0][0];
}
