<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录页面</title>
	</head>
	<body>
		<h3><font color="red">${ msg }</font></h3>	<!-- 回显错误信息 -->
		<form action="${ pageContext.request.contextPath }/UserServlet" method="post">
			<table border="1" width="400">
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="password"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="登录"></td>
			</tr>
			</table>
		</form>
		
	</body>
</html>