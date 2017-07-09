<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" src="scripts/AjaxUtil.js"></script>
<script type="text/javascript" src="scripts/productCategory.js"></script>
</head>
<body>
	<div>
		&nbsp;
		<%
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			String nowDate = sdf.format(date);
		%>
		当前时间：<%=nowDate%>
	</div>
	<jsp:include page="include/head.jsp"></jsp:include>
	<div id="main" class="wrap">
		<div class="lefter">
		<!-- 分类 -->
			<jsp:include page="include/left.jsp"></jsp:include>
			<div class="spacer"></div>
			<div class="last-view">
				<h2>最近浏览</h2>
				<dl class="clearfix">
				<c:forEach items="${productsHostory}" var="product">
					<dt>
						<img src="images/product/${product.pfileName}" width="54"/>
					</dt>
					<dd class="title">
						<a href="productServlet?command=productView&pid=${product.pid}">${product.pname }</a>
					</dd>
				</c:forEach>
				</dl>
			</div>
		</div>
		<div class="main">
			<div class="price-off">
				<h2>今日特价</h2>
				${pageUtil}
				<ul class="product clearfix" id="showProducts">
					<c:forEach items="${pageUtil.infos}" var="product">
						<li>
							<dl>
								<dt>
									<a href="productServlet?command=productView&pid=${product.pid}"
										target="_blank"><img
										src="images/product/${product.pfileName }" /> </a>
								</dt>
								<dd class="title">
									<a href="productServlet?command=productView&pid=${product.pid}"
										target="_blank">${product.pname }</a>
								</dd>
								<dd class="price">${product.pprice }</dd>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="side">
				<div class="news-list">
					<h4>最新公告</h4>
					<ul>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
					</ul>
				</div>
				<div class="spacer"></div>
				<div class="news-list">
					<h4>新闻动态</h4>
					<ul>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
						<li><a href="news-view.html" target="_blank">抢钱啦</a></li>
					</ul>
				</div>
			</div>
			<div class="spacer clear"></div>
			<div class="hot">
				<h2>热卖推荐</h2>
				<ul class="product clearfix">
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/1.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">法国德菲丝松露精品巧克力500g/盒</a>
							</dd>
							<dd class="price">￥108.0</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/2.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">乐扣普通型保鲜盒圣诞7件套</a>
							</dd>
							<dd class="price">￥69.90</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/3.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">欧珀莱均衡保湿四件套</a>
							</dd>
							<dd class="price">￥279.0</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/4.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">联想笔记本电脑 高速独立显存</a>
							</dd>
							<dd class="price">￥4199</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/5.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">法姿韩版显瘦彩边时尚牛仔铅笔裤</a>
							</dd>
							<dd class="price">￥49.00</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/6.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">Genius925纯银施华洛世奇水晶吊坠</a>
							</dd>
							<dd class="price">￥69.90</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/7.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">利仁2018M福满堂电饼铛
									好用实惠</a>
							</dd>
							<dd class="price">￥268.0</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/8.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">达派高档拉杆箱20寸 经典款式</a>
							</dd>
							<dd class="price">￥198.0</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/9.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">爱国者MP4 全屏触摸多格式播放
									4G</a>
							</dd>
							<dd class="price">￥289.0</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/10.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">多美滋金装金盾3阶段幼儿配方奶粉</a>
							</dd>
							<dd class="price">￥186.0</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/1.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">法国德菲丝松露精品巧克力500g/盒</a>
							</dd>
							<dd class="price">￥108.0</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<a href="product-view.html" target="_blank"><img
									src="images/product/2.jpg" /> </a>
							</dt>
							<dd class="title">
								<a href="product-view.html" target="_blank">乐扣普通型保鲜盒圣诞7件套</a>
							</dd>
							<dd class="price">￥69.90</dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%-- 	<div>
		当前登录人数：<%=((List<String>) application.getAttribute("allLoginUser"))
					.size()%></div>--%>
	<div id="footer">Copyright &copy;  All Rights Reserved.
		京ICP证1000001号</div>
</body>
</html>
