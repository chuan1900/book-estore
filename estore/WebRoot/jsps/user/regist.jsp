<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>REGISTER</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	function checkUsername(){
		var username = $("#username").val(); // document.getElementById("username")
		$.post("${pageContext.request.contextPath}/userServlet",{"method":"checkUsername","username":username},function(data){
			if(1==data){
				$("#span1").html("<font color='green'>You can use this username!</font>");
				$("#regBut").show();
			}else if(2==data){
				$("#span1").html("<font color='red'>The username has been used!</font>");
				$("#regBut").hide();
			}
		});
	}
	/*
		function checkUsername(){
			// 获得文本框的值
			var username = document.getElementById("username").value;
			// 1.创建异步对象
			var xhr = createXmlHttpRequest();
			// 2.设置监听
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						var data = xhr.responseText;
						if(1 == data){
							// 可以使用
							document.getElementById("span1").innerHTML="<font color='green'>用户名可以使用</font>";
							document.getElementById("regBut").style.display = "block";
						}else if(2 == data){
							// 不可以使用
							document.getElementById("span1").innerHTML="<font color='red'>用户名已经存在!</font>";
							document.getElementById("regBut").style.display = "none";
						}
					}
				}
			}
			// 3.打开链接
			xhr.open("GET","${pageContext.request.contextPath}/userServlet?method=checkUsername&username="+username+"&time="+new Date().getTime(),true);
			// 4.发送数据
			xhr.send(null);
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
		*/
	</script>
  </head>
  
  <body>
  <h1>REGISTER</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="${ pageContext.request.contextPath }/userServlet" method="post">
	<input type="hidden" name="method" value="regist"/>
	username：<input type="text" id="username" name="username" value="" onblur="checkUsername()"/><span id="span1"></span><br/>
	password：<input type="password" name="password"/><br/>
	  email ：<input type="text" name="email" value=""/><br/>
	<input type="submit" id="regBut" value="Register"/>
</form>
  </body>
</html>
