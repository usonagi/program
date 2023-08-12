package javaExample;

/******** 定义柱类 ********/

public class Pillar {
	Geometry bottom;   //声明抽象类变量
	
	double height;
	
	Pillar (Geometry bottom,double height) {  //构造方法
		this.bottom = bottom;
		this.height = height;
	}

	public double getVolume() {
		if( bottom == null ) {
			System.err.println("没有底，无法计算体积 ");
			return -1;
		}
		// bottom可以调用子类重写的getArea()
		return bottom.getArea() * height;
	}
}
