<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>viewAll</title>
 <link rel="stylesheet" href="css/projectstyle.css"> 
<style>
div {
    text-align: center;
}
.button {
height: 40px;
width: 300px;
}

.body{
text-align: center;
}
</style>

</head>
<body>

<div>
<h1> Menu </h1>

<% 
  /* This statement hides the button to populate the review table, if assigns
    have already been made    */
  if (session.getAttribute("reviewsPopulated") != "yes")
  {
	 pageContext.setAttribute("reviewsPopulated", "no");
  }
  
  /* if(assignmentsCompleteSignal != null && !successMessagePrinted) */
  if(request.getAttribute("atr") != null )
  {
 %>
   Success!! Assignments have been completed. <BR> You can now view them under 'View Review Table'
 <% 
  session.setAttribute("reviewsPopulated", "yes" );
  }
%>

<BR>
 <form name="users" method="post" action="viewTables">
<input type="submit" class="button" name="button" value="View User Table" />
</form>

<BR>
 <form name="blogs" method="post" action="viewTables">
<input type="submit" class="button" name="button" value="View Blog Table" />
</form>
<BR>
 <form name="blogtags" method="post" action="viewTables">
<input type="submit" class="button" name="button" value="View Blog Tags Table" />
</form>
<BR>
 <form name="writeblogs" method="post" action="viewTables">
<input type="submit"  class="button" name="button" value="View Write Blog Table" />
</form>
<BR>
 <form name="hobbies" method="post" action="viewTables">
<input type="submit" class="button" name="button" value="View Hobbies Table" />
</form>
<BR>
<BR>
 <form name="follow" method="post" action="viewTables">
<input type="submit" class="button" name="button" value="View Follow Table" />
</form>
<BR>
<BR>
 <form name="comments" method="post" action="viewTables">
<input type="submit" class="button" name="button" value="View Comments Table" />
</form>
<BR>
<BR>
<BR>
 <form name="signout" method="post" action="Login">
<input type="submit" name="button" value="Logout" />
</form>


</div>
</body>
</html>