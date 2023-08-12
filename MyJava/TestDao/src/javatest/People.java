package javatest;

public class People {
		int height;
		String ear;
		void speak(String s){
			System.out.print(s);
		}
		double computerArea(Circle c){  //重载方法
			double area = c.getArea();
			return area;
		}
		double computerArea(Lader l){  //重载方法
			double area = l.getArea();
			return area;
		}
		
}

class A{
	int m;
	int getM(){
		return m;
	}
	int seeM(){
		return m;
	}
}
class B extends A{
	int m;
	int getM(){
		return m+100;
	}
}

class Cry {
	public void cry() {
		System.out.println("大家好！");
	}
	
}

