package javaExample;

/********** 对字符串进行排序 ************/

public class SortString {
	public static void sortString(String s[])
	{
		for(int i = 0;i < s.length-1; i++) 
		{
			for(int j = i+1; j < s.length; j++)
			{
				if(s[j].compareTo(s[i]) < 0 ) { //若s[j]比s[i]小 交换两者
					String str = s[j];
					s[j] = s[i];
					s[i] = str;
				}
				//  忽略大小写 进行排序
				/*if(s[j].compareToIgnoreCase(s[i]) < 0 ) { //若s[j]比s[i]小 交换两者
					String str = s[j];
					s[j] = s[i];
					s[i] = str;
				}*/
			} //for
		} //for
	} //sortString()
}
