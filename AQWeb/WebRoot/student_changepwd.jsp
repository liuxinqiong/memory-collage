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
									&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;
									&nbsp; &nbsp;&nbsp;<font color="#000000" size="-1">修改密码</font>&nbsp;
								</p>
								<hr size="1" />
								<div class="clr"></div>
							</div>
							<div class="quest_sort_list">
							
							
							<script language="javascript">
									
									function CheckItem() {
										
										
										var pwdNew = document.form1.pwdNew.value;
										var pwdAgain =  document.form1.pwdAgain.value;
										if(pwdNew==""){
											document.getElementById("new").innerHTML = "密码不能为空";
											return false;
										}										
										else if(pwdNew!=pwdAgain){
											document.getElementById("new").innerHTML = "两次密码不一致";
											return false;
										}
										
										return true;				
										
									}
									
									function FocusItem() {
										document.getElementById("new").innerHTML = "";
										document.getElementById("new").innerHTML = "";
										
									}
								</script>
								<form id="form1" name="form1" method="post" action="userServlet?command=studentchangeinfo" onsubmit="return CheckItem()">
									<table width="500" border="0"
										style="font-size:14px ; color:#000 ; font-family: 'Microsoft YaHei'">
										
										<tr>
											<td width="150" align="center">新密码：</td>
											<td height="30"><label for="pwdNew2"></label> <input
												type="password" name="pwdNew" id="pwdNew"
												style="border: thin solid #A0A0A4" onfocus="FocusItem()" onblur="CheckItem()"/>&nbsp;&nbsp;<span id = "new" style=" color:#F00"  > </span></td>
										</tr>
										<tr>
											<td align="center">确认密码：</td>
											<td height="30"><label for="pwdAgain2"></label> <input
												type="password" name="pwdAgain" id="pwdAgain"
												style="border: thin solid #A0A0A4" onfocus="FocusItem()" onblur="CheckItem()"/>&nbsp;&nbsp;<span id = "again" style=" color:#F00"  > </span></td>
										</tr>
										<tr>
											<td height="30" align="right"><input type="submit"
												name="btnSubmit" id="btnSubmit" value="修改密码"
												style="border: thin solid #A0A0A4" /></td>
											<td height="30" align="center">&nbsp;</td>
										</tr>
									</table>
								</form>
								<p>&nbsp;</p>
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