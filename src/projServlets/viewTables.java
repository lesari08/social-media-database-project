

/* viewTables.java
This ile used to call different jsps that output the table tuples, 
depending on what buttons the user presses */


package projServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tableDao.*;



/**
 * Servlet implementation class viewTables
 */
@WebServlet("/viewTables")
public class viewTables extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewTables() {  super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/** This function is called when a user clicks on a button to view a specific
	 * table in our database. The text on the buttons state which table clicking on it
	 * will lead to. 
	 * This function will look at the text of the button clicked, and will use that information
	 * to forward the request to the jsp responsible for printing the corresponding table
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String listUserTuples = "/jspsLists/listUsers.jsp";
		String listBlogTuples = "/jspsLists/listBlogs.jsp";
		String listBlogTagTuples = "/jspsLists/listBlogTags.jsp";
		String listWriteTuples = "/jspsLists/listWrite.jsp";
		String listHobbyTuples = "/jspsLists/listHobbies.jsp";
		String listFollowTuples = "/jspsLists/listFollows.jsp";
		String listCommentTuples = "/jspsLists/listComments.jsp";
		String listAllTables = "/ViewAll.jsp";
		
		String choice;
		
      try 
      {
    	//There are multiple buttons that can trigger this method, 
    	// so in order to know which one was clicked,
    	//we will save the parameter of the button clicked 
	    choice = request.getParameter("button");
	    
	    //for debugging purposes, doesnt print anywhere but the console
	    System.out.println(choice);
	    
	    //If the user clicked the button with the text "View Author Table", 
	    //we will call findAll() to retrieve all the tuples of the table from
	    //our database, saved into a List. Then we will forward that list to 
	    //the jsp responsible for printing the author table
	    if(choice.equals("View User Table"))
	    {
	    	System.out.println("if == View User Table");
	    	SiteUsersDao siteuserdao = new SiteUsersDao();
	    	request.setAttribute("UserList", siteuserdao.findall());
			request.getRequestDispatcher(listUserTuples).forward(request, response);
	    	

	    }
	    else if(choice.equals("View Blog Table"))
	    {
	    	BlogsDao blogsdao = new BlogsDao();
	    	
	    	//Send the request object the results of the findall() function, which is a list of 
	    	//the tuples in the specified table. 
			request.setAttribute("BlogList", blogsdao.findall());
			//then, go to the jsp file path that will output the tuples
			request.getRequestDispatcher(listBlogTuples).forward(request, response);
	    }
	    else if(choice.equals("View Blog Tags Table"))
	    {
	    	BlogTagsDao blogtagdao = new BlogTagsDao();
	    	
			request.setAttribute("BlogTagList", blogtagdao.findall());
			request.getRequestDispatcher(listBlogTagTuples).forward(request, response);
	    }
	    else if(choice.equals("View Write Blog Table"))
	    {
	    	WriteBlogDao writeblogdao = new WriteBlogDao();
	    	request.setAttribute("WriteBlogList", writeblogdao.findall());
	    	request.getRequestDispatcher(listWriteTuples).forward(request, response);
	    }
	    else if(choice.equals("View Hobbies Table"))
	    {
	    	HobbiesDao hobbiesdao = new HobbiesDao();
	    	request.setAttribute("HobbyList", hobbiesdao.findall());
	    	request.getRequestDispatcher(listHobbyTuples).forward(request, response);
	    }
	    else if(choice.equals("View Follow Table"))
	    {
	  
	    	FollowDao followdao = new FollowDao();
	    	request.setAttribute("FollowList", followdao.findall());
	    	request.getRequestDispatcher(listFollowTuples).forward(request, response);
	    	
	    }
	    else if(choice.equals("View Comments Table"))
	    {
	  
	    	CommentsDao commentsdao = new CommentsDao();
	    	request.setAttribute("CommentList", commentsdao.findall());
	    	request.getRequestDispatcher(listCommentTuples).forward(request, response);
	    	
	    }
	    //If none of the other if conditions are triggered for whatever reason
	    else
	    {
	      request.getRequestDispatcher(listAllTables).forward(request, response);
	    }	
	    	
	   }
       catch (Exception e) 
       {
         System.out.println(e);
	     request.getRequestDispatcher(listAllTables).forward(request, response);
	   }

	}
}
