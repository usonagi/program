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
/*import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;*/

import domain.Activity;
import domain.PageBean;
import model.ActivityModel;

/**
 * ����Activity��ز�����servlet
 */
public class ActivityServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private ActivityModel ActModel;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�õ�����������͵���������е�method  ����methodִ�в�ͬ����
		String method = request.getParameter("method");
		
		System.out.println(method);
		
		switch (method) {
		case "findByPage":
			findByPage(request, response);
			break;

		case "findByText":
			findByText(request, response);
			break;

		case "addActivity":
			addActivity(request, response);
			break;
			
		case "delActivity":
			delActivity(request, response);
			break;

		case "findById":
			findById(request, response);
			break;
			
		case "Update":
			updateActivity(request, response);
			break;

		default:
			break;
		}
		
		/*if("findAllActivity".equals(method)){
			findAllActivity(request,response);
		}
		
		if("addActivity".equals(method)){
			addActivity(request,response);
		}
		
		if("findById".equals(method)){
			findById(request,response);
		}
		
		if("Update".equals(method)){
			updateActivity(request, response);
		}
		
		if("findByPage".equals(method)){
			findByPage(request,response);
		}
		
		if("findByText".equals(method)){
			findByText(request,response);
		}
		
		if("delActivity".equals(method)){
			delActivity(request,response);
		}*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//��������ѯ  �ݶ�ʹ�÷�����UNAME���в�ѯ
	public void findByText(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����ActivityServlet findByText����");
		
		String arg = request.getParameter("UNAME");
		System.out.println("�������ķ�����: "+arg);
		
		ActModel = new ActivityModel();
		List<Activity> activities = ActModel.findByText(arg);
		
		response.getWriter().write(JSONObject.toJSONString(activities));
	}
	
	//��ҳ��ѯ���л
	public void findByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����ActivityServlet findByPage����");
		
		// ������������������ pageNO pageSize ҳ�� ÿҳ����
		int pageNO = Integer.parseInt(request.getParameter("pageNO"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		System.out.println("��ǰҳ��: "+pageNO + " ÿҳ��С: " + pageSize);
		
		ActModel = new ActivityModel();
		PageBean pb = ActModel.findPage(pageNO,pageSize);
		
		System.out.println("ActivityServlet �ܼ�¼����"+pb.getTotalCount());
		System.out.println("ActivityServlet ��ҳ���� "+pb.getTotalPage());
		
		response.getWriter().write(JSONObject.toJSONString(pb));
	}

	// �޸�Activity
	public void updateActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����ActivityServlet updateActivity����");
		
		Activity activity = new Activity();

		try {
			BeanUtils.populate(activity, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println("�������޸ĵĻ��š����:"+activity.getAct_id()+" "+activity.getAct_name());

		ActModel = new ActivityModel();
		ActModel.updateActivity(activity);
	}

	//����id��ѯActivity
	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����ActivityServlet findById����");
		
		String act_id = request.getParameter("act_id");
		
		ActModel = new ActivityModel();
		Activity activity = ActModel.findById(act_id);
		
		response.getWriter().write(JSONObject.toJSONString(activity));
	}

	//��ӻ	ʹ��beanutils�����������ķ�װ  ������������������������ͬ
	public void addActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����ActivityServlet addActivity����");
		
		Activity activity = new Activity();
		
		try {
			//�����������װ��Activity��  ����1--����   ����2--����  ���������������ļ���
			BeanUtils.populate(activity, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println(activity.getAct_id()+" "+activity.getAct_name()+" "+activity.getUname());
		
		ActModel = new ActivityModel();
		ActModel.addActivity(activity);
	}
	
	//ɾ���   ���ݻ���ACT_IDɾ��
	public void delActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("����ActivityServlet delActivity����");
		
		/*Activity activity = new Activity();

		try {
			// �����������װ��Activity�� ����1--���� ����2--���� ���������������ļ���
			BeanUtils.populate(activity, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}*/

		String Act_id = request.getParameter("act_id");
		String Act_name = request.getParameter("act_name");

		System.out.println("������ɾ���Ļ��š������"+Act_id+" "+Act_name);

		ActModel = new ActivityModel();
		ActModel.delActivity(Act_id);
	}
	
	//��ѯ���л��Ϣ
	public void findAllActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("����ActivityServlet findAllActivity����");
		
		//����ActivityModel�еķ�����ѯ���л��Ϣ
		ActModel = new ActivityModel();
		List<Activity> activities = ActModel.findAllActivity();
		
		/*for(int i=0;i<activities.size();i++)
		{
			System.out.println(activities.get(i).getAct_name());
		}*/
		
		//���õ�������ת����json���ݣ�����Ӧ�������
		response.getWriter().write(JSONObject.toJSONString(activities));
	}
}
