package javaExample;

// 一个包只能有一个public类
public class ExaClass {

}

//Java不支持多重继承 即子类只能有一个父类（超类）

class Goods {
	public double weight;
	
	//在其它类中创建了对象后，该对象不能访问自己的私有变量、调用私有方法
	private int number;   //不能被子类继承，但是可以被子类继承的方法调用
	
	//同包中的对象可以访问友好成员变量及方法，不同包不能访问
	public void oldSetWeight(double w) {
		weight = w;  	 //友好成员变量/方法  不用private、public、protected修饰
		System.out.println("double型的weight="+weight);
	}
	
	public double oldGetPrice() {
		double price = weight * 10;
		return price;
	} 
	
	// 子类继承的方法操作的成员变量一定是被子类 继承 或 隐藏 的成员变量
	public int getNum() {  //子类可以继承该方法，操作未继承的number
		number = 5;
		return number;
	}
	
}
// 子类不继承父类的私有成员变量
class CheapGoods extends Goods {
	
	//protected 同一个包中的其他类有效，不同包的子类也有效
	protected int weight;  //子类隐藏了父类的weight，可以通过super.成员变量调用被隐藏的变量
	
	public void newSetWeight(int w) {
		weight = w;
		System.out.println("int型的weight="+weight);
	}
	
	public double newGetPrice() {
		double price = weight * 10;
		return price;
	}
	
	//方法的名字、参数个数、参数类型和父类的方法完全相同  称为重写方法
	public int getNum() {
		int m = 10;
		return m + super.getNum();  //调用父类的getNum方法访问父类的私有变量
	}
}