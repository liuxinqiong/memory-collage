<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>搜索结果</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="scripts/index.js" charset="utf-8">
	
</script>
</head>
<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体;">
	<%@include file="include/top.jsp"%>
	<div class="out">
		<div class="greenbox">
			<div class="fl wd665">
				<div class="dw01 blue_bian titBg_blue">
					<h3 class="fl zt_cu11">
						<a title="" target="_blank" href="questionServlet">长沙理工大学在线答疑V1.0</a>&nbsp;&gt;&nbsp;搜索结果
					</h3>
				</div>
				<div class="wd665 dw01">
					<div class="blue_bian" style="overflow:hidden;">
						<div class="quest_tab">
							<ul>
								<li class="current_none"><a
									href="questionServlet?command=search&key=${key }">全部问答</a></li>
								<li class="on"><font color="#1bbf00">√ </font>已解决</li>
								<li class="current_none"><font color="#ff6600">？</font><a
									href="questionServlet?command=unfinished&key=${key }">待解决</a></li>
							</ul>
							<div class="clr"></div>
						</div>

						<div class="search_amount">
							包含关键词“<span class="highlight">${key }</span>” 的搜索结果
						</div>
						<div class="search_amount" style="float:right;">
							&nbsp;&nbsp;共找到 <span>${size}</span> 条结果
						</div>

						<div class="search_result">
							<div class="search_detail">
								<ul>
									<c:forEach items="${searchAskInfos}" var="searchAskInfo">
										<li>
											<h3>
												<a href="questionServlet?command=questionView&askId=${searchAskInfo.askNo}" target="_blank">${searchAskInfo.askTopic }</a>
											</h3>
											<p style="padding-left:20px;">${searchAskInfo.askContent
												}</p>
											<p style="color:#898989;font-size: 12px; padding-left:5px;">
												${searchAskInfo.studentNo
												}&nbsp;-&nbsp;${searchAskInfo.askDate }
												&nbsp;-&nbsp;浏览:${searchAskInfo.readerCount}&nbsp;-&nbsp;<a
													href="#" target="_blank">${searchAskInfo.professionNo}</a>
											</p>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="pages">
								<div class="scott"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="include/footer.jsp"%>
</body>
</html>