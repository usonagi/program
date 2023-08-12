<%@page import="com.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成功登录页面</title>
</head>
<body>
<%
	if(request.getSession().getAttribute("existUser") == null){
%>

	<h1>您还没有登录! 请先<a href="/web_login/login.jsp">登录</a>! </h1>
	
<%	
	}else{
%>

	<h1>用户登录成功!</h1>
	
<%
	User existUser = (User)request.getSession().getAttribute("existUser");
%>

	<h3>您好: <%=existUser.getUsername() %> <a href="/web_login/LogoutServlet">退出</a></h3>
	
<%
	}
%>

</body>
</html>