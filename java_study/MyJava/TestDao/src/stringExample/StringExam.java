package stringExample;

/********** 字符串类的使用  *************/

// Java把String类定义为final类，因此String类不能有子类

public class StringExam {
	public static void main(String args[]) {
		/*String s = "hello";
		System.out.println(s);
		
		//使用已存在的String对象创建另一个String对象
		String s1 = new String(s);  
		System.out.println(s1);
		
		//String对象可以用“+”进行并置 首尾相接得到一个新的String对象
		//参与并置的String对象只要有一个是变量 结果就相当于new String()
		String s3 = s + s1;
		System.out.println(s3);
		
		// 把String常量的引用赋值给一个String对象
		System.out.println(s == s1); //s1重新分配了空间 所以与s的引用不同
		String s2 = s;//s1;		s和s2的引用相同都指向hello
		System.out.println(s2 == s);*/
		
		/*//不能输出String对象的引用 只能输出对象实体即字符序列
		String t = new String("Hello");   
		System.out.println(t);*/
		
		/*//使用构造方法String(char a[]) 用一个字符数组创建一个String对象
		char c[] = {'J','a','v','a'};
		String str = new String(c);
		System.out.println(str);	//相当于 String str = new String("Java");
*/		
		/*//使用构造方法String(char a[],int startIndex,int count)
		//提取字符数组中从 startIndex 开始的 count 个字符 创建一个字符对象
		char c[] = {'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
		String str = new String(c,1,5);
		System.out.println(str);    */
		
		/************** String 类的常用方法 *****************/
		//public int length()获取一个String对象的字符序列的长度
		/*System.out.println("Java".length());
		
		//public boolean equals(String s)比较当前String对象的字符序列与s是否相同
		System.out.println("world".equals("world"));
		
		//public boolean starts(ends)With(String s) 判断当前字符序列前缀(后缀)是否是字符序列s
		System.out.println("hello world".startsWith("he"));
		
		//public int compareTo(String s) 按字典序与字符序列s比较大小 返回差值
		System.out.println("hello".compareTo("java"));
		
		//public int compareToIgnoreCase(String s)忽略大小写 按字典序比较大小  返回差值
		System.out.println("Hello".compareToIgnoreCase("hello"));*/
		
		//public boolean contains(String s)判断当前字符序列是否包含字符序列s
		System.out.println("Application".contains("App"));
		
		// int indexOf(String s)从当前字符串0索引位置开始检索首次出现s的位置 返回该位置
		// int lastIndexOf(String s)从当前字符串0索引位置开始检索最后一次出现s的位置 返回该位置
		String str = "I am a good students.";
		System.out.println(str.indexOf("a"));
		System.out.println(str.lastIndexOf("d"));
		
		//String substring(int startpoint)复制当前字符串中从startpoint至最后位置的字符创建字符串
		//substring(int start,int end) 复制start到end-1上的字符创建新字符串
		System.out.println(str.substring(7));
		System.out.println(str.substring(5, str.length())); //下标从0开始
		
		//public String trim()得到新String对象是当前字符串去掉 前后 空格后的字符序列
		System.out.println(str.trim());
		
	}

}
