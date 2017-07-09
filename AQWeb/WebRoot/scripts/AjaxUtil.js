/**
*
*/

function AjaxUtil(method,url,postParams,callBack){
	this.method=method;
	this.url=url;
	this.postParams=postParams;
	this.createXmlHttpRequest = function() {
		if (window.ActiveXObject) {// iE
			return new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		}
	};
	
	this.sendAjaxReq=function(){
		var xmlHttpRequest =this.createXmlHttpRequest();	
		xmlHttpRequest.onreadystatechange=function(){
			if(xmlHttpRequest.readyState==4&&xmlHttpRequest.status==200){
//				var dataInfos=xmlHttpRequest.responseXML;
//				if(dataInfos.documentElement){
				
					dataInfos=xmlHttpRequest.responseText;
					alert(dataInfos);
//				}
				callBack(dataInfos);
			}
		};
		
		xmlHttpRequest.open(this.method,this.url,true);
		if (this.reqMothed == "GET") {
			xmlHttpRequest.send(null);
		} else {
			xmlHttpRequest.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded"); // 用post方法的话，一定要加这句。
			xmlHttpRequest.send(this.postParams);
		}
	};
	this.sendAjaxReq();
}