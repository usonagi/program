

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
	
	// ����get��ʽ������
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//�����û��������� ��request���������getParameter��������key��ȡ����
		String user = request.getParameter("name");
		String pwd = request.getParameter("password");
		System.out.println(user+"  "+pwd);
		
		//�������ݽ���
		if(user.equals("xmy")&& pwd.equals("123"))
		{
			//ҳ����ת
			//ͨ������ַ�������תҳ��  /��ʾ���·�� �ṩ��תҳ���·����ʹ��forword������ת
			request.getRequestDispatcher("/login.html").forward(request, response);;
		}
		
	}
	//����post��ʽ������
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
