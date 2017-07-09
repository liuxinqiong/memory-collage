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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生管理</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.tabfont01 {
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}

.font051 {
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}

.font201 {
	font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}

html {
	overflow-x: auto;
	overflow-y: auto;
	border: 0;
}
-->
</style>

<link href="admin/css/css.css" rel="stylesheet" type="text/css" />
<link href="admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="admin/js/xiangmu.js"></script>
</head>
<SCRIPT language=JavaScript>
	function sousuo() {
		window
				.open(
						"gaojisousuo.htm",
						"",
						"depended=0,alwaysRaised=1,width=800,height=510,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
	}
	function selectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				obj[i].checked = true;
			}
		}
	}

	function unselectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				if (obj[i].checked == true)
					obj[i].checked = false;
				else
					obj[i].checked = true;
			}
		}
	}

	function link() {
		document.getElementById("fom").action = "sendxiaoxi.htm";
		document.getElementById("fom").submit();
	}

	function on_load() {
		var loadingmsg = document.getElementById("loadingmsg");
		var mainpage = document.getElementById("mainpage");
		loadingmsg.style.display = "";
		mainpage.style.display = "none";

		loadingmsg.style.display = "none";
		mainpage.style.display = "";
	}
	function selectTop(){
		var msg = document.getElementById("textfield").value;
		
		 window.location.href='<%=basePath%>bgmQuesServlet?command=select&textSelect='+msg.replace(/[ ]/g,"");
	}
</SCRIPT>

<body onload="on_load()">
	<form name="fom" id="fom" method="post" action="<%=basePath%>bgmQuesServlet?command=deleteAll">
		<table id="mainpage" width="100%" border="0" cellspacing="0"
			cellpadding="0">

			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="62" background="admin/images/nav04.gif">
								<form action=""></form>
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="21"><img src="admin/images/ico07.gif"
											width="20" height="18" />
										</td>
										<td width="550"><span>搜索问题标题</span> 
										    <input name="textfield" id="textfield" type="text" size="12" /> 
											<input name="Submit" type="button" class="right-button02" value="查 询"
											onclick="selectTop();"/>
										</td>

									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><table id="subtree1" style="DISPLAY: " width="100%"
						border="0" cellspacing="0" cellpadding="0">

						<tr>
							<td>
								
									<table width="95%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td><span>部分功能：</span>
											<c:choose><c:when test="${user.userType==0}">无权限</c:when><c:otherwise><a href="admin/file/addUser.jsp">新 增</a></c:otherwise></c:choose>
										</td>
										</tr>
										<tr>
											<td height="40" class="font42">
												<table width="100%" border="0" cellpadding="4"
													cellspacing="1" bgcolor="#464646" class="newfont03">

													<tr>
														<td height="20" colspan="7" align="center"
															bgcolor="#EEEEEE" class="tablestyle_title">
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管理员 &nbsp;</td>
													</tr>
													<tr>
														<td width="5%" align="center" bgcolor="#EEEEEE">选择</td>
														<td width="30%" height="20" align="center"
															bgcolor="#EEEEEE">姓名</td>
															<td width="35%" align="center" bgcolor="#EEEEEE">账户类型</td>
														<td width="30%" align="center" bgcolor="#EEEEEE">操作</td>
													</tr>
													<%int i=1; %>
													<c:forEach items="${users}" var="item">
														<tr align="center">
															<td bgcolor="#FFFFFF"><!-- <input type="checkbox"
																name="delid" id="delid" value="1" /> -->
																<b><%=i %><%i++; %></b>
															</td>
															<td height="20" bgcolor="#FFFFFF">${item.userName}</td>
															<td height="20" bgcolor="#FFFFFF"><c:choose><c:when test="${item.userType==0}">普通管理员</c:when><c:otherwise>超级管理员</c:otherwise></c:choose></td>
														
															<td height="20" bgcolor="#FFFFFF"><c:choose><c:when test="${user.userType==0}">无权限</c:when><c:otherwise><a
																href="<%=basePath%>bgmUserServlet?command=deleteUser&bid=${item.userNo}">删除</a></c:otherwise></c:choose>
															</td>
														</tr>
													</c:forEach>
												</table>
											</td>
										</tr>
									</table>
								
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="6"><img src="admin/images/spacer.gif" width="1"
								height="1" />
							</td>
						</tr>
						<tr>
							<td height="33"><table width="100%" border="0"
									align="center" cellpadding="0" cellspacing="0"
									class="right-font08">
									<tr>
										<td width="50%">共 <span class="right-text09">5</span> 页 |
											第 <span class="right-text09">1</span> 页</td>
										<td width="49%" align="right">[<a href="#"
											class="right-font08">首页</a> | <a href="#"
											class="right-font08">上一页</a> | <a href="#"
											class="right-font08">下一页</a> | <a href="#"
											class="right-font08">末页</a>] 转至：</td>
										<td width="1%"><table width="20" border="0"
												cellspacing="0" cellpadding="0">
												<tr>
													<td width="1%"><input name="textfield3" type="text"
														class="right-textfield03" size="1" />
													</td>
													<td width="87%"><input name="Submit23222"
														type="submit" class="right-button06" value=" " /></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>

	<div id="loadingmsg"
		style="width:100px; height:18px; top:0px; display:none;">
		<img src="file:///F|/项目管理相关资料/项目管理系统页面/images/loadon.gif" />
	</div>

</body>
</html>

