#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

/* 此程序用于演示Linux调用可执行程序*/

int main()
{
	int res;

	//无论函数执行成功与否，execl函数后的代码都不会被执行！
	//res = execl("/bin/ls", "bin/ls", "-1", "/home/xmy", 0);
	//res = execl("/bin/ls", "bin/ls", "-1", "/home/my", 0);
	
	//调用编写的可执行程序
	//res = execl("/home/xmy/Cstudy/hello", "/home/xmy/Cstudy/hello", 0);

	//使用system函数，此函数后的代码会执行
	//res = system("/bin/ls -1 /home/xmy");

	res = system("/home/xmy/Cstudy/hello");

	if( res == -1)
		printf("%d:%s\n",errno,strerror(errno));

	
	printf("程序到此结束!\n");

	return 0;
}
