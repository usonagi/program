package javaExample;

//   Բ��   ��5��P131����
public class Circle extends Geometry {

	double r;
	
	Circle(double r)
	{
		this.r =  r;
	}
	// ��abstract���� ��Ҫ��д�����е�abstract����
	public double getArea() {  //��дgetArea����
		return PI * r * r;
	}
}
