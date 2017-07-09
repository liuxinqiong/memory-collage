<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

function ask_submit(){
	location.href="questionServlet?command=ask";
}
</script>
<div class="topBg">

	<div class="topleft" style="padding:10px 0 0 10px;">
		<a href="questionServlet" target="_blank">Csust首页</a> <a
			href="userServlet?command=teacherGroup" target="_blank">专家团</a>
	</div>
	<div class="fr topright">
		<div class="topleft" style="padding:10px 5px 0 0;">
			您好，欢迎来到长沙理工大学在线答疑V1.0！
			<c:choose>
				<c:when test="${loginUser!=null && idType=='Teacher'}">
					教师：<a href="teacher_news.jsp">${loginUser.teaName}</a>
							<a href="userServlet?command=exit">注销</a>	
				</c:when>
					<c:when test="${loginUser!=null && idType=='Student'}">
						学生：<a href="student_news.jsp">${loginUser.stuName}</a>
							<a href="userServlet?command=exit">注销</a>
						</c:when>
				<c:otherwise>
					<a href="login.jsp">登录</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>


</div>
<div class="out_top topLogo">
	<div class="fl logo">
		<a href="questionServlet" title="长沙理工大学在线答疑V1.0" target="_top"><img
			src="images/logo.png" alt="长沙理工大学在线答疑V1.0" /> </a>
	</div>
	<div class="fl topNav_wid" style="margin-left:20px;">
		<form name="searchform" action="questionServlet?command=search"
			method="post">
			<div class="qust_input">
				<input type="hidden" name="q" id="kw_hide" autocomplete="off" /> <input
					type="text" id="kw" onmouseover="this.focus()" autocomplete="off"
					class="search" style="vertical-align:middle;outline:none;"
					name="word" maxlength="100" tabindex="1" value="" />
				<button type="submit" class="sub" onclick="search_submit();"></button>
				<c:choose>
				<c:when test="${idType=='Teacher'}">
				
				</c:when>
				<c:otherwise><button type="button" class="but" onclick="ask_submit();"></button></c:otherwise>
				</c:choose>
			</div>
		</form>

	</div>
	<div class="question fr">
		<div class="quest_list">

			<span>已解决问题数:</span><span class="finish">${askInfosCheckedSize
				}</span><br /> <span>待解决问题数:</span><span class="wait">${askInfosUnCheckedSize}</span>
		</div>
		<div class="clear"></div>
	</div>
</div>

