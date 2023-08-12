package javaExample;

//   圆类   第5章P131例题
public class Circle extends Geometry {

	double r;
	
	Circle(double r)
	{
		this.r =  r;
	}
	// 非abstract子类 需要重写父类中的abstract方法
	public double getArea() {  //重写getArea方法
		return PI * r * r;
	}
}
