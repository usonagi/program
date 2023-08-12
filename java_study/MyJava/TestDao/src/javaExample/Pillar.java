package javaExample;

/******** �������� ********/

public class Pillar {
	Geometry bottom;   //�������������
	
	double height;
	
	Pillar (Geometry bottom,double height) {  //���췽��
		this.bottom = bottom;
		this.height = height;
	}

	public double getVolume() {
		if( bottom == null ) {
			System.err.println("û�еף��޷�������� ");
			return -1;
		}
		// bottom���Ե���������д��getArea()
		return bottom.getArea() * height;
	}
}
