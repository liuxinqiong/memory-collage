<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="t" uri="http://www.sxt.com/myTag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			List<String> temp = new ArrayList<String>();
			temp.add("aaa");
			temp.add("bbb");
			temp.add("ccc");
			pageContext.setAttribute("list", temp);
		%>

		<t:forEach items="${list}" var="temp">
			<span style="color: red;">${temp}</span>
			<br />
		</t:forEach>
		
		<t:Date/>
		<br/>
		<t:maxValue num1="34" num2="56"/>
		<br/>
		<t:circle begin="1" end="100" step="2"/>
		<br/>
		<t:toUpperCase>sfsdasdsd</t:toUpperCase>
		<br/>
		<t:readFile src="F:\Sogou\sogoupinyin\readme.txt"/>
	</body>
</html>