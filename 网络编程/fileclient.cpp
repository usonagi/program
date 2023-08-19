#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <sys/stat.h>
#include <time.h>

/* 文件传输模块——客户端  默认服务端地址和端口号:192.168.88.100 6600
 * 用于与多进程服务端进行文件传输  与fileserver.cpp使用
 * 1、与服务器创建socket连接，将文件信息发送给服务端。
 *	（文件信息包括文件名、文件大小、文件时间，格式为xml）
 * 2、将文件内容发送给服务端，等待服务端的接受结果。
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

//定义文件信息 (考虑使用结构体?)
char filename[301];	//文件名
int filesize;			//文件大小
char filetime[21];			//文件时间(指文件创建日期)

int GetFileSize(const char*);
bool GetFileTime(const char* Fname, char* Ftime, const char* fmt);
void timetostr(const time_t, char* , const char* );

int main(int argc, char* argv[])
{
    if(argc != 2){
       printf("please use fileclient filename,example:./client hello.c\n ");
       return -1;
    }

	//1、获取文件信息
	strcpy(filename,argv[1]);	//拷贝文件名
	//读取文件时间
	if( GetFileTime(filename, filetime, "yyyymmddhh24miss")== false) 
	{ printf("文件%s不存在或没有访问权限!\n",filename); return -1; }
	//读取文件大小
	filesize = GetFileSize(filename);
	
	printf("filename=%s,filesize=%d,filetime=%s\n",filename,filesize,filetime);

    MyClient mc;

	//2、连接服务器，建立socket
    if(mc.ConnectToServer("192.168.88.100",6600) == false){
       printf("连接服务端失败...\n");  return -1;
    }

    //3、与服务端进行通信,传输文件信息与内容
    char filebuf[1024];
	//用xml格式保存文件信息
    sprintf(filebuf,"<filename>%s</filename><size>%d</size><mtime>%s</mtime>",
			filename,filesize,filetime);
    //memset(filebuf,0,sizeof(filebuf));

    //向服务端发送报文
    printf("client发送：%s\n",filebuf);

    if( mc.Send(filebuf,strlen(filebuf)) <= 0 )
    {
       printf("MyClient.Send() falied...\n");  return -1;
    }

	//3、向服务器发送文件内容
	//if() return -1;	//打开文件

	//int readsize;
	//while( true )
	//{
	//	memset(filebuf, 0, sizeof(filebuf));
	//	if( (readsize = read()) <= 0)	break;
	//	if(write() == false)	return -1;
	//}

    //4、接收服务端的回应报文
    memset(filebuf,0,sizeof(filebuf));
    if( mc.Recv(filebuf,sizeof(filebuf)) <= 0 )
    {
        printf("MyClient.Recv() falied...\n");  return -1;
    }
       printf("client接收：%s\n",filebuf);

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

/* 获取文件的大小。
 * filename：待获取的文件名，建议采用绝对路径的文件名。
 * 返回值：如果文件不存在或没有访问权限，返回-1，成功返回文件的大小，单位是字节。
 */
int GetFileSize(const char *filename)
{
   struct stat st_filestat;
   
   if (stat(filename,&st_filestat) < 0) return -1;
   
   return st_filestat.st_size;
}

/* 获取文件的时间。
 filename：待获取的文件名，建议采用绝对路径的文件名。
 mtime：用于存放文件的时间，即stat结构体的st_mtime。
 fmt：设置时间的输出格式，与LocalTime函数相同，但缺省是"yyyymmddhh24miss"。
 返回值：如果文件不存在或没有访问权限，返回false，成功返回true。
 */
bool GetFileTime(const char *filename,char *mtime,const char *fmt)
{
   // 判断文件是否存在。
   struct stat st_filestat;

   if (stat(filename,&st_filestat) < 0) return false;
   
   char strfmt[25];
   memset(strfmt,0,sizeof(strfmt));
   if (fmt == 0) strcpy(strfmt, "yyyymmddhh24miss");
   else strcpy(strfmt, fmt);

   timetostr(st_filestat.st_mtime, mtime, strfmt);
   
   return true;
}

/* 把整数表示的时间转换为字符串表示的时间。
 ltime：整数表示的时间。
 stime：字符串表示的时间。
 fmt：输出字符串时间stime的格式，与LocalTime函数的fmt参数相同，如果fmt的格式不正确，stime将为空。
 */
 void timetostr(const time_t ltime,char *stime,const char *fmt)
{
   if (stime==0) return;
   
   strcpy(stime,"");
   
   struct tm sttm = *localtime ( &ltime );

   sttm.tm_year = sttm.tm_year + 1900;
   sttm.tm_mon ++;

   if(fmt == 0)
   {
	   snprintf(stime,20,"%04u-%02u-%02u %02u:%02u:%02u",sttm.tm_year,sttm.tm_mon,
			   sttm.tm_mday,sttm.tm_hour,sttm.tm_min,sttm.tm_sec);
	   return ;
   }

   if (strcmp(fmt,"yyyymmddhh24miss") == 0)
   {
	   snprintf(stime,15,"%04u%02u%02u%02u%02u%02u",sttm.tm_year,sttm.tm_mon,sttm.tm_mday,
			   sttm.tm_hour,sttm.tm_min,sttm.tm_sec);
	   return;
	}

}

