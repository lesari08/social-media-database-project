<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="css/projectstyle.css"> 
 <style>
     div 
     {
     text-align:center;
     padding: 100px 0px 0px 0px;
     }
 </style>
</head>
<body>

	<h3 align="center"> Write Order Table </h3>
	<p> Lists each author with their paper and order of contribution significance </p>
	<table border="5px ridge #1C6EA4" width="70%" align="center">
	<tr>
		<th>Date Written</th>
		<th>Blog ID</th>
		<th>Username</th>
	</tr>
<c:forEach items="${WriteBlogList}" var="write">
	<tr>
		<td>${write.dateWritten}</td>
		<td>${write.blogId}</td>
		<td>${write.userName}</td>
	</tr>
</c:forEach>
</table>


<!-- button that returns to main menu -->
<div>
 <form name="menuReturn" method="post" action="viewAll.jsp">
<input type="submit"name="button" value="Click to return to menu" />
</form>
</div>
</body>
</html>