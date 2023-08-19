#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <netdb.h>

/* socket通信——客户端   需要输入服务端地址和端口号
 * 将连接服务端的准备封装成函数
 * */

//连接服务端 IP地址  端口号
int ConnectToServer(char* sip,const int port)
{
    //1、创建客户端的socket
    int csfd ; //= socket(AF_INET,SOCK_STREAM,0);
    if( (csfd = socket(AF_INET,SOCK_STREAM,0)) == -1) 
    {
	//printf("csfd  error !");
       perror("socket");  return -1;
    }

    //2、向服务器发起连接请求
    struct hostent* ht;
    if( (ht = gethostbyname(sip)) == 0 ){ //指定服务端的IP地址
       printf("gethostbyname failed\n");
       close(csfd);  return -1;
    }

    struct sockaddr_in saddr;
    memset(&saddr,0,sizeof(saddr));
    saddr.sin_family = AF_INET;
    saddr.sin_port = htons(port);  //指定服务端的通信端口
    memcpy(&saddr.sin_addr,ht->h_addr,ht->h_length);

    //向服务器发起连接请求
    int conn ; //=  connect(csfd,(struct sockaddr *)&saddr,sizeof(saddr));
    if( (conn = connect(csfd,(struct sockaddr *)&saddr,sizeof(saddr))) != 0)
    {
       perror("connect");  close(csfd);  return -1;
    }
    
    return csfd;
}

int main(int argc, char* argv[])
{
    if(argc != 3){
       printf("please use ip and port,example:./client 127.0.0.1 5000\n ");
       return -1;
    }

    int csfd =  ConnectToServer(argv[1],atoi(argv[2]));
    if(csfd <= 0){
       printf("连接服务端失败...\n");  return -1;
    }

    //3、与服务端进行通信
    char buf[1024];
    int i = 0;
    for(i; i < 3; i++){
       int iret;
       memset(buf,0,sizeof(buf));
       sprintf(buf,"Hello server! I am client. 这是第%d条信息...",i+1);

       //向服务端发送报文
       printf("client发送：%s\n",buf);

       if( (iret = send(csfd,buf,strlen(buf),0)) <= 0 )
       {
          perror("send");  break;
       }

       //接收服务端的回应报文
       memset(buf,0,sizeof(buf));
       if( (iret = recv(csfd,buf,sizeof(buf),0)) <- 0 )
       {
          printf("iret=%d",iret);  break;
       }
       printf("client接收：%s\n",buf);
    }

    //4、关闭socket，释放资源
    close(csfd);
    return 0;
}
