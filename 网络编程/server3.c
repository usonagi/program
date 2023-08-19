#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <netdb.h>

/**
 * socket通信——服务端  需要输入端口号
 * 将准备工作封装成函数
**/

//初始化服务
int InitServer (int port)
{
    //1、创建服务端的socket 
    int lsfd ;//= socket(AF_INET,SOCK_STREAM,0);
    if( (lsfd = socket(AF_INET,SOCK_STREAM,0)) == -1)
    {
       perror("socket");   return -1;   
    }

    //2、将服务端用于通信的地址和端口绑定到socket
    struct sockaddr_in saddr;	//服务端地址信息的数据结构
    memset(&saddr,0,sizeof(saddr));
    saddr.sin_family = AF_INET;	//协议族，socket编程中只能是AF_INET
    //saddr.sin_addr.s_addr = htonl(INADDR_ANY); //任意IP地址
    saddr.sin_addr.s_addr = inet_addr("192.168.88.100");  //指定IP地址
    //指定通信端口
    saddr.sin_port = htons(port);
    //saddr.sin_port = htons(999);
    if( (bind(lsfd,(struct sockaddr *)&saddr,sizeof(saddr))) != 0)
    {
       perror("bind");  close(lsfd);  return -1;
    }

    //3、把socket设置为监听模式
    if(listen(lsfd,5) != 0){
       perror("listen");  close(lsfd);  return -1;
    }

    return lsfd;

}

int main(int argc, char *argv[])
{
    if( argc != 2){
      printf("please use port example:./server 5000 \n");
      return -1;
    }

    int listen = InitServer(atoi(argv[1]));
    if(listen <= 0){
      printf("初始化服务失败...\n");  return -1;
    }

    //4、接收客户端的连接
    int socklen = sizeof(struct sockaddr_in);
    struct sockaddr_in caddr;	//存储客户端的地址信息
    printf("服务端正在等待客户端连接...\n");
    int client = accept(listen,(struct sockaddr *)&caddr,(socklen_t *)&socklen);
    printf("客户端(%s)已连接。\n",inet_ntoa(caddr.sin_addr));

    //5、与客户端通信，接收客户端发送的报文，然后回复
    char buf[1024];
    while( 1 ){
       int iret;
       memset(buf,0,sizeof(buf));

       //接收客户端的请求报文
       if( (iret = recv(client,buf,sizeof(buf),0)) <= 0 )
       { 
           printf("客户端已关闭... recv函数返回值=%d\n",iret);  break;
       }

       printf("server接收: %s\n",buf);

       //向客户端发送响应报文
       //printf("server用于测试阻塞client的recv函数，取消send函数的发送。\n");
       strcpy(buf,"Hello client! I am server. 欢迎你加入我们...");
       if( (iret = send(client,buf,strlen(buf),0)) <= 0 )
       {
           perror("send"); break;
       }
       printf("server发送: %s\n",buf);
    }

    //6、关闭socket，释放资源
    close(listen);
    close(client);

}
