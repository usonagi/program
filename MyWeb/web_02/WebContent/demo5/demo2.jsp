<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>EL 获取数据</title>
	</head>
	<body>
		<h1>EL 获取数组的数据</h1>
		<%
			String[] arrs = {"zs","ls","zl"};
			pageContext.setAttribute("arrs", arrs);
		%>
		${ arrs[0] }
		${ arrs[1] }
		${ arrs[2] }
		
		<h1>EL 获取List集合的数据</h1>
		<%
			List<String> list = new ArrayList<String>();
			list.add("hello");
			list.add("world");
			list.add("java");
			pageContext.setAttribute("list", list);
		%>
		${ list[0] }
		${ list[1] }
		${ list[2] }
		
		<h1>EL 获取Map中的数据</h1>
		<%
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("aaa", 11);
			map.put("bbb", 22);
			map.put("ccc.d", 33);
			pageContext.setAttribute("map", map);
		%>
		${ map.aaa }
		${ map.bbb }
		<%-- ${ map.ccc.d } 包含特殊符号不能使用.访问，使用map["xxx"] --%>
		${ map["ccc.d"] }
	</body>
</html>