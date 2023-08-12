package Work;

/******** 教材课后习题 ***********/

class TestStatic{
	static {
		System.out.println("我是TestStatic中的静态块");
	}
}

public class HomeWork {
	
	public static void f(int ... x)  //x是可变参数的代表
	{
		for(int i=0;i<x.length;i++)  //x.length是参数的个数
			System.out.print(x[i]+" ");  //第i个参数
	}
	
	
//	static {
//		System.out.println("我是最先执行的静态块");
//	}
	
	public static void main(String args[]) {
		/*Son son = new Son();
		// son.money = 300;  //不能使用父类的private 成员
		//son.height = 1.78F;  //float型height被隐藏
		son.height = 178;*/
		
		/*Fish redFish = new Fish();
		System.out.println(redFish.weight);
		Lake lake = new Lake();
		lake.setFish(redFish);
		lake.foodFish(120);
		System.out.println(redFish.weight);
		System.out.println(lake.fish.weight);*/
		
		/*Bsum b1 = new Bsum(),b2 = new Bsum();
		b1.setN(3);
		System.out.print("b1的n:"+b1.n);
		b2.setN(5);
		System.out.println("  b2的n:"+b2.n);
		
		System.out.print("b1求和前 sum:"+b1.sum);
		int sum1 = b1.getSum();
		System.out.println("  b2求和前 sum:"+b2.sum);
		int sum2 = b2.getSum();
		
		System.out.printf("sum1 = %d, sum2 = %d \n",sum1,sum2);
		System.err.println(sum1+sum2);*/
		
		/*f(1,2);  //可变参数
		f(-1,-2,-3,-4);
		f(9,7,6);*/
		
		/*TestStatic ts = new TestStatic();
		System.out.println("了解静态块");*/
		
		/*CPU cpu = new CPU();
		HardDisk hd = new HardDisk();
		PC pc = new PC();
		cpu.setSpeed(2200);
		hd.setAmount(200);
		pc.setCPU(cpu);
		pc.setHardDisk(hd);
		pc.Show();*/
		
		Simulator simulator = new Simulator();
		simulator.playSound(new Dog());
		simulator.playSound(new Cat("猫"));
		
		
		
	}
}

