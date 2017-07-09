<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>项目管理系统 by www.mycodes.net</title>
<link rel="stylesheet" rev="stylesheet" href="../css/style.css"
	type="text/css" media="all" />


<script language="JavaScript" type="text/javascript">
	function tishi() {
		var a = confirm('数据库中保存有该人员基本信息，您可以修改或保留该信息。');
		if (a != true)
			return false;
		window
				.open(
						"冲突页.htm",
						"",
						"depended=0,alwaysRaised=1,width=800,height=400,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
	}

	function check() {
		document.getElementById("aa").style.display = "";
	}

	function link() {
		alert('保存成功！');
		document.getElementById("fom").action = "xuqiumingxi.htm";
		document.getElementById("fom").submit();
	}
</script>
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->
</style>
</head>

<body class="ContentBody">
	<form action="<%=basePath%>studentServlet?command=save" method="post"
		 name="fom" id="fom" target="sypost">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th height="33" class="tablestyle_title">任务添加页面</th>
				</tr>
				<tr>
					<td height="190" class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td align="left"><input type="submit" name="Submit"
									value="保存" class="button"  /></td>
							</tr>
						</table> <legend>学生管理</legend>
						<table border="0" cellpadding="2" cellspacing="1">

							<tr>
								<td width="100" height="24" align="right" nowrap>用户名:</td>
								<td width="360"><input name="stuName" type="text"
									class="text" value="${student.stuName}" style="width:250px"
									size="30" maxlength="30" /> *
							</tr>
							<tr>


								<td width="100" height="31" align="right" nowrap>学院:</td>


								<td width="360"><select name="professNo" id="profession"
									onchange="">
										<c:forEach items="${professions}" var="profession">
											<option value="${profession.professionNo}" selected="selected">${profession.professionName}</option>
										</c:forEach>
								</select>*
							</tr>
							<tr>
								<td width="100" height="25" align="right" nowrap>邮箱:</td>
								<td width="360"><input name="stuEmail" class="text"
									style="width:250px" type="text" size="40" />
							</tr>
							<tr>
								<td width="100" height="26" align="right" nowrap>头像:</td>
								<td width="360"><input name="stuPic" class="text"
									style="width:250px" type="text" size="40" />
							</tr>

						</table> <font style="color:#F00"> 提示：*为必填 默认密码123</font>
					</td>







					
			</TABLE>


			</td>
			</tr>




			</table>

		</div>
	</form>
</body>
</html>