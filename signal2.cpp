#include<stdio.h>
#include<unistd.h>
#include<sys/ipc.h>
#include<sys/shm.h>
#include<string.h>

//用于学习共享内存的程序

struct student
{
   int no;
   char name[12];
   int age;
};

int main()
{
  struct student st;  

  //1、创建共享内存
  int shmid = -1;	//共享内存标识符
  if( (shmid = shmget((key_t)0x6600, sizeof(struct student)*10, 0640|IPC_CREAT)) == -1)
  {
     printf("shmat failed!\n");  return -1;
  }
  printf("当前共享内存的标识符：%d\n",shmid);

  //2、将共享内存连接到当前进程的地址空间
  //char * shadd = 0;	//指向共享内存的指针,sprintf()需要传入char*型数据
//  char *shadd = (char *)shmat(shmid,0,0);
//  printf("当前共享内存的起始地址:%d\n",&shadd);

//  printf("共享内存写入前:%s\n",shadd);
//  sprintf(shadd,"hello world!当前程序的pid=%d",getpid());
//  printf("共享内存写入后:%s\n",shadd);

  //使用结构体
  struct student *ps = (struct student*)shmat(shmid,0,0);
  int i = 0;
  for(i; i < 5; i ++){
     printf("共享内存写入前:no=%d,name=%s,age=%d\n",(ps+i)->no,(ps+i)->name,(ps+i)->age);
  }
//  for(int i=0; i < 3; i++)
//  {
//     sprintf((ps+i)->name,"李华");
//     (ps+i)->no = i;
//     (ps+i)->age = ps->age+i;
//  }
//
  (ps+3)->no = 1000;
  strcpy((ps+3)->name,"李明");
  (ps+3)->age = 20;

  //ps指向结构st的方法不能正确使用共享内存，只有ps指向共享内存才行。
//printf("ps指向st前，指向的地址%x\n",ps);
//  st.no = 1001;
//  strcpy(st.name,"李华2");
//  st.age = 18;
//  ps = &st;	//ps先后移，然后ps指向st
//printf("ps指向st后，指向的地址%x st的内存地址:%x\n",ps,&st);

  for(int i=0; i < 5; i++)
  {
     printf("当前ps指针指向的内存地址：%x\n",ps);
     printf("结构体写入共享内存后:no=%d,name=%s,age=%d\n",ps->no,ps->name,ps->age);
     ps++;
     //printf("当前ps指针指向的内存地址：%x\n",ps);
  }

  //3、将共享内存从进程分离
  //shmdt(shadd);
  shmdt(ps);

  //4、删除共享内存，删除后此共享内存消失
  if(shmctl(shmid, IPC_RMID, 0) == -1)
  {
      printf("shmctl failed!\n");   return -1;
  }

  return 0;
}
