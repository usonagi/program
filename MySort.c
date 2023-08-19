#include<stdio.h>
#include<stdlib.h>  // malloc
#include<string.h>  // memset

// 基本排序算法
#define ARRSIZE 20

void QuickSort(int*, int, int);
int Partition(int*, int, int);
int Split(int*, int, int);
void QuickSortIterate(int*, int, int);
void QuickSort2(int*, int, int);

void MergeSort(int*, int, int);
void MergeArray(int*, int, int, int);
void MergeSort2(int*, int, int);
void MergeSortIterate(int*, int);

void SelectSort(int*, int);

int main()
{
    int sortArr[ARRSIZE];
    int *p;

    int i;
    for(i = 0; i < ARRSIZE; i++){
        sortArr[i] = rand() % 100;  // 生成一个100以内的随机数
    }
    p = sortArr;
    int len = sizeof(sortArr) / sizeof(*sortArr);
    //printf("len = %d, p[0] = %d, p[1] = %d\n", len, *p, *(p+1));

    printf("排序前的顺序：");
    for(i = 0; i < len; i++){
        printf("%d ", sortArr[i]);
    }
    printf("\n");

    //QuickSort(p, 0, len-1);
    //QuickSort2(p, 0, len-1);
    //QuickSortIterate(p, 0, len-1);
    //MergeSort(p, 0, len-1);
    //MergeSort2(p, 0, len-1);
    MergeSortIterate(p, len);
    //SelectSort(p, len);

    printf("排序后的顺序：");
    for(i = 0; i < len; i++){
        printf("%d ", sortArr[i]);
    }
    printf("\n");

    return 0;
}

void Swap(int *x, int *y)
{
    int temp = *x;
    *x = *y;
    *y = temp;
}

void QuickSort(int* A, int low, int high)
{
    int mid;
    if(low < high){
        //mid = Partition(A, low, high);
        mid = Split(A, low, high);
        printf("mid = %d\n", mid);
        QuickSort(A, low, mid - 1);
        QuickSort(A, mid + 1, high);
    }
}

int Partition(int* A, int low, int high)
{
    int index = low;  // 记录比较元素的下标
    int pivot = A[low];  // 记录比较元素的值
    
    /* 先从后面找比pivot小的元素，然后从前面找比pivot大的元素，交换两者的位置。
     * 最后将pivot放到期望的位置，然后返回此位置。
     */
    while(low < high){
        while(high > low && A[high] >= pivot){
            high--;
        }
        A[low] = A[high];
        while(low < high && A[low] <= pivot){
            low++;
        }
        A[high] = A[low];
    }
    A[low] = pivot;

//    while(low < high){
//        while(high > low && A[high] >= pivot){
//            high--;
//        }
//        while(low < high && A[low] <= pivot){
//            low++;
//        }
//        printf("swap %d and %d \n", A[low], A[high]);
//        Swap(&A[low], &A[high]);
//    }
//    Swap(&A[low], &A[index]);

    return low;
}

// 快速排序的另一种划分方式
int Split(int *A, int low, int high)
{
    int pivot = A[low];
    int i = low;
    int j;
    for(j = i + 1; j <= high; j++){
        if(A[j] <= pivot){
            i++;
            if(i != j){
                Swap(A+i, A+j);
            }
        }
    }
    Swap(A+low, A+i);
    return i;
}


void QuickSortIterate(int *A, int low, int high)
{
    if(A == NULL || low >= high){
        return;
    }
    int size = high - low + 1;
    int *index = (int*)malloc(sizeof(int) * size);  // 存储待排序元素的范围
    int top = -1;  // 栈顶指针

    if(index == NULL){
        return;
    }
    memset(index, 0, sizeof(int)*size);

    index[++top] = low;
    index[++top] = high;

    while(top >= 0){
        // 待排序的范围出栈，寻找首元素的期望位置
        int right = index[top--];
        int left = index[top--];
        int p = Split(A, left, right);

        // pivot元素左侧与右侧是否需要排序
        if((p - 1) > left){
            index[++top] = left;
            index[++top] = p - 1;
        }
        if((p + 1) < right){
            index[++top] = p + 1;
            index[++top] = right;
        }
    }
}

void QuickSort2(int* A, int low, int high)
{
    if(low >= high || A == NULL){
        return;
    }

    // 分别记录本次排序的比较元素、起始位置、结束位置
    int pivot = A[low];
    int start = low;
    int end = high;

    // 左侧元素小于pivot 右侧元素大于pivot
    while(low < high){
        while(high > low && A[high] >= pivot){
            high--;
        }
        if(A[high] < pivot){
            A[low] = A[high];
            low++;
        }
        while(low < high && A[low] <= pivot){
            low++;
        }
        if(A[low] > pivot){
            A[high] = A[low];
            high--;
        }
    }
    A[low] = pivot;  // 比较元素放置到期望位置
    printf("本轮排序的low：%d\n", low);

    // 递归对pivot左侧和右侧进行排序
    QuickSort2(A, start, low-1);
    QuickSort2(A, low+1, end);
}

// n是待排序数组的元素个数
void SelectSort(int *A, int n)
{
    int i,j;
    for(i = 0; i < n - 1; i++){
        for(j = i + 1; j < n; j++){
            if(A[i] < A[j]){
                Swap(A+i, A+j);
            }
        }
    }
}

void MergeSort(int* A, int left, int right)
{
    if(left < right){
        int mid = (left + right) / 2;
        MergeSort(A, left, mid);
        MergeSort(A, mid+1, right);
        MergeArray(A, left, mid, right);
    }
}

void MergeArray(int* A, int left, int mid, int right)
{
    int i = 0;
    int j = mid - left + 1;
    int index;
    int *T = malloc(sizeof(int) * (right - left + 1));  // 保存当前数组元素
    for(index = left; index <= right; index++){
        T[index - left] = A[index];
    }

    // 将原数组mid左右两侧元素进行合并
    index = left;
    while(i <= (mid-left) && j <= (right-left)){
        if(T[i] <= T[j]){
            A[index++] = T[i];
            i++;
        }else{
            A[index++] = T[j];
            j++;
        }
    }
    while(i <= (mid-left)){
        A[index++] = T[i++];
    }
    while(j <= (right-left)){
        A[index++] = T[j++];
    }
    free(T);
}

void MergeSort2(int *A, int left, int right)
{
    if(A == NULL || left >= right){
        return;
    }
    
    int mid = (left + right) / 2;
    MergeSort2(A, left, mid);
    MergeSort2(A, mid+1, right);

    int *T = (int*)malloc(sizeof(int) * (right+1));
    memset(T, 0, sizeof(int)*(right+1));  // 按照字节初始化指定地址
    int index;
    for(index = left; index <= right; index++){
        T[index] = A[index];
    }

    index = left;
    int i = left;
    int j = mid + 1;
    while(i <= mid && j <= right){
        if(T[i] <= T[j]){
            A[index++] = T[i++];
        }else{
            A[index++] = T[j++];
        }
    }
    while(i <= mid){
        A[index++] = T[i++];
    }
    while(j <= right){
        A[index++] = T[j++];
    }
    free(T);
}


void MergeSortIterate(int* A, int n)
{
    if(A == NULL || n <= 1){
        return;
    }

    int *temp = (int*)malloc(sizeof(int) * n);  // 临时存储较小的元素
    if(index == NULL){
        return;
    }
    memset(temp, 0, sizeof(int) * n);

    int L, R, ML, MR; //L是左侧起始下标，ML是左侧结束下标(不可取)，MR是右侧起始下标
    int index;  // 每次排序的元素数量/下标
    int i;  // 每次归并的步长
    for(i = 1; i < n; i *= 2){
        for(L = 0; L + i < n; L = R){
            ML = MR = L + i;
            R = ML + i;
            if(R > n){
                R = n;
            }
            //MergeArray(A, L, (L+R)/2, R);
            index = 0;
            while(L < ML && MR < R){
                if(A[L] < A[MR]){
                    temp[index++] = A[L++];
                }
                else
                {
                    temp[index++] = A[MR++];
                }
            }
            // 剩余元素都是较大的元素，若左侧有剩余元素需要拷贝到右侧，若右侧有剩余则忽略
            while(L < ML){
                A[--MR] = A[--ML];
            }
            // 将较小的元素拷贝到左侧
            while(index > 0){
                A[--MR] = temp[--index];
            }
        }
    }
}

