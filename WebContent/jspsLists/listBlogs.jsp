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
 
 <h3 align="center"> Blogs Table </h3>
    <p> Lists all blogs  </p>
<table border="5px ridge #1C6EA4" width="70%" align="center">
    <tr>
        <th>Blog ID</th>
        <th>Subject</th>
        <th>Description</th>
          <th>Writer</th>
          <th>Date Written</th>
    </tr>
<c:forEach items="${BlogList}" var="blog">
    <tr>
        <td><a href="viewAll.jsp"> ${blog.blogId}</a></td>
        <td>${blog.blogSubject}</td>
        <td>${blog.description}</td>
         <td>${blog.writer}</td>
          <td>${blog.date_written}</td>
    </tr>
</c:forEach>
</table>
</head>
<body>
  <% session.setAttribute("table", "blog"); %>


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



<div>
    <%-- drop down menu for table editing --%>
    <form action="editTables.jsp">
        <center><br>To edit the table: Choose an option from the drop-down menu, then click submit
        <br>
        <br>
        <select name="editTable">
          <option value="insertT">Insert a new Blog Post</option>
          <option value="updateT">Modify an existing Blog Post</option>
          <option value="deleteT">Delete an existing Blog Post</option>
       </select>
        <br>
       <input type="submit" value="Submit">
       </center>
    </form>

</div>

<!-- button that returns to main menu -->
<div>
 <form name="menuReturn" method="post" action="viewAll.jsp">
<input type="submit"name="button" value="Click to return to menu" />
</form>
</div>
</body>
</html>