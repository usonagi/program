package javaExample;

/********** �ַ�������   ***********/

public class EncryptAndDecrypt {

	String encrypt(String sourceString,String password)
	{
		char [] p = password.toCharArray();
		int n = p.length;
		char [] c = sourceString.toCharArray();
		int m = c.length;
		for(int k = 0;k < m; k++)
		{
			int mina = c[k] + p[k%n];		//����
			c[k] = (char)mina;
		}
		return new String(c);
	}
	
	String decrypt(String sourceString,String password)
	{
		char [] p = password.toCharArray();
		int n = p.length;
		char [] c = sourceString.toCharArray();
		int m = c.length;
		for(int k = 0;k < m; k++)
		{
			int mina = c[k] - p[k%n];		//����
			c[k] = (char)mina;
		}
		return new String(c);
	}
}
