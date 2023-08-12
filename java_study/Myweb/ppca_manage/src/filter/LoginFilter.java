package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Admin;

/**不登录无法访问pages/
 * 拦截request和forword请求 
 */
public class LoginFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//判断用户是否登录，只需要判断session中是否有user
		HttpServletRequest hreq = (HttpServletRequest) request ;
		HttpServletResponse hrsp = (HttpServletResponse) response;
		//获取session中的existUser对象
		Admin user = (Admin) hreq.getSession().getAttribute("existAdmin");
		if(user != null){
			//已登录
			chain.doFilter(hreq, hrsp);
		}else{
			//未登录, 重定向到登录页面
			hrsp.sendRedirect(hreq.getContextPath()+"/Login.jsp");
			//System.out.println("路径名:"+hreq.getContextPath());
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
