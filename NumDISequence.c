#include<stdio.h>
#include<string.h>

// DI序列的有效排列 力扣-903

#define MAXLEN 201
int numPermsDISequence(const char*);

int main()
{
    const char *strDI = NULL;
    char str[MAXLEN];
    printf("请输入只包含'D'与'I'的字符串：");
    scanf("%s", str);
    //printf("%s",str);
    int res = 0;
    res = numPermsDISequence(str);
    printf("此DI序列的有效排列数(对1000000007取余后)：%d\n", res);
    return 0;
}

int numPermsDISequence(const char* s)
{
    int mod = 100000007;
    if(s == NULL){
        return 0;
    }
    int n = strlen(s);
    int totalCount = 0;
    int dp[MAXLEN][MAXLEN] = {0};

    dp[0][0] = 1;
    int i, j;
    for(i = 1; i <= n; i++){
        if(s[i-1] == 'D'){
            dp[i][i] = 0;
            for(j = i-1; j >= 0; j--){  // 上一个序列以大于等于j结尾的都可以构成降序
                dp[i][j] = (dp[i][j+1] + dp[i-1][j]) % mod;  // 等价于for(k=j;k<i;k++) sum(dp[i-1][k])
            }
        }else{
            dp[i][0] = 0;
            for(j = 1; j <= i; j++){  // 上一个序列以小于j结尾的都可以构成升序
                dp[i][j] = (dp[i][j-1] + dp[i-1][j-1]) % mod;
            }
        }
    }
    for(i = 0; i <= n; i++){
        totalCount = (totalCount + dp[n][i]) % mod;
    }

    return totalCount;
}
