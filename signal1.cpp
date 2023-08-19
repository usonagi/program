#include<stdio.h>
#include<unistd.h>
#include<signal.h>
#include<stdlib.h>

/**
 *  程序用于演示信号通知后台服务程序退出
 * */

void EXIT(int sig)
{
    printf("EXIT接收信号%d，退出程序。\n",sig);
    exit(0);
}

int main()
{
   for(int i=0; i < 100; i++)
      signal(i,SIG_IGN);	//屏蔽全部的信号

   //设置需要接受的信号,并使用自定义处理函数EXIT
   signal(SIGINT,EXIT);
   signal(SIGTERM,EXIT);
   
   //SIGKILL强制杀死程序 即执行kill -9  无法被程序捕获
   signal(SIGKILL,EXIT);

   while(1)
      sleep(5);

   return 0;
}
