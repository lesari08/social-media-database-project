<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/projectstyle.css">
<style>
div {
	text-align: center;
	padding: 100x;
}
</style>
</head>
<body>
	<%-- 
        Here we are setting the session attribute 'table' so that it can be retrieved later,
        specifically for UserInsertions.java file.That file  will be handling user defined 
        modifications to the database. That responsibility requires that it know which
        table is being addressed at any given time
     --%>
	<% session.setAttribute("table", "review"); %>
	<div>

		<!-- the Attribute "err" will be set if an error occurred. 
    If it is set, print err's value (which will be an error string))
    
    If our operations performed correctly the Atrribute "okay" will be set. 
    In that scenario we will print okay's value (which will be a string)-->

		<%  if(request.getAttribute("err") != null) 
        {   
    %>
		<BR> **
		<%= request.getAttribute("err") %>
		** <BR>

		<%  } 
        else if(request.getAttribute("okay") != null) 
        {
    %>
		<BR> **
		<%= request.getAttribute("okay") %>
		** <BR>
		<%
        }
    %>

	</div>
	<h3>Comments Table</h3>
	<p>Lists paper assignments and submitted reports</p>
	<table border="5px ridge #1C6EA4" width="70%" align="center">
		<tr>
			<th>Comment ID</th>
			<th>Blog ID</th>
			<th>Description</th>
			<th>Sentiment</th>
			<th>Date</th>
			<th>Commenter</th>

		</tr>
		<c:forEach items="${CommentList}" var="comment" varStatus="loop">
			<tr>
				<td>${comment.commentId}</td>
				<td>${comment.blogId }</td>
				<td>${comment.description}</td>
				<td>${comment.sentiment}</td>
				<td>${comment.date_written}</td>
				<td>${comment.commenter}</td>
			</tr>

		</c:forEach>
	</table>

	<BR>
	<div>
		<% 
		    /*Show the "assign papers to pc members" button if the reviews table has not been populated yet 
		      NOte: The attribute we are comparing, 'reviewsPopulated' is initially set in viewAll.jsp */
		    if (session.getAttribute("reviewsPopulated") != "yes")
		     {
		   %>
		<form name="asn" method="post" action="AssignThree">
			<input type="submit" name="button"
				value="Click to Assign Papers to PC Members for review" />
		</form>
		<BR>
		<%
		     }
		     else
		     {
		   %>

		<!-- ***** drop down menu for table editing ***** -->
		<form action="editTableUser.jsp">
			<br>To edit: Choose an option from the drop-down menu, then
			click submit <br> <br> <select name="editTable">
				<option value="insertT">Insert a new review</option>
				<option value="updateT">Modify an existing review</option>
				<option value="deleteT">Delete an existing review</option>
			</select> <br> <input type="submit" value="Submit">
		</form>
		<%
		     }
		   %>
	</div>


	<!-- button that returns to main menu -->
	<div>
		<form name="menuReturn" method="post" action="viewAll.jsp">
			<input type="submit" name="button" value="Click to return to menu" />
		</form>
		<BR>

	</div>
</body>
</html>