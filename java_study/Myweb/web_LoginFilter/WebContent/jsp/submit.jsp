<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交数据的JSP</title>
</head>
<body>
<h1>GET提交方式</h1>
<form action="${ pageContext.request.contextPath }/ServletDemo1" method="get">
	姓名:<input type="text" name="name"/><br>
	<input type="submit" value="提交"/>
</form>

<h1>POST提交方式</h1>
<form action="${ pageContext.request.contextPath }/ServletDemo1" method="post">
	姓名:<input type="text" name="name"/><br>
	<input type="submit" value="提交"/>
</form>
</body>
</html>