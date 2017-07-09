<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>消息提示</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体;">
	<%@include file="include/top.jsp"%>
	<div class="out">
		<div class="greenBox">
			<div class="blue_bian tip">
				<div id="pos">个人中心&gt; 消息提示</div>
				<div
					style="width:600px; height:200px;margin:auto; margin-top:30px; border:#CCCCCC 1px solid; background-color:#FAFFBD">
					<div style="margin:40px; color:#294e56;">

						<c:choose>
							<c:when test="${result=='success' }">
								<div>修改成功</div>
							</c:when>
							<c:otherwise>
								<div>修改失败</div>
							</c:otherwise>
						</c:choose>
						<div class="">
							页面将在3秒后回到当前页面，你也可以直接点
							<c:choose>
								<c:when test="${nextpage=='changestupwd' }">
									<a id="jump" href="http://localhost:8080/AQWeb/student_changepwd.jsp">立即跳转</a>。
							 		
								</c:when>
								<c:when test="${nextpage=='changestuinfo' }">
									<a id="jump" href="userServlet?command=studentInfo">立即跳转</a>。
							 	
								</c:when>
								<c:when test="${nextpage=='changeteapwd' }">
									<a id="jump" href="http://localhost:8080/AQWeb/teacher_changepwd.jsp">立即跳转</a>。
							 	
								</c:when>
								<c:when test="${nextpage=='changeteainfo' }">
									<a id="jump" href="userServlet?command=teacherInfo">立即跳转</a>。
							 	
								</c:when>
							</c:choose>
							<script type="text/javascript">
											var hf = document.getElementById("jump");
											function redirect(url, time) {
												setTimeout("window.location='"
														+ url + "'",
														time * 1000);
											}
											redirect(
													hf,
													3);
										</script>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="content">
			<div class="tips"></div>
		</div>
		<div class="clr"></div>
	</div>
	<%@include file="include/footer.jsp"%>

</body>

</html>
