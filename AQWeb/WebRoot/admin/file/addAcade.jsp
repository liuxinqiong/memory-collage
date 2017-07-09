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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增学院</title>
<link rel="stylesheet" rev="stylesheet" href="../css/style.css"
	type="text/css" media="all" />

<script type="text/javascript" src="<%=basePath%>scripts/jquery-1.7.js"></script>
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

function add(){
	/* var msg = document.getElementById("acadeName").value.replace(/[ ]/g,"");
		if(msg!=""){
		var testDiv = document.getElementsByName("testDiv");
		for ( var acadeName in testDiv) {
			alert(acadeName.value);
		}
			
		}else if(msg==""){
		 alert("新增不能为空");
		}
		 */
		 
	var name= document.getElementById("acadeName").value.replace(/[ ]/g,"");
	if(name==""){
	alert("不能为空");
	}
	else{
	var url="<%=basePath%>bgmAcademyServlet?command=addAcademy&academyName="+name;
	$.get(url,null,function(msg){
		if(msg=='true'){
		alert("一一一一一一一一一一新增成功!一一一一一一一一一一");
		}
		else alert("一一一一一一一一添加失败,"+name+"已经存在一一一一一一一一");
	});
	}
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
	<form action="<%=basePath%>bgmAcademyServlet?command=addAcade"
		method="post" name="form">
		<div class="MainDiv">
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="CContent">
				<tr>
					<th class="tablestyle_title">添加学院</th>
				</tr>
				<tr>
					<td class="CPanel">

						<table border="0" cellpadding="0" cellspacing="0"
							style="width:100%">
							<tr>
								<td width="100%">
									<fieldset style="height:100%;">
										<legend>学院信息</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width:100%">

											<tr>
												<td nowrap align="right" width="11%"><font>学 院 名</font>
												</td>
												<td align="left" width="27%"><input name="acadeName"
													id="acadeName" type="text" class="text" style="width:154px"
													value="" /></td>
											</tr>

										</table>
										<div style="display: none;">
											<table>
												<c:forEach items="${academies}" var="item">
													<tr>
														<td id="testDiv" name="testDiv">${item.academyName}</td>
													</tr>
												</c:forEach>
											</table>
										</div>
										<br />
									</fieldset></td>

							</tr>

						</table></td>
				</tr>

				<TR>
					<TD colspan="2" align="center" height="50px"><input
						type="button" name="Submit" value="保存" class="button"
						onclick="add();" /> <input type="button" name="Submit2" value="返回"
						class="button" onclick="window.history.go(-1);" />
					</TD>
				</TR>
			</TABLE>


			</td>
			</tr>



			</table>

		</div>
	</form>
</body>
</html>
