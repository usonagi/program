#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>  // 编译时必须指定 -pthread
#include<unistd.h>

/* 生产者-消费者 模型： 生产者产生数据等待消费者使用数据*/

pthread_cond_t cond;
pthread_mutex_t mutex;

struct Data{
    int data;
    struct Data *next;
};
typedef struct Data* Node;

Node List = NULL;

void* producer()
{
    // 生产者生成数据
    while(1){
        pthread_mutex_lock(&mutex);

        Node node = (struct Data*)malloc(sizeof(struct Data));
        node->data = rand()%1000;
        node->next = List;
        List = node;
        printf("producer: thread id is %ld, data is %d\n", pthread_self(), List->data);

        pthread_mutex_unlock(&mutex);
        pthread_cond_broadcast(&cond);  // 唤醒消费者线程
        sleep(1);
    }
    return NULL;
}

void* consumer()
{
    // 消费者使用数据
    while(1){
        pthread_mutex_lock(&mutex);

        while(!List){
            // 阻塞当前进程，等待被唤醒 同时释放互斥量防止死锁(生产者与消费者共用同一互斥量)
            pthread_cond_wait(&cond, &mutex);
        }
        printf("consumer: thread id is %ld, data is %d\n", pthread_self(), List->data);
        Node tmp = List;
        List = List->next;
        free(tmp);  // 删除结点 释放内存

        pthread_mutex_unlock(&mutex);
        pthread_cond_broadcast(&cond);
        sleep(1);
    }
    return NULL;
}

int main()
{
    pthread_mutex_init(&mutex, NULL);  // 初始化互斥量
    pthread_cond_init(&cond, NULL);    // 初始化条件变量

    // 创建生产者与消费者进程
    const int thread_num = 5;
    pthread_t per[thread_num], cer[thread_num];
    for(int i = 0; i < thread_num; i++){
        pthread_create(&per[i], NULL, producer, NULL);
    }
    for(int i = 0; i < thread_num; i++){
        pthread_create(&cer[i], NULL, consumer, NULL);
    }

    for(int i = 0; i < thread_num; i++){
        pthread_join(per[i], NULL);
    }
    for(int i = 0; i < thread_num; i++){
        pthread_join(cer[i], NULL);
    }
    pthread_mutex_destroy(&mutex);  // 销毁互斥量
    pthread_cond_destroy(&cond);    // 销毁条件变量
//    return 0;
}
