<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
	<%@include file="include/head.jsp"%>
	<div id="position" class="wrap">
		您现在的位置：<a href="index.html">易买网</a> &gt; <a href="product-list.html">图书音像</a>
		&gt; 图书
	</div>
	<div id="main" class="wrap">
		<div class="lefter">
			<div class="box">
				<h2>商品分类</h2>
				<dl>
					<dt>图书音像</dt>
					<dd>
						<a href="product-list.html">图书</a>
					</dd>
					<dd>
						<a href="product-list.html">音乐</a>
					</dd>
					<dt>百货</dt>
					<dd>
						<a href="product-list.html">运动健康</a>
					</dd>
					<dd>
						<a href="product-list.html">服装</a>
					</dd>
					<dd>
						<a href="product-list.html">家居</a>
					</dd>
					<dd>
						<a href="product-list.html">美妆</a>
					</dd>
					<dd>
						<a href="product-list.html">母婴</a>
					</dd>
					<dd>
						<a href="product-list.html">食品</a>
					</dd>
					<dd>
						<a href="product-list.html">手机数码</a>
					</dd>
					<dd>
						<a href="product-list.html">家具首饰</a>
					</dd>
					<dd>
						<a href="product-list.html">手表饰品</a>
					</dd>
					<dd>
						<a href="product-list.html">鞋包</a>
					</dd>
					<dd>
						<a href="product-list.html">家电</a>
					</dd>
					<dd>
						<a href="product-list.html">电脑办公</a>
					</dd>
					<dd>
						<a href="product-list.html">玩具文具</a>
					</dd>
					<dd>
						<a href="product-list.html">汽车用品</a>
					</dd>
				</dl>
			</div>
		</div>
		<div id="product" class="main">
			<h1>${product.pname }</h1>
			<div class="infos">
				<div class="thumb">
					<img src="images/product/${product.pfileName }" width="200px" />
				</div>
				<div class="buy">
					<p>
						商城价：<span class="price">￥${product.pprice }</span>
					</p>
					<p>库 存：有货</p>
					<p>库 存：有货</p>
					<p>库 存：有货</p>
					<p>库 存：有货</p>
					<div class="button">
						<script>
				function goBuy(pid){
					location.href="productServlet?command=buy&pid="+pid;
				}
				</script>
						<input type="button" name="button" value=""
							onclick="goBuy(${product.pid})" /><a href="#">放入购物车</a>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="introduce">
				<h2>
					<strong>商品详情</strong>
				</h2>
				<div class="text">
					sdf<br /> sdf<br />
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy;  All Rights Reserved.
		京ICP证1000001号</div>
</body>
</html>
