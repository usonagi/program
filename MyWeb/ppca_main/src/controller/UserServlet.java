package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSONObject;

import domain.JsonResult;
import domain.User;
import model.UserModel;

/**
 * 用来处理User相关操作的servlet
 */
public class UserServlet extends HttpServlet {
	
	private UserModel userModel = new UserModel();
	private JsonResult result = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("method");
		System.out.println(method);
		
		switch (method) {
		case "findUser":
			findUser(request,response);
			break;
			
		case "login":
			login(request,response);
			break;
			
		case "register":
			register(request, response);
			break;
			
		case "updateUser":
			updateUser(request,response,method);
			break;
			
		case "getUserDetail":
			getDetail(request,response);
			break;
			
		case "getRealUser":
			getRealUser(request,response);
			break;
			
		case "certify":
			userCertify(request,response);
			break;
			
		case "signOut":
			signOut(request,response);
			break;
			
		case "updateUserDetail":
			updateUser(request,response,method);
			break;

		default:
			break;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//查询用户信息   基本信息
	public void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入 UserServlet findUser");
		
		//得到当前登录的用户
		User currentUser = (User) request.getSession().getAttribute("user");
		result = new JsonResult();
		//userModel = new UserModel();
		
		if(currentUser == null){
			//未登录  提示用户登录
			result.setStatus(false);
			result.setErrorJson("您还未登录，请先登录!");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}else{
			System.out.println("当前用户: "+currentUser.getUsername());
			
			//查询当前用户的基本信息
			User user = userModel.findByPhone(currentUser.getPhone());
			
			if(user == null){
				result.setStatus(false);
				result.setErrorJson("查询用户信息失败...");
				response.getWriter().write(JSONObject.toJSONString(result));
				return ;
			}
			
			System.out.println(user.getUsername()+" "+user.getPhone()+" "+user.getSex()+" "+user.getEmail());
			
			result.setStatus(true);
			result.setContent(user);
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
	}

	//用户登录的操作
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入 UserServlet login");
		
		User loginUser = null;
		JsonResult loginJson = new JsonResult();
		//userModel = new UserModel();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("正在登录: "+username+" "+password);
		
		try {
			// 接收验证码
			String checkcode1 = request.getParameter("checkCode");
			// 从session中获取一次性验证码的值
			String checkcode2 = (String) request.getSession().getAttribute("checkcode");
			// 为了保证验证码只使用一次，应将session中的验证码清空
			request.getSession().removeAttribute("checkcode");
			// 校验一次性验证码
			if (!checkcode1.equalsIgnoreCase(checkcode2)) {
				loginJson.setStatus(false);
				loginJson.setErrorJson("验证码错误!");
				response.getWriter().write(JSONObject.toJSONString(loginJson)); //不能刷新验证码？？？？？ 
				//request.setAttribute("ErrorMsg", "验证码输入错误!");
				//请求转发
				//request.getRequestDispatcher("/login.html").forward(request, response);
				return;
			}
			
			//如果输入的是手机号 则将手机号对应的username赋值给loginUser对象
			String phone = "^(1+[34578][0-9]{9})$";
			Pattern pattern = Pattern.compile(phone);
			Matcher matcher = pattern.matcher(username);
			boolean isPhone = matcher.matches();
			
			if( isPhone ){
				User user = userModel.findByPhone(username);
				username = user.getUsername();
				user = null;
			}

			// 处理数据  判断用户是否存在
			loginUser = userModel.login(username,password);

			if (loginUser == null) {
				// 登录失败  保存错误信息
				loginJson.setStatus(false);
				loginJson.setErrorJson("用户名或密码错误!");
				response.getWriter().write(JSONObject.toJSONString(loginJson));
				return ;
			} else {
				// 保存用户信息,保存到session中
				request.getSession().setAttribute("user", loginUser);
				
				// 记住用户名
				String remember = request.getParameter("remember");
				if ("true".equals(remember)) {
					// 已经勾选了记住用户名
					Cookie cookie = new Cookie("remember", loginUser.getUsername());
					// 设置有效路径
					cookie.setPath("/ppca_main");
					// 设置有效时长 24小时
					cookie.setMaxAge(60 * 60 * 24);
					// 将Cookie回写到浏览器
					response.addCookie(cookie);
				}
				
				loginJson.setStatus(true);
				loginJson.setErrorJson("登录成功!");
				response.getWriter().write(JSONObject.toJSONString(loginJson));
			}
		} catch (Exception e) {
			e.printStackTrace();
			loginJson.setStatus(false);
			loginJson.setErrorJson("登录失败!");
			response.getWriter().write(JSONObject.toJSONString(loginJson));
			return;
		}
		
	}
	
	//用户注册的操作
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入UserServlet register");
		
		User newUser = new User();
		try {
			// 使用beanutils完成请求参数的封装 参数名必须与对象的属性名相同
			BeanUtils.populate(newUser, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.print("新用户: "+newUser.getUsername()+" "+newUser.getPhone());
		//System.out.println(newUser.getPassword());
		
		//userModel = new UserModel();
		result = new JsonResult();
		
		try {
			//保证用户的手机号和用户名唯一
			User exitUname = userModel.findByUname(newUser.getUsername());
			User exitPhone = userModel.findByPhone(newUser.getPhone());
			
			if( exitUname != null ){
				//用户名已存在
				result.setStatus(false);
				result.setErrorJson("用户名已存在");
				response.getWriter().write(JSONObject.toJSONString(result));
				return ;
			}
			
			if( exitPhone != null ){
				//手机号已被注册
				result.setStatus(false);
				result.setErrorJson("手机号已被注册");
				response.getWriter().write(JSONObject.toJSONString(result));
				return ;
			}

			userModel.addUser(newUser);
			result.setStatus(true);
			result.setErrorJson("注册成功");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("注册账号失败...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		}

	}
	
	// 更新用户信息的操作  根据用户名对用户基本信息修改
	public void updateUser(HttpServletRequest request, HttpServletResponse response,String method) throws IOException {
		System.out.println("进入UserServlet update方法");

		//userModel = new UserModel();
		User user = new User();
		result = new JsonResult();

		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		System.out.println("即将被修改的用户: "+user.getPhone()+" "+user.getUsername());
		
		try {
			userModel.updateUser(user,method);
			result.setStatus(true);
			result.setErrorJson("修改成功!");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("修改失败...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		}

	}
	
	// 查询用户信息 详细信息
	public void getDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入 UserServlet getDetail");

		// 得到当前登录的用户
		User currentUser = (User) request.getSession().getAttribute("user");
		JsonResult msg = new JsonResult();

		if (currentUser == null) {
			// 未登录 提示用户登录
			msg.setStatus(false);
			msg.setErrorJson("您还未登录，请先登录!!!");
			response.getWriter().write(JSONObject.toJSONString(msg));
			return;
		} else {
			System.out.println("当前用户: " + currentUser.getUsername() + " " + currentUser.getPhone());

			// 查询当前用户的基本信息
			User user = userModel.getDetailByPhone(currentUser.getPhone());

			if (user == null) {
				msg.setStatus(false);
				msg.setErrorJson("查询用户详细信息失败...");
				response.getWriter().write(JSONObject.toJSONString(msg));
				return;
			}

			System.out.println(
					user.getUsername() + " " + user.getPhone() + " " + user.getRealname() + " " + user.getStuid());

			msg.setStatus(true);
			msg.setContent(user);
			response.getWriter().write(JSONObject.toJSONString(msg));
			return;
		}
	}
	
	//查询用户是否实名  查找USERINFO是否有记录
	public void getRealUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入UserServlet getRealUser");

		// 得到当前登录的用户
		User currentUser = (User) request.getSession().getAttribute("user");
		result = new JsonResult();

		if (currentUser == null) {
			// 未登录 提示用户登录
			result.setStatus(false);
			result.setErrorJson("您还未登录，请先登录!!!");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		} else {
			System.out.println("当前用户: " + currentUser.getUsername() + " " + currentUser.getPhone());

			// 查询当前用户的基本信息
			User user = userModel.findRealUser(currentUser.getPhone());

			if (user == null) {
				result.setStatus(false);
				result.setErrorJson("您还未实名认证，请先完成认证...");
				response.getWriter().write(JSONObject.toJSONString(result));
				return;
			}
			
			result.setStatus(true);
			//result.setErrorJson("..");
			result.setContent(user);
			response.getWriter().write(JSONObject.toJSONString(result));
		}
	}
	
	//用户实名认证  在USERINFO表中添加数据
	public void userCertify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入UserServlet userCertify");

		User newUser = new User();
		User currentUser = (User) request.getSession().getAttribute("user");
		//userModel = new UserModel();
		JsonResult result = new JsonResult();
		
		try {
			// 使用beanutils完成请求参数的封装 参数名必须与对象的属性名相同
			BeanUtils.populate(newUser, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		System.out.print("用户认证: " + newUser.getPhone() + " " + newUser.getRealname());

		try {
			if( currentUser.getPhone().equals( newUser.getPhone() ) ){
				result.setStatus(false);
				result.setErrorJson("手机号已被绑定...");
				response.getWriter().write(JSONObject.toJSONString(result));
				return;
			}
			
			// 保证用户的学号和手机号唯一
			User exitStuid = userModel.findStuid(newUser.getStuid());
			User exitPhone = userModel.findPhone(newUser.getPhone());

			if (exitStuid != null) {
				result.setStatus(false);
				result.setErrorJson("学号已被绑定...");
				response.getWriter().write(JSONObject.toJSONString(result));
				return;
			}

			if (exitPhone != null) {
				result.setStatus(false);
				result.setErrorJson("手机号已被绑定...");
				response.getWriter().write(JSONObject.toJSONString(result));
				return;
			}

			userModel.addUserInfo(newUser);
			result.setStatus(true);
			result.setErrorJson("实名认证成功!");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("实名认证失败...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		}

	}
	
	//退出登录
	public void signOut(HttpServletRequest request, HttpServletResponse response) {
		//销毁session
		request.getSession().invalidate();
	}
	
}
