<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="box">
	<h2>
		商品分类
	</h2>
	<dl>
	<c:forEach items="${productCategorys}" var="productCategory">
	<c:choose>
	<c:when test="${productCategory.epcId==productCategory.epcParentId}">
		<dt>
			${productCategory.epcName }
		</dt>
	</c:when>
	<c:otherwise>	
		<dd>
			<a href="javascript:getCategory(${productCategory.epcId})">${productCategory.epcName }</a>
		</dd>
		</c:otherwise>
		</c:choose>
	</c:forEach>
	</dl>
</div>