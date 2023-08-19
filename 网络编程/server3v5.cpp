#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <signal.h>
#include <pthread.h>

/**
 * 使用教程示例演示 与client3v4配合使用
 * 使用Linux多线程的互斥锁，实现服务端的多个线程与客户端通信
 * 编译时加上参数 -lpthread
**/

class MyServer{
public:
  int ser_sock;	//服务端用于监听的socket
  int cli_sock;	//客户端用于连接的socket

  MyServer();
  
  bool InitServer(int port);	//初始化服务端
  
  bool Accept();	//等待客户端的连接

  int Send(const void *buf, const int bufsize);	//发送报文
  int Recv(void *buf, const int bufsize);	//接收报文

  //多线程服务端不需要以下两个函数
  //void CloseClient();	//关闭客户端的socket
  //void CloseListen();	//关闭用于监听的socket

  ~MyServer();
};

MyServer msv;

//SIGINT和SIGTERM的处理函数
void EXIT(int sig)
{
	printf("程序退出，信号值=%d\n",sig);

	close(msv.ser_sock);	//关闭服务端用于监听的socket
	//此处添加关闭客户端的socket的代码
	close(msv.cli_sock);	//演示案例 只有一个客户端连接
	
	exit(0);
}

//与客户端通信线程的主函数
void *pth_main(void* arg);

int main(int argc, char *argv[])
{
    //忽略子进程退出的信号，避免产生僵尸进程
    signal(SIGCHLD,SIG_IGN);
	
	//设置SIGINT与SIGTERM的处理函数
	signal(SIGINT,EXIT);  signal(SIGTERM,EXIT);

    if( msv.InitServer(6000) == false ){
      printf("初始化服务失败...\n");  
      return -1;
    }

    //与客户端通信，接收客户端发送的报文，然后回复
    while( 1 ){
      
       //服务端等待客户端连接
       if( msv.Accept() == false){
           //printf("MyServer.Accept() failed...\n");
           continue;
       }

	   pthread_t pthid; //创建线程用于与新连接的客户端通信
	   if( pthread_create(&pthid, NULL, pth_main, (void*)((long)msv.cli_sock)) != 0)
	   { printf("创建线程失败，程序退出!\n"); return -1; }

	   //将新创建的线程设为detached状态，防止僵尸线程产生
	   //pthread_detach(pthid);
	   //pthread_join(pthid, NULL);

	   printf("与客户端通信的线程已创建。\n");
	}
}

//构造函数初始化socket
MyServer::MyServer()
{
    ser_sock = 0;
    cli_sock = 0;
}

//析构函数关闭socket
MyServer::~MyServer()
{
    if(ser_sock != 0)
      close(ser_sock);
    if(cli_sock != 0)
      close(cli_sock);
}

//初始化服务端的socket 指定端口号port  IP地址为本机任意
bool MyServer::InitServer (int port)
{
    //1、创建服务端的socket
    if( (ser_sock = socket(AF_INET,SOCK_STREAM,0)) == -1)
    {
       perror("socket");   return false;   
    }

    //2、将服务端用于通信的地址和端口绑定到socket
    struct sockaddr_in saddr;	//服务端地址信息的数据结构
    memset(&saddr,0,sizeof(saddr));
    saddr.sin_family = AF_INET;	//协议族，socket编程中只能是AF_INET
    saddr.sin_addr.s_addr = htonl(INADDR_ANY); //任意IP地址
    //saddr.sin_addr.s_addr = inet_addr("192.168.88.100");  //指定IP地址
    //指定通信端口
    saddr.sin_port = htons(port);
    
    if( (bind(ser_sock,(struct sockaddr *)&saddr,sizeof(saddr))) != 0)
    {
        close(ser_sock);  cli_sock = 0;  
        return false;
    }

    //3、把socket设置为监听模式
    if(listen(ser_sock,5) != 0){
       perror("listen");  close(ser_sock);  cli_sock = 0;
       return false;
    }

    return true;
}

bool MyServer::Accept()
{
    //4、接收客户端的连接
    if( (cli_sock = accept(ser_sock,0,0)) <= 0 )
       return false;

    return true;
}

int MyServer::Send(const void *buf, const int bufsize)
{
    return send(cli_sock,buf,bufsize,0); 
}

int MyServer::Recv(void *buf, int bufsize)
{
    return recv(cli_sock,buf,bufsize,0);
}

//void MyServer::CloseClient()
//{
//    if( cli_sock != 0){
//        close(cli_sock);
//        cli_sock = 0;
//    }
//}
//
//void MyServer::CloseListen()
//{
//    if( ser_sock != 0){
//        close(ser_sock);
//        ser_sock = 0;
//    }
//}

//与客户端通信线程的主函数，参数arg是客户端的socket
void *pth_main(void* arg)
{
	int client_sock = (long) arg;
	printf("客户端socket=%d\n",client_sock);

	char buf[1024];

	while(1)
	{
		memset(buf, 0, sizeof(buf));
		//if( recv(client_sock, buf, sizeof(buf), 0) <= 0) break;
		if( msv.Recv(buf, sizeof(buf)) <= 0) break;
		printf("接收的消息：%s\n",buf);

		strcpy(buf, "OK!");
		//if( send(client_sock, buf, strlen(buf), 0) <= 0) break;
		if( msv.Send(buf, sizeof(buf)) <= 0) break;
		printf("发送的消息：%s\n",buf);
	}

	printf("客户端已断开连接...\n");

	close(client_sock);

	pthread_exit(0);
}
