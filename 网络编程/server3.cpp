#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>

/**
 * socket通信——服务端  需要输入端口号
 * 封装成类
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


int main(int argc, char *argv[])
{
    /*if( argc != 2){
      printf("please use port example:./server 5000 \n");
      return false;
    }*/

    MyServer msv;

    if( msv.InitServer(6000) == false ){
      printf("初始化服务失败...\n");  
      return -1;
    }

    if( msv.Accept() == false){
       printf("MyServer.Accept() failed...\n");
       return -1;
    }

    char msg[1024];

    //5、与客户端通信，接收客户端发送的报文，然后回复
    while( 1 ){
      
       memset(msg,0,sizeof(msg));

       //接收客户端的请求报文
       if( msv.Recv(msg,sizeof(msg)) <= 0 )
       { 
           printf("MyServer.Recv() failed...\n");  break;
       }

       printf("server接收: %s\n",msg);

       //向客户端发送响应报文
       strcpy(msg,"Hello client! I am server. 欢迎你加入我们...");
       if( msv.Send(msg,strlen(msg)) <= 0 )
       {
           printf("MyServer.Send() failed...\n"); break;
       }
       printf("server发送: %s\n",msg);
    }

    printf("客户端已断开连接...\n");

    return 0;

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
