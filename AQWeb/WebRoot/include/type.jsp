<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="fl">
				<!--热门分类开始-->
				<div class="wd277 blue_bian_top">
					<div class="dw blueTitle">
						<h3 class="fl zt_cu02">所有分类</h3>
					</div>

					<dl class=" greenCont">
					
					<c:forEach items ="${academyInfos}" var="Academy">
						<div class="sort_1">
							<a href="questionServlet?command=questionList&academyNo=${Academy.academyNo }" >${Academy.academyName}</a>
						</div>
						<div class="sort_2">							
							<c:forEach  items ="${professionInfos}" var="Profession">
								<c:if test="${Profession.academyNo == Academy.academyNo}">	
									<a href="questionServlet?command=questionList&academyNo=${Academy.academyNo }&professionNo=${Profession.professionNo}">${ Profession.professionName}</a>&nbsp;
								</c:if>
							</c:forEach>
						</div>
					</c:forEach>	
					</dl>
				</div>
				<!--热门分类结束-->
			</div>
