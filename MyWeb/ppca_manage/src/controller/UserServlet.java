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
 * 处理管理员查询用户信息的Servlet
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

	// 删除用户的操作
	public void delUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("进入UserServlet delUser方法");

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

	// 更新用户信息的操作
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("进入UserServlet update方法");

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

	// 查询所有用户的操作
	public void findAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("进入UserServlet findAllUser方法");

		int pageNO = Integer.parseInt(request.getParameter("pageNO"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		System.out.println("UserServlet pageNo 和 pageSize 分别是: "+pageNO + " " + pageSize);

		userModel = new UserModel();

		// 查询所有记录
		List<User> list = userModel.findAllUser();
		//System.out.println(list.size());

		// 分页查询数据
		users = userModel.findUserPage(pageNO, pageSize);

		// 封装分页数据 每页内容、当前页号、每页大小、总记录数、总页数
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

	// 通过手机号查询用户
	public void findUserPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("进入UserServlet findUserPhone方法");

		String phone = request.getParameter("phone");
		System.out.println("UserServlet phone: "+phone);

		userModel = new UserModel();
		user = userModel.findByPhone(phone);

		//System.out.println("phone:" + user.getPhone() + " username:" + user.getUsername());
		response.getWriter().write(JSONObject.toJSONString(user));
	}
}
