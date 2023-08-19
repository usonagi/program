/**
 *此程序用于测试fork产生的子进程与父进程的执行流程
 * */

#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

int main()
{
  int curr = 0;
  printf("curr的地址:%d\n",&curr);
  int cpid = fork(); 

  printf("当前程序的pid:%d\n",getpid());
  
  if(cpid != 0){
    //sleep(1);
    printf("进入父进程的if语句...\n");
    printf("当前程序curr变量的内存地址:%d",&curr);
    curr ++;
  }else {
    //sleep(1);
    printf("进入子进程的if语句...\n");
    printf("当前程序curr变量的内存地址:%d",&curr);
    curr += 10;
  }

  printf("当前程序执行后变量curr的值:%d \n",curr);
  sleep(2);
  return 0;
}
