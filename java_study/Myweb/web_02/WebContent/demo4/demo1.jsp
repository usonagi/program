<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP动作标签演示</title>
	</head>
		<body>
		<h1>JSP的动作标签：转发</h1>
		<jsp:forward page="/demo4/demo2.jsp">
			<jsp:param value="zsf" name="name"/>
		</jsp:forward>
		
		<h1>JSP的动作标签：包含</h1>
		<%-- <jsp:include page="/demo2/logo.jsp"></jsp:include> --%>
	</body>
</html>