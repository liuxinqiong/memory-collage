<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link type="text/css" rel="stylesheet" href="css/index.css" />
</head>

<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体; ">
	<div class="topBg">
		<%@include file="include/top.jsp"%>
		<div
			style="background-color: #CCCCCC; margin-top:20px; padding-left:20px; font-size:14px;">用户登录</div>
		<div class="user_reg">

			<script language="javascript">
				function CheckItem() {

					var username = document.loginform.username.value;
					var password = document.loginform.password.value;
					if (username == "") {
						document.getElementById("new").innerHTML = "用户名不能为空";
						return false;
					}
					else if (password == "") {
						document.getElementById("new").innerHTML = "密码不能为空";
						return false;
					}

					return true;

				}

				function FocusItem() {
					document.getElementById("new").innerHTML = "";
					document.getElementById("new").innerHTML = "";

				}
			</script>
			<form name="loginform" action="userServlet?command=login"
				method="post" onsubmit="return CheckItem()">
				<div style="padding-top:20px; padding-bottom:5px;">
					<div class="reg_field">
						<h2>登录用户：</h2>
						<select name="selType">
							<option value="学生登录">学生登录</option>
							<option value="教师登录">教师登录</option>
							<option value="管理员登录">管理员登录</option>
						</select>
					</div>
					<div style="clear:both"></div>
					<div class="reg_field">
						<h2>用户名：</h2>
						<input type="text" name="username" id="username" class="input_reg"
							onfocus="FocusItem()" onblur="CheckItem()" /> &nbsp;&nbsp; <span
							id="new" style=" color:#F00"> </span>
					</div>
					<div style="clear:both"></div>
					<div class="reg_field">
						<h2>密&nbsp;&nbsp;码：</h2>
						<input type="password" name="password" id="password"
							class="input_reg" onfocus="FocusItem()" onblur="CheckItem()" />
						&nbsp;&nbsp; <span id="again" style=" color:#F00"> </span>
					</div>
					<div style="clear:both"></div>
					<div class="get_pa">
						<input type="checkbox" value="2592000" name="cookietime"
							id="cookietime" />下次自动登录
					</div>
					<div class="get_pa">
						<input type="submit" name="submit" class="button4"
							value="登&nbsp;录" />&nbsp;忘记密码了？请点击 “<a class="red"
							href="http://ask.bjbys.net.cn/?user/getpass.html">找回密码</a>” 。
					</div>
				</div>
			</form>
			<div class="clr"></div>
		</div>
		<%@include file="include/footer.jsp"%>
	</div>

</body>
</html>
