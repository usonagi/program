#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main()
{   //pid_t getpid()  获得程序运行时的进程编号
    int this_pid = getpid();
    printf("本程序的进程编号:%d\n",this_pid);
    
    //pid_t fork()   产生一个新进程,在父进程中返回子进程的进程编号，在子进程中返回0
    int ipid = fork();
    sleep(1); //等待进程的生成
    printf("生成的进程的pid:%d\n",ipid);

    if( ipid != 0 ){
      printf("当前程序的进程编号:%d  ",getpid());
      printf("子进程的进程编号:%d \n",ipid);
    }
    else{
      printf("当前程序的进程编号:%d  \n",getpid()); 
//      printf("子进程的进程编号:%d\n",getpid());
    }
    sleep(30);  //在shell下使用ps -ef|grep mypro 查看本程序的进程编号

    return 0;
}
