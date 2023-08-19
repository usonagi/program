#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <wait.h>
#include <string.h>

/* 文件功能：拷贝文件到window共享目录下
 * Windows下共享目录路径：E:\\share7
 * Linux下挂载点路径：/mnt/hgfs
 * 文件拷贝命令：cp -r 文件路径 目标文件路径
 * */

const char* windows_share_path = "E:\\share7";
const char* linux_share_path = "/mnt/hgfs";

int main(int argc, const char* argv[])
{
    int pid;
    int childpid;
    char* src_path = NULL;
    char* dst_path = "/mnt/hgfs";

    int i = 0;
    if(argc > 0){
        for(i; i < argc; i++){
            printf("argv index %d value is %s\n", i, argv[i]);
        }
    }
    if(argc >= 2){
        memcpy(src_path, argv[1], strlen(argv[1])+1);
        if(argc >= 3){
            memcpy(dst_path, argv[2], strlen(argv[2])+1);
        }
    }else{
        src_path = getcwd(NULL, 0);  // 获取当前目录路径 需要手动free返回值
    }
    printf("src:<%s>; dst:<%s>\n", src_path, dst_path);

    // execl()四个参数：文件的绝对路径、标识(文件名)、参数、NULL--标识参数结束
    //printf("execl output:\n");
    if((pid = fork()) < 0){
        printf("main process fork failed.\n");
        exit(errno);
    }else if(pid == 0){
        // 子进程执行shell命令
        //if(execl("/bin/ls", "ls", "/home/xmy/demo", (char*)0) < 0){
        //if(execl("/bin/ls", "ls", "/home/xmy/demo", ">", "./temp.txt", (char*)0) < 0){  // 错误 重定向'>'被看作文件名
        if(execl("/bin/cp", "cp", "-r", src_path, dst_path, (char*)0) < 0){
            perror("execl execute error");
            exit(errno);
        }
    }else{
        // 主进程等待子进程执行结束
        wait(&childpid);
        printf("subprocess execute completed!\n");
    }

    // system(const char*) 调用/bin/sh执行shell命令
    /*printf("system output:\n");
    if((system("ls /home/xmy") < 0) && (errno != 0)){
        printf("execute command may be failed.\n");
        perror("system");
    }*/
    /*const char* cmd_copy_file = "cp -r /home/xmy/demo /mnt/hgfs";
    if((system(cmd_copy_file) < 0)){
        printf("execute command may be failed.\n");
    }*/

    free(src_path);
    return 0;
}
