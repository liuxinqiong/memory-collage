<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心</title>
<link type="text/css" rel="stylesheet" href="css/index.css" />
</head>
<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体;">
	<%@include file="include/top.jsp"%>
	<div class="out">
		<table width="1000" align="center">

			<%@include file="include/person_top.jsp"%>
			<tr>
				<%@include file="include/person_left.jsp"%>
				<td valign="middle">
					<div class="fr myspace_right" style=" border:#C0DCC0 solid thin">
						<div>
							<div class="fr myspace_right">
								<h2>详细资料</h2>
								<div class="expertAnswer">
									<h1>${teacher.teaName }&nbsp;</h1>
									<ul>
										<!--<li>用户组：实习生</li>-->
										<li>专业： <c:forEach items="${professions}"
												var="profession">
												<c:if test="${teacher.profeNo ==profession.professionNo }">
													${profession.professionName }
												</c:if>
											</c:forEach></li>
										<li>回答数：${answerNum}</li>
										<li>级别：${teacher.teaGrade }</li>
										<li>满意度：${teacher.teaSatisfaction }</li>

									</ul>

								</div>
								<div class="clearBox"></div>
								<div
									style="font-size:14px; font-weight:bolder; margin-top:15px;">
									<img width="18" height="16" src="images/userinfo.gif">&nbsp;个人简介
								</div>
								<div
									style="margin-top:5px; margin-bottom:10px;border-bottom:#CCCCCC 1px solid"></div>
								<div style="width:685px; margin:auto;">
									${teacher.teaDescription }</div>
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
								<div style="margin-top:5px; border-bottom:#CCCCCC 1px solid"></div>
								<h2>最近解答</h2>
								<div style="margin-top:5px; border-bottom:#CCCCCC 1px solid"></div>

								<div class="quest_sort_list">
									<ul>
										<li class="li1">标题</li>
										<li class="li2">支持/反对</li>
										<li class="li3">已采纳</li>
										<li class="li4">回答时间</li>
									</ul>

									<!-- 			<ul>
					 		<li class="li1"><a
											href="http://ask.bjbys.net.cn/?q-274.html" target="_blank"
											title="户口迁移证有有效期么？">户口迁移证有有效期么？</a>
										</li>
										<li class="li2">0/0</li>
										<li class="li3"><font color="red">是</font>
										</li>
										<li class="li4">2014/12/19 16:10</li>
									</ul>
									-->

									<div class="pages">
										<div class="scott"></div>
									</div>
								</div>
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