#include <stdio.h>
#include <stdlib.h>  // malloc
#include <sys/mman.h>

/* gdb学习示例 */

#define MMU_PAGESIZE 4096
#define VOS_ALIGN(addr,boundary) (((unsigned long int)(addr)+(boundary)-1)&~((unsigned long int)((boundary)-1)))
#define VOS_TRUNCATE(addr,boundary) (((unsigned long int)(addr))&~((unsigned long int)((boundary)-1)))

unsigned int uiLen = 0x2345;  // 9029
void *paddr = NULL;

void alloc_test();
void mprotect_test();
void write_test();

int main()
{
    mprotect_test();
    write_test();

    if(paddr){
        free(paddr);
    }

    return 0;
}

void alloc_test()
{
    paddr = (void*)malloc(sizeof(void) * uiLen);
    if(paddr == NULL){
        printf("paddr memory request failed.\n");
        exit(-1);
    }
}

void mprotect_test()
{
    alloc_test();
    unsigned long int uvBeginAddr = VOS_ALIGN(paddr, MMU_PAGESIZE);
    unsigned long int uvEndAddr = VOS_TRUNCATE(paddr+uiLen, MMU_PAGESIZE);
    (void)mprotect(uvBeginAddr, uvEndAddr-uvBeginAddr, 1);
    printf("paddr=%p, mprotect:%p--%p\n", paddr, uvBeginAddr, uvEndAddr);
}

void write_test()
{
    void *addr = paddr + 2000;
    printf("修改前地址%p的值为：%ld\n", addr, *((int*)addr));
    *((int*)addr) = -0xff;  // -255
    printf("修改后地址%p的值为：%d\n", addr, *((int*)addr));
}
