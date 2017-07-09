<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
<c:when test="${idType=='Student'}">
	<td align="left" valign="top"><br />
		<div class="leftnavigation" style="border:#C0DCC0 1px solid; ">
			<ul>

				<li class=""><a title="我的消息" target="_top" href="student_news.jsp"><img
						width="16px" height="16px" 
						src="images/mymsg.gif" /> 我的消息</a></li>
				

				<li class="on"><img width="16px" height="16px"
					src="images/myquestion.gif" /> <a title="我的问题" target="_top"
					href="questionServlet?command=myUnfinishedQuestion">我的提问</a></li>
				

				<li class="on"><img width="16px" height="16px"
					src="images/myquestion.gif" /> <a title="修改密码" target="_top"
					href="student_changepwd.jsp">修改密码</a></li>
				

				<li class="on"><img width="16px" height="16px"
					 src="images/myinfo.gif"> <a title="个人信息"
					target="_top" href="userServlet?command=studentInfo">个人信息</a></li>
			</ul>
		</div>
		<div class="leftnavigation-bottom"></div></td>
</c:when>
<c:when test="${idType=='Teacher'}">
	<td align="left" valign="top"><br />
		<div class="leftnavigation" style="border:#C0DCC0 1px solid; ">
			<ul>

				<li class=""><a title="我的消息" target="_top" href="teacher_news.jsp"><img
						width="16px" height="16px"
						src="images/mymsg.gif" /> 参与问题</a></li>
				


				<li class="on"><img width="16px" height="16px"
					src="images/myquestion.gif" /> <a title="修改密码" target="_top"
					href="teacher_changepwd.jsp">修改密码</a></li>
				

				<li class="on"><img width="16px" height="16px"
					 src="images/myinfo.gif"> <a title="个人信息"
					target="_top" href="userServlet?command=teacherInfo">个人信息</a></li>
				
				<li class="on"><img width="16px" height="16px"
					 src="images/myinfo.gif"> <a title="个人信息"
					target="_top" href="userServlet?command=teacherFile">学习资料</a></li>
			</ul>
		</div>
		<div class="leftnavigation-bottom"></div></td>

</c:when>



</c:choose>