package Work;

public abstract class Animal {  //抽象类  非abstract类不能有abstract方法
	
	//不能使用new创建abstract类的对象
	//可以声明对象，该对象可以成为其子类对象的上转型对象
	
	//  不能使用final和abstract同时修饰一个类或方法
	public abstract void Cry();  //抽象方法  只能声明不允许实现  必须是实例方法（不能使用static修饰）
	public abstract String getAnimalName();
}

class Simulator {  //模拟器
	void playSound(Animal am){
		System.out.println("现在播放"+am.getAnimalName()+"类的声音");
		am.Cry();
	}
}

//一个非abstrct类若是abstract类的子类，则必须重写父类中所有的abstract方法

/*class Dog extends Animal{  //可行的
	public void Cry(){
		System.out.println("汪汪...汪汪!");
	}
	public String getAnimalName(){
		return "狗";
	}
}*/
class Cat extends Animal {  
	String name;  //种类名
	Cat(String s){  //定义构造方法
		name = s;
	}
	public void Cry(){    		//重写abstract方法
		System.out.println("喵喵...喵喵!");
	}
	public String getAnimalName(){   //重写abstract方法
		return  name; //"猫";
	}
}