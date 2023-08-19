GCC=/usr/local/gcc920/bin/g++
Version=c++17
GDB=-g

filename=template_program
sourcefile=$(filename).cpp
resultfile=$(filename).out

$(resultfile):$(sourcefile)
		$(GCC) $(GDB) -std=$(Version) -o $(resultfile) $(sourcefile)

# 可以不写clean 本makefile不生成中间文件
.PHONY:clean
clean:
	-rm -f -*.o

