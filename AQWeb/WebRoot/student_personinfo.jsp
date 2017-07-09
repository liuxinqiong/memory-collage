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


							<div class="myAskTab" id="tab">
								<p style="font-size:16px; font-family: 'Microsoft YaHei'; ">
									&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;
									&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;<a
										href="http://ask.bjbys.net.cn/?message/personal.html"> <font
										color="#000000" size="-1">个人信息</font></a>
								</p>
								<hr size="1" />
								<div class="clr"></div>
							</div>
							<div class="quest_sort_list">

								<script language="javascript" type="text/javascript">
									function emailCheck() {
										var mail = document.form1.txtMail;
									
										var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
										if (!pattern.test(mail.value)) {
											alert("请输入正确的邮箱地址。");
											mail.focus();
											return false;
										}
										return true;
									}
								</script>
								<form id="form1" name="form1" method="post"
									action="userServlet?command=studentchangeinfo"
									onsubmit="return emailCheck()">
									<table width="500" border="0"
										style="font-size:14px ; color:#000 ; font-family: 'Microsoft YaHei'">
										<tr>
											<td width="150" align="center">姓名：</td>
											<td height="30"><label for="txtName"></label> <input
												name="txtName" type="text" id="txtName"
												value="${loginUser.stuName }" readonly="readonly"
												style="border: thin solid #A0A0A4" /></td>
										</tr>
										<tr>
											<td align="center">学号：</td>
											<td height="30"><label for="txtNo"></label> <input
												name="txtNo" type="text" id="txtNo"
												value="${loginUser.stuNo }" readonly="readonly"
												style="border: thin solid #A0A0A4" /></td>
										</tr>
										<tr>
											<c:forEach items="${professions}" var="profession">
												<c:if
													test="${ profession.professionNo == loginUser.professNo}">
													<td align="center">专业：</td>
													<td height="30"><label for="txtAcademy"></label> <input
														name="txtAcademy" type="text" id="txtAcademy"
														value="${profession.professionName }" readonly="readonly"
														style="border: thin solid #A0A0A4" /></td>
												</c:if>
											</c:forEach>
										</tr>
										<tr>
											<td height="30" align="center">邮箱：</td>
											<td height="30" align="left"><input name="txtMail"
												type="text" id="txtMail" value="${loginUser.stuEmail }"
												style="border: thin solid #A0A0A4" /></td>
										</tr>
										<tr>
											<td height="30" align="right"><input type="submit"
												name="btnChange" id="btnChange" value="修改信息"
												style="border: thin solid #A0A0A4" /></td>
											<td height="30" align="left">&nbsp;</td>
										</tr>
									</table>
								</form>
								<form name="msgform" method="POST">
									<input type="hidden" name="type" value="in" />
								</form>
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