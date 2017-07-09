<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>个人中心</title>
<link type="text/css" rel="stylesheet" href="css/index.css" />
</head>

<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体;">

	<%@include file="include/top.jsp"%>
	<div class="out">
		<table width="950" align="center">
				<%@include file="include/person_top.jsp"%>
			<tr>
				<%@include file="include/person_left.jsp"%>
				<td valign="middle">
					<div class="fr myspace_right" style=" border:#C0DCC0 solid thin">
						<div>

							<div
								style="margin-top:5px; margin-bottom:10px;border-bottom:#CCCCCC 1px solid"></div>

							<h2>教师分享资料</h2>
							<div>
								<div class="quest_sort_list">

									
									<c:forEach items="${files}" var="fileShare">
										<ul>
											<li class="li1">${fileShare.fileName }</li>
											<li class="li2"><a href="files/${fileShare.filePath }">
													点击下载</a></li>


										</ul>
									</c:forEach>

								</div>
							</div>

							<div class="quest_sort_list">
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
								<p>
									<a href=""
										style="font-size:16px; color:#2A9FAA ;font-family: 'Microsoft YaHei' ">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点击上传资料</a>
								</p>
							</div>

							<div class="pages">
								<div class="scott"></div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<%@include file="include/footer.jsp"%>

</body>
</html>