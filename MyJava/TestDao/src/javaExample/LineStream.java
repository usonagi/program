package javaExample;

import java.util.Scanner ;

public class LineStream {
	InputScore one;
	DelScore two;
	ComputerAver three;
	LineStream(){
		three = new ComputerAver();
		two = new DelScore(three);
		one = new InputScore(two);
	}
	public void giveScore(){
		one.inputscore();
	}
}

class InputScore{
	DelScore del;
	InputScore(DelScore del){
		this.del = del;
	}
	public void inputscore(){
		System.out.println("请输入评委数");
		Scanner read = new Scanner(System.in);
		int count = read.nextInt();
		System.out.println("请输入各个评委的分数");
		double []a= new double[count];
		for(int i = 0;i < count;i++)
			a[i] = read.nextDouble();
		del.doDelete(a);
	}
}

class DelScore{
	ComputerAver caver;
	DelScore(ComputerAver caver){
		this.caver = caver;
	}
	public void doDelete(double [] a){
		java.util.Arrays.sort(a);   //对数组进行从大到小排序
		System.out.println("去掉一个最高分:"+a[a.length-1]+", ");
		System.out.println("去掉一个最低分:"+a[0]+". ");
		double b[] = new double[a.length-2];
		for(int i = 1;i < a.length-1;i++)  //去掉最高分和最低分
			b[i-1] = a[i];
		caver.giveAver(b);
	}
}

class ComputerAver{
	public void giveAver(double [] b){
		double sum = 0;
		for(int i = 0;i < b.length;i++)
			sum += b[i];
		double aver = sum / b.length;
		System.out.println("选手最后得分"+aver);
	}
}
