<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>审阅问题</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script language="javascript" src="scripts/jquery-1.7.js"></script>
<script>
function acceptAnswer(answerNo,askNo){
	url="questionServlet?command=acceptAnswer&answerNo="+answerNo+"&askNo="+askNo;
	$.get(url,null,function(msg){
		if(msg=='true'){
			alert("采纳成功");
			location.href="questionServlet";
		}else{
			alert("采纳失败");
		}
	});
}
</script>
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
							<h3 class="fl zt_cu10">未解决</h3>
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

				<!--选择答案开始-->
				<div class="wd665 dw01 mt03">
					<div class="blue_bian" style="overflow:hidden;">
						<div class="answer_h dw02" style="padding-top:0;">

							<span class="fl"
								style="padding-top:8px; margin-left:10px; color:#CC0000">未被采纳答案</span>
						</div>

						<c:forEach items="${answerInfos }"
							var="answerInfo">
							<div>
								<div style="padding-top:4px">
									<span style="margin:0 10px 0 10px;">回答者:专家组</span> <img
										src="images/clock.png" style="margin-left:10px;" />
									回答于:${answerInfo.answerDate }&nbsp;&nbsp;
								</div>
							</div>
							<div>
								<div class="quest_cont">

									<p style="text-indent:28px;line-height:24px">
										<span style="font-family:仿宋_gb2312">${answerInfo.answerContent}</span>
									</p>

									<p>
										<br />
									</p>
								</div>
								<input type="button" name="chooseAnswer" class="button4 fr" value="采纳此答案" onclick="acceptAnswer(${answerInfo.answerNo},${askInfoViewBean.askInfo.askNo });"/>
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