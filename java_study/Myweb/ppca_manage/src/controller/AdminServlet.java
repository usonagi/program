package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import domain.Admin;
import model.AdminModel;

/**
 * ����Admin��¼���˳����ܵ�servlet
 */
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if("login".equals(method)){
			login(request,response);
		}
		
		if("shutDown".equals(method)){
			shutDown(request,response);
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	//��¼����
	private void login(HttpServletRequest request, HttpServletResponse response) {
		try {
			//1����������
			request.setCharacterEncoding("UTF-8");
			String ANO = request.getParameter("ANO");
			String APWD = request.getParameter("APWD");
			
			System.out.println("��ǰ��¼���ʺš�����: "+ANO+" "+APWD);
			
			//�ǿ�У��  �����Զ���js����ʵ��
			/*if(StringUtils.isBlank(ANO)){
				request.setAttribute("NameErrorMsg", "�û�������Ϊ��!");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return ;
			}
			if(StringUtils.isBlank(APWD)){
				request.setAttribute("PwdErrorMsg", "���벻��Ϊ��!");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return ;
			}*/
			
			//====һ������֤���У��=====
			//������֤��
			String checkcode1 = request.getParameter("checkcode");
			//��session�л�ȡһ������֤���ֵ
			String checkcode2 = (String) request.getSession().getAttribute("checkcode");
			//Ϊ�˱�֤��֤��ֻʹ��һ�Σ�Ӧ��session�е���֤�����
			request.getSession().removeAttribute("checkcode");
			//У��һ������֤��
			if(!checkcode1.equalsIgnoreCase(checkcode2)){
				request.setAttribute("ErrorMsg", "��֤���������!");
				//����ת��
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return ;
			}
			
			//System.out.println("·����:"+request.getContextPath());
			
			//2����װ����
			Admin admin = new Admin();
			admin.setAno(ANO);
			admin.setApwd(APWD);
			//3����������
			AdminModel  adminModel = new AdminModel();
			Admin existAdmin = adminModel.login(admin);
			
			//4��ҳ����ת
			if(existAdmin == null){
				//��¼ʧ��  ��request���б��������Ϣ
				request.setAttribute("ErrorMsg", "�û������������!");
				//ʹ������ת������ҳ����ת
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
			}else{
				//�����û���Ϣ,���浽�Ự��
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("existAdmin", existAdmin);
				//��ס�û���
				String remember = request.getParameter("remember");
				if("true".equals(remember)){
					//�Ѿ���ѡ�˼�ס�û��� 
					Cookie cookie = new Cookie("remember",existAdmin.getAno());
					//������Ч·��
					cookie.setPath("/ppca_manage");
					//������Чʱ�� 24Сʱ
					cookie.setMaxAge(60*60*24);
					//��Cookie��д�������
					response.addCookie(cookie);
				}
				//�ض��򵽳ɹ�ҳ��
				response.sendRedirect("/ppca_manage/pages/home.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	//�˳�ϵͳ
	private void shutDown(HttpServletRequest request, HttpServletResponse response) {
		//����session
		request.getSession().invalidate();
		
	}
}
