<%@page import="cn.com.entity.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js" charset="gbk"></script>
<script>
function checkForm(frm)
{
	var els = frm.getElementsByTagName("input");
	for(var i=0; i<els.length; i++) {
		if(typeof(els[i].getAttribute("onblur")) == "string") {
			if(!CheckItem(els[i])) return false;
		}
	}
	return true;
}
</script>
</head>
<body>
	<jsp:include page="include/head.jsp"></jsp:include>
	<div id="register" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<h1>欢迎注册易买网</h1>
				<ul class="steps clearfix">
					<li class="current"><em></em>填写注册信息</li>
					<li class="last"><em></em>注册成功</li>
				</ul>
				<form id="regForm" method="post"
					action="userServlet?command=register"
					onsubmit="return checkForm(this);">
					<table>
						<tr>
							<td class="field">用户名：</td>
							<td>
								<!-- 隐藏域 --> <input type="hidden" name="uid"
								value="${loginUser!=null?loginUser.id:0}" />
					<input class="text" type="text" name="userName"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" value="${loginUser.uname }" /><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">登录密码：</td>
							<td><input class="text" type="password" id="passWord"
								name="passWord" onfocus="FocusItem(this)"
								onblur="CheckItem(this);" value="${loginUser.pwd}" /><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">确认密码：</td>
							<td><input class="text" type="password" name="rePassWord"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" value="${loginUser.pwd}"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">验证码：</td>
							<td><input class="text verycode" type="text" name="veryCode"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" /><img
								id="veryCode" src="" /><span></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-green"><input type="submit"
									name="submit" value="提交注册" />
							</label>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy;  All Rights Reserved.
		京ICP证1000001号</div>
</body>
</html>
