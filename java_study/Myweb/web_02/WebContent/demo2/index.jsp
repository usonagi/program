<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 使用include属性 可以引用其他页面 -->
<!-- 也能使用<jsp:include> 引用其他页面 -->
<%@ include file="logo.jsp" %>
<%@ include file="menu.jsp" %>
<h1>This Is Body !</h1>
<%@ include file="foot.jsp" %>
</body>
</html>