<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>教师团</title>
<link type="text/css" rel="stylesheet" href="css/index.css" />
</head>

<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体;">

	<%@include file="include/top.jsp"%>
	<div class="out">
		<div class="greenBox">
			<div class="fl" style="width:950px;">
				<div class="dw01 blue_bian titBg_blue">

				<c:forEach items="${teachers }" var="teacher">
					<div>
						<div class="expert"
							style="margin-top:-1px; float:left; width:227px; border-bottom:none">
							<div>
								<div class="expert_img1" style="margin-right:0px;">
									<a href="userServlet?command=lookTeacherInfo&teaName=${teacher.teaName }"> <img
										src="pic/${teacher.teaPic}"
										height="70px" width="70px" />
									</a>
									<h2 style="margin:0;padding-top:-1px;">
										<a style="color:#000;"
											href="userServlet?command=lookTeacherInfo&teaName=${teacher.teaName }">${teacher.teaName }</a>
									</h2>
								</div>
								<div class="expert_zl1" style="margin-right:14px;">
									<h3>
										${teacher.teaGrade }
									</h3>
									所属专业：
									<c:forEach items="${professions}" var="profession">
										<c:if test="${teacher.profeNo ==profession.professionNo }">
										${profession.professionName }
										</c:if>
									</c:forEach>
								</div>
								<div class="fr" style="margin-right:22px;">
									<a href="questionServlet?command=ask"> <img
										src="images/woyaotiwen.png"
										height="22px" width="94px" />
									</a>
								</div>
								<div class="clr"></div>
							</div>
						</div>
					</div>
				</c:forEach>	
					
					
				</div>
			</div>
		</div>
	</div>
	<%@include file="include/footer.jsp"%>

</body>
</html>
