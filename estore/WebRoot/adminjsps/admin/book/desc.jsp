<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
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
	body {
		font-size: 10pt;
		background: rgb(254,238,189);
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
<script type="text/javascript">
	function update(){
		var form1 = document.getElementById("form");
		form1.action="${pageContext.request.contextPath}/updateBookServlet";
		form1.submit();
	}
	
	function pushDown(){
		var form1 = document.getElementById("form");
		form1.action="${pageContext.request.contextPath}/adminBookServlet?method=pushDown";
		form1.enctype="application/x-www-form-urlencoded";
		form1.submit();
	}
</script>
  </head>
  
 
  <body>
  <div>
    <img src="${ pageContext.request.contextPath }/${book.image}" width="130" height="140" border="0"/>
  </div>
  <form style="margin:20px;" id="form" action="" method="post" enctype="multipart/form-data">
    <input type="hidden" name="bid" value="${book.bid}">
    <input type="hidden" name="isdel" value="${book.isdel}">
    <input type="hidden" name="image" value="${book.image}">
  	 Name  ：<input type="text" name="bname" value="${ book.bname }"/><br/>
  	 Price ：<input type="text" name="price" value="${ book.price }"/><br/>
  	 Image ：<input type="file" name="image"/><br/>
  	 Author ：<input type="text" name="author" value="${ book.author }"/><br/>
  	Category：<select style="width: 150px; height: 20px;" name="cid">
  			<c:forEach var="c" items="${ list }">
     		<option value="${ c.cid }" <c:if test="${ book.cid == c.cid }">selected</c:if>>${ c.cname }</option>
     		</c:forEach>
    	</select><br/>
  	<input type="button" value="Delete" onclick="pushDown()"/>
  	<input type="button" value="Change" onclick="update()"/>
  </form>
  </body>
</html>
