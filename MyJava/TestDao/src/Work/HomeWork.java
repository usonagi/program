package Work;

/******** �̲Ŀκ�ϰ�� ***********/

class TestStatic{
	static {
		System.out.println("����TestStatic�еľ�̬��");
	}
}

public class HomeWork {
	
	public static void f(int ... x)  //x�ǿɱ�����Ĵ���
	{
		for(int i=0;i<x.length;i++)  //x.length�ǲ����ĸ���
			System.out.print(x[i]+" ");  //��i������
	}
	
	
//	static {
//		System.out.println("��������ִ�еľ�̬��");
//	}
	
	public static void main(String args[]) {
		/*Son son = new Son();
		// son.money = 300;  //����ʹ�ø����private ��Ա
		//son.height = 1.78F;  //float��height������
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
		System.out.print("b1��n:"+b1.n);
		b2.setN(5);
		System.out.println("  b2��n:"+b2.n);
		
		System.out.print("b1���ǰ sum:"+b1.sum);
		int sum1 = b1.getSum();
		System.out.println("  b2���ǰ sum:"+b2.sum);
		int sum2 = b2.getSum();
		
		System.out.printf("sum1 = %d, sum2 = %d \n",sum1,sum2);
		System.err.println(sum1+sum2);*/
		
		/*f(1,2);  //�ɱ����
		f(-1,-2,-3,-4);
		f(9,7,6);*/
		
		/*TestStatic ts = new TestStatic();
		System.out.println("�˽⾲̬��");*/
		
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
		simulator.playSound(new Cat("è"));
		
		
		
	}
}

