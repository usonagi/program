#include<stdio.h>
#include<stdlib.h>
#include<string.h>  // 使用memset函数
#include<stdbool.h>  //使用bool类型

/* 力扣习题 1971——寻找图中是否存在路径：
 * 具有n个顶点双向图，图中的边用一个二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和顶点 vi 之间的双向边。
 * 每个顶点对由 最多一条 边连接，并且没有顶点存在与自身相连的边。判断是否存在从顶点source到顶点destination的有效路径。
 * 解题方法：1、BFS——广度优先搜索 2、DFS——深度优先搜索 3、并查集
 * 并查集中图的顶点默认是从0开始！！！ 即顶点范围为0~(n-1).
 */

typedef int ElemType;

struct Node{  // 图的节点信息 顶点及其连通的顶点数组
    ElemType value;
    struct Node* next;
};

struct Node *createListNode(ElemType);
bool validPathBFS(int,int**,int,int,int);  // BFS解法
bool validPathDFS(int,int**,int,int,int);  // DFS解法
//bool validPathDFS(int,int(*)[2],int,int,int);  // 使用数组指针做参数


typedef struct{  // 并查集的信息
    int *parent;  // parent[i]=j 表示顶点i的父结点为j 即属于父结点为j的集合
    int *rank;  // rank[i]=j 表示父结点为i的树的深度 用于合并时防止生成的树倾斜
}UnionFind;

bool validPathUnion(int,int**,int,int,int);  // 并查集解法
bool existCycle(int,int**,int);

int main()
{
    int n = 3;  // 顶点数
    //int edges[][2] = {{0,1},{0,2},{1,2}};
    int edgesSize; // = sizeof(edges) / sizeof(edges[0]);  // 边的数量
    int source = 0;  // 源点
    int destination = 2;  // 目的点
    printf("please input the number of vertices and edges:");
    scanf("%d %d", &n, &edgesSize);
    printf("please enter the source and destination(example:x y):");
    scanf("%d %d", &source, &destination);
    printf("please enter the vertex for each edges(%d edges):\n", edgesSize);
    int **edges = (int**)malloc(sizeof(int*) * edgesSize);
    for(int i = 0; i < edgesSize; i++){
        *(edges+i) = (int*)malloc(sizeof(int) * 2);
        scanf("%d %d", &edges[i][0], &edges[i][1]);
    }

    bool ret = false;

    //ret = validPathBFS(n, edges, edgesSize, source, destination);
    //ret = validPathDFS(n, edges, edgesSize, source, destination);
    ret = validPathUnion(n, edges, edgesSize, source, destination);
    if(ret){
        printf("There has a path from %d to %d in graph\n", source, destination);
    }
    else{
        printf("There has no path from %d to %d in graph\n", source, destination);
    }

    if(existCycle(n, edges, edgesSize)){
        printf("There hava a cycle.\n");
    }
    else{
        printf("There have not a cycle.\n");
    }

    return 0;
}

// 建立顶点val的连通链表 表示与顶点val直接相连的顶点集合
struct Node *createListNode(int val)
{
    struct Node *ListNode = (struct Node*)malloc(sizeof(struct Node));
    if(ListNode == NULL){
        printf("创建Node指针失败！");
        exit(-1);
    }
    ListNode->value = val;
    ListNode->next = NULL;
    return ListNode;
}

bool validPathBFS(int n, int **edges, int edgesSize, int source, int destination)
{
    struct Node* adj[n];  // 图的顶点信息数组 adj[x]=y表示顶点x与顶点y相连
    bool visit[n];
    int i, j;
    for(i = 0; i < n; i++){
        adj[i] = NULL;
        visit[i] = false;
    }
    for(i = 0; i < edgesSize; i++){  // 将每条边的信息记录到adj中
        int x = edges[i][0];
        int y = edges[i][1];
        struct Node *nodex = createListNode(x);
        nodex->next = adj[y];
        adj[y] = nodex;
        struct Node *nodey = createListNode(y);
        nodey->next = adj[x];
        adj[x] = nodey;
    }

    int queue[n];
    int head = 0, tail = 0;
    queue[tail++] = source;
    visit[source] = true;
    while(head != tail){  // 顶点队列不为空时 依次遍历队列中的元素
        int vertex = queue[head++];
        if(vertex == destination){
            break;
        }
        for(struct Node *p = adj[vertex]; p != NULL; p = p->next){  // 访问与队首元素直接相连的顶点
            int nextVertex = p->value;
            if(!visit[nextVertex]){
                queue[tail++] = nextVertex;
                visit[nextVertex] = true;
            }
        }
    }

    for(i = 0; i < n; i++){  // 释放顶点信息数组
        for(struct Node *p = adj[i]; p != NULL; ){
            struct Node *cur = p;
            p = p->next;
            free(cur);
        }
    }
    return visit[destination];
}


bool DFSGraph(const struct Node** Adj, bool *visit, int vertex, int destination)
{
    if(vertex == destination){
        return true;
    }
    if(!visit[vertex]){  // 如果当前结点未被访问 访问与其相连的顶点
        visit[vertex] = true;
        for(const struct Node *p = Adj[vertex]; p != NULL; p= p->next){
            if(!visit[p->value] && DFSGraph(Adj, visit, p->value, destination)){
                return true;
            }
        }
    }
    return false;
}

// 大体与BFS相同 只有搜索方式的不同
bool validPathDFS(int n,int **edges, int edgesSize, int source, int destination)
{
    struct Node* adj[n];
    bool visit[n];
    int i, j;
    for(i = 0; i < n; i++){
        adj[i] = NULL;
        visit[i] = false;
    }
    for(i = 0; i < edgesSize; i++){
        int x = edges[i][0];
        int y = edges[i][1];
        struct Node *nodex = createListNode(x);
        nodex->next = adj[y];
        adj[y] = nodex;
        struct Node *nodey = createListNode(y);
        nodey->next = adj[x];
        adj[x] = nodey;
    }
    bool ret = DFSGraph(adj, visit, source, destination);
    for(i = 0; i < n; i++){
        for(struct Node *p = adj[i]; p != NULL; ){
            struct Node *cur = p;
            p = p->next;
            free(cur);
        }
    }
    return ret;
}


// 初始化并查集 每个顶点的父结点是其本身，包含的元素个数为0
UnionFind *createUnionFind(int n)
{
    UnionFind *set = (UnionFind*)malloc(sizeof(UnionFind));
    set->parent = (int*)malloc(sizeof(int) * n);
    set->rank = (int*)malloc(sizeof(int) * n);
    memset(set->rank, 0, sizeof(int) * n);
    for(int i = 0; i < n; i++){
        set->parent[i] = i;
    }
    return set;
}

int Find(const UnionFind *obj, int x)  // 查找X的父结点
{
    //if(x == obj->parent){  // 直接查找 
    //    return x
    //}
    //return Find(obj, obj->parent[x]);

    if(x != obj->parent[x]){
        obj->parent[x] = Find(obj, obj->parent[x]);  // 路径压缩 在返回的时候更新父结点
    }
    return obj->parent[x];
}

void Union(UnionFind *obj, int x, int y)
{   
    int rootx = Find(obj, x);
    int rooty = Find(obj, y);
    // 直接合并
    //obj->parent[rootx] = rooty;

    // 按秩合并 即将深度小的子树合并到深度较大的树中
    if(rootx != rooty){
        if(obj->rank[rootx] < obj->rank[rooty]){
            obj->parent[rootx] = rooty;
        }
        else if(obj->rank[rootx] > obj->rank[rooty]){
            obj->parent[rooty] = rootx;
        }
        else{  // 深度相同时 合并到任意一个，并将其深度+1
            obj->parent[rootx] = rooty;
            obj->rank[rooty]++;
        }
    }
}

bool isConnect(const UnionFind *obj, int x, int y)  // 判断顶点x与顶点y是否在同一集合
{
    if(obj == NULL){
        return false;
    }
    return Find(obj, x) == Find(obj, y);
}

bool validPathUnion(int n,int **edges,int edgesSize,int source,int destination)
{
    if(edges == NULL || n <= 0){
        return false;
    }
    UnionFind *sets = createUnionFind(n);
    for(int i = 0; i < edgesSize; i++){  // 将图的所有边记录到并查集中
        Union(sets, edges[i][0], edges[i][1]);
    }
    return isConnect(sets, source, destination);
}

bool existCycle(int n,int **edges ,int edgesSize)
{
    UnionFind *sets = createUnionFind(n);
    for(int i = 0; i < edgesSize; i++){  // 判断图的每条边的两个顶点是否在同一连通集中 若在则root、x、y三点互相连通成环
        int rootx = Find(sets, edges[i][0]);
        int rooty = Find(sets, edges[i][1]);
        if(rootx == rooty){
            return true;
        }
        Union(sets, edges[i][0], edges[i][1]);
    }
    return false;
}
