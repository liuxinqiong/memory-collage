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
<title>新增学院</title>
<link rel="stylesheet" rev="stylesheet" href="admin/css/style.css" type="text/css" media="all" />


<script language="JavaScript" type="text/javascript">
function tishi()
{
  var a=confirm('数据库中保存有该人员基本信息，您可以修改或保留该信息。');
  if(a!=true)return false;
  window.open("冲突页.htm","","depended=0,alwaysRaised=1,width=800,height=400,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}

function check()
{
document.getElementById("aa").style.display="";
}

</script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>

<body class="ContentBody">
  <form action="<%=basePath%>bgmProfeServlet?command=addProfession" method="post"  name="form">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >添加专业</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr>
			<td width="100%">
				<fieldset style="height:100%;">
				<legend>专业信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					    
					  <tr>
					    <td align="right" width="11%"><font>专业名</font></td>
					    <td align="left" width="27%">
					    <input name="professName" id="professName" type="text" class="text" style="width:154px" />
					    </td>
					    <td>
					    <select name="academy" id="academy" onchange="" >
					   <c:forEach items="${academies}" var="item">  
					   <option value="${item.academyNo}" selected="selected" >${item.academyName}</option>
					   </c:forEach>
					</select>
					    </td>
					   </tr>

					  </table>
			  <br />
				</fieldset>			</td>
			
		</tr>

		</table>
	
	
	 </td>
  </tr>

		<tr>
			<td colspan="2" align="center" height="50px">
			<input type="submit" name="Submit" value="保存" class="button"/>　
			
			<input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/></td>
		</tr>
		</table>
	

</div>
</form>
</body>
</html>
