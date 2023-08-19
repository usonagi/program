#include<stdio.h>
#include<string.h>
#include<stdlib.h>

// 翻转括号中的字符串

char* ReverseParenthes(char*);  // 
char* ReverseParenthes2(char*);  // 预处理括号 
char* ReverseParenthes3(char*);  // 

int main()
{
    char *str = "a(bcdefghijkl(mno)p)q";  //"(ed(et(oc))el)";
    char *res = NULL;

    //res = ReverseParenthes(str);
    //res = ReverseParenthes2(str);
    res = ReverseParenthes3(str);

    printf("翻转后的结果：%s\n", res);

    return 0;
}


void reverse(char *str, int size)
{
    if(size <= 0 || str == NULL){
        return;
    }
    int i;
    for(i = 0; i < size / 2; i++){
        char c = str[i];
        str[i] = str[size - i - 1];
        str[size - i - 1] = c;
    }
}

char* ReverseParenthes(char* s)
{
    if(s == NULL){
        return s;
    }
    int n = strlen(s);
    char *stk[n];  // 存储括号内的子串
    int top = 0;
    int strSize = 0;
    char *str = malloc(sizeof(char) * (n+1));
    if(str == NULL){
        printf("malloc failed.\n");
        return;
    }
    memset(str, 0, sizeof(char)*n);

    int i, j;
    for(i = 0; i < n; i++){
        if(s[i] == '('){  // 将当前的子串拷贝到stk中
            stk[top] = (char*)malloc(sizeof(char) * (strSize + 1));
            memcpy(stk[top], str, sizeof(char) * (strSize + 1));
            top++;
            str[0] = '\0';
            strSize = 0;
        }else if(s[i] == ')'){  // 将当前str中的子串翻转后拼接到上一个子串后
            reverse(str, strSize);
            int len = strlen(stk[top - 1]);
            for(j = strSize; j >= 0; j--){
                str[j + len] = str[j];
            }
            memcpy(str, stk[top - 1], sizeof(char) * len);
            strSize += len;
            top--;
        }else{
            str[strSize++] = s[i];
            str[strSize] = '\0';
        }
    }
    return str;
}


char* ReverseParenthes2(char* s)
{
    if(s == NULL){
        return NULL;
    }
    int n = strlen(s);
    int stk[n];  // 存储左括号的索引
    int index[n];  // 存储对应的另一个括号的下标 index[1]=5表示一对括号的下标(1,5)
    char *res = malloc(sizeof(char) * (n+1));
    if(res == NULL){
        return NULL;
    }
    memset(res, 0, sizeof(char) * (n+1));

    int top = 0;
    int i;
    for(i = 0; i < n; i++){
        if(s[i] == '('){
            stk[top++] = i;
        }else if(s[i] == ')'){
            int k = stk[--top];
            index[i] = k;  // 右括号的下标的元素值为存储左括号的下标
            index[k] = i;
        }
    }

    int step = 1;
    top = 0;
    i = 0;
    while(i < n){
        if(s[i] == '(' || s[i] == ')'){
            i = index[i];
            step = -step;
        }else{
            res[top++] = s[i];
        }
        i += step;
    }
    res[top] = '\0';
    return res;
}

char* ReverseParenthes3(char* s)
{
    if(s == NULL){
        return NULL;
    }
    int n = strlen(s);
    int resSize = 0;  // 存储当前res中的元素个数
    char* res = malloc(sizeof(char) * (n+1));
    if(res == NULL){
        return NULL;
    }
    memset(res, 0, sizeof(char)*(n+1));

    int i;
    for(i = 0; i < n; i++){
        if(s[i] != ')'){
            res[resSize++] = s[i];
        }else{
            int count = 0;  // 存储当前括号内的元素个数
            while(resSize > 0 && res[resSize-1] != '('){
                count++;
                resSize--;
            }
            reverse(res+(resSize-1), count+1);  // 翻转离栈顶最近的左括号后的元素
            resSize += count;
            res[--resSize] = '\0';  // 左括号出栈
        }
    }
    res[resSize] = '\0';
    return res;
}
