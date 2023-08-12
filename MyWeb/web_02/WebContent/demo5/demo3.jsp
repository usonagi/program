<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>EL 执行运算</title>
	</head>
	<body>
		<h1>EL 执行算术运算</h1>
		<%
			pageContext.setAttribute("a", "10");
			pageContext.setAttribute("b", "20");
		%>
		${ a+b }
		
		<h1>EL 执行关系运算</h1>
		${ a < b } -- ${ a lt b } <br>
		${ a > b } -- ${ a gt b } <br>
		${ a == b } -- ${ a eq b } <br>
		${ a >= b } -- ${ a ge b } <br>
		${ a <= b } -- ${ a le b } <br>
		${ a != b } -- ${ a ne b } <br>
		
		<h1>EL 执行逻辑运算</h1>
		<!-- 好像不能对100以上的数字进行 -->
		<%
			pageContext.setAttribute("c", "50");
			pageContext.setAttribute("d", "40");
		%>
		${ a }-${ b }-${ c }-${ d }--${ c < d }<br>
		${ (c < d) && (a < b) } -- ${ (c < d) and (a < b) } <br>
		${ (c < d) || (a < b) } -- ${ (c < d) or (a < b) } <br>
		${ !(c < d) } -- ${ not (c < d) } <br>
		${(a>b) && (c<d) } <br>
		${(a<b) && (c<d) } <br>
		${(a>b) || (c<d) } <br>
		${(a<b) || (c<d) }
		
		<h1>EL 执行三元运算</h1>
		${ a < b ? "a小于b" : "a不小于b" }
		${ c < d ? "c小于d" : "c不小于d" }
		
		<h1>EL 空运算符</h1>
		${ empty user }
	</body>
</html>