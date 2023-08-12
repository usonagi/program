package filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/*
 * 用于处理请求中文乱码的filter
 */
public class EncodingFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 处理请求乱码
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletRequest myRequest = new MyRequest(httpServletRequest);

		// 处理响应乱码
		response.setContentType("text/html;charset=utf-8");

		chain.doFilter(myRequest, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}

// 自定义request对象
class MyRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;

	private boolean hasEncode;

	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	// 增强request.getParameter()方法
	@Override
	public Map getParameterMap() {
		// 获取请求方式
		String method = request.getMethod();
		if (method.equalsIgnoreCase("post")) {
			// post方式的请求
			try {
				// 处理post方式乱码
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else if (method.equalsIgnoreCase("get")) {
			// get方式的请求
			Map<String, String[]> parameterMap = request.getParameterMap();
			if (!hasEncode) { // 确保get手动编码只运行一次
				for (String parameterName : parameterMap.keySet()) {
					String[] values = parameterMap.get(parameterName);
					if (values != null) {
						for (int i = 0; i < values.length; i++) {
							try {
								// 处理get方式乱码
								values[i] = new String(
										values[i].getBytes("ISO-8859-1"),
										"utf-8");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
					}
				}
				hasEncode = true;
			}
			return parameterMap;
		}

		return super.getParameterMap();
	}

	@Override
	public String getParameter(String name) {
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		if (values == null) {
			return null;
		}
		return values[0]; // 取回参数的第一个
	}

	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		return values;
	}
}
