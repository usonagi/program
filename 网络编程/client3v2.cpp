#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>

/* socket通信——客户端  默认服务端地址和端口号:192.168.88.100 6000
 * 将连接服务端的准备封装成类的成员
 * 用于演示多进程通信的客户端  与server3v2/3使用
 * */

class MyClient
{
private:
    int cli_sock;
  
public:
    MyClient();

    //向服务端发起连接，serviceip是服务端ip，port通信端口
    bool ConnectToServer(const char* serverip, const int port);

    //向服务端发送消息
    int Send(const void *buf, const int bufsize);

    //接收服务端的报文
    int Recv( void *buf, int bufsize);

    ~MyClient();

};


int main(int argc, char* argv[])
{
    /*if(argc != 3){
       printf("please use ip and port,example:./client 127.0.0.1 5000\n ");
       return -1;
    }*/

    MyClient mc;

    if(mc.ConnectToServer("192.168.88.100",6000) == false){
       printf("连接服务端失败...\n");  return -1;
    }

    //3、与服务端进行通信
    char buf[1024];
    int i = 0;
    for(i; i < 20; i++){
       
       memset(buf,0,sizeof(buf));
       sprintf(buf,"Hello server! I am client. 这是第%d条信息...",i+1);

       //向服务端发送报文
       printf("client发送：%s\n",buf);

       //阻止客户端报文的发送，查看服务端信号对recv的影响
       //if( mc.Send(buf,strlen(buf)) <= 0 )
       //{
       //   printf("MyClient.Send() falied...\n");  break;
       //}

       //接收服务端的回应报文
       memset(buf,0,sizeof(buf));
       if( mc.Recv(buf,sizeof(buf)) <= 0 )
       {
          printf("MyClient.Recv() falied...\n");  break;
       }
       printf("client接收：%s\n",buf);
	   
	   sleep(1);  //用于观察程序运行状况
    }

    return 0;
}

MyClient::MyClient()
{
    cli_sock = 0;
}

MyClient::~MyClient()
{
    if(cli_sock != 0)
       close(cli_sock);
}

//连接服务端 IP地址  端口号
bool MyClient:: ConnectToServer(const char* sip,const int port)
{
    //1、创建客户端的socket
    if( (cli_sock = socket(AF_INET,SOCK_STREAM,0)) == -1) 
    {
	printf("cli_sock  error !");
        return -1;
    }

    //2、向服务器发起连接请求
    struct hostent* ht;
    if( (ht = gethostbyname(sip)) == 0 ){ //指定服务端的IP地址
       printf("gethostbyname failed\n");
       cli_sock = 0;  return false;
    }

    struct sockaddr_in saddr;
    memset(&saddr,0,sizeof(saddr));
    saddr.sin_family = AF_INET;
    saddr.sin_port = htons(port);  //指定服务端的通信端口
    memcpy(&saddr.sin_addr,ht->h_addr,ht->h_length);

    //向服务器发起连接请求
    if( connect(cli_sock,(struct sockaddr *)&saddr,sizeof(saddr)) != 0)
    {
       perror("client connect server failed...\n");  
       cli_sock = 0;  return false;
    }
    
    return true;
}

int MyClient::Send(const void *buf, const int bufsize)
{
    return send(cli_sock,buf,bufsize,0);
}

int MyClient::Recv(void *buf, int bufsize)
{
    return recv(cli_sock,buf,bufsize,0);
}
