<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="css/projectstyle.css"> 
<title>Welcome</title>
<style>
h1 {
    text-align: center;
}

b1 {
    text-align: center;
}
</style>
</head>
<body>


 <h1>CSC Social Media </h1>
        <h1>Welcome!</h1>
    <br>
  <!--   
  
  <form name="loginForm" method="post" action="loginServlet">
    Username: <input type="text" name="username"/> <br/>
    Password: <input type="password" name="password"/> <br/>
    <input type="submit" value="Login" />
</form> <a href="<c:url value='/findAll'/>" target="body">Click here to populate the table</a>&nbsp;&nbsp;
 	-->
<b1>
 <form name="loginForm" method="post" action="populateTables">

    <input type="submit" value="Click here to populate the tables" />
</form>

</b1>


</body>
</html>