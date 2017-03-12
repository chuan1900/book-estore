<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>List</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
var bar1 = new Q6MenuBar("bar1", "Book Estore");
function load() {
	bar1.colorStyle = 2;
	bar1.config.imgDir = "<c:url value='/menu/img/'/>";
	bar1.config.radioButton=false;
	bar1.add("Category", "Show Category", "${ pageContext.request.contextPath}/adminCategoryServlet?method=findAll", "body");
	bar1.add("Category", "Add  Category", "<c:url value='/adminjsps/admin/category/add.jsp'/>", "body");

	bar1.add("Book", "Show Details", "${ pageContext.request.contextPath }/adminBookServlet?method=findAll", "body");
	bar1.add("Book", "Add New Books", "${ pageContext.request.contextPath }/adminBookServlet?method=saveUI", "body");
	bar1.add("Book", "Change Books", "${ pageContext.request.contextPath }/adminBookServlet?method=pushUpUI", "body");

	bar1.add("Order", "   All Orders ", "${ pageContext.request.contextPath }/adminOrderServlet?method=findAll", "body");
	bar1.add("Order", " Unpaid Orders", "${ pageContext.request.contextPath }/adminOrderServlet?method=findAll&state=1", "body");
	bar1.add("Order", "   Paid Orders ", "${ pageContext.request.contextPath }/adminOrderServlet?method=findAll&state=2", "body");
	bar1.add("Order", "Unreceived Orders", "${ pageContext.request.contextPath }/adminOrderServlet?method=findAll&state=3", "body");
	bar1.add("Order", " Finished Orders", "${ pageContext.request.contextPath }/adminOrderServlet?method=findAll&state=4", "body");

	var d = document.getElementById("menu");
	d.innerHTML = bar1.toString();
}
</script>

</head>

<body onload="load()" style="margin: 0px; background: rgb(254,238,189);">
<div id="menu"></div>

</body>
</html>
