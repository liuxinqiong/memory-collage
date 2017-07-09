<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提问信息</title>
<link rel="stylesheet" rev="stylesheet" href="admin/css1/style.css" type="text/css" media="all" />
</head>

<body>
<div class="#">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="th" ordercolordark="#0033CC" frame="box" bordercolordark="#6795B4">
<tr><td colspan="4" class="title">提问信息</td></tr>
<tr><td>提问标题</td><td>提问人</td><td>提问时间</td><td>是否完结</td>
<c:forEach items="${askinfos}" var="item">
<tr><td>${item.askContent}</td><td>${item.studentNo}</td><td>${item.askDate}</td><td>${item.status}</td></tr>
</c:forEach>
</tr>
</table>
</div>
</body>
</html>