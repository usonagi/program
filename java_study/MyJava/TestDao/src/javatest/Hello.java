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
      // System.out.print(" ��� ��");
      // System.out.print(" java ��");
    	/*People zhubajie;
    	zhubajie = new People();
    	zhubajie.height = 170;
    	zhubajie.ear = "��ֻ�����";
    	System.out.println("��ߣ�"+zhubajie.height);
    	System.out.println(zhubajie.ear);
    	zhubajie.speak("ʦ�������Ǳ�ȥ�����ˣ���ȥ�¹��ɣ�");*/
    	
    	//**********���Բ���
    	/*try {   //�Ӽ��������ı���ָ��λ��
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
    						System.out.println("��Һã�ף����˳����");
    						}
    				};  //������
    	hello.cry();*/
    	
    	/*B b = new B();
    	b.m = 20;
    	System.out.println(b.getM());
    	A a = b;
    	a.m = -100;
    	System.out.println(a.getM());
    	System.out.println(b.seeM());*/
    	
    	
    	
    	char chinaWord = '��',japanWord = '��';
    	char you = '\u4F60';
    	int position = 20320 ;
    	System.out.println("����:"+chinaWord+"��λ����:"+(int)chinaWord );
    	System.out.println("����:"+japanWord+"��λ����:"+(int)japanWord );
    	System.out.println(position+"λ���ϵ��ַ���:"+(char)position);
    	position = 21319;
    	System.out.println(position+"λ���ϵ��ַ���:"+(char)position);
    	System.out.println("you:"+you);  // Exam 2_1 
    	
    	/*byte b = 22;
    	int n = 129;
    	float f = 123456.6789f;
    	double d = 123456789.123456789;
    	System.out.println("b =  "+b);
    	System.out.println("n =  "+n);
    	System.out.println("f =  "+f);
    	System.out.println("d =  "+d);
    	b = (byte) n;  //����ȱʧ
    	f = (float) d; //����ȱʧ
    	System.out.println("b =  "+b);
    	System.out.println("f =  "+f);  //  Exam 2-2 */
    	
    	/*System.out.println("���������ɸ�����ÿ����һ�����س�ȷ��");
    	System.out.println("�����������0��������");
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
    	System.out.println("a��Ԫ�ظ���"+a.length);
    	System.out.println("b��Ԫ�ظ���"+b.length);
    	System.out.println("����a������ ="+a);
    	System.out.println("����b������ ="+b);
    	a = b;
    	System.out.println("a��Ԫ�ظ���"+a.length);
    	System.out.println("b��Ԫ�ظ���"+b.length);
    	for(int i = 0;i < a.length;i++){
    		System.out.print("a[" + i + "]"+ "=" +a[i]);
    		//System.out.println("  b[" + i + "]"+ "=" +b[i]);
    		System.out.printf(" a[%d] = %d\n",i,a[i]);}  //Exam 2-3 ������
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
    	System.out.println("�����������жϸ������Ƿ���������:");
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
    		System.out.printf("%d����������.\n",number);
    	else 
    		System.out.printf("%d��������.\n",number);  //Exam 2-5  */
    	
    	/*for(int i = 20302;i <= 20322;i++){
    		System.out.println((char)i);
    	}  // ��������*/
    	
    	/*int x = 32;
    	int y = 432;
    	System.out.println(x + "<"+ (2 * x) );
    	System.out.print("���������󲻻س�");
    	System.out.println("������������Զ��س�����һ��");
    	System.out.println("X + y =" + (x+y) );     //P31��2��*/
    	
    	// ������������ ȡֵ��Χ
    	/*System.out.println("byteȡֵ��Χ:" + Byte.MIN_VALUE+"��"+Byte.MAX_VALUE);
    	System.out.println("shortȡֵ��Χ:" + Short.MIN_VALUE+"��"+Short.MAX_VALUE);
    	System.out.println("intȡֵ��Χ:" + Integer.MIN_VALUE+"��"+Integer.MAX_VALUE);
    	System.out.println("longȡֵ��Χ:" + Long.MIN_VALUE+"��"+Long.MAX_VALUE);
    	System.out.println("floatȡֵ��Χ:" + Float.MIN_VALUE+"��"+Float.MAX_VALUE);
    	System.out.println("doubleȡֵ��Χ:" + Double.MIN_VALUE+"��"+Double.MAX_VALUE);*/
    	
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
    	
    	/*char CW1 = '��',CW2 = '��',CW3 = '��';
    	System.out.println("�ҡ��㡢�� ��Unico�е�λ��:"+(int)CW1+"\b"+(int)CW2+"\b"+(int)CW3 );*/
    	
    	/*char start = '��',end = '��';
    	System.out.println("ȫ����ϣ����ĸ:");
    	for(char c = start;c <= end;c++)
    		System.out.print(c + " ");  */
    	
    	/*char a1 = 'ʮ',a2 = '��',a3 = '��',a4 = '��';
		char secret = 'A';
		a1 = (char)(a1^secret);
		a2 = (char)(a2^secret);
		a3 = (char)(a3^secret);
		a4 = (char)(a4^secret);
		System.out.println("����: "+a1+a2+a3+a4);
		a1 = (char)(a1^secret);
		a2 = (char)(a2^secret);
		a3 = (char)(a3^secret);
		a4 = (char)(a4^secret);
		System.out.println("ԭ��: "+a1+a2+a3+a4);   //Exam 3-1  */
		
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
			System.out.println("��ѧ�����ˣ�");
		else System.out.println("��ѧ������");
		
		if(english > 90)
			System.out.println("Ӣ�������㣡");
		else System.out.println("Ӣ�ﲻ�����㣡");*/
		
		/*char x = '��',y = 'e',z = '��';
		if(x > 'A'){
			y = 'Ƽ';
			z = '��';
		}
		else
			y = '��';
		z = '��';
		System.out.println(x+","+y+","+z);*/
    	
    	/*Rect rectangle = new Rect();
    	rectangle.width = 109.87;
    	rectangle.height = 25.18;
    	double area = rectangle.getArea();
    	System.out.println("���ε����: "+area);
    	Lader lader = new Lader();
    	lader.above = 10.798;
    	lader.bottom = 156.65;
    	lader.height = 18.12;
    	area = lader.getArea();
    	System.out.println("���ε����: "+area);*/
    	
    	/*Circle circle = new Circle();
    	circle.SetRadius(10);
    	Circular circular = new Circular();
    	System.out.println("circle������: "+circle);
    	System.out.println("Բ׶��bottom������:"+circular.bottom);
    	circular.setHeight(5);
    	circular.setBottom(circle);
    	System.out.println("circle������: "+circle);
    	System.out.println("Բ׶�����:"+circular.getVolume());
    	System.out.println("�޸�circle�İ뾶��bottom�İ뾶ͬ���仯");
    	circle.SetRadius(50);
    	System.out.println("bottom�İ뾶:"+circular.getBottomRadius());
    	System.out.println("Բ׶�����:"+circular.getVolume());
    	System.out.println("���´���circle��circle�����ý������仯");
    	circle = new Circle();
    	System.out.println("circle������: "+circle);
    	System.out.println("���ǲ�Ӱ��circular��bottom������");
    	System.out.println("Բ׶bottom������:"+circular.bottom);*/
    	
    	/*Lader lader = new Lader();
    	Lader.bottom = 10;
    	System.out.println(lader.bottom);
    	lader.setBottom(20);     //�޸������ bottom
    	System.out.println(lader.bottom);*/
    	
    	/*Circle circle = new Circle();
    	circle.SetRadius(196.78);
    	Lader lader = new Lader(3,21,9);  //ʹ�ù��캯����ʼ������
    	People li = new People();
    	System.out.print("li����Բ�����:");
    	double result = li.computerArea(circle);
    	System.out.println(result);
    	System.out.print("li�������ε����:");
    	result = li.computerArea(lader);
    	System.out.println(result);*/
    	
    	
    }

}