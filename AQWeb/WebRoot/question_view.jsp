<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>问题详情</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体;">
	<%@include file="include/top.jsp"%>
	<div class="out">
		<div class="greenBox">
			<div class="fl wd665">
				<div class="wd665">
					<div class="dw01 blue_bian titBg_blue mt03">
						<h3 class="fl zt_cu11">
							<a href="questionServlet">长沙理工大学在线答疑V1.0</a> &gt; <a href="#">${askInfoViewBean.profession.professionName
								}</a>

						</h3>
					</div>
					<!--已解决的提问-->
					<div class="mt03 blue_bian_top">
						<div class="dw01 redTitle" style="border-bottom:#CCCCCC 1px solid">
							<c:choose>
								<c:when test="${checkedAnswerInfo!=null}">
									<h3 class="fl zt_cu10">已解决</h3>
								</c:when>
								<c:otherwise>
									<h3 class="fl zt_cu10">未解决</h3>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="">
							<div>
								<h1 class="quest_tit">${askInfoViewBean.askInfo.askTopic }</h1>
							</div>
							<div style="margin-left:12px; overflow:hidden">
								<div class="fl">
									<div class="quest_people">
										<div id="userinfo-1"></div>
										<div>
											提问者：<a href="#">${askInfoViewBean.student.stuName}</a>
										</div>
									</div>
									<img src="images/lefttime.gif">
									提问时间：${askInfoViewBean.askInfo.askDate } &nbsp;&nbsp; 浏览次数:<span
										id="views">${askInfoViewBean.askInfo.readerCount }</span>
									&nbsp;&nbsp;

								</div>
							</div>
							<div class="quest_cont" id="mydescription">
								<p>
									${askInfoViewBean.askInfo.askContent}<br />
								</p>
							</div>
						</div>

					</div>
					<!--已解决的提问结束-->


				</div>
				<!--最佳答案开始-->
				<c:choose>
					<c:when test="${checkedAnswerInfo!=null}">
						<div class="wd665 dw01 mt03">
							<div class="blue_bian" style="overflow:hidden;">
								<div class="answer_h dw02" style="padding-top:0;">
									<img width="44px" height="41px" src="images/zjda.png"
										class="fl"> <span class="fl"
										style="padding-top:8px; margin-left:10px; color:#CC0000">该答案已被采纳</span>
								</div>
								<div>
									<div style="padding-top:4px">
										<span style="margin:0 10px 0 10px;">回答者:专家组</span> <img
											src="images/clock.png" style="margin-left:10px;" />
										回答于:${checkedAnswerInfo.answerDate }&nbsp;&nbsp;
									</div>
								</div>
								<div>
									<div class="quest_cont">

										<p style="text-indent:28px;line-height:24px">
											<span style="font-family:仿宋_gb2312">${checkedAnswerInfo.answerContent}</span>
										</p>

										<p>
											<br />
										</p>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${loginUser!=null && idType=='Teacher'}">
					<script>
					function checkItem(){
						var answerContent=document.getElementById("answerContent").value;
						if(answerContent==""){
							alert("回复信息不能为空");
							return false;
						}
						return true;
					}
					</script>
						<div class="fr" style="margin-top:10px;margin-right:0px;"
							class="answer_h">
							<h1 class="quest_tit">我帮TA解答</h1>
							<form name="answerForm" action="questionServlet?command=addAnswer&askNo=${askInfoViewBean.askInfo.askNo}&studentNo=${askInfoViewBean.askInfo.studentNo }" 
							 method="post" onsubmit="return checkItem();">
								<textarea cols="87.5" rows="13" name="answerContent" id="answerContent"></textarea>
								<div class="fr" style="margin-bottom:10px;">
									<p class="validate_code fl">
										<input type="submit" class="answer_sub_button" name="mybutton" value=""/>
									</p>
								</div>
							</form>
						</div>
					</c:when>
				</c:choose>
				<!--最佳答案结束-->

				<!--相关已解决-->
				<div class="wd665 dw01 mt03">
					<div class="blue_bian" style="overflow:hidden;">
						<div class="answer_h dw02" style="padding-top:0;">
							<span class="fl"
								style="padding-top:8px; margin-left:10px; color:#CC0000">相关问题</span>
						</div>
						<dl class="greenCont">
							<c:forEach items="${aboutAskInfos }" var="aboutAskInfo">
								<dd>
									<a
										href="questionServlet?command=questionView&askId=${aboutAskInfo.askNo}"
										target="_blank">${aboutAskInfo.askContent}</a>
								</dd>
							</c:forEach>
						</dl>
					</div>
				</div>

				<!--其他答案开始-->
				<div class="wd665 dw01 mt03">
					<div class="blue_bian" style="overflow:hidden;">
						<div class="answer_h dw02" style="padding-top:0;">

							<span class="fl"
								style="padding-top:8px; margin-left:10px; color:#CC0000">未被采纳答案</span>
						</div>

						<c:forEach items="${uncheckedAnswerInfos }"
							var="uncheckedAnswerInfo">
							<div>
								<div style="padding-top:4px">
									<span style="margin:0 10px 0 10px;">回答者:专家组</span> <img
										src="images/clock.png" style="margin-left:10px;" />
									回答于:${uncheckedAnswerInfo.answerDate }&nbsp;&nbsp;
								</div>
							</div>
							<div>
								<div class="quest_cont">

									<p style="text-indent:28px;line-height:24px">
										<span style="font-family:仿宋_gb2312">${uncheckedAnswerInfo.answerContent
											}</span>
									</p>

									<p>
										<br />
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- 其他回答结束 -->
			</div>
		</div>
	</div>
	<%@include file="include/footer.jsp"%>
</body>
</html>