package Work;


public class WorkClass {

}

class Father {
	private int money = 12;
	float height;
	int seeMoney(){
		return money;
	}
}

class Son extends Father {
	//子类继承的方法所操作的成员变量一定是被 子类 继承 或 隐藏 的成员变量
	int height ;  //与父类变量同名，隐藏继承的成员变量height
	int lookMoney() {
		int m = seeMoney();
		return m;
	}
}

class Fish{
	int weight = 1;
}

class Lake {
	Fish fish;
	void setFish(Fish f){
		fish = f;
	}
	
	void foodFish(int m){
		fish.weight += m;
	}
}

class Bsum {
	int n;
	static int sum = 0;  //类变量
	void setN(int n) {
		this.n = n;
	}
	
	int getSum(){
		for(int i = 1;i <= n;i++)
			sum += i;
		return sum;
	}
}

class CPU {
	
	static int speed;
	
	int getSpeed(){
		return speed;
	}
	
	void setSpeed(int m){
		speed = m;
	}
}

class HardDisk {
	
	static int amount;
	
	int getAmount(){
		return amount;
	}
	
	void setAmount(int m){
		amount = m;
	}
	
}

class PC {
	CPU cpu;
	HardDisk HD;
	
	void setCPU (CPU c){
		cpu = c;
	}
	
	void setHardDisk(HardDisk h){
		HD = h;
	}
	
	void Show(){
		System.out.println("CPU的速度: "+cpu.getSpeed());
		System.out.println("HardDisk的容量: "+HD.getAmount());
	}
}
