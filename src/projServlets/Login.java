package projServlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class Login
 */





import java.io.*;  

import javax.servlet.*;  
import javax.servlet.http.*;  

  
@WebServlet("/Login")
public class Login extends HttpServlet 
{  
	private static final long serialVersionUID = 1L;
	String username = "john";
	String password = "pass1234";
  
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException 
   {  
  
    HttpSession session = null;
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter(); 
	String n=request.getParameter("userName"); 
   	String p=request.getParameter("userPass"); 
    
    //check to see if this class is being invoked because of a pushed logout button
    String signOut = (request.getParameter("button") == null)? "null": request.getParameter("button");
    if(!(signOut.contains("Logout")))
    {
          
    	if(p.equals(password) && n.equals(username))
    	{  
    		RequestDispatcher rd=request.getRequestDispatcher("/WelcomeServlet");  
        
        //optional
        session=request.getSession();  
        rd.forward(request, response);  
    	}  
    	else
    	{  
        out.print("Sorry UserName or Password Error!");  
        RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
        rd.include(request, response);                 
        }  
    }
    else
    {
    	out.print("You are now signed out"); 
    	RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
    	rd.include(request, response);       
    	session.invalidate();
    }
    
 
  
   } 
}
