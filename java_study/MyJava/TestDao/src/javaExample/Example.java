package javaExample;

import java.util.Arrays;
import java.util.Scanner ;

/******** �̲����� ***********/

public class Example {
	
	// �����������ж���
	
	//  ��ǰn�����Ľ׳�֮�� 1!+2!+..+n!
	void FactorialSum(int n){
		int sum = 0,mul = 1, j = 1;  //��1!+..+n!
		for(int i = 1;i <= n; i++){
			for(;j <= i;j++){
				mul *= j;     //��n�׳�
			}
			sum += mul;  //�׳˺�
		}
		System.out.println(sum);
	}
	// ��i��p֮�������
	void PrimerNumber(int i,int p){
		int j;
		for(;i <= p; i++){
			for(j = 2;j < i;j++){
				if(i % j == 0)  //��������0��ʾ��������
					break;
			}
			if(j >= i) 
				System.out.print(i+" ");
		}
	}
	//  ��s��n�ĸ���׳˵ĵ���֮��
	void FactorSum(int start,int n){
		double sum = 0,p = 1.0 ;
		int i = start;
//		for(;start <= n;start++){
//			p *= start;  //���ĸ n!
//			sum += 1 / p ;  //���
//		}
		
		do{
			p *= (1.0 / i);
			sum += p;
			i++;
		} while(i <= n ) ;
		
		System.out.println("��ͽ��: "+sum); //��ǰn��� 1/1!+1/2!+..+1/n!  
	}
	// ������  ����֮�͵��ڱ���
	void PerfectNumber(int s,int n){
		int sum = 0,j = s,i;
		for( ;j <= n;j++){
			for(i = 1,sum = 0;i < j;i++){  // ���Ӳ����㱾��
				if(j % i == 0){  //i��n������
					sum += i;  //���Ӻ�
				}
			}
			if(sum == j)
				System.out.printf("%d������.  ",j);
			//else System.out.printf("%d��������",j);
		}
	}
	// �� ��s�ۼ� С��n �������
	int MaxFactor(int s,int n){
		int sum = 0,i = s;
		if(n == 1)
			return 1;
		while(sum < n){
			i++;
			sum += i;
			
		}
		//System.out.println(i);
		return i-1;
	}

	public static void main(String[] args) {
		
		Example exa = new Example();  //����Example ����
		
		/*Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();  //�Ӽ�������*/
		
		//exa.FactorialSum(n);
		
		/*int i = reader.nextInt();
		exa.PrimerNumber(i, n);*/
		
		//exa.FactorSum(1, n);
		
		//exa.PerfectNumber(1, n);
		
		/*int res = exa.MaxFactor(1,n);
		System.out.println(res);*/
		
		//     �ַ��������㷨
		/*String sourceString = "������";
		EncryptAndDecrypt ead = new EncryptAndDecrypt();
		System.out.println("�����������: "+sourceString);
		Scanner scanner = new Scanner(System.in);
		String password = scanner.nextLine();		//�������롤
		String secret = ead.encrypt(sourceString, password); //��ԭ�ļ���
		System.out.println("����: "+secret);
		System.out.println("�����������:");
		password = scanner.nextLine();
		String source = ead.decrypt(secret, password);  //�����Ľ���
		System.out.println("����:"+source);*/
		
		
		//     String ��
		/*String str1 = "�������";
		String str2 = "�������";
		String str3 = new String("�������");		//str1\2 ���ַ�������  str3�Ƕ�̬������
		
		System.out.println(str1.equals(str2));  //equals�����ж��ַ��������Ƿ���ͬ
		System.out.println(str1 == str2);       // == �����ж��ַ����������Ƿ���ͬ
		System.out.println(str1.equals(str3));  //str1\2\3������ͬ����ֵΪtrue
		System.out.println(str1 == str3);   	//1\2������ͬ  ������3��ͬ  */
		
		/*String a[] = {"apple","melon","pear","banana"};
		String b[] = {"����","�㽶","����","ƻ��"};
		System.out.print("���ֵ��������: ");
		SortString.sortString(a);
		for(int i = 0; i < a.length; i++ )
			System.out.print(" "+a[i]);
		System.out.printf("\nʹ������е�Arrays�࣬�������������: ");
		Arrays.sort(b);
		for(int i = 0; i < b.length; i++ )
			System.out.print(" "+b[i]);*/
		
		
		
		//     ����ӿڱ��
		/*ADBoard board = new ADBoard();
		board.show(new WhiteCloud());*/
		
		//   ���������
		/*Pillar pillar;
		Geometry bottom = null;   //��Ϊ��
		pillar = new Pillar(bottom,100);
		System.out.println("���: "+pillar.getVolume());
		bottom = new Rectangle(12,22);
		pillar = new Pillar(bottom,58);  //������
		System.out.println("���: "+pillar.getVolume());
		bottom = new Circle(10);
		pillar = new Pillar(bottom,58);  //Բ��
		System.out.println("���: "+pillar.getVolume());*/
		
		//    ��Ա����������
		/*CheapGoods CG = new CheapGoods();
		//CG.weight = 198.0;  		//�Ƿ� �����weight��int��
		CG.newSetWeight(198);
		System.out.println("CG��weightֵ��:"+CG.weight);
		System.out.println("CG�������������Żݷ�������۸�:"+CG.newGetPrice());
		CG.oldSetWeight(198.98);  	//���������ü̳еķ����������صĳ�Ա����
		System.out.println("CGʹ�ü̳еķ�������۸�(���Ż�)"+CG.oldGetPrice());
		Goods goods = new Goods();
		System.out.println("goods��getNum��ֵ"+goods.getNum());
		//goods.number = 10;  		//���ܷ��������˽�б���
		System.out.println("CG��getNum��ֵ"+CG.getNum());*/
		
		/*//  ����������������
		Rational r1 = new Rational();
		r1.setNumberator(1);
		r1.setDenominator(5);
		Rational r2 = new Rational();
		r2.setNumberator(3);
		r2.setDenominator(2);
		Rational result = r1.Add(r2);
		// ���� �Ӽ��˳�
		int a = result.getNumberator();  //����
		int b = result.getDenominator(); //��ĸ
		System.out.println("1/5+3/2 = " + a + "/" + b);
		result = r1.Sub(r2);
		a = result.getNumberator();
		b = result.getDenominator();
		System.out.println("1/5-3/2 = " + a + "/" + b);
		result = r1.Mutil(r2);
		a = result.getNumberator();
		b = result.getDenominator();
		System.out.println("1/5��3/2 = " + a + "/" + b);
		result = r1.Div(r2);
		a = result.getNumberator();
		b = result.getDenominator();
		System.out.println("1/5��3/2 = " + a + "/" + b);
		
		int k = 1;
		System.out.println("����2/1+3/2+5/3+...+��ǰ"+ n + "��͡�" );
		Rational sum = new Rational();
		sum.setNumberator(0);
		Rational item = new Rational();
		item.setNumberator(2);
		item.setDenominator(1);
		while( k <= n) {
			sum = sum.Add(item);
			k ++;
			int numberator = item.getNumberator();  //����
			int denominator = item.getDenominator(); //��ĸ
			item.setNumberator(denominator+numberator); //���ӵ��� ����+��ĸ 
			item.setDenominator(numberator);  //��ĸ���� ����
		}
		a = sum.getNumberator();
		b = sum.getDenominator();
		System.out.println("�÷�����ʾ: " + a + "/" + b);
		double doubleres = (a*1.0)/b;
		System.out.println("��С����ʾ: " + doubleres);*/
		
		/*LineStream line = new LineStream();   //��ˮ������
		line.giveScore();*/
		
	/*	int a[] = {1,2,3,4};
		char b[] = {'a','b','c','d'};
		for(int i:a){
			System.out.println(i); //ѭ������i����ȡ����a��ÿһ��Ԫ��
		}
		for(char ch:b){
			System.out.println(ch); //ѭ������ch����ȡ����b��ÿһ��Ԫ��
		}*/
		
		
		/*char c = '\0';
		for(int i = 1;i<=4;i++){
			switch(i){ // ��switch�е�ֵ��case���ֵ��ȣ���ִ�и�case������ֱ������break
				case 1 : c = 'J';
						//System.out.print("����һ��");
						System.out.print(c+","+i);
				case 2 : c = 'e';
						//System.out.print("����һ��");
						System.out.print(c+","+i);
						break;
				case 3 : c = 'p';
						//System.out.print("����һ��");
						System.out.print(c+","+i);
				default: System.out.print("��"+","+i); //��switch�е�ֵ�����κ�case��ȣ�ִ��default
			}
		}*/
		
		
	}

}
