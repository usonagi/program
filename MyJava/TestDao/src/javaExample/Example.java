package javaExample;

import java.util.Arrays;
import java.util.Scanner ;

/******** 教材例题 ***********/

public class Example {
	
	// 函数须在类中定义
	
	//  求前n个数的阶乘之和 1!+2!+..+n!
	void FactorialSum(int n){
		int sum = 0,mul = 1, j = 1;  //求1!+..+n!
		for(int i = 1;i <= n; i++){
			for(;j <= i;j++){
				mul *= j;     //求n阶乘
			}
			sum += mul;  //阶乘和
		}
		System.out.println(sum);
	}
	// 求i到p之间的素数
	void PrimerNumber(int i,int p){
		int j;
		for(;i <= p; i++){
			for(j = 2;j < i;j++){
				if(i % j == 0)  //余数等于0表示不是素数
					break;
			}
			if(j >= i) 
				System.out.print(i+" ");
		}
	}
	//  求s到n的各项阶乘的倒数之和
	void FactorSum(int start,int n){
		double sum = 0,p = 1.0 ;
		int i = start;
//		for(;start <= n;start++){
//			p *= start;  //求分母 n!
//			sum += 1 / p ;  //求和
//		}
		
		do{
			p *= (1.0 / i);
			sum += p;
			i++;
		} while(i <= n ) ;
		
		System.out.println("求和结果: "+sum); //求前n项和 1/1!+1/2!+..+1/n!  
	}
	// 求完数  因子之和等于本身
	void PerfectNumber(int s,int n){
		int sum = 0,j = s,i;
		for( ;j <= n;j++){
			for(i = 1,sum = 0;i < j;i++){  // 因子不能算本身
				if(j % i == 0){  //i是n的因子
					sum += i;  //因子和
				}
			}
			if(sum == j)
				System.out.printf("%d是完数.  ",j);
			//else System.out.printf("%d不是完数",j);
		}
	}
	// 求 从s累加 小于n 的最大数
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
		
		Example exa = new Example();  //创建Example 对象
		
		/*Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();  //从键盘输入*/
		
		//exa.FactorialSum(n);
		
		/*int i = reader.nextInt();
		exa.PrimerNumber(i, n);*/
		
		//exa.FactorSum(1, n);
		
		//exa.PerfectNumber(1, n);
		
		/*int res = exa.MaxFactor(1,n);
		System.out.println(res);*/
		
		//     字符串加密算法
		/*String sourceString = "今晚开黑";
		EncryptAndDecrypt ead = new EncryptAndDecrypt();
		System.out.println("输入密码加密: "+sourceString);
		Scanner scanner = new Scanner(System.in);
		String password = scanner.nextLine();		//加密密码·
		String secret = ead.encrypt(sourceString, password); //对原文加密
		System.out.println("密文: "+secret);
		System.out.println("输入解密密码:");
		password = scanner.nextLine();
		String source = ead.decrypt(secret, password);  //对密文解密
		System.out.println("明文:"+source);*/
		
		
		//     String 类
		/*String str1 = "天道酬勤";
		String str2 = "天道酬勤";
		String str3 = new String("天道酬勤");		//str1\2 是字符串常量  str3是动态创建的
		
		System.out.println(str1.equals(str2));  //equals用于判断字符串内容是否相同
		System.out.println(str1 == str2);       // == 用于判断字符串的引用是否相同
		System.out.println(str1.equals(str3));  //str1\2\3内容相同返回值为true
		System.out.println(str1 == str3);   	//1\2引用相同  但都和3不同  */
		
		/*String a[] = {"apple","melon","pear","banana"};
		String b[] = {"西瓜","香蕉","葡萄","苹果"};
		System.out.print("按字典序排序后: ");
		SortString.sortString(a);
		for(int i = 0; i < a.length; i++ )
			System.out.print(" "+a[i]);
		System.out.printf("\n使用类库中的Arrays类，对数组进行排序: ");
		Arrays.sort(b);
		for(int i = 0; i < b.length; i++ )
			System.out.print(" "+b[i]);*/
		
		
		
		//     面向接口编程
		/*ADBoard board = new ADBoard();
		board.show(new WhiteCloud());*/
		
		//   面向抽象编程
		/*Pillar pillar;
		Geometry bottom = null;   //底为空
		pillar = new Pillar(bottom,100);
		System.out.println("体积: "+pillar.getVolume());
		bottom = new Rectangle(12,22);
		pillar = new Pillar(bottom,58);  //四棱柱
		System.out.println("体积: "+pillar.getVolume());
		bottom = new Circle(10);
		pillar = new Pillar(bottom,58);  //圆柱
		System.out.println("体积: "+pillar.getVolume());*/
		
		//    成员变量的隐藏
		/*CheapGoods CG = new CheapGoods();
		//CG.weight = 198.0;  		//非法 子类的weight是int型
		CG.newSetWeight(198);
		System.out.println("CG的weight值是:"+CG.weight);
		System.out.println("CG用子类新增的优惠方法计算价格:"+CG.newGetPrice());
		CG.oldSetWeight(198.98);  	//子类对象调用继承的方法操作隐藏的成员变量
		System.out.println("CG使用继承的方法计算价格(无优惠)"+CG.oldGetPrice());
		Goods goods = new Goods();
		System.out.println("goods的getNum的值"+goods.getNum());
		//goods.number = 10;  		//不能访问自身的私有变量
		System.out.println("CG的getNum的值"+CG.getNum());*/
		
		/*//  有理数的四则运算
		Rational r1 = new Rational();
		r1.setNumberator(1);
		r1.setDenominator(5);
		Rational r2 = new Rational();
		r2.setNumberator(3);
		r2.setDenominator(2);
		Rational result = r1.Add(r2);
		// 计算 加减乘除
		int a = result.getNumberator();  //分子
		int b = result.getDenominator(); //分母
		System.out.println("1/5+3/2 = " + a + "/" + b);
		result = r1.Sub(r2);
		a = result.getNumberator();
		b = result.getDenominator();
		System.out.println("1/5-3/2 = " + a + "/" + b);
		result = r1.Mutil(r2);
		a = result.getNumberator();
		b = result.getDenominator();
		System.out.println("1/5×3/2 = " + a + "/" + b);
		result = r1.Div(r2);
		a = result.getNumberator();
		b = result.getDenominator();
		System.out.println("1/5÷3/2 = " + a + "/" + b);
		
		int k = 1;
		System.out.println("计算2/1+3/2+5/3+...+的前"+ n + "项和。" );
		Rational sum = new Rational();
		sum.setNumberator(0);
		Rational item = new Rational();
		item.setNumberator(2);
		item.setDenominator(1);
		while( k <= n) {
			sum = sum.Add(item);
			k ++;
			int numberator = item.getNumberator();  //分子
			int denominator = item.getDenominator(); //分母
			item.setNumberator(denominator+numberator); //分子等于 分子+分母 
			item.setDenominator(numberator);  //分母等于 分子
		}
		a = sum.getNumberator();
		b = sum.getDenominator();
		System.out.println("用分数表示: " + a + "/" + b);
		double doubleres = (a*1.0)/b;
		System.out.println("用小数表示: " + doubleres);*/
		
		/*LineStream line = new LineStream();   //流水线评分
		line.giveScore();*/
		
	/*	int a[] = {1,2,3,4};
		char b[] = {'a','b','c','d'};
		for(int i:a){
			System.out.println(i); //循环变量i依次取数组a的每一个元素
		}
		for(char ch:b){
			System.out.println(ch); //循环变量ch依次取数组b的每一个元素
		}*/
		
		
		/*char c = '\0';
		for(int i = 1;i<=4;i++){
			switch(i){ // 若switch中的值与case后的值相等，就执行该case后的语句直到遇到break
				case 1 : c = 'J';
						//System.out.print("进入一次");
						System.out.print(c+","+i);
				case 2 : c = 'e';
						//System.out.print("进入一次");
						System.out.print(c+","+i);
						break;
				case 3 : c = 'p';
						//System.out.print("进入一次");
						System.out.print(c+","+i);
				default: System.out.print("好"+","+i); //若switch中的值不与任何case相等，执行default
			}
		}*/
		
		
	}

}
