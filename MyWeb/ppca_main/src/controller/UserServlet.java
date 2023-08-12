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
 * ��������User��ز�����servlet
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
	
	//��ѯ�û���Ϣ   ������Ϣ
	public void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("���� UserServlet findUser");
		
		//�õ���ǰ��¼���û�
		User currentUser = (User) request.getSession().getAttribute("user");
		result = new JsonResult();
		//userModel = new UserModel();
		
		if(currentUser == null){
			//δ��¼  ��ʾ�û���¼
			result.setStatus(false);
			result.setErrorJson("����δ��¼�����ȵ�¼!");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}else{
			System.out.println("��ǰ�û�: "+currentUser.getUsername());
			
			//��ѯ��ǰ�û��Ļ�����Ϣ
			User user = userModel.findByPhone(currentUser.getPhone());
			
			if(user == null){
				result.setStatus(false);
				result.setErrorJson("��ѯ�û���Ϣʧ��...");
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

	//�û���¼�Ĳ���
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("���� UserServlet login");
		
		User loginUser = null;
		JsonResult loginJson = new JsonResult();
		//userModel = new UserModel();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("���ڵ�¼: "+username+" "+password);
		
		try {
			// ������֤��
			String checkcode1 = request.getParameter("checkCode");
			// ��session�л�ȡһ������֤���ֵ
			String checkcode2 = (String) request.getSession().getAttribute("checkcode");
			// Ϊ�˱�֤��֤��ֻʹ��һ�Σ�Ӧ��session�е���֤�����
			request.getSession().removeAttribute("checkcode");
			// У��һ������֤��
			if (!checkcode1.equalsIgnoreCase(checkcode2)) {
				loginJson.setStatus(false);
				loginJson.setErrorJson("��֤�����!");
				response.getWriter().write(JSONObject.toJSONString(loginJson)); //����ˢ����֤�룿�������� 
				//request.setAttribute("ErrorMsg", "��֤���������!");
				//����ת��
				//request.getRequestDispatcher("/login.html").forward(request, response);
				return;
			}
			
			//�����������ֻ��� ���ֻ��Ŷ�Ӧ��username��ֵ��loginUser����
			String phone = "^(1+[34578][0-9]{9})$";
			Pattern pattern = Pattern.compile(phone);
			Matcher matcher = pattern.matcher(username);
			boolean isPhone = matcher.matches();
			
			if( isPhone ){
				User user = userModel.findByPhone(username);
				username = user.getUsername();
				user = null;
			}

			// ��������  �ж��û��Ƿ����
			loginUser = userModel.login(username,password);

			if (loginUser == null) {
				// ��¼ʧ��  ���������Ϣ
				loginJson.setStatus(false);
				loginJson.setErrorJson("�û������������!");
				response.getWriter().write(JSONObject.toJSONString(loginJson));
				return ;
			} else {
				// �����û���Ϣ,���浽session��
				request.getSession().setAttribute("user", loginUser);
				
				// ��ס�û���
				String remember = request.getParameter("remember");
				if ("true".equals(remember)) {
					// �Ѿ���ѡ�˼�ס�û���
					Cookie cookie = new Cookie("remember", loginUser.getUsername());
					// ������Ч·��
					cookie.setPath("/ppca_main");
					// ������Чʱ�� 24Сʱ
					cookie.setMaxAge(60 * 60 * 24);
					// ��Cookie��д�������
					response.addCookie(cookie);
				}
				
				loginJson.setStatus(true);
				loginJson.setErrorJson("��¼�ɹ�!");
				response.getWriter().write(JSONObject.toJSONString(loginJson));
			}
		} catch (Exception e) {
			e.printStackTrace();
			loginJson.setStatus(false);
			loginJson.setErrorJson("��¼ʧ��!");
			response.getWriter().write(JSONObject.toJSONString(loginJson));
			return;
		}
		
	}
	
	//�û�ע��Ĳ���
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����UserServlet register");
		
		User newUser = new User();
		try {
			// ʹ��beanutils�����������ķ�װ ������������������������ͬ
			BeanUtils.populate(newUser, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.print("���û�: "+newUser.getUsername()+" "+newUser.getPhone());
		//System.out.println(newUser.getPassword());
		
		//userModel = new UserModel();
		result = new JsonResult();
		
		try {
			//��֤�û����ֻ��ź��û���Ψһ
			User exitUname = userModel.findByUname(newUser.getUsername());
			User exitPhone = userModel.findByPhone(newUser.getPhone());
			
			if( exitUname != null ){
				//�û����Ѵ���
				result.setStatus(false);
				result.setErrorJson("�û����Ѵ���");
				response.getWriter().write(JSONObject.toJSONString(result));
				return ;
			}
			
			if( exitPhone != null ){
				//�ֻ����ѱ�ע��
				result.setStatus(false);
				result.setErrorJson("�ֻ����ѱ�ע��");
				response.getWriter().write(JSONObject.toJSONString(result));
				return ;
			}

			userModel.addUser(newUser);
			result.setStatus(true);
			result.setErrorJson("ע��ɹ�");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("ע���˺�ʧ��...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		}

	}
	
	// �����û���Ϣ�Ĳ���  �����û������û�������Ϣ�޸�
	public void updateUser(HttpServletRequest request, HttpServletResponse response,String method) throws IOException {
		System.out.println("����UserServlet update����");

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

		System.out.println("�������޸ĵ��û�: "+user.getPhone()+" "+user.getUsername());
		
		try {
			userModel.updateUser(user,method);
			result.setStatus(true);
			result.setErrorJson("�޸ĳɹ�!");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("�޸�ʧ��...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		}

	}
	
	// ��ѯ�û���Ϣ ��ϸ��Ϣ
	public void getDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("���� UserServlet getDetail");

		// �õ���ǰ��¼���û�
		User currentUser = (User) request.getSession().getAttribute("user");
		JsonResult msg = new JsonResult();

		if (currentUser == null) {
			// δ��¼ ��ʾ�û���¼
			msg.setStatus(false);
			msg.setErrorJson("����δ��¼�����ȵ�¼!!!");
			response.getWriter().write(JSONObject.toJSONString(msg));
			return;
		} else {
			System.out.println("��ǰ�û�: " + currentUser.getUsername() + " " + currentUser.getPhone());

			// ��ѯ��ǰ�û��Ļ�����Ϣ
			User user = userModel.getDetailByPhone(currentUser.getPhone());

			if (user == null) {
				msg.setStatus(false);
				msg.setErrorJson("��ѯ�û���ϸ��Ϣʧ��...");
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
	
	//��ѯ�û��Ƿ�ʵ��  ����USERINFO�Ƿ��м�¼
	public void getRealUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����UserServlet getRealUser");

		// �õ���ǰ��¼���û�
		User currentUser = (User) request.getSession().getAttribute("user");
		result = new JsonResult();

		if (currentUser == null) {
			// δ��¼ ��ʾ�û���¼
			result.setStatus(false);
			result.setErrorJson("����δ��¼�����ȵ�¼!!!");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		} else {
			System.out.println("��ǰ�û�: " + currentUser.getUsername() + " " + currentUser.getPhone());

			// ��ѯ��ǰ�û��Ļ�����Ϣ
			User user = userModel.findRealUser(currentUser.getPhone());

			if (user == null) {
				result.setStatus(false);
				result.setErrorJson("����δʵ����֤�����������֤...");
				response.getWriter().write(JSONObject.toJSONString(result));
				return;
			}
			
			result.setStatus(true);
			//result.setErrorJson("..");
			result.setContent(user);
			response.getWriter().write(JSONObject.toJSONString(result));
		}
	}
	
	//�û�ʵ����֤  ��USERINFO�����������
	public void userCertify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����UserServlet userCertify");

		User newUser = new User();
		User currentUser = (User) request.getSession().getAttribute("user");
		//userModel = new UserModel();
		JsonResult result = new JsonResult();
		
		try {
			// ʹ��beanutils�����������ķ�װ ������������������������ͬ
			BeanUtils.populate(newUser, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		System.out.print("�û���֤: " + newUser.getPhone() + " " + newUser.getRealname());

		try {
			if( currentUser.getPhone().equals( newUser.getPhone() ) ){
				result.setStatus(false);
				result.setErrorJson("�ֻ����ѱ���...");
				response.getWriter().write(JSONObject.toJSONString(result));
				return;
			}
			
			// ��֤�û���ѧ�ź��ֻ���Ψһ
			User exitStuid = userModel.findStuid(newUser.getStuid());
			User exitPhone = userModel.findPhone(newUser.getPhone());

			if (exitStuid != null) {
				result.setStatus(false);
				result.setErrorJson("ѧ���ѱ���...");
				response.getWriter().write(JSONObject.toJSONString(result));
				return;
			}

			if (exitPhone != null) {
				result.setStatus(false);
				result.setErrorJson("�ֻ����ѱ���...");
				response.getWriter().write(JSONObject.toJSONString(result));
				return;
			}

			userModel.addUserInfo(newUser);
			result.setStatus(true);
			result.setErrorJson("ʵ����֤�ɹ�!");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("ʵ����֤ʧ��...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		}

	}
	
	//�˳���¼
	public void signOut(HttpServletRequest request, HttpServletResponse response) {
		//����session
		request.getSession().invalidate();
	}
	
}
