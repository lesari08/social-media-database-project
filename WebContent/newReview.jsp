<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="css/projectstyle.css"> 
<title>Assign Reviewers</title>
</head>
<body>

To submit a review, enter the following information
<BR>
<BR>


<form name="loginForm" method="post" action="viewAll.jsp">
    Report ID: <input type="text" name="reportId"/> <br/>
    Date(YYYY-MM-DD): <input type="text" name="date"/> <br/>
    Comment: <input type="text" name="comment"/> <br/>
    Recommendation(Y or N): <input type="text" name="rec"/> <br/>
    Email: <input type="text" name="email"/> <br/>
    PaperId: <input type="text" name="paperid"/> <br/>
    <input type="submit" name ="subreview" value="submit" />
</form>
</body>
</html>