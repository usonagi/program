package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSONObject;

import domain.Activity;
import domain.JsonResult;
import domain.User;
import model.ActivityModel;

/**
 * 用于处理activity相关操作的servlet
 */
public class ActivityServlet extends HttpServlet {

	JsonResult result = null;
	ActivityModel actModel = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		System.out.println(method);
		
		switch (method) {
		case "findActivity":
			findActByUname(request, response);
			break;

		case "addActivity":
			addActivity(request, response);
			break;

		case "delActivity":
			delActivity(request, response);
			break;

		case "findByActName":
			findByActName(request, response);
			break;

		case "findByActId":
			findByActId(request, response);
			break;
			
		case "updateAct":
			updateAct(request,response);
			break;
			
		case "visitFindAct":
			visitFind(request,response);
			break;

		default:
			break;
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//根据用户名查询 所有的activity
	protected void findActByUname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入 ActivityServlet fndActByUname");
		
		actModel = new ActivityModel();
		result = new JsonResult();
		List<Activity> acts = new ArrayList<Activity>();
		
		User currentUser = (User) request.getSession().getAttribute("user");
		System.out.println(currentUser.getUsername());
		
		acts = actModel.findActDetail(currentUser.getUsername());
		
		if(acts != null){
			result.setStatus(true);
			result.setErrorJson("查询活动记录成功...");
			result.setContent(acts);
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
			
		}else {
			result.setStatus(false);
			result.setErrorJson("查询活动记录失败...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
	}

	//用户添加活动的操作
	protected void addActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入ActivityServlet addActivity");
		
		Activity activity = new Activity();
		actModel = new ActivityModel();
		result = new JsonResult();
		
		try {
			//将请求参数封装到Activity中  参数1--对象   参数2--集合  获得所有请求参数的集合
			BeanUtils.populate(activity, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println(activity.getAct_id()+" "+activity.getAct_name()+" "+activity.getUname());
		
		try {
			actModel.addActivity(activity);
			result.setStatus(true);
			result.setErrorJson("发布活动成功...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("发布活动失败...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
		
	}
	
	//用户撤销活动的操作
	protected void delActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入ActivityServlet delActivity");
		
		try {
			String act_id = request.getParameter("actId");
			System.out.println("即将被删除的活动编号: " +act_id);
			
			actModel = new ActivityModel();
			actModel.delActivity(act_id);
			
			result.setStatus(true);
			result.setErrorJson("撤销活动成功...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("撤销活动失败...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
	}
	
	//根据活动编号修改activity的操作
	protected void updateAct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入ActivityServlet updateAct");
		
		Activity updateAct = new Activity();
		result = new JsonResult();
		
		try {
			BeanUtils.populate(updateAct, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println("即将被修改的活动编号、名: "+updateAct.getAct_id()+" "+updateAct.getAct_name());
		
		try {
			actModel.updateActivity(updateAct);
			result.setStatus(true);
			result.setErrorJson("修改活动成功...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("修改活动失败...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
		
	}
	
	//根据活动名查询活动的操作
	protected void findByActName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入ActivityServlet findByActName");

		List<Activity> activities;
		result = new JsonResult();

		User currentUser = (User) request.getSession().getAttribute("user");
		String act_name = request.getParameter("act_name");

		String username = currentUser.getUsername();

		System.out.println(username + " " + act_name);

		activities = actModel.findByActName(username, act_name);

		/*
		 * for(int i=0;i<activities.size();i++) {
		 * System.out.println(activities.get(i).getAct_name()); }
		 */

		if (activities != null) {

			result.setStatus(true);
			result.setErrorJson("查询活动记录成功...");
			result.setContent(activities);
			response.getWriter().write(JSONObject.toJSONString(result));
			return;

		} else {
			result.setStatus(false);
			result.setErrorJson("查询活动记录失败...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		}

	}
	
	protected void visitFind(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入ActivityServlet visitFind");

		List<Activity> activities;
		result = new JsonResult();
		actModel = new ActivityModel();

		String act_name = request.getParameter("act_name");

		System.out.println( act_name);
		
		activities = actModel.findActName(act_name);
		
		if (activities != null) {

			result.setStatus(true);
			result.setErrorJson("查询活动记录成功...");
			result.setContent(activities);
			response.getWriter().write(JSONObject.toJSONString(result));
			return;

		} else {
			result.setStatus(false);
			result.setErrorJson("查询活动记录失败...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		}

	}

	// 根据活动编号查询活动的操作
	protected void findByActId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入ActivityServlet findByActId");
		
		Activity activity = new Activity();
		result = new JsonResult();
		//actModel = new ActivityModel();
		String act_id = request.getParameter("act_id");
		
		System.out.println("当前查询活动编号: "+act_id);
		
		activity = actModel.findById(act_id);
		
		//System.out.println(activity.getAct_name()+" "+activity.getUname());
		
		if(activity != null){
			result.setStatus(true);
			result.setErrorJson("查询活动记录成功...");
			result.setContent(activity);
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
			
		}else {
			result.setStatus(false);
			result.setErrorJson("查询活动记录失败...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
	}

}
