<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="fl wd400">
	<!--热门推荐开始-->
	<div class="wd400 fl blue_bian_top ">
		<div class="dw blueTitle">
			<h3 class="fl zt_cu03">
				<a target="_top" href="#">热门推荐</a>
			</h3>
		</div>

		<dl class="greenCont ">
			<c:forEach items="${askInfos }" var="askInfo">
				<dd>
					[<a target="_blank" href="#">${askInfo.askTopic}</a>] <a
						target="_blank" href="questionServlet?command=questionView&askId=${askInfo.askNo}">${askInfo.askContent}</a>
				</dd>
			</c:forEach>
		</dl>
	</div>
	<!--热门推荐结束-->


	<!--已解决问题开始-->
	<div class="wd400 fl mt03 blue_bian_top ">
		<div class="dw blueTitle">
			<h3 class="fl zt_cu04">
				<a target="_top" href="#">已解决问题</a>
			</h3>
		</div>

		<dl class="greenCont ">
			<c:forEach items="${askInfosChecked}" var="askInfoChecked">
				<dd>
					[<a target="_blank" href="#">${askInfoChecked.askTopic}</a>] <a
						target="_blank" href="questionServlet?command=questionView&askId=${askInfoChecked.askNo}">${askInfoChecked.askContent}</a>
				</dd>
			</c:forEach>
		</dl>
	</div>

	<!--已解决问题结束-->
	<!--最新问题开始-->
	<div class="wd400 fl mt03 blue_bian_top">
		<div class="dw blueTitle">
			<h3 class="fl zt_cu05">
				<a target="_top" title="更多问题"
					href="#">最新问题</a>
			</h3>
		</div>
		<dl class="greenCont ">
			<c:forEach items="${askInfosUnChecked}" var="askInfoUnChecked">
				<dd>
					[<a target="_blank" href="#">${askInfoUnChecked.askTopic}</a>]
					<a target="_blank" href="questionServlet?command=questionView&askId=${askInfoUnChecked.askNo}">${askInfoUnChecked.askContent}</a>
				</dd>
			</c:forEach>
		</dl>
	</div>
	<!--最新问题结束-->
</div>
