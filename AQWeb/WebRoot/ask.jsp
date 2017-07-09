<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<title>提问</title>
<script language="javascript" src="scripts/jquery-1.7.js"></script>
<!-- fckeditor -->
<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
<script type="text/javascript">

window.onload = function()
{
	// Automatically calculates the editor base path based on the _samples directory.
	// This is usefull only for these samples. A real application should use something like this:
	// oFCKeditor.BasePath = '/fckeditor/' ;	// '/fckeditor/' is the default value.
	//	var sBasePath = document.location.pathname.substring(0,document.location.pathname.lastIndexOf('_samples')) ;
	var sBasePath="<%=request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath()
					+ "/fckeditor/"%>";
	var oFCKeditor = new FCKeditor('askContent') ;
	oFCKeditor.BasePath	= sBasePath ;
	oFCKeditor.ReplaceTextarea() ;
	alert(sBasePath);
	
}
</script>
<!-- end of fckeditor -->

<script>
function getProfession(){
    var no= document.getElementById("ClassLevel1").value;
    // alert($("ClassLevel1").val());
//	var url="questionServlet?command=getProfession&academyNo="+no;
//	$.get(url,null,function(msg){
//		var showProfessions=document.getElementById("ClassLevel2");
		//msg当成一个对象
//		var jsonObj=jQuery.parseJSON(msg);
//		var professions =jsonObj.professions;
//		var content="";
//		for(var i=0;i<professions.length;i++){
//			var profession=professions[i];
//			content +="<option value="+profession.professionNo+">"+profession.professionName+"</option>";
//		}
//		showProfessions.innerHTML=content;
//	});

	$.ajax({
			type:"GET",
			url:"questionServlet?command=getProfession&academyNo="+no,
			data:null,
			success:function(msg){
				var showProfessions=document.getElementById("ClassLevel2");
				//msg当成一个对象
				var jsonObj=jQuery.parseJSON(msg);
				var professions =jsonObj.professions;
				var content="";
				for(var i=0;i<professions.length;i++){
					var profession=professions[i];
					content +="<option value="+profession.professionNo+">"+profession.professionName+"</option>";
				}
				showProfessions.innerHTML=content;
			}
		});
}

function checkItem(){
	var askTopic=document.getElementById("mytitle").value;
	var askContent=document.getElementById("askContent").value;
	var profession=document.getElementById("ClassLevel2").value;
	if(askTopic==""||askContent==""||profession==""){
		alert("问题信息填写不完全");
		return false;
	}
	return true;
}

</script>
</head>
<body style="font-family: 'Microsoft YaHei', 微软雅黑,雅黑,黑体;">
	<%@include file="include/top.jsp"%>
	<div class="out">
		<div class="greenBox">
			<%@include file="include/type.jsp"%>
			<div class="fr wd665 ">
				<!--middle开始-->
				<div class="dw01 blue_bian titBg_blue mt03">
					<h3 class="fl zt_cu11" style="color:#333333">
						您现在所在位置：<a href="questionServlet" class="CurrentPosition"
							style="color:#000000;">长沙理工大学在线答疑V1.0</a><span
							class="CurrentPosition"> 提问问题</span>
					</h3>
				</div>

				<div class="mt03 blue_bian_top">
					<div class="dw blueTitle">
						<h3 class="fl zt_cu10">请将您的问题告诉我们:</h3>
					</div>
					<div class="">
						<form name="askform" action="questionServlet?command=addAskInfo"
							method="post" onsubmit="return checkItem();">
							<div class="your_quest">
								<div class="yq">
									<div class="fl q_zuo">
										<h2 style="padding:15px 0 0 45px">我想问一下：</h2>
									</div>
									<div class="fr" style="margin-top:20px;">
										<input type="text" maxlength="100" size="65" name="title"
											value="" id="mytitle" class="input1" />
									</div>
								</div>
								<div class="yq">
									<div class="fl q_zuo" style="height:220px;">
										<h2 style="padding:15px 0 0 45px">我要详细说：</h2>
									</div>
									<div class="fr" style="margin-top:20px;margin-right:33px;">
										<textarea cols="63" rows="13" name="askContent"
											id="askContent"></textarea>
									</div>
								</div>

								<div class="yq">
									<div class="fl q_zuo" style="height:220px;">
										<h2 style="padding:15px 0 0 45px">问题分类：</h2>
									</div>
									<div class="fr" style="margin-top:20px;">
										<table border="0" cellpadding="0" cellspacing="0"
											width="515px">
											<tr valign="top">
												<td width="15%"><select id="ClassLevel1"
													class="catselect2" size="8" name="classlevel1"
													onchange="getProfession();">

														<c:forEach items="${ academyInfos}" var="academyInfo">
															<option value="${academyInfo.academyNo}">${academyInfo.academyName
																}</option>
														</c:forEach>
												</select>
												</td>
												<td align="center" valign="middle" width="7%">
													<div>
														<b>→</b>
													</div>
												</td>
												<td width="15%"><select id="ClassLevel2"
													class="catselect2" size="8" name="classlevel2">
														<option selected></option>
												</select>
												</td>

												<td width="32%"></td>
											</tr>
											<tr valign="top">
												<td class="" colspan="6" align="left" valign="middle">请您选择正确的分类，以使您的问题尽快得到解答。</td>
											</tr>
										</table>
									</div>
								</div>

								<div class="yq">
									<input type="hidden" value="0" name="cid" /> <input
										type="hidden" value="0" name="askfromuid" />
								</div>
								<div class="yq">
									<div class="fl q_zuo" style="height:60px;">
										<h2 style="padding:15px 0 0 45px"></h2>
									</div>
									<div class="fr">
										<div id="searchresult"></div>
										<div class="yqsubmit">
											<button name="submit" type="submit" class="add_question"></button>
										</div>
									</div>
								</div>

							</div>
						</form>
					</div>
				</div>
				<div class="leftc">
					<!--middle结束-->
				</div>
			</div>
		</div>
	</div>
	<%@include file="include/footer.jsp"%>
</body>
</html>