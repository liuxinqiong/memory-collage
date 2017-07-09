<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>问题列表</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体;">
	<%@include file="include/top.jsp"%>
	<div class="out">
		<div class="greenBox">
			<div class="fl" style="width:950px;">
				<div class="dw01 blue_bian titBg_blue">
					<h3 class="fl zt_cu11">
						<a href="questionServlet">长沙理工大学在线答疑V1.0</a> &gt; <a href="#">${academy.academyName
							}</a> &gt; ${profession.professionName }
					</h3>
				</div>
			</div>
			<div class="dw01" style="width:950px;">
				<div class="blue_bian" style="overflow:hidden;">
					<div class="quest_tab">
						<ul>
							<c:forEach items="${professions }" var="professionNew">
								<c:choose>
									<c:when
										test="${professionNew.professionNo==profession.professionNo }">
										<li>${profession.professionName }</li>
									</c:when>
									<c:otherwise>
										<li><a
											href="questionServlet?command=questionList&academyNo=${academy.academyNo }&professionNo=${professionNew.professionNo}">${professionNew.professionName
												}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div></div>
			<c:if test="${profession!=null }">
			<div class="wd666 dw01 blue_bian" style="margin-top:4px;">
				<div class="quest_sort_tab">
					<ul>
						<li class="on">全部问题</li>
						<li class="current_none"><a
							href="questionServlet?command=unfinishedQuestionList&academyNo=${academy.academyNo }&professionNo=${profession.professionNo}"><font
								color="#ff6600">？</font>待解决</a>
						</li>
						<li class="current_none"><a
							href="questionServlet?command=finishedQuestionList&academyNo=${academy.academyNo }&professionNo=${profession.professionNo}"><font
								color="#1bbf00">√ </font>已解决</a>
						</li>
					</ul>

				</div>
			
			</div>
			</c:if>
			<div class="wd666 dw01 greenbox blue_bian" style="border-top:none">
				<div class="quest_sort_list">
					<ul>
						<li class="li1">标题(共1条)</li>
						<li class="li2">查看</li>
						<li class="li3">状态</li>
						<li class="li4">提问时间</li>
					</ul>
					<c:forEach items="${askInfos}" var="askInfo">
						<ul>
							<li class="li1"><a
								href="questionServlet?command=questionView&askId=${askInfo.askNo}"
								target="_blank">${askInfo.askContent }</a> &nbsp;<span>[<a
									target="_blank" href="#" style="color:#777777">${askInfo.askTopic
										}</a>]</span></li>
							<li class="li2">${askInfo.readerCount }</li>
							<c:choose>
								<c:when test="${askInfo.status==1 }">

									<li class="li3">已完结</li>
								</c:when>
								<c:otherwise>
									<li class="li3">未完结</li>
								</c:otherwise>
							</c:choose>
							<li class="li4">${askInfo.askDate }</li>
						</ul>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<%@include file="include/footer.jsp"%>
</body>
</html>