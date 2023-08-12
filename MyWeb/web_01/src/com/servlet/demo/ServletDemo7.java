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
		// 当ServletDemo7被创建，初始化一个值
		this.getServletContext().setAttribute("name", "新东方");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*ServletContext context = this.getServletContext();
		String mimeType = context.getMimeType("xx.txt");
		System.out.println("mimeType");*/
		
		//输出初始化的值  由于ServletContext是全局的，可以在Demo6中同样输出
		String name = (String) this.getServletContext().getAttribute("name");
		System.out.println("姓名："+ name);
		
		//ReadFile();
	}

	
	/*
	 * 使用ServletContext读取web项目下的文件
	 * 每次启动服务器后添加db.properties才可 否则会报错
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
