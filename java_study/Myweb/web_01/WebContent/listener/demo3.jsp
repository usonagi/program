<%@page import="com.listener.bean.Bean1"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>测试HttpSessionBindingListener监听器的页面</h3>
<%
	Bean1 bean1 = new Bean1();
	bean1.setName("索拉卡");
	session.setAttribute("bean1", bean1);	//属性添加可以放任意类型，绑定只能放Java对象
	
	session.removeAttribute("bean1");
%>
</body>
</html>