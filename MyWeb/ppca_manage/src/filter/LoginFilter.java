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

/**����¼�޷�����pages/
 * ����request��forword���� 
 */
public class LoginFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//�ж��û��Ƿ��¼��ֻ��Ҫ�ж�session���Ƿ���user
		HttpServletRequest hreq = (HttpServletRequest) request ;
		HttpServletResponse hrsp = (HttpServletResponse) response;
		//��ȡsession�е�existUser����
		Admin user = (Admin) hreq.getSession().getAttribute("existAdmin");
		if(user != null){
			//�ѵ�¼
			chain.doFilter(hreq, hrsp);
		}else{
			//δ��¼, �ض��򵽵�¼ҳ��
			hrsp.sendRedirect(hreq.getContextPath()+"/Login.jsp");
			//System.out.println("·����:"+hreq.getContextPath());
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
