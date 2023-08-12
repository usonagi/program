package javatest;

import java.util.Scanner ;
import java.io.*;

/********  ************/

/*
public class Hello {
	public static void main(String args[]) {
		System.out.println("hello java !");
	}
}*/



public class Hello {
    public static void main (String args[ ]) {
      // System.out.print(" 你好 ！");
      // System.out.print(" java ！");
    	/*People zhubajie;
    	zhubajie = new People();
    	zhubajie.height = 170;
    	zhubajie.ear = "两只大耳朵";
    	System.out.println("身高："+zhubajie.height);
    	System.out.println(zhubajie.ear);
    	zhubajie.speak("师傅，咱们别去西天了，改去月宫吧！");*/
    	
    	//**********测试部分
    	/*try {   //从键盘输入文本到指定位置
			InputStreamReader ir = new InputStreamReader(System.in);
			FileWriter fout = new FileWriter("D:\\eclipse64\\Test.txt");
			BufferedReader bin = new BufferedReader(ir);
			BufferedWriter bout = new BufferedWriter(fout);

			String s=bin.readLine( );
			while(!s.equals("^^^^^"))
			{
				bout.write(s);
				bout.newLine();
				s=bin.readLine();
			}

			bout.flush();
			bout.close();
			bin.close();
		} catch (Exception e) {
			System.out.println("File read Error!"+e);
		}*/
    	
    	/*Cry hello = new Cry(){    
    				public void cry() {
    						System.out.println("大家好，祝工作顺利！");
    						}
    				};  //匿名类
    	hello.cry();*/
    	
    	/*B b = new B();
    	b.m = 20;
    	System.out.println(b.getM());
    	A a = b;
    	a.m = -100;
    	System.out.println(a.getM());
    	System.out.println(b.seeM());*/
    	
    	
    	
    	char chinaWord = '好',japanWord = 'あ';
    	char you = '\u4F60';
    	int position = 20320 ;
    	System.out.println("汉字:"+chinaWord+"的位置是:"+(int)chinaWord );
    	System.out.println("日文:"+japanWord+"的位置是:"+(int)japanWord );
    	System.out.println(position+"位置上的字符是:"+(char)position);
    	position = 21319;
    	System.out.println(position+"位置上的字符是:"+(char)position);
    	System.out.println("you:"+you);  // Exam 2_1 
    	
    	/*byte b = 22;
    	int n = 129;
    	float f = 123456.6789f;
    	double d = 123456789.123456789;
    	System.out.println("b =  "+b);
    	System.out.println("n =  "+n);
    	System.out.println("f =  "+f);
    	System.out.println("d =  "+d);
    	b = (byte) n;  //精度缺失
    	f = (float) d; //精度缺失
    	System.out.println("b =  "+b);
    	System.out.println("f =  "+f);  //  Exam 2-2 */
    	
    	/*System.out.println("请输入若干个数，每输入一个数回车确认");
    	System.out.println("最后输入数字0结束输入");
    	Scanner reader = new Scanner(System.in);
    	double sum = 0;
    	double x = reader.nextDouble();
    	while(x != 0){
    		sum += x;
    		x = reader.nextDouble();
    	}
    	System.out.println("sum = "+sum);  //EXam 2-3 */
    	
    	/*int a[] = {1,2,3,4};
    	int b[] = {100,200,300};
    	System.out.println("a的元素个数"+a.length);
    	System.out.println("b的元素个数"+b.length);
    	System.out.println("数组a的引用 ="+a);
    	System.out.println("数组b的引用 ="+b);
    	a = b;
    	System.out.println("a的元素个数"+a.length);
    	System.out.println("b的元素个数"+b.length);
    	for(int i = 0;i < a.length;i++){
    		System.out.print("a[" + i + "]"+ "=" +a[i]);
    		//System.out.println("  b[" + i + "]"+ "=" +b[i]);
    		System.out.printf(" a[%d] = %d\n",i,a[i]);}  //Exam 2-3 ？？？
*/    	
    	/*int start = 0, end,middle;
    	int a[] = {12,45,67,89,-45,67};
    	int N = a.length;
    	for(int i = 0;i < N ;i++){
    		for(int j =i+1; j < N;j++){
    			if(a[j] < a[i]){
    				int temp = a[j];
    				a[j] = a[i];
    				a[i] = temp;  }
    		}
    	}
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("输入整数，判断该整数是否在数组中:");
    	int number = scanner.nextInt();
    	int count = 0;
    	end = N;
    	middle = (start + end) / 2;
    	while(number != a[middle]){
    		if(number > a[middle])
    			start = middle;
    		else if(number < a[middle])
    			end = middle;
    		middle = (start + end)/2;
    		count ++;
    		if(count > N / 2)
    			break;
    	}
    	if(count > N / 2)
    		System.out.printf("%d不在数组中.\n",number);
    	else 
    		System.out.printf("%d在数组中.\n",number);  //Exam 2-5  */
    	
    	/*for(int i = 20302;i <= 20322;i++){
    		System.out.println((char)i);
    	}  // 部分中文*/
    	
    	/*int x = 32;
    	int y = 432;
    	System.out.println(x + "<"+ (2 * x) );
    	System.out.print("我输出结果后不回车");
    	System.out.println("我输出结果后会自动回车到下一行");
    	System.out.println("X + y =" + (x+y) );     //P31（2）*/
    	
    	// 基本数据类型 取值范围
    	/*System.out.println("byte取值范围:" + Byte.MIN_VALUE+"至"+Byte.MAX_VALUE);
    	System.out.println("short取值范围:" + Short.MIN_VALUE+"至"+Short.MAX_VALUE);
    	System.out.println("int取值范围:" + Integer.MIN_VALUE+"至"+Integer.MAX_VALUE);
    	System.out.println("long取值范围:" + Long.MIN_VALUE+"至"+Long.MAX_VALUE);
    	System.out.println("float取值范围:" + Float.MIN_VALUE+"至"+Float.MAX_VALUE);
    	System.out.println("double取值范围:" + Double.MIN_VALUE+"至"+Double.MAX_VALUE);*/
    	
    	/*int [] a = {10,20,30,40},b[] = {{1,2},{4,5,6,7}} ;
    	for(int i = 0;i<a.length;i++)
    		System.out.println(a[i]);
    	System.out.println("  !!! ");
    	b[0] = a;
    	System.out.println(b[0][1]);
    	b[0][1] = b[1][3] ;
    	System.out.println(b[1][3]);
    	//System.out.println(b[0][2]);
    	for(int i = 0;i<a.length;i++)
    		System.out.println(a[i]);    // ======== ???   */
    	
    	/*char CW1 = '我',CW2 = '你',CW3 = '他';
    	System.out.println("我、你、他 在Unico中的位置:"+(int)CW1+"\b"+(int)CW2+"\b"+(int)CW3 );*/
    	
    	/*char start = 'α',end = 'ω';
    	System.out.println("全部的希腊字母:");
    	for(char c = start;c <= end;c++)
    		System.out.print(c + " ");  */
    	
    	/*char a1 = '十',a2 = '点',a3 = '进',a4 = '攻';
		char secret = 'A';
		a1 = (char)(a1^secret);
		a2 = (char)(a2^secret);
		a3 = (char)(a3^secret);
		a4 = (char)(a4^secret);
		System.out.println("密文: "+a1+a2+a3+a4);
		a1 = (char)(a1^secret);
		a2 = (char)(a2^secret);
		a3 = (char)(a3^secret);
		a4 = (char)(a4^secret);
		System.out.println("原文: "+a1+a2+a3+a4);   //Exam 3-1  */
		
		/*int a = 9,b = 5,c = 7,t = 0;  // Exam 3-2
		if(b < a){
			t = a;
			a = b;
			b = t;
		}
		if(c < a){
			t = a;
			a = c;
			c = t;
		}
		if(c < b){
			t = b;
			b = c;
			c = t;
		}
		System.out.println("a= "+a + " b=" + b+" c= "+c);*/
		
		/*int math = 65, english = 85;  //Exam 3-3
		if(math > 70)
			System.out.println("数学及格了！");
		else System.out.println("数学不及格！");
		
		if(english > 90)
			System.out.println("英语是优秀！");
		else System.out.println("英语不是优秀！");*/
		
		/*char x = '你',y = 'e',z = '吃';
		if(x > 'A'){
			y = '萍';
			z = '果';
		}
		else
			y = '酸';
		z = '甜';
		System.out.println(x+","+y+","+z);*/
    	
    	/*Rect rectangle = new Rect();
    	rectangle.width = 109.87;
    	rectangle.height = 25.18;
    	double area = rectangle.getArea();
    	System.out.println("矩形的面积: "+area);
    	Lader lader = new Lader();
    	lader.above = 10.798;
    	lader.bottom = 156.65;
    	lader.height = 18.12;
    	area = lader.getArea();
    	System.out.println("梯形的面积: "+area);*/
    	
    	/*Circle circle = new Circle();
    	circle.SetRadius(10);
    	Circular circular = new Circular();
    	System.out.println("circle的引用: "+circle);
    	System.out.println("圆锥的bottom的引用:"+circular.bottom);
    	circular.setHeight(5);
    	circular.setBottom(circle);
    	System.out.println("circle的引用: "+circle);
    	System.out.println("圆锥的体积:"+circular.getVolume());
    	System.out.println("修改circle的半径，bottom的半径同样变化");
    	circle.SetRadius(50);
    	System.out.println("bottom的半径:"+circular.getBottomRadius());
    	System.out.println("圆锥的体积:"+circular.getVolume());
    	System.out.println("重新创建circle，circle的引用将发生变化");
    	circle = new Circle();
    	System.out.println("circle的引用: "+circle);
    	System.out.println("但是不影响circular的bottom的引用");
    	System.out.println("圆锥bottom的引用:"+circular.bottom);*/
    	
    	/*Lader lader = new Lader();
    	Lader.bottom = 10;
    	System.out.println(lader.bottom);
    	lader.setBottom(20);     //修改类变量 bottom
    	System.out.println(lader.bottom);*/
    	
    	/*Circle circle = new Circle();
    	circle.SetRadius(196.78);
    	Lader lader = new Lader(3,21,9);  //使用构造函数初始化变量
    	People li = new People();
    	System.out.print("li计算圆的面积:");
    	double result = li.computerArea(circle);
    	System.out.println(result);
    	System.out.print("li计算梯形的面积:");
    	result = li.computerArea(lader);
    	System.out.println(result);*/
    	
    	
    }

}