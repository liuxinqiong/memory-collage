<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>回复结果</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体; ">
	<%@include file="include/top.jsp"%>

	<div class="out">
		<div class="greenBox">
			<div class="blue_bian tip">
				<div id="pos">
					<a href="questionServlet">长沙理工大学在线答疑V1.0</a>&gt; 消息提示
				</div>
				<div
					style="width:600px; height:200px;margin:auto; margin-top:30px; border:#CCCCCC 1px solid; background-color:#FAFFBD">
					<div style="margin:40px; color:#294e56;">
						<div>问题回复成功!......</div>
						<div class="">
							<a href="javascript:history.go(-1);">返回原处</a>&nbsp;&nbsp; <a href="questionServlet">回到首页</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="include/footer.jsp"%>
</body>
</html>