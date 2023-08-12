package javaExample;

/******** 定义矩形类 ********/

public class Rectangle extends Geometry{
	double a,b;
	
	Rectangle(double a,double b){
		this.a = a;
		this.b = b;
	}
	
	public double getArea() {  //重写方法
		return a * b;
	}
}
