<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Change Category</title>
    
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
    <h1>Change Category</h1>
    <form action="${ pageContext.request.contextPath }/adminCategoryServlet" method="post">
    	<input type="hidden" name="method" value="update" />
    	<input type="hidden" name="cid" value="${ category.cid }" />
    	Category Name：<input type="text" name="cname" value="${ category.cname }"/>
    	<input type="submit" value="Change"/>
    </form>
  </body>
</html>
