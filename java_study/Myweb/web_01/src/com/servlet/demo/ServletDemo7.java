package com.servlet.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 
 */
public class ServletDemo7 extends HttpServlet{

	@Override
	public void init() throws ServletException {
		// ��ServletDemo7����������ʼ��һ��ֵ
		this.getServletContext().setAttribute("name", "�¶���");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*ServletContext context = this.getServletContext();
		String mimeType = context.getMimeType("xx.txt");
		System.out.println("mimeType");*/
		
		//�����ʼ����ֵ  ����ServletContext��ȫ�ֵģ�������Demo6��ͬ�����
		String name = (String) this.getServletContext().getAttribute("name");
		System.out.println("������"+ name);
		
		//ReadFile();
	}

	
	/*
	 * ʹ��ServletContext��ȡweb��Ŀ�µ��ļ�
	 * ÿ�����������������db.properties�ſ� ����ᱨ��
	 */
	private void ReadFile() throws IOException{
		Properties properties = new Properties();
		//InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/db.properties");
		String path = this.getServletContext().getRealPath("/WEB-INF/db.properties");
		System.out.println(path);
		InputStream is = new FileInputStream(path);
		properties.load(is);
		
		String driver = properties.getProperty("driverClassName");
		String url = properties.getProperty("url");
		String user = properties.getProperty("username");
		String pwd = properties.getProperty("password");
		System.out.println(driver+" "+url+" "+user+" "+pwd);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
