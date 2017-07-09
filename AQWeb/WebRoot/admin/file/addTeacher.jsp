<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>项目管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="admin/css/css.css" rel="stylesheet" type="text/css" />
<link href="admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="admin/js/xiangmu.js"></script>
<script language="javascript" src="scripts/jquery-1.7.js"></script>
</head>

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

	function getProfession() {
		var no = document.getElementById("academy").value;
		var url = "questionServlet?command=getProfession&academyNo=" + no;
		$.get(url, null, function(msg) {
			var showProfessions = document.getElementById("profession");
			//msg当成一个对象
			var jsonObj = jQuery.parseJSON(msg);
			var professions = jsonObj.professions;
			var content = "";
			for ( var i = 0; i < professions.length; i++) {
				var profession = professions[i];
				content += "<option value="+profession.professionNo+">"
						+ profession.professionName + "</option>";
			}
			showProfessions.innerHTML = content;
		});
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
<script>
function checkItem(){
	var teaName=document.getElementById("teaName").value;
	var teaPwd=document.getElementById("teaPwd").value;
	var teaGrade=document.getElementById("teaGrade").value;
	var textarea=document.getElementById("textarea").value;
	var academy=document.getElementById("select1").value;
	if(teaName==""||teaPwd==""||teaGrade==""||textarea==""||academy==""){
	    alert("信息填写不完全");
	    return false;
	}
	return true;
}

</script>
	<form action="<%=basePath%>bgmTeacherServlet?command=add" method="post"
		 name="fom" id="fom" target="sypost" onsubmit="return checkItem();">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">教师添加页面</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<TR>
								<TD width="100%">
									<fieldset style="height:100%;">
										<legend>添加教师</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%">

											<tr>
												<td nowrap align="right" width="13%">教师姓名:</td>
												<td width="41%"><input name="teaName" id="teaName" class="text"
													style="width:250px" type="text" size="40" /> <span
													class="red"> *</span></td>

												<td align="right" width="19%">教师密码:</td>
												<td width="27%"><input name="teaPwd" id="teaPwd"
													class="text" style="width:154px" /></td>
											</tr>
											<tr>
												<td nowrap align="right">教师级别:</td>
												<td><input name="teaGrade" id="teaGrade" class="text"
													style="width:154px" /></td>
											</tr>
											<tr>
												<td nowrap align="right">学院:</td>
												<td><select name="select1" id="select1" onchange="getProfession();"
													id="academy">
														<option selected="selected">==请选择==</option>
														<c:forEach items="${academyInfos}" var="academyInfo">
															<option value="${academyInfo.academyNo }">${academyInfo.academyName
																}</option>
														</c:forEach>
												</select></td>

												<td nowrap align="right">专业:</td>
												<td><select name="select2" id="profession">
														<option selected="selected">==请选择==</option>
														<c:forEach items="professionInfos" var="professionInfos">
															<option>暂不</option>
														</c:forEach>
												</select></td>

											</tr>
											<tr>
												<td nowrap align="right" height="120px">教师描述:</td>
												<td colspan="3"><textarea id="textarea" name="deacrip"
														rows="5" cols="80"></textarea></td>
											</tr>
										</table>
										<br />
									</fieldset>
								</TD>
							</TR>

						</TABLE>
					</td>
				</tr>
				<TR>
					<TD colspan="2" align="center" height="50px"><input
						type="submit" name="submit" value="保存" class="button"/> <input
						type="button" name="Submit2" value="返回" class="button"
						onclick="window.history.go(-1);" /></TD>
				</TR>
			</TABLE>
		</div>
	</form>
</body>
</html>

