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
		您现在的位置： <a href="index.jsp">易买网</a> &gt; 购物车
	</div>
	<div class="wrap">
		<div id="shopping">
			<form action="productServlet?command=order" method="post">
				<table>
					<tr>
						<th>商品名称</th>
						<th>商品价格</th>
						<th>购买数量</th>
						<th>操作</th>
					</tr>
					<script type="text/javascript">
				function reloadPrice(pid){
					var num=document.getElementById("number_id_1"+pid).value;
					location.href="productServlet?command=buy&pid="+pid+"&num="+num;
				}
				</script>
					<c:forEach items="${cart}" var="item">
						<tr id="product_id_1">
							<td class="thumb"><img
								src="images/product/${item.product.pfileName}" /> <a
								href="product-view.jsp">${item.product.pname }</a></td>
							<td class="price" id="price_id_1"><span>￥${item.product.pprice*item.number}</span>
								<input type="hidden" value="99" /></td>
							<td class="number">
								<dl>
									<dt>
										<input id="number_id_1${item.product.pid}" type="text"
											name="number" value="${item.number}" />
									</dt>
									<dd onclick="reloadPrice(${item.product.pid});">修改</dd>
								</dl></td>
							<td class="delete"><a href="javascript:delShopping(1);">删除</a>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td><span>合计</span></td>
						<td colspan="3" class="price"><span>￥${count}</span>
						</td>
					</tr>
				</table>
				<div class="button">
					<input type="submit" value=""/>
				</div>
			</form>
		</div>
		<%-- 	<script type="text/javascript">
		document.write("Cookie中记录的购物车商品ID：" + getCookie("product")
			+ "，可以在动态页面中进行读取");
	</script>--%>
	</div>
	<div id="footer">Copyright &copy;  All Rights Reserved.
		京ICP证1000001号</div>
</body>
</html>
