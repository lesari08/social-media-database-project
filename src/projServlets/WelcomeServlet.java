package projServlets;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.*;  

import javax.servlet.*;  
import javax.servlet.http.*;  
  

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    String n=request.getParameter("userName");  
    //out.print("Welcome "+n);  wont print when dispatch is in place below
    
    RequestDispatcher rd=request.getRequestDispatcher("/gather.jsp");  
    rd.forward(request, response); 
    }  
  
}  