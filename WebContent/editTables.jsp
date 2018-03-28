<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <style>
  </style>
  <title>Author Edits</title>
  
 </head>
 <body>
 <%-- /* <jsp:include page="/jspsLists/listAuthors.jsp"/> */ --%>

    <% 
    System.out.println("editBlogTable...");
    /*-- If the attribute 'table' is not null, set it equal to our currentTable
    else, assign to currentTable an empty string instead */
    String currentTable = (session.getAttribute("table") != null)? 
                             session.getAttribute("table").toString() : ""; 
    %>
<BR>
<BR>
    <!-- **************************************************************
        Defining variables below, many with the same name as their value
        just to avoid hard coding strings into the code 
     *****************************************************************-->
    <c:set var = "choice" scope = "session" value = "${param}"/>
    <c:set var = "update" scope = "session" value = "update"/>
    <c:set var = "delete" scope = "session" value = "delete"/>
    <c:set var = "insert" scope = "session" value = "insert"/>
    <c:set var = "paper" scope = "session" value = "paper"/>
    <c:set var = "pc" scope = "session" value = "pcmember"/>
    <c:set var = "review" scope = "session" value = "review"/>
    <c:set var = "table" scope = "session" value ="<%= currentTable %>" />
   
   <%--  ***************************************************************************
   c:choose behaves like a c++ switch statement
   The variable 'choice' is set based on the internal buttonlabel the user clicked.
    The buttons have different labels depending on what edits the user choose from the
    'edit table' dropdown menu*/ %>
   ********************************************************************************** --%>

   
   <c:choose>
       <%-- ***If the user makes the choice to insert a value***--%>
      <c:when test="${fn:contains(choice, insert)}">
      
         <%-- ************************************************************
         The If statements below will ask the user for different  
         variables/column values based on the table they want to insert into
        ******************************************************************* --%>
         <c:if test = "${fn:contains(table, blog)}">
            <br />
            <fieldset>
            <legend> To insert a new Blog, complete the forms below </legend>
            <br>
            <form name="editForm" method="post" action="UserInsertions">
              Blog Subject: <input type="text" name="subject"/> <br/>
              Blog Description: <input type="text" name="description"/> <br/>
              Blog Tag: <input type="text" name="tag"/> <br/>
      
              <!-- using the input below to pass the name of the table into the form -->
              <!--so the servlet handing this request knows which table in our db to manipulate -->
              <input type="hidden" name="table" value= "<%= currentTable %>"/>
              <input type="submit" name ="inbutton" value="submit" />
            </form>
            </fieldset>
         </c:if>
         
         <%--  *****If pcmember is the table.....***** --%>
         <c:if test = "${fn:contains(table, pc)}">
            <br />
            <fieldset>
            <legend> To insert a new PC Member, complete the forms below </legend>
            <br>
            <form name="editForm" method="post" action="UserInsertions">
              Email: <input type="text" name="email"/> <br/>
              Name: <input type="text" name="name"/> <br/>
              <input type="hidden" name="table" value= "<%= currentTable %>"/>
              <input type="submit" name ="inbutton" value="submit" />
            </form>
            </fieldset>
         </c:if>
     
          <%--   *****If review is the table....*****  --%>
         <c:if test = "${fn:contains(table, review)}">
            <br />
            <fieldset>
            <legend> To insert a new Review, complete the forms below </legend>
            <br>
            <form name="editForm" method="post" action="UserInsertions">
         
              Date(YYYY-MM-DD): <input type="text" name="date"/> <br/>
              Comment: <input type="text" name="comment"/> <br/>
              Recommendation: <input type="text" name="recommendation"/> <br/>
              Paper ID: <input type="text" name="paperid"/> <br/>
              Email: <input type="text" name="email"/> <br/>
              <input type="hidden" name="table" value= "<%= currentTable %>"/>
              <input type="submit" name ="inbutton" value="submit" />
            </form>
            </fieldset>
         </c:if>
      </c:when>
      
     <%--***************************************************
      ****** Test below checks to see if the user   ************
      ******    want to make updates to a table    *********** 
      *******************************************************--%>
      <c:when test="${fn:contains(choice, update)}">
          <c:if test = "${fn:contains(table, paper)}">
          
          <fieldset> 
            <legend>Enter the paper ID of the paper you would like to 
            edit, followed by its values specified below<br>To keep a particular attribute
            unchanged, enter in its original value on the proper line <br> </legend>
           <form name="editForm" method="post" action="UserInsertions">
             Paper ID: <input type="text" name="paperid"/> <br/>
             Title: <input type="text" name="title"/> <br/>
             Abstract: <input type="text" name="abstract"/> <br/>
             PDF File Name: <input type="text" name="pdf"/> <br/>
             <input type="hidden" name="table" value= "<%= currentTable %>"/>
             <input type="submit" name ="upbutton" value="submit" />
           </form>
         </fieldset>
        <br />
        </c:if>
        
        <c:if test = "${fn:contains(table, pc)}">
          
          <fieldset> 
            <legend>Enter the Member ID of the PC Member you would like to 
            edit, followed by its values specified below<br>To keep a particular attribute
            unchanged, enter in its original value on the proper line <br> </legend>
           <form name="editForm" method="post" action="UserInsertions">
             Member ID: <input type="text" name="memberid"/> <br/>
             Email: <input type="text" name="email"/> <br/>
             Name: <input type="text" name="name"/> <br/>
             <input type="hidden" name="table" value= "<%= currentTable %>"/>
             <input type="submit" name ="upbutton" value="submit" />
           </form>
         </fieldset>
            <br />
        </c:if>
        
         <c:if test = "${fn:contains(table, review)}">
          
          <fieldset> 
            <legend>Enter the Report ID of the review you would like to 
            edit, followed by its values specified below<br>To keep a particular attribute
            unchanged, enter in its original value on the proper line <br> </legend>
           <form name="editForm" method="post" action="UserInsertions">
              Report ID <input type="text" name="reportid"/> <br/>
              Date(YYYY-MM-DD): <input type="text" name="date"/> <br/>
              Comment: <input type="text" name="comment"/> <br/>
              Recommendation: <input type="text" name="recommendation"/> <br/>
              Paper ID: <input type="text" name="paperid"/> <br/>
              Email: <input type="text" name="email"/> <br/>
              <input type="hidden" name="table" value= "<%= currentTable %>"/>
              <input type="submit" name ="inbutton" value="submit" />
           </form>
         </fieldset>
            <br />
        </c:if>
        
      </c:when>
      
     <%--  ***************************************************
      ****** Test below checks to see if the user   ************
      ******    want to delete a tuple from a table  *********** 
      *******************************************************--%>
      <c:when test="${fn:contains(choice, delete)}">
         <c:if test = "${fn:contains(table, paper)}">
            <fieldset>
            <legend>Enter the Paper ID  of the paper to be deleted below</legend>
            <BR><BR>
            <form name="editForm" method="post" action="UserInsertions">
                Paper ID: <input type="text" name="paperid"/> <br/>
                <input type="hidden" name="table" value= "<%= currentTable %>"/>
                <input type="submit" name ="debutton" value="submit" />
            </form>
            </fieldset>
            <br/>
        </c:if>
        
        <c:if test = "${fn:contains(table, pc)}">
            <fieldset>
            <legend>Enter the Member ID of the PC Member to be deleted below</legend>
            <BR><BR>
            <form name="editForm" method="post" action="UserInsertions">
                Member ID: <input type="text" name="memberid"/> <br/>
                <input type="hidden" name="table" value= "<%= currentTable %>"/>
                <input type="submit" name ="debutton" value="submit" />
            </form>
            </fieldset>
            <br/>
        </c:if>
        
        <c:if test = "${fn:contains(table, review)}">
            <fieldset>
            <legend>Enter the Report ID of the paper to be deleted below</legend>
            <BR><BR>
            <form name="editForm" method="post" action="UserInsertions">
                Report ID: <input type="text" name="reportid"/> <br/>
                <input type="hidden" name="table" value= "<%= currentTable %>"/>
                <input type="submit" name ="debutton" value="submit" />
            </form>
            </fieldset>
            <br/>
        </c:if>
        
     </c:when>
    
      <c:otherwise>
       CMS
       <br />
      </c:otherwise>
    </c:choose>
   


 </body>
</html>