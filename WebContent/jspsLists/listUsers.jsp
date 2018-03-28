<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="css/projectstyle.css"> 
 
 <style>
div {
    text-align: center;
    padding: 100px 0px 0px 0px;
    color: #3399ff;
}
</style>
</head>
<body>
   
   <%! String driverName="com.mysql.jdbc.Driver"; %>
   <%! String url="jdbc:mysql://localhost/sampledb";  %>
   <%! String  user="sampledb"; %>
   <%! String password="pass1234"; %>
   
   
  

	<h3> User Table ex</h3>
	<p> Lists all users</p>
	<table border = "5px ridge #1C6EA4" width="70%" align="center">
		<%
		Connection con = null;
		PreparedStatement ps = null;
		try
		{
		Class.forName(driverName);
		con = DriverManager.getConnection(url,user,password);
		String sql = "SELECT * FROM site_user";
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(); 
	%>
		<tr>
			<th>First</th>
			<th>Last</th>
			<th>Username</th>
			<th>Email</th>
		</tr> 
		<%
			while(rs.next())
			{
			String fname = rs.getString("first_name"); 
			String lname = rs.getString("last_name");
			String uname = rs.getString("username");
			String email = rs.getString("email");
		%>
			<tr>
				<td><%=fname %></td>
				<td><%=lname %></td>
				<td><%=uname %></td>
				<td><%=email %></td>
			</tr>
		<% 
			}
		
		}
		catch(SQLException sqe)
		{ 
		out.println(sqe);
		}
		%>
	</table>


   <% //attempt %>
   
	<h3> User Table </h3>
	<p> Lists all users</p>
	<table border = "5px ridge #1C6EA4" width="70%" align="center">
		<tr>
			<th>First</th>
			<th>Last</th>
			<th>Username</th>
			<th>Email</th>
		</tr> 
		<c:forEach items="${UserList}" var="user">
			<tr>
				<td>${user.firstName }</td>
				<td>${user.lastName }</td>
				<td><a href="viewAll.jsp"> ${user.userName }</a></td>
				<td>${user.email }</td>
			</tr>
		</c:forEach>
	</table>



<div>
	<!-- button that returns to main menu -->
	 <form name="menuReturn" method="post" action="viewAll.jsp">
		<input type="submit"name="button" value="Click to return to menu" />
	</form>
</div>
	
</body>
</html>