package javaExample;

/********** ���ַ����������� ************/

public class SortString {
	public static void sortString(String s[])
	{
		for(int i = 0;i < s.length-1; i++) 
		{
			for(int j = i+1; j < s.length; j++)
			{
				if(s[j].compareTo(s[i]) < 0 ) { //��s[j]��s[i]С ��������
					String str = s[j];
					s[j] = s[i];
					s[i] = str;
				}
				//  ���Դ�Сд ��������
				/*if(s[j].compareToIgnoreCase(s[i]) < 0 ) { //��s[j]��s[i]С ��������
					String str = s[j];
					s[j] = s[i];
					s[i] = str;
				}*/
			} //for
		} //for
	} //sortString()
}
