<%@page import="com.listener.bean.Bean2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>测试HttpSessionActiveListener监听器的页面</h3>
<%
	Bean2 bean2 = new Bean2();
	bean2.setName("宋怡霏");
	session.setAttribute("bean2", bean2);
	
%>
</body>
</html>