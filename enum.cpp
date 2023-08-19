#include<iostream>
#include<typeinfo>

// enum成员的可见范围被提升至该枚举类型所在的作用域内，enum class限定作用域在枚举名下。

enum PrimaryColors{
    RED,  // 每个元素用逗号分开，最后一个元素可省略逗号
    GREEN,
    BULE,
};  // 使用分号

enum Colors : uint8_t{  // 指定数据底层类型
    //GREEN,  // 错误 重声明
    BLACK,
    //WHILTE = -1,  // 错误 指定数据类型为uint8_t
};

enum class Student{
    NO,
    NAME,
};

enum class PersonalInfo : uint8_t{
    GREEN,  // 可以
    NAME,  // enum class 可以
    GENDER,
    ADDRESS,
};

int main()
{
    int red = RED;  // enum成员可以转换为整型
    if(red == 0){
        std::cout << "color is Red!" << std::endl;
    }
    //BLACK = 1;  // 错 不能直接赋值给枚举成员
    //BLACK = (enum Colors)1;  // 错
    std::cout << sizeof(RED) << " " << sizeof(BLACK) << std::endl;  // 4 1

    Student stu = Student::NAME;
    std::cout << "value of BLACK: " << BLACK << std::endl;
    Student x = Student::NAME;  // 访问enum class成员必须使用作用域限定符
    //int num = Student::NO;  // 错 enum class成员不能隐式转换为整型,可以强制转换。
    int num = (int)Student::NO;
    num = static_cast<int>(Student::NAME);

    return 0;
}
