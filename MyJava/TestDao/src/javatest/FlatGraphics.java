package javatest;

//  ƽ��ͼ����

public class FlatGraphics {
   
}

class Rect{
	 // ������
	double width;
	double height;
	double getArea(){
		double area = width * height;
		return area;
	}
}

class Lader{ //������
	static double above;    
	// �����๲�������  ��������޸�
	static double bottom;  //����� ����ͨ���������á�����������
	double height;    //ʵ������ ֻ��ͨ������������
	
	void setBottom(double b){  //ʵ�������ɲ��� ����� �� ʵ������
		bottom = b;
	}
	
	static void setAbove(double a){  //�෽��(��̬����) ��ͨ������������������
		above = a;
		//�෽�������Բ���ʵ������ ��Ϊ���ഴ������֮ǰ��ʵ��������û�з����ڴ�
		//height = a;  
	}
	
	Lader(double a,double b,double h){  //���캯��  ϵͳ�����ṩĬ�ϵĹ��캯��
		above = a;
		bottom = b;
		height = h;
	}
	double getArea(){
		return (above+bottom)*height/2;
	}
}

class Circle{  //Բ����
	double radius,area;
	
	void SetRadius(double r){
		radius = r;
	}
	
	double getRadius(){
		return radius;
	}
	
	//��������  1�������ĸ�����ͬ 2������������ͬ��ĳ���������Ͳ�ͬ
	//����ͬ����������ʹ�������Ͳ�ͬ��Ҳ���뱣֤������ͬ
	double setRadius(int r){
		radius = r;
		return radius;
	}
	/*int setRadius(int R){  //����
		return R;
	}*/
	/*double setRadius(int R,double r){  //���ط���
		radius = R - r;
		return  radius;
	}
	double setRadius(int R,int r){    //���ط���
		radius = R - r;
		return radius;
	}*/
	
	double getArea(){
		area  = 3.14*radius*radius;
		return area;
	}
}

class Circular{  //Բ׶��
	Circle bottom;  //circle����bottom ��Բ 
	double height;
	void setBottom(Circle c){  //Բ׶�ĵ���һ��circle����
		bottom = c;
	}
	void setHeight(double h){
		height = h;
	}
	double getVolume(){  //�����  �����*��/3
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