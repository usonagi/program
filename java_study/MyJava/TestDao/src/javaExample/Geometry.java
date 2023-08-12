package javaExample;

/******** 定义抽象类  **********/


public abstract class Geometry {
//   可以使用final修饰类、方法、变量 
//	final类不能被继承，final方法不允许子类重写 
	final double PI = 3.1415;   //final修饰变量PI后  PI是常量
	
	public abstract double getArea();  //抽象方法 只能声明 没有方法体
	
}
