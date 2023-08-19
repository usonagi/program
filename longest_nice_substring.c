#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>  // 使用islower()

/* 力扣-1763 最长美好子字符串
 * 当一个字符串s包含的每一种字母的大写和小写形式同时存在，即为美好字符串。 例：AbBa
 * 返回s最长的美好子字符串，若有多个则返回最早出现的，若不存在则返回空字符串。
 * 解法：1、从字符串索引为0处开始枚举所有子字符串，判断是否为美好字符串。
 *       2、根据只有大写或小写形式的字母将字符串拆分为若干段，最长美好子字符串一定在某个段内。
 *       3、枚举最长字串中的字符种类数(不分大小写)，对于给定的字符种类数k，维护窗口的左右边界，对于任一右边界r,
 *       能找到最小的左边界lmin使s[lmin,r]之间的字符种类不多于k，最长美好子字符串一定在某个窗口中 即判断当前的s[lmin,r]是否是美好字符串。
 * */

char *LongestNiceSubstring(const char *s);  // 枚举
char *LongestNiceSubstringPartition(char*);  // 区间递归
char *LongestNiceSubstringWindow(char*);  // 滑动窗口

int main()
{
    char s[10] = "AGBabbcgG";
    char *ret;

    //ret = LongestNiceSubstring(s);
    //ret = LongestNiceSubstringPartition(s);
    ret = LongestNiceSubstringWindow(s);

    if(ret == NULL){
        ret = "";
    }
    printf("最长美好子字符串：%s\n", ret);

    return 0;
}

char *LongestNiceSubstring(const char *s)
{
    if(s == NULL){
        return NULL;
    }
    int n = strlen(s);
    int pos = 0, maxlen = 0;  // 记录子串起始位置与其长度
    int i, j;
    for(i = 0; i < n; i++){  // 枚举所有可能的子字符串
        int lower = 0, upper = 0;  // 记录出现的小写字母与大写字母
        for(j = i; j < n; j++){
            //if(('a' <= s[j]) && (s[j] <= 'z')){
            if (islower(s[j])) {
                lower |= 1 << (s[j] - 'a');
            } else { 
                upper |= 1 << (s[j] - 'A');
            }
            if(lower == upper && (j - i + 1 > maxlen)){  // 出现美好字符串
                pos = i;
                maxlen = j - i + 1;
            }
        }
    }
    char *ret = (char*)malloc(sizeof(char) * (maxlen + 1));
    strncpy(ret, s + pos, maxlen);
    ret[maxlen] = '\0';
    return ret;
}

void SearchNiceString(const char *s, int start, int end, int *maxPos, int *maxLen)
{
    if(start >= end){  // 递归边界
        return;
    }
    int lower = 0, upper = 0;
    int i;
    for(i = start; i <= end; i++){  // 记录当前字符串中的大小写字母
        if(islower(s[i])) {
            lower |= 1 << (s[i] - 'a');
        } else {
            upper |= 1 << (s[i] - 'A');
        }
    }
    if(lower == upper){  // 若此字符串为美好字符串 
        if(*maxLen < end - start + 1){
            *maxPos = start;
            *maxLen = end - start + 1;
        }
        return;
    }

    int niceLetter = upper & lower;  // 记录同时出现大小写的字母
    int pos = start;  // 记录分段的位置 即只有大写或小写形式的字母的下标
    while(pos <= end){
        start = pos;
        while(pos <= end && (1 << tolower(s[pos])-'a') & niceLetter){  // 寻找非法字母的位置
            pos++;
        }
        SearchNiceString(s, start, pos-1, maxPos, maxLen);
        pos++;  // 跳过非法字母
    }
}

char *LongestNiceSubstringPartition(char *s)
{
    if(s == NULL || strlen(s) <= 1){
        return NULL;
    }
    int maxPos = 0, maxLen = 0;
    SearchNiceString(s, 0, strlen(s)-1, &maxPos, &maxLen);  // 递归检查区间内是否存在美好字符串
    s[maxPos + maxLen] = '\0';
    return s + maxPos;
}

void check(const char *s, int types, int *pos, int *len)  // 检查字符种类数为types时的最长美好字符串
{
    int lowerCnt[26], upperCnt[26];  // 记录每种大小写字母的数量
    memset(lowerCnt, 0, sizeof(lowerCnt));
    memset(upperCnt, 0, sizeof(upperCnt));
    int count = 0, total = 0;  // 同时存在大小写的字符种类 总的字符种类
    int left = 0, right = 0;  // 当前字符串的索引
    int n = strlen(s);

    for(right = 0; right < n; right++){
        // 右移右边界 对应的大/小写字母的数量+1，count可能增加（两种情况）
        int idx = tolower(s[right]) - 'a';
        if(islower(s[right])){
            lowerCnt[idx]++;
            if(lowerCnt[idx] == 1 && upperCnt[idx] > 0){  // 表示已有大写字母,且小写字母刚加入，count+1
                count++;
            }
        } else {
            upperCnt[idx]++;
            if(lowerCnt[idx] > 0 && upperCnt[idx] == 1){  // 表示已有小写字母,且大写字母刚加入，count+1
                count++;
            }
        }
        total += (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;  // 若增加了一种大写或小写字母 则总字符种类+1

        // 当总字符种类大于字符种类types时 右移左边界，对应的字母数量-1，count可能减少（两种情况）
        while(total > types){
            idx = tolower(s[left]) - 'a';
            total -= (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;  // 若唯一的字母被去除 则总字符种类-1
            if(islower(s[left])){
                lowerCnt[idx]--;
                if(lowerCnt[idx] == 0 && upperCnt[idx] > 0){  // 表示唯一的小写字母被去除 count-1
                    count--;
                }
            } else {
                upperCnt[idx]--;
                if(lowerCnt[idx] > 0 && upperCnt[idx] == 0){  // 表示唯一的大写字母被去除 count-1
                    count--;
                }
            }
            left++;
        }

        // 若count==types表示s[left,right]是美好字符串
        if(count == types && (right - left + 1) > *len){
            *pos = left;
            *len = right - left + 1;
        }
    }
}

char *LongestNiceSubstringWindow(char *s)
{
    if(s == NULL || strlen(s) <= 1){
        return NULL;
    }
    int n = strlen(s);
    int maxPos = 0, maxLen = 0, mask = 0;
    int i;
    for(i = 0; i < n; i++){
        mask |= (1 << (tolower(s[i]) - 'a'));
    }
    int typeNum = __builtin_popcount(mask);
    for(i = 1; i <= typeNum; i++){
        check(s, i, &maxPos, &maxLen);
    }
    s[maxPos + maxLen] = '\0';
    return s + maxPos;
}
