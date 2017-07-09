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
									&nbsp; &nbsp; &nbsp; <a
										href="http://ask.bjbys.net.cn/?message/new.html"> <font
										color="#000000" size="-1">我参与的问题</font></a>&nbsp; 
								</p>
								<hr size="1" />
								<div class="clr"></div>
							</div>
							<div class="quest_sort_list">
								<table width="780" border="0" align="center">
									<tr
										style="font-size:14px; font-family: 'Microsoft YaHei'; color:#8B8B8B ">
										<td width="40">状态</td>
										<td width="400">标题（共0条）</td>
										<td width="100">发件人</td>
										<td>日期</td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
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