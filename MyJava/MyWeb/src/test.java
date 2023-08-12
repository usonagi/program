

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.java_cup.internal.runtime.Symbol;


@WebServlet("/login")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//		http://localhost:8080/MyWeb/login?name="xmy"&password="123"
	
	// 接受get方式的请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//接收用户名和密码 从request请求对象中getParameter方法根据key获取数据
		String user = request.getParameter("name");
		String pwd = request.getParameter("password");
		System.out.println(user+"  "+pwd);
		
		//进行数据交换
		if(user.equals("xmy")&& pwd.equals("123"))
		{
			//页面跳转
			//通过请求分发器来跳转页面  /表示相对路径 提供跳转页面的路径，使用forword方法跳转
			request.getRequestDispatcher("/login.html").forward(request, response);;
		}
		
	}
	//接受post方式的请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
