<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="css/projectstyle.css"> 
 <style>
 
 div {
 
 text-align:center;
 font-size: 17px;
 padding: 100px 0px 0px 0px;
 }
 

 </style>
 
</head>
<body>
  <% session.setAttribute("table", "acceptedPaper"); %>


    <%  /* the attribute "err" will be set if an error occurred. 
    If it is set, print err's value (an error string)) */ %>
    
    <%     
    if(request.getAttribute("err") != null) 
    { 
    %>
    <BR> 
        ** <%= request.getAttribute("err") %> ** 
    <BR>
    <%
    } else if(request.getAttribute("okay") != null)
    {
    %>
     <BR> 
        ** <%= request.getAttribute("okay") %> ** 
     <BR>
   <%
    }
   %>


    <h3 align="center"> VIEW Accepted Papers  </h3>
    <p> Lists all papers given two yes recommendations or more </p>
<table border="5px ridge #1C6EA4" width="70%" align="center">
    <tr>
        <th>Paper ID</th>
        <th>Title</th>
         <th>Recommendation Count</th>
    </tr>
<c:forEach items="${aPaperList}" var="acceptedpaper">
    <tr>
        <td>${acceptedpaper.paperid}</td>
        <td>${acceptedpaper.title}</td>
        <td>${acceptedpaper.recommendation_count}</td>
    </tr>
</c:forEach>
</table>




<div>
          <!-- button that returns to Query page -->
 <form name="menuReturn" method="post" action="partTwoQueries.jsp">
<input type="submit"name="button" value="Click to return to  Queries" />
</form>

<!-- button that returns to main menu -->
 <form name="menuReturn" method="post" action="viewAll.jsp">
<input type="submit"name="button" value="Click to return to menu" />
</form>
</div>
</body>
</html>