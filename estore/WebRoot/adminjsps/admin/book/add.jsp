<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Add Book</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {background: rgb(254,238,189);}
</style>
  </head>
  
  <body>
    <h1>Add Book</h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <!-- 文件上传的三个要素:
    		* 提交的方式:Post
    		* 有个<input type='file' name="image"/>
    		* 表单的enctype="multipart/form-data"
     -->
    <form action="${ pageContext.request.contextPath }/addBookServlet" method="post" enctype="multipart/form-data">
    	 Name   ：<input style="width: 150px; height: 20px;" type="text" name="bname"/><br/>
    	 Image  ：<input style="width: 223px; height: 20px;" type="file" name="image"/><br/>
    	 Price  ：<input style="width: 150px; height: 20px;" type="text" name="price"/><br/>
    	 Author ：<input style="width: 150px; height: 20px;" type="text" name="author"/><br/>
    	Category：<select style="width: 150px; height: 20px;" name="cid">
  			<c:forEach var="c" items="${ list }">
    			<option value="${ c.cid }">${ c.cname }</option>
  			</c:forEach>
    	</select>
    	<br/>
    	<input type="submit" value="Add"/>
    </form>
  </body>
</html>
