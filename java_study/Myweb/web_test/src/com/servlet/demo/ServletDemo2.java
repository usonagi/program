package com.servlet.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet 模板
 * Response响应中文的处理
 */
public class ServletDemo2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//useByte(response);
		useChar(response);
	}

	/*使用字符流输出中文一定会出现乱码 response默认的缓冲区编码不支持中文
	 *解决方法：设置response获得字符流的缓冲区的编码与浏览器默认打开采用的字符集一致即可
	 */
	private void useChar(HttpServletResponse response) throws IOException, UnsupportedEncodingException {
		//设置浏览器默认打开采用的字符集
		//response.setHeader("Content-Type", "text/html);charset=UTF-8");
		//设置response获得字符流的缓冲区的编码
		//response.setCharacterEncoding("UTF-8");
		
		//使用以下方法替换上两行  简化代码
		response.setContentType("text/html;charset=UTF-8");
		
		response.getWriter().println("演示使用字符流输出中文...");
	}
	
	/*使用字节流输出中文不一定会乱码  
	    解决方法：将中文转换成字节数组的字符集设置与浏览器默认打开采用的字符集一致即可
	*/
	private void useByte(HttpServletResponse response) throws IOException, UnsupportedEncodingException {
		//使用字节流的方式输出中文
		ServletOutputStream outputStream = response.getOutputStream();
		//设置浏览器默认打开时的字符集
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		//设置中文转成字节数组的字符集编码
		outputStream.write("演示使用字节流输出中文...".getBytes("UTF-8"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
