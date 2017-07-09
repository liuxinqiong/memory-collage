<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="fr wd310 ">
	<div class="wd310 fr blue_bian_top">
		<div class="dw blueTitle">
			<h3 class="fl zt_cu06">
				<a href="#" target="_blank">热门老师排行</a>
			</h3>
		</div>
		<div style="">
			<table width="300" border="0">
				<c:forEach items="${ teacherSati}" var="Teacher">
					<tr>
						<td width="70" height="30"
							style="font-size:16px; color:#2A7FAA  ;font-family: 'Microsoft YaHei'">${Teacher.teaName }</td>
						<td width="200" height="30">满意度：${Teacher.teaSatisfaction }</td>
					</tr>
				</c:forEach>
			</table>



		</div>
		<div class="dw blueTitle">
			<h3 class="fl zt_cu06">
				<a href="#" target="_blank">老师积分排行</a>
			</h3>
		</div>
		<div style="">
			<table width="300" border="0">
				<c:forEach items="${ teacherPoint}" var="Teacher">
					<tr>
						<td width="70" height="30"
							style="font-size:16px; color:#2A7FAA  ;font-family: 'Microsoft YaHei'">${Teacher.teaName }</td>
						<td width="200" height="30">积分：${Teacher.points }</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		
	</div>

	

</div>