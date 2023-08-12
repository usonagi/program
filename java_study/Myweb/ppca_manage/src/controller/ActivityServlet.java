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
 * 处理Activity相关操作的servlet
 */
public class ActivityServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private ActivityModel ActModel;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//得到从浏览器发送的请求参数中的method  根据method执行不同操作
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
	
	//按条件查询  暂定使用发布者UNAME进行查询
	public void findByText(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入ActivityServlet findByText方法");
		
		String arg = request.getParameter("UNAME");
		System.out.println("被搜索的发布者: "+arg);
		
		ActModel = new ActivityModel();
		List<Activity> activities = ActModel.findByText(arg);
		
		response.getWriter().write(JSONObject.toJSONString(activities));
	}
	
	//分页查询所有活动
	public void findByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入ActivityServlet findByPage方法");
		
		// 接受浏览器的请求参数 pageNO pageSize 页码 每页条数
		int pageNO = Integer.parseInt(request.getParameter("pageNO"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));

		System.out.println("当前页号: "+pageNO + " 每页大小: " + pageSize);
		
		ActModel = new ActivityModel();
		PageBean pb = ActModel.findPage(pageNO,pageSize);
		
		System.out.println("ActivityServlet 总记录数："+pb.getTotalCount());
		System.out.println("ActivityServlet 总页数： "+pb.getTotalPage());
		
		response.getWriter().write(JSONObject.toJSONString(pb));
	}

	// 修改Activity
	public void updateActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入ActivityServlet updateActivity方法");
		
		Activity activity = new Activity();

		try {
			BeanUtils.populate(activity, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println("即将被修改的活动编号、活动名:"+activity.getAct_id()+" "+activity.getAct_name());

		ActModel = new ActivityModel();
		ActModel.updateActivity(activity);
	}

	//根据id查询Activity
	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入ActivityServlet findById方法");
		
		String act_id = request.getParameter("act_id");
		
		ActModel = new ActivityModel();
		Activity activity = ActModel.findById(act_id);
		
		response.getWriter().write(JSONObject.toJSONString(activity));
	}

	//添加活动	使用beanutils完成请求参数的封装  参数名必须与对象的属性名相同
	public void addActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入ActivityServlet addActivity方法");
		
		Activity activity = new Activity();
		
		try {
			//将请求参数封装到Activity中  参数1--对象   参数2--集合  获得所有请求参数的集合
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
	
	//删除活动   根据活动编号ACT_ID删除
	public void delActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入ActivityServlet delActivity方法");
		
		/*Activity activity = new Activity();

		try {
			// 将请求参数封装到Activity中 参数1--对象 参数2--集合 获得所有请求参数的集合
			BeanUtils.populate(activity, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}*/

		String Act_id = request.getParameter("act_id");
		String Act_name = request.getParameter("act_name");

		System.out.println("即将被删除的活动编号、活动名："+Act_id+" "+Act_name);

		ActModel = new ActivityModel();
		ActModel.delActivity(Act_id);
	}
	
	//查询所有活动信息
	public void findAllActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("进入ActivityServlet findAllActivity方法");
		
		//调用ActivityModel中的方法查询所有活动信息
		ActModel = new ActivityModel();
		List<Activity> activities = ActModel.findAllActivity();
		
		/*for(int i=0;i<activities.size();i++)
		{
			System.out.println(activities.get(i).getAct_name());
		}*/
		
		//将得到的数据转换成json数据，并响应到浏览器
		response.getWriter().write(JSONObject.toJSONString(activities));
	}
}
