package stringExample;

/********** �ַ������ʹ��  *************/

// Java��String�ඨ��Ϊfinal�࣬���String�಻��������

public class StringExam {
	public static void main(String args[]) {
		/*String s = "hello";
		System.out.println(s);
		
		//ʹ���Ѵ��ڵ�String���󴴽���һ��String����
		String s1 = new String(s);  
		System.out.println(s1);
		
		//String��������á�+�����в��� ��β��ӵõ�һ���µ�String����
		//���벢�õ�String����ֻҪ��һ���Ǳ��� ������൱��new String()
		String s3 = s + s1;
		System.out.println(s3);
		
		// ��String���������ø�ֵ��һ��String����
		System.out.println(s == s1); //s1���·����˿ռ� ������s�����ò�ͬ
		String s2 = s;//s1;		s��s2��������ͬ��ָ��hello
		System.out.println(s2 == s);*/
		
		/*//�������String��������� ֻ���������ʵ�弴�ַ�����
		String t = new String("Hello");   
		System.out.println(t);*/
		
		/*//ʹ�ù��췽��String(char a[]) ��һ���ַ����鴴��һ��String����
		char c[] = {'J','a','v','a'};
		String str = new String(c);
		System.out.println(str);	//�൱�� String str = new String("Java");
*/		
		/*//ʹ�ù��췽��String(char a[],int startIndex,int count)
		//��ȡ�ַ������д� startIndex ��ʼ�� count ���ַ� ����һ���ַ�����
		char c[] = {'��','Ҽ','��','��','��','��','½','��','��','��'};
		String str = new String(c,1,5);
		System.out.println(str);    */
		
		/************** String ��ĳ��÷��� *****************/
		//public int length()��ȡһ��String������ַ����еĳ���
		/*System.out.println("Java".length());
		
		//public boolean equals(String s)�Ƚϵ�ǰString������ַ�������s�Ƿ���ͬ
		System.out.println("world".equals("world"));
		
		//public boolean starts(ends)With(String s) �жϵ�ǰ�ַ�����ǰ׺(��׺)�Ƿ����ַ�����s
		System.out.println("hello world".startsWith("he"));
		
		//public int compareTo(String s) ���ֵ������ַ�����s�Ƚϴ�С ���ز�ֵ
		System.out.println("hello".compareTo("java"));
		
		//public int compareToIgnoreCase(String s)���Դ�Сд ���ֵ���Ƚϴ�С  ���ز�ֵ
		System.out.println("Hello".compareToIgnoreCase("hello"));*/
		
		//public boolean contains(String s)�жϵ�ǰ�ַ������Ƿ�����ַ�����s
		System.out.println("Application".contains("App"));
		
		// int indexOf(String s)�ӵ�ǰ�ַ���0����λ�ÿ�ʼ�����״γ���s��λ�� ���ظ�λ��
		// int lastIndexOf(String s)�ӵ�ǰ�ַ���0����λ�ÿ�ʼ�������һ�γ���s��λ�� ���ظ�λ��
		String str = "I am a good students.";
		System.out.println(str.indexOf("a"));
		System.out.println(str.lastIndexOf("d"));
		
		//String substring(int startpoint)���Ƶ�ǰ�ַ����д�startpoint�����λ�õ��ַ������ַ���
		//substring(int start,int end) ����start��end-1�ϵ��ַ��������ַ���
		System.out.println(str.substring(7));
		System.out.println(str.substring(5, str.length())); //�±��0��ʼ
		
		//public String trim()�õ���String�����ǵ�ǰ�ַ���ȥ�� ǰ�� �ո����ַ�����
		System.out.println(str.trim());
		
	}

}
