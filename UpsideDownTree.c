#include<stdio.h>
#include<stdlib.h>
#include<string.h>

// 上下翻转二叉树 
// 给定一颗二叉树，其中右结点要么具有兄弟结点，要么为空

struct BNode{
    char val;
    struct BNode *lchild;
    struct BNode *rchild;
};
typedef struct BNode* BTree;

BTree UpsideDownBinaryTree(BTree);  // 上下翻转二叉树 对右结点有子树的二叉树翻转错误
BTree UpsideDownBinaryLeftTree(BTree);  // 只对左子树进行操作 满足要求
BTree UpsideDownBinaryLeftTreeIterate(BTree);

void PreOrder(BTree);  // 先序遍历
BTree CreateBinaryTree(const char*, int*);  // 先序递归建立二叉树
BTree CreateBinaryTree2(BTree);  // 先序递归建立二叉树
void DestoryBinaryTree(BTree);  // 销毁二叉树

BTree CreateBinaryTreeIterate(const char*);  // 先序迭代创建二叉树
BTree CreateBTreeNode(const char*, int);  // 层次递归建立二叉树

BTree res = NULL;

int main()
{
    const char* tree = NULL;
    BTree root;
    int index = 0;

    tree = "124##56##7##3##";//"AB##C##";
    //root = CreateBinaryTree(tree, &index);
    //printf("please input nodes of tree:");
    //root = CreateBinaryTree2(root);
    root = CreateBinaryTreeIterate(tree);
    //
    //tree = "ABC####";
    //root = CreateBTreeNode(tree, 1);

    printf("created tree preorder:");
    PreOrder(root);
    printf("\n");

    //UpsideDownBinaryTree(root);
    root = UpsideDownBinaryLeftTree(root);
    //root = UpsideDownBinaryLeftTreeIterate(root);

    printf("result tree preorder:");
    PreOrder(root);
    printf("\n");

    DestoryBinaryTree(root);
    //DestoryBinaryTree(res);
    return 0;
}

BTree UpsideDownBinaryTree(BTree T)
{
    if(!T){
        return NULL;
    }
    if(T->lchild == NULL && T->rchild == NULL){  // 最左侧的叶结点成为新的根结点
        if(res == NULL){
            res = T;
        }
        return T;
    }

    BTree newLeft = UpsideDownBinaryTree(T->lchild);
    BTree newRight = UpsideDownBinaryTree(T->rchild);

    if(newLeft){
        newLeft->lchild = newRight;
        newLeft->rchild = T;
    }
    T->lchild = NULL;
    T->rchild = NULL;
    return T;
}

BTree UpsideDownBinaryLeftTree(BTree T)
{
    if( !T || (!T->lchild && !T->rchild)){
        return T;
    }

    BTree newRoot = UpsideDownBinaryLeftTree(T->lchild);

    T->lchild->lchild = T->rchild;
    T->lchild->rchild = T;
    T->lchild = NULL;
    T->rchild = NULL;

    return newRoot;
}

BTree UpsideDownBinaryLeftTreeIterate(BTree T)
{
    if( !T || (!T->lchild && !T->rchild)){
        return T;
    }
    BTree parent = NULL, brother = NULL;

    while(T){
        BTree tmp = T->lchild;

        T->lchild = brother;
        brother = T->rchild;
        T->rchild = parent;
        parent = T;

        T = tmp;
    }
    return parent;
}

BTree CreateBinaryTree2(BTree T)
{
    char data;
    scanf("%c", &data);
    if(data == '#'){
        T =  NULL;
    }else{
        T = (struct BNode*)malloc(sizeof(struct BNode));
        T->val = data;
        T->lchild = CreateBinaryTree2(T->lchild);
        T->rchild = CreateBinaryTree2(T->rchild);
    }
    return T;
}

BTree CreateBinaryTree(const char* s, int *index)
{
    if(*index >= strlen(s)){
        return NULL;
    }else if(s[*index] == '#'){
        return NULL;
    }else{
        BTree node = (struct BNode*)malloc(sizeof(struct BNode));
        node->val = s[*index];
        (*index)++;
        node->lchild = CreateBinaryTree(s, index);
        (*index)++;
        node->rchild = CreateBinaryTree(s, index);
        return node;
    }
}

BTree CreateBinaryTreeIterate(const char* s)  
{
    if(s == NULL || s[0] == '#'){
        return NULL;
    }
    int n = strlen(s);
    BTree root, node;  // root是根节点 node是每次生成的结点 在引用前都会malloc因此不用置空
    BTree stk[n];  // 节点栈 栈顶元素表示当前需要生成左右节点的节点
    int top = -1;
    
    root = (BTree)malloc(sizeof(struct BNode));
    root->val = s[0];
    stk[++top] = root;

    int i = 1;  // 序列指针 
    while(i < n && top >= 0){
        if(stk[top]->lchild == NULL && s[i-1] != '#'){  // 生成左子节点
            if(s[i] != '#'){
                node = (BTree)malloc(sizeof(struct BNode));
                node->val = s[i];
                stk[top]->lchild = node;
                stk[++top] = node;
            }
            i++;
        }/*else{
            if(stk[top]->rchild == NULL){  // 生成右子节点
                if(s[i] != '#'){
                    node = (BTree)malloc(sizeof(struct BNode));
                    node->val = s[i];
                    stk[top]->rchild = node;
                    stk[++top] = node;
                }
                else{  // 即当前节点左右节点都已生成 栈顶元素出栈
                    stk[top] = NULL;
                    top--;
                }
                i++;
            }else{  // 当前节点左右节点都生成了，栈顶元素出栈
                stk[top] = NULL;
                top--;
            }
        }*/
        else if(stk[top]->rchild == NULL){  // 生成右子节点时 栈顶元素出栈
            BTree tmp = stk[top];
            stk[top] = NULL;
            top--;
            if(s[i] != '#'){
                node = (BTree)malloc(sizeof(struct BNode));
                node->val = s[i];
                tmp->rchild = node;
                stk[++top] = node;
            }
            i++;
        }
    }
    return root;
}

BTree CreateBTreeNode(const char* s, int index)  // 层次递归生成二叉树
{
    if(s == NULL || index >= strlen(s) || s[index-1] == '#'){
        return NULL;
    }
    BTree newNode = (BTree)malloc(sizeof(struct BNode));
    newNode->val = s[index-1];
    newNode->lchild = CreateBTreeNode(s, 2 * index);
    newNode->rchild = CreateBTreeNode(s, 2 * index + 1);
    return newNode;
}

void PreOrder(BTree root)
{
    if(root){
        printf("%c",root->val);
        PreOrder(root->lchild);
        PreOrder(root->rchild);
    }else{
        printf("#");
    }
}

void DestoryBinaryTree(BTree root)
{
    if(root){
        if(root->lchild){
            DestoryBinaryTree(root->lchild);
        }
        if(root->rchild){
            DestoryBinaryTree(root->rchild);
        }
        free(root);
    }
    root = NULL;
}
