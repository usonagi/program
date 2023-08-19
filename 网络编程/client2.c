#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <netdb.h>

/**
 * socket通信——客户端   需要输入服务端地址和端口号
 * 客户端向服务端发送消息，发送”bye”结束本次对话
 * */

int main(int argc, char* argv[])
{
    if(argc != 3){
       printf("please use ip and port,example:./client 127.0.0.1 5000\n ");
       return -1;
    }

    //1、创建客户端的socket
    int csfd ; //= socket(AF_INET,SOCK_STREAM,0);
    if( (csfd = socket(AF_INET,SOCK_STREAM,0)) == -1) 
    {
       perror("socket");  return -1;
    }
//printf("1...csfd=%d\n",csfd);

    //2、向服务器发起连接请求
    struct hostent* ht;
    if( (ht = gethostbyname(argv[1])) == 0 ){ //指定服务端的IP地址
       printf("gethostbyname failed\n");
       close(csfd);  return -1;
    }

    struct sockaddr_in saddr;
    memset(&saddr,0,sizeof(saddr));
    saddr.sin_family = AF_INET;
    saddr.sin_port = htons(atoi(argv[2]));  //指定服务端的通信端口
    memcpy(&saddr.sin_addr,ht->h_addr,ht->h_length);

    printf("连接的IP地址和端口号: %d, %d\n",saddr.sin_addr,saddr.sin_port);
    printf("服务端IP:%s, 服务端的端口号:%s \n",argv[1],argv[2]);

    //向服务器发起连接请求
    int conn ; //=  connect(csfd,(struct sockaddr *)&saddr,sizeof(saddr));
//printf("connet : %d\n",conn);
    if( (conn = connect(csfd,(struct sockaddr *)&saddr,sizeof(saddr))) != 0)
    {
       perror("connect");  close(csfd);  return -1;
    }
//printf("2....\n");

    //3、与服务端进行通信
    char buf[1024];
    int i = 0;
    while( strcmp(buf,"bye") != 0){
       int iret;
       memset(buf,0,sizeof(buf));

       //向服务端发送报文
       printf("client: ");
       gets(buf);  //使用gets函数会出警告
       //scanf("%s",&buf);

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
       printf("server：%s\n",buf);
    }

    //4、关闭socket，释放资源
    close(csfd);
    return 0;
}
