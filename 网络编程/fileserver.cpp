#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>
#include<stdlib.h>
#include<signal.h>

/**
 * 文件传输模块——服务端  默认端口号6600
 * 使用Linux多进程，实现服务端多进程，与fileclient.cpp配合
 * fork()在父进程中返回pid，在子进程中返回0.
 * 1、等待客户端的连接，连接后fork一个子进程。
 * 2、子进程接收客户端的文件信息，进行解析。
 * 3、接收文件的内容，向客户端发送接收结果
**/

class MyServer{
private:
  int ser_sock;	//服务端用于监听的socket
  int cli_sock;	//客户端用于连接的socket

public:
  MyServer();
  
  bool InitServer(int port);	//初始化服务端
  
  bool Accept();	//等待客户端的连接

  int Send(const void *buf, const int bufsize);	//发送报文
  int Recv(void *buf, const int bufsize);	//接收报文

  ~MyServer();
};

MyServer msv;

void _fileserver();

void EXIT(int );


int main(int argc, char *argv[])
{

	if(argc != 2)
	{
		printf("please use fileserver filepath, example: ./fileserver /home/hello.c");
		return -1;
	}

    //屏蔽所有信号
    for(int i=0; i < 100; i++)
       signal(i,SIG_IGN);
    //设置需要捕获的信号
    signal(SIGINT,EXIT);  signal(SIGTERM,EXIT);

    if( msv.InitServer(6600) == false ){
      printf("初始化服务失败...\n");  
      return -1;
    }

    //与客户端通信，接收客户端发送的报文，然后回复
    while( 1 ){
      
printf("服务端等待客户端连接...\n");
       //服务端等待客户端连接
       if( msv.Accept() == false){
           printf("MyServer.Accept() failed...\n");
           continue;
       }
printf("客户端成功连接...\n");

       //生成子进程与当前客户端通信,父进程继续循环
       if(fork() == 0){

		   //子进程关闭ser_sock,直到客户端断开连接
		   //close(ser_sock);

		   printf("服务端等待接收客户端的报文...\n");

		   //进入服务端文件传输函数
		   _fileserver();

		   exit(0);
	   }
	   //父进程关闭cli_sock，继续循环
	   //close(msv.cli_sock);
	}

	return 0;
}

void _fileserver()
{
    char msg[1024];

	memset(msg, 0, sizeof(msg));

	//接收客户端的请求报文，与客户端通信
    if( msv.Recv(msg,sizeof(msg)) <= 0 )
	{ 	printf("MyServer.Recv() failed...\n");	return ;  }

	printf("server接收: %s\n",msg);

	//接收客户端发送的文件内容
	
			   
	//向客户端发送响应报文
	strcpy(msg,"<result>0</result><message>ok</message>");
	if( msv.Send(msg,strlen(msg)) <= 0 )
	{   printf("MyServer.Send() failed...\n");	return ;  }

	printf("server发送: %s\n",msg);
	
	return ;
}

void EXIT(int sig)
{
    printf("程序接收到信号%d...\n",sig);

    //kill(pid,sig),pid是进程编号，sig是信号值
    //成功执行返回0，失败返回-1
    int ret;
//    kill(0,15);	  //pid=0,将信号sig传送给进程组中的所有进程，即父进程与子进程
    //kill(4417,15);   //终止4417进程
//    ret = kill(0,80);
//    if(ret == -1)
//       perror("kill");

    exit(0);
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
