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
		System.out.println("��������ί��");
		Scanner read = new Scanner(System.in);
		int count = read.nextInt();
		System.out.println("�����������ί�ķ���");
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
		java.util.Arrays.sort(a);   //��������дӴ�С����
		System.out.println("ȥ��һ����߷�:"+a[a.length-1]+", ");
		System.out.println("ȥ��һ����ͷ�:"+a[0]+". ");
		double b[] = new double[a.length-2];
		for(int i = 1;i < a.length-1;i++)  //ȥ����߷ֺ���ͷ�
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
		System.out.println("ѡ�����÷�"+aver);
	}
}
