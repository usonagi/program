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
	//����̳еķ����������ĳ�Ա����һ���Ǳ� ���� �̳� �� ���� �ĳ�Ա����
	int height ;  //�븸�����ͬ�������ؼ̳еĳ�Ա����height
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
	static int sum = 0;  //�����
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
		System.out.println("CPU���ٶ�: "+cpu.getSpeed());
		System.out.println("HardDisk������: "+HD.getAmount());
	}
}
