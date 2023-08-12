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
 * ���ڴ���activity��ز�����servlet
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
	
	//�����û�����ѯ ���е�activity
	protected void findActByUname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("���� ActivityServlet fndActByUname");
		
		actModel = new ActivityModel();
		result = new JsonResult();
		List<Activity> acts = new ArrayList<Activity>();
		
		User currentUser = (User) request.getSession().getAttribute("user");
		System.out.println(currentUser.getUsername());
		
		acts = actModel.findActDetail(currentUser.getUsername());
		
		if(acts != null){
			result.setStatus(true);
			result.setErrorJson("��ѯ���¼�ɹ�...");
			result.setContent(acts);
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
			
		}else {
			result.setStatus(false);
			result.setErrorJson("��ѯ���¼ʧ��...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
	}

	//�û���ӻ�Ĳ���
	protected void addActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����ActivityServlet addActivity");
		
		Activity activity = new Activity();
		actModel = new ActivityModel();
		result = new JsonResult();
		
		try {
			//�����������װ��Activity��  ����1--����   ����2--����  ���������������ļ���
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
			result.setErrorJson("������ɹ�...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("�����ʧ��...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
		
	}
	
	//�û�������Ĳ���
	protected void delActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����ActivityServlet delActivity");
		
		try {
			String act_id = request.getParameter("actId");
			System.out.println("������ɾ���Ļ���: " +act_id);
			
			actModel = new ActivityModel();
			actModel.delActivity(act_id);
			
			result.setStatus(true);
			result.setErrorJson("������ɹ�...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("�����ʧ��...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
	}
	
	//���ݻ����޸�activity�Ĳ���
	protected void updateAct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����ActivityServlet updateAct");
		
		Activity updateAct = new Activity();
		result = new JsonResult();
		
		try {
			BeanUtils.populate(updateAct, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println("�������޸ĵĻ��š���: "+updateAct.getAct_id()+" "+updateAct.getAct_name());
		
		try {
			actModel.updateActivity(updateAct);
			result.setStatus(true);
			result.setErrorJson("�޸Ļ�ɹ�...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setErrorJson("�޸Ļʧ��...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
		
	}
	
	//���ݻ����ѯ��Ĳ���
	protected void findByActName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����ActivityServlet findByActName");

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
			result.setErrorJson("��ѯ���¼�ɹ�...");
			result.setContent(activities);
			response.getWriter().write(JSONObject.toJSONString(result));
			return;

		} else {
			result.setStatus(false);
			result.setErrorJson("��ѯ���¼ʧ��...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		}

	}
	
	protected void visitFind(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����ActivityServlet visitFind");

		List<Activity> activities;
		result = new JsonResult();
		actModel = new ActivityModel();

		String act_name = request.getParameter("act_name");

		System.out.println( act_name);
		
		activities = actModel.findActName(act_name);
		
		if (activities != null) {

			result.setStatus(true);
			result.setErrorJson("��ѯ���¼�ɹ�...");
			result.setContent(activities);
			response.getWriter().write(JSONObject.toJSONString(result));
			return;

		} else {
			result.setStatus(false);
			result.setErrorJson("��ѯ���¼ʧ��...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return;
		}

	}

	// ���ݻ��Ų�ѯ��Ĳ���
	protected void findByActId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����ActivityServlet findByActId");
		
		Activity activity = new Activity();
		result = new JsonResult();
		//actModel = new ActivityModel();
		String act_id = request.getParameter("act_id");
		
		System.out.println("��ǰ��ѯ����: "+act_id);
		
		activity = actModel.findById(act_id);
		
		//System.out.println(activity.getAct_name()+" "+activity.getUname());
		
		if(activity != null){
			result.setStatus(true);
			result.setErrorJson("��ѯ���¼�ɹ�...");
			result.setContent(activity);
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
			
		}else {
			result.setStatus(false);
			result.setErrorJson("��ѯ���¼ʧ��...");
			response.getWriter().write(JSONObject.toJSONString(result));
			return ;
		}
	}

}
