<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${idType=='Student'}">
		<tr>

			<td width="151" height="141" align="center"><a
				href="userServlet?command=studentInfo" style="border:#FFF"><span>
					<img src="pic/${loginUser.stuPic }" width="120" height="140" border="0"
						 /></span></a></td>
			<td valign="middle">
				<p>

					<a href="userServlet?command=studentInfo">&nbsp;&nbsp; <font size="-2">编辑资料</font><img
						src="images/myquestion.gif" width="16" height="16" /></a>
				</p> <span>&nbsp;<font
					style="font-size:20px; color:#000 ; font-weight:600;font-family: 'Microsoft YaHei'">${loginUser.stuName }</font>
				</span><br />
			</td>
		</tr>
	</c:when>

	<c:when test="${idType=='Teacher'}">
		<tr>

			<td width="151" height="141" align="center"><a
				href="userServlet?command=teacherInfo" style="border:#FFF"><span>
					<img src="pic/${loginUser.teaPic }" width="120" height="140" border="0"
						 /></span></a></td>
			<td valign="middle">
				<p>

					<a href="userServlet?command=teacherInfo">&nbsp;&nbsp; <font size="-2">编辑资料</font><img
						src="images/myquestion.gif" width="16" height="16" /></a>
				</p> <span>&nbsp;<font
					style="font-size:20px; color:#000 ; font-weight:600;font-family: 'Microsoft YaHei'">${loginUser.teaName }</font>
				</span><br />
			</td>
		</tr>
	</c:when>

</c:choose>

