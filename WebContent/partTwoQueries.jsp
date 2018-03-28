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
height: 50px;
width: 800px;
}
</style>

</head>
<body>

<div>
<h1> Select a Query </h1>


<BR>
 <form name="question4" method="post" action="QueryService">
<input type="submit" class="button" name="button" value="List all the papers by Lu as the single author" />
</form>

<BR>
 <form name="question5" method="post" action="QueryService">
<input type="submit" class="button" name="button" value="List all the papers in which Lu is the first author" />
</form>
<BR>
 <form name="question6" method="post" action="QueryService">
<input type="submit" class="button"  name="button" value="Find all the papers coauthored by Zhang and Lu" />
</form>
<BR>
 <form name="question7" method="post" action="QueryService">
<input type="submit"  class="button" name="button" value="List the PC member who reviews the most number of papers" />
</form>
<BR>
 <form name="question8" method="post" action="QueryService">
<input type="submit"  class="button" name="button" value="List the PC members who have no papers assigned to them for review" />
</form>
<BR>
 <form name="question9" method="post" action="QueryService">
<input type="submit"  class="button" name="button" value="List all papers rejected by both PC members Matt and John" />
</form>
<BR>
<form name="question10" method="post" action="QueryService">
<input type="submit"  class="button" name="button" value="Display all the accepted papers in the system" />
</form>

<BR>
<BR>
<BR>
    <!-- button that returns to main menu -->
  <form name="menuReturn" method="post" action="viewAll.jsp">
     <input type="submit"name="button" value="Click to return to menu" />
   </form>

</div>
</body>
</html>