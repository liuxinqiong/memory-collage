<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>专业管理</title>
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
<script type="text/javascript" src="<%=basePath%>scripts/jquery-1.7.js"></script>
<script type="text/javascript">
function sousuo(){
	window.open("gaojisousuo.htm","","depended=0,alwaysRaised=1,width=800,height=510,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}
function profe(){
	var profeType = document.getElementById("profeType").value;
	window.location.href='<%=basePath%>bgmProfeServlet?command=profeByAcadeNo&profeByAcadeNo='+profeType;
	
}
</script>
<script type="text/javascript">
function showCustomer(profeType)
{
console.log(profeType);
	$.ajax({
		        type: "POST",
		        dataType:"json",
		        url: '<%=basePath%>bgmProfeServlet?command=profeByAcadeNo&profeByAcadeNo='+profeType,
		        success: function(result){
		        console.log(result);
		        console.log(result.professions);
		        console.log(result.professions[0].professionName);
		        var content="";
		        for(var i=0;i<result.professions.length;i++){
		          /*  $("#testTable").append("<tr id='test123'><td bgcolor='#FFFFFF' align='center' ><span>"+(i+1)+"</span></td><td height='20' bgcolor='#FFFFFF' align='center'>"+result.professions[i].professionName+"</td>");
                    
                   $("#testTable").append("<td bgcolor='#FFFFFF' align='center'><a href=''>删除</a></td></tr>");
		           
		           content+="<tr><td bgcolor='#FFFFFF' align='center' ><span>"+(i+1)+"</span></td><td height='20' bgcolor='#FFFFFF' align='center'>"+${item.professionName}+"</td> <td bgcolor='#FFFFFF'  align='center'><a href='bgmProfeServlet?command=delete&profeDeletId=${item.professionNo}&profeByAcadeNo=${academie.academyNo}'">删除</a></td> </tr>";
		           */
		        }
		        document.getElementById().innerHTML=content;
			     }
			});
}
<%-- var xmlhttp;    
if (profeType=="")
  {
  document.getElementById("profeType").innerHTML="123";
  return;
  }
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    //document.getElementById("profeType").innerHTML=xmlhttp.responseText;
    console.log(xmlhttp.responseText);
    console.log(xmlhttp.responseText.professions);
    }
  }
xmlhttp.open("GET","<%=basePath%>bgmProfeServlet?command=profeByAcadeNo&profeByAcadeNo="+profeType,true);
xmlhttp.send(); --%>

</script>
</head>

<body>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">

		<tr>
			<td height="30">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="62" background="admin/images/nav04.gif">

							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="40%"><br />
									</td>
									<td width="60%"></td>

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

							<table width="80%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td align="right">
										<form action="<%=basePath%>bgmProfeServlet" name="form1"
											id="form1">
											<select name="profeType" id="profeType" onchange="profe()">
												<option value="123" selected="selected">-请选择-</option>
												<c:forEach items="${academies}" var="item">
													<option value="${item.academyNo}">${item.academyName}</option>
												</c:forEach>
											</select>
											<!-- <input type="submit" name="Submit1" value="查看"/>
					<input type="hidden" name="command" value="profeByAcadeNo"/> -->
										</form></td>
									<td align="left" colspan="2"><a
										href="<%=basePath%>bgmProfeServlet?command=addProfess">添加专业</a>
									</td>
								</tr>
								<tr>
									<td height="40" class="font42" width="80%">
										<table width="100%" border="0" cellpadding="4" id="testTable"
											cellspacing="1" bgcolor="#464646" class="newfont03">

											<tr>
												<td height="20" colspan="4" align="center" bgcolor="#EEEEEE"
													class="tablestyle_title">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													${academie.academyName} &nbsp;</td>
											</tr>
											<tr>
												<td width="3%" align="center" bgcolor="#EEEEEE">序号</td>
												<td width="70%" height="20" align="center" bgcolor="#EEEEEE">专业名称</td>

												<td width="27%" align="center" bgcolor="#EEEEEE">操作</td>
											</tr>
											<%
												int i = 0;
											%>
											<c:forEach items="${professions}" var="item">
											<div id="professionData">
												<tr>
													<td bgcolor="#FFFFFF" align="center"><span>
															<%
																i++;
															%><%=i%></span>
													</td>
													<td height="20" bgcolor="#FFFFFF" align="center">${item.professionName}</td>

													<td bgcolor="#FFFFFF" align="center"><a
														href="<%=basePath%>bgmProfeServlet?command=delete&profeDeletId=${item.professionNo}&profeByAcadeNo=${academie.academyNo}">删除</a>
													</td>
												</tr>
											</div>
											</c:forEach>
										</table></td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>