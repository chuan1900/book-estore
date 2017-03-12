<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Lists of Orders</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px rgb(78,78,78);
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
<script type="text/javascript">
	function showDetail(oid){
		var but = document.getElementById("but"+oid);
		var tab = document.getElementById("tab"+oid);
		if(but.value == "Order Details"){
			// 1.创建异步对象.
			var xhr = createXmlHttpRequest();
			// 2.设置监听
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						var data = xhr.responseText;
						// alert(data);
						var json = eval("("+data+")");
						
						for(var i = 0 ;i<json.length;i++){
							tab.innerHTML +="<tr><td><img width='65' height='70' src='${pageContext.request.contextPath}/"+json[i].book.image+"'></td><td>"+json[i].book.bname+"</td><td>"+json[i].book.author+"</td><td>"+json[i].book.price+"</td><td>"+json[i].count+"</td><td>"+json[i].subtotal+"</td></tr>";
						}
					}
				}
			}
			// 3.打开链接
			xhr.open("GET","${pageContext.request.contextPath}/adminOrderServlet?method=showDetail&oid="+oid+"&time="+new Date().getTime(),true);
			// 4.发送数据
			xhr.send(null);
			but.value = "Close";
		}else{
			tab.innerHTML = "";
			but.value="Order Details";
		}
	}
	
	function  createXmlHttpRequest(){
		   var xmlHttp;
		   try{    //Firefox, Opera 8.0+, Safari
		           xmlHttp=new XMLHttpRequest();
		    }catch (e){
		           try{    //Internet Explorer
		                  xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		            }catch (e){
		                  try{
		                          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		                  }catch (e){}  
		           }
		    }
		   return xmlHttp;
		 }
</script>
  </head>
  
  <body style="background: rgb(254,238,189);">
<h1>My Orders</h1>

<table border="1" width="100%" cellspacing="0" background="black">
	<c:forEach var="order" items="${ list }">
	<tr bgcolor="rgb(78,78,78)" bordercolor="rgb(78,78,78)" style="color: white;">
		<td colspan="6">
			Orders：${ order.oid }　Time：${ order.ordertime }　Value：<font color="red"><b>${ order.total }</b></font>
			<c:if test="${ order.state==1 }">Unpaid</c:if>
			<c:if test="${ order.state==2 }"><a href="${ pageContext.request.contextPath }/adminOrderServlet?method=update&oid=${ order.oid }">Post</a></c:if>
			<c:if test="${ order.state==3 }">Waiting for Receiving</c:if>
			<c:if test="${ order.state==4 }">Close Orders</c:if>
			&nbsp;&nbsp;&nbsp;&nbsp;<input id='but${ order.oid }' type="button" value="Order Details" onclick="showDetail('${ order.oid }')">
		</td>
	</tr>
	<tr>
		<td colspan="6">
			<table border="1" id="tab${ order.oid }" width="100%">
				
			</table>
		</td>
	</tr>
  </c:forEach>
 

</table>
  </body>
</html>
