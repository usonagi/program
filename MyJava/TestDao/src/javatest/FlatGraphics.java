package javatest;

//  平面图形类

public class FlatGraphics {
   
}

class Rect{
	 // 矩形类
	double width;
	double height;
	double getArea(){
		double area = width * height;
		return area;
	}
}

class Lader{ //梯形类
	static double above;    
	// 所有类共享类变量  类变量可修改
	static double bottom;  //类变量 可以通过类名调用、对象名调用
	double height;    //实例变量 只能通过对象名调用
	
	void setBottom(double b){  //实例方法可操作 类变量 和 实例变量
		bottom = b;
	}
	
	static void setAbove(double a){  //类方法(静态方法) 可通过类名、对象名调用
		above = a;
		//类方法不可以操作实例变量 因为在类创建对象之前，实例变量还没有分配内存
		//height = a;  
	}
	
	Lader(double a,double b,double h){  //构造函数  系统不再提供默认的构造函数
		above = a;
		bottom = b;
		height = h;
	}
	double getArea(){
		return (above+bottom)*height/2;
	}
}

class Circle{  //圆形类
	double radius,area;
	
	void SetRadius(double r){
		radius = r;
	}
	
	double getRadius(){
		return radius;
	}
	
	//方法重载  1、参数的个数不同 2、参数个数相同但某个参数类型不同
	//两个同名方法，即使返回类型不同，也必须保证参数不同
	double setRadius(int r){
		radius = r;
		return radius;
	}
	/*int setRadius(int R){  //出错
		return R;
	}*/
	/*double setRadius(int R,double r){  //重载方法
		radius = R - r;
		return  radius;
	}
	double setRadius(int R,int r){    //重载方法
		radius = R - r;
		return radius;
	}*/
	
	double getArea(){
		area  = 3.14*radius*radius;
		return area;
	}
}

class Circular{  //圆锥类
	Circle bottom;  //circle对象bottom 底圆 
	double height;
	void setBottom(Circle c){  //圆锥的底是一个circle对象
		bottom = c;
	}
	void setHeight(double h){
		height = h;
	}
	double getVolume(){  //求体积  底面积*高/3
		if(bottom == null)
			return -1;
		else
			return bottom.getArea()*height/3.0;
	}
	double getBottomRadius(){
		return bottom.getRadius();
	}
	public void setBottomRadius(double r){
		bottom.SetRadius(r);
	}
}