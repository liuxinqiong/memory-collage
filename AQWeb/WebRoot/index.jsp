<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link type="text/css" rel="stylesheet" href="css/index.css" />
<script type="text/javascript" src="scripts/index.js" charset="utf-8"> </script>
</head>

<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体;">
	<%@include file="include/top.jsp"%>
	<div class="out" style="margin-top:5px;">
		<div class="greenBox">
			<%@include file="include/type.jsp"%>
			<div class="fr wd665 ">
				<!--middle开始-->
				<%@include file="include/question.jsp"%>
				<!--middle结束-->
				<!--right结束-->
				<%@include file="include/teacher.jsp"%>
				<!--right结束-->
			</div>
		</div>
	</div>
	<%@include file="include/footer.jsp"%>
</body>
</html>
