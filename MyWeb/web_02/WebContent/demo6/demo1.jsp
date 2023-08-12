<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSTL 标签的使用</title>
	</head>
	<body>
		<h3>使用JSTL保存数据</h3>
		<!-- 向域中保存数据  值、名称、域 -->
		<c:set value="zs" var="name" scope="page"></c:set>
		${ name }
		
		<h3>if 标签的使用</h3>
		<c:set var="i" value="0" scope="page"></c:set>
		<c:if test="${ i >= 10 }">
			<font color="red"> 条件成立，则显示 </font>
		</c:if>
		<c:if test="${ i < 10 }">
			<font color="green"> jstl没有else语句，需要另写if语句 </font>
		</c:if>
		
		<h3>foreach 标签的使用</h3>
		<h5> 遍历数组 </h5>
		<%
			String[] arrs = {"hello","world","JSTL"};
			pageContext.setAttribute("arrs", arrs);
		%>
		<c:forEach var="s" items="${ arrs }">
			${ s }
		</c:forEach>
		
		<h5> 遍历list集合 </h5>
		<%
			List<String> list = new ArrayList<String>();
			list.add("first");
			list.add("second");
			pageContext.setAttribute("list", list);
		%>
		<c:forEach var="l" items="${ list }">
			${ l }
		</c:forEach>
		
		<h5> 遍历Map集合 </h5>
		<%
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("first", 1);
			map.put("second", 2);
			map.put("third", 3);
			pageContext.setAttribute("map", map);
		%>
		<c:forEach var="kv" items="${ map }">
			${ kv.key } - ${ kv.value }<br>
		</c:forEach>
		
		<h5> 遍历1-100 将第4个数变红 </h5>
		<c:forEach var="i" begin="1" end="100" step="5" varStatus="S">
			<c:if test="${ S.count % 4 == 0 }">
				<font color="red">${ i }</font>
			</c:if>
			<c:if test="${ S.count % 4 != 0 }"> ${ i } </c:if>
		</c:forEach>
		
		<!-- 求1~10的和 -->
		<c:set value="0" var="sum" scope="page"></c:set>
		<c:forEach var="i" begin="1" end="10" step="1">
			<c:set var="sum" value="${ sum + i }"></c:set>
		</c:forEach>
		<font color="blue"> ${ sum } </font>
		
	</body>
</html>