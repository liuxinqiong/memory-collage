function getCategory(pcChildId){
	var url="productServlet?command=getCategory&pcChildId="+pcChildId;
	var ajaxUtil=new AjaxUtil("GET",url,null,parseProducts_json);
}

function parseProducts(msg){
	var showProducts=document.getElementById("showProducts");
	var products=msg.split("||");
	var content="";
	for(var i=0;i<products.length-1;i++){
		var proAttributes=products[i].split("|");
		content +="<li><dl><dt><a href=\"productServlet?command=productView&pid="+proAttributes[0]+" target=\"_blank\">"
			+ "<img src=\"images/product/"
			+ proAttributes[3]
			+ "\" /></a></dt><dd class=\"title\"><a href=\"productServlet?command=productView&pid="+proAttributes[0]+""
			+ "target=\"_blank\">"
			+ proAttributes[1]
			+ "</a></dd><dd class=\"price\">&yen;"
			+ proAttributes[2]
			+ "</dd></dl></li>";
	}
	showProducts.innerHTML = content;
}

function parseProducts_xml(msg){
	var showProducts=document.getElementById("showProducts");
	var products=msg.getElementsByTagName("product");
	var content="";
	for(var i=0;i<products.length;i++){
		//不能使用innerHTML
		var pid=products[i].getElementsByTagName("pid")[0].firstChild.nodeValue;
		var pname=products[i].getElementsByTagName("pname")[0].firstChild.nodeValue;
		var pprice=products[i].getElementsByTagName("pprice")[0].firstChild.nodeValue;
		var pfileName=products[i].getElementsByTagName("pfileName")[0].firstChild.nodeValue;
		content +="<li><dl><dt><a href=\"productServlet?command=productView&pid="+pid+" target=\"_blank\">"
		+ "<img src=\"images/product/"
		+ pfileName
		+ "\" /></a></dt><dd class=\"title\"><a href=\"productServlet?command=productView&pid="+pid+""
		+ "target=\"_blank\">"
		+ pname
		+ "</a></dd><dd class=\"price\">&yen;"
		+ pprice
		+ "</dd></dl></li>";
	}
	showProducts.innerHTML = content;
}

function parseProducts_json(msg){
	var showProducts=document.getElementById("showProducts");
	//msg当成一个对象
	var jsonObj=eval("("+msg+")");
	var products = jsonObj.products;
	var content="";
	for(var i=0;i<products.length;i++){
		var product=products[i];
		content +="<li><dl><dt><a href=\"productServlet?command=productView&pid="+product.pid+" target=\"_blank\">"
		+ "<img src=\"images/product/"
		+ product.pfileName
		+ "\" /></a></dt><dd class=\"title\"><a href=\"productServlet?command=productView&pid="+product.pid+""
		+ "target=\"_blank\">"
		+ product.pname
		+ "</a></dd><dd class=\"price\">&yen;"
		+ product.pprice
		+ "</dd></dl></li>";
	}
	showProducts.innerHTML = content;
}