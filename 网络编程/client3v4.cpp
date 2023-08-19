#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <pthread.h>

/* socket通信——客户端  默认服务端地址和端口号:192.168.88.100 6000
 * 使用Linux多线程的互斥锁，实现服务端与多个客户端通信
 * 与服务端server3v5配合使用
 * 编译时加上参数 -lpthread
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

MyClient mc;

//声明互斥锁
pthread_mutex_t mutex;

//与服务端通信线程的主函数
void *pth_main(void *arg);

int main()
{

    if(mc.ConnectToServer("192.168.88.100",6000) == false){
       printf("连接服务端失败...\n");  return -1;
    }

	//创建锁，使用缺省属性
	pthread_mutex_init(&mutex, 0);
	
	//创建线程
	pthread_t pthd1, pthd2;
	pthread_create(&pthd1, NULL, pth_main, (void*)1);

	//在pth_main中sleep注释时，使程序正常运行，不加则线程2不能执行
	//usleep(10);
	
	pthread_create(&pthd2, NULL, pth_main, (void*)2);

	//等待线程退出
	pthread_join(pthd1, NULL);
	pthread_join(pthd2, NULL);

	//销毁锁
	pthread_mutex_lock(&mutex);

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

void *pth_main(void *arg)
{
	int pthno = (long) arg;	//线程编号

	printf("当前线程是%d\n",pthno);

	//改变线程状态，防止产生僵尸线程
	pthread_detach(pthread_self());

    //与服务端进行通信
    char buf[1024];

	//不加锁的情况下，线程1接收了线程2接收的消息，导致线程2阻塞
    for(int i=0; i < 10; i++){
       
		//加锁
		pthread_mutex_lock(&mutex);

		//向服务端发送报文
		memset(buf, 0, sizeof(buf));
		sprintf(buf,"I am thread %d. 这是第%d条信息...",pthno,i+1);

		if( mc.Send(buf, strlen(buf)) <= 0)
		{ printf("MyClient.Send() failed...\n"); break; }

		printf("client发送：%s\n",buf);

		//接收服务端的回应报文
		memset(buf, 0, sizeof(buf));
		if( mc.Recv(buf,sizeof(buf)) <= 0 )
		{
			printf("MyClient.Recv() falied...\n");  break;
		}
		printf("线程%d接收：%s\n",pthno,buf);

		//释放锁
		pthread_mutex_unlock(&mutex);

		//防止抢占锁,不sleep则出现线1程执行完线程2才执行。
		usleep(200);	

    }

	//线程终止
	pthread_exit(0);
}
