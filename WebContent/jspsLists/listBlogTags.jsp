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
  padding: 100px 0px 0px 0px;
 }
 
 p {
 
  text-align:center;
 }
 </style>
 
 
    <h3 align="center"> Blog Tag Table </h3>
    <p> Lists blog tags </p>
</head>
<body>
     <% session.setAttribute("table", "blogtable"); %>
     
     
<input type="text" id="tagInput" onkeyup="filterFunc()" placeholder="Search for blogs by tag" title="Type in a name">

<table border="5px ridge #1C6EA4" width="70%" align="center" id="tagTable">
  <tr class="header">
    <th style="width:60%;">Blog ID</th>
    <th style="width:40%;">Tag</th>
  </tr>
 <c:forEach items="${BlogTagList}" var="btag">
    <tr>
        <td>${btag.blogId }</td>
        <td>${btag.tag }</td>
    </tr>
</c:forEach>
</table>

<script>
function filterFunc() {
  var input, filter, table, tr, td, i;
  input = document.getElementById("tagInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("tagTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
</script>



 <form name="menuReturn" method="post" action="viewAll.jsp">
<input type="submit"name="button" value="Click to return to menu" />
</form>
</div>
</body>
</html>