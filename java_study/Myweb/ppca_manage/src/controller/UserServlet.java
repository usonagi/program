package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSONObject;

import domain.PageBean;
import domain.User;
import model.UserModel;

/**
 * �������Ա��ѯ�û���Ϣ��Servlet
 */
public class UserServlet extends HttpServlet {

	User user = null;
	UserModel userModel = null;
	List<User> users = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");

		System.err.println(method);

		switch (method) {
		case "findAllUser":
			findAllUser(request, response);
			break;

		case "findUserPhone":
			findUserPhone(request, response);
			break;

		case "UpdateUser":
			updateUser(request, response);
			break;

		case "delUser":
			delUser(request, response);
			break;

		default:
			break;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// ɾ���û��Ĳ���
	public void delUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("����UserServlet delUser����");

		userModel = new UserModel();
		user = new User();

		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		System.out.println(user.getPhone());

		userModel.delUser(user.getPhone());
	}

	// �����û���Ϣ�Ĳ���
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("����UserServlet update����");

		userModel = new UserModel();
		user = new User();

		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		userModel.updateUser(user);

	}

	// ��ѯ�����û��Ĳ���
	public void findAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("����UserServlet findAllUser����");

		int pageNO = Integer.parseInt(request.getParameter("pageNO"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		System.out.println("UserServlet pageNo �� pageSize �ֱ���: "+pageNO + " " + pageSize);

		userModel = new UserModel();

		// ��ѯ���м�¼
		List<User> list = userModel.findAllUser();
		//System.out.println(list.size());

		// ��ҳ��ѯ����
		users = userModel.findUserPage(pageNO, pageSize);

		// ��װ��ҳ���� ÿҳ���ݡ���ǰҳ�š�ÿҳ��С���ܼ�¼������ҳ��
		int totalCount = list.size();
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);

		PageBean<User> pageInfo = new PageBean<User>();
		pageInfo.setContent(users);
		pageInfo.setPageNo(pageNO);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotalCount(list.size());
		pageInfo.setTotalPage(totalPage);

		response.getWriter().write(JSONObject.toJSONString(pageInfo));
	}

	// ͨ���ֻ��Ų�ѯ�û�
	public void findUserPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("����UserServlet findUserPhone����");

		String phone = request.getParameter("phone");
		System.out.println("UserServlet phone: "+phone);

		userModel = new UserModel();
		user = userModel.findByPhone(phone);

		//System.out.println("phone:" + user.getPhone() + " username:" + user.getUsername());
		response.getWriter().write(JSONObject.toJSONString(user));
	}
}
