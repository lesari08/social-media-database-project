package projServlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import table.domains.*;
import tableDao.*;


/**
 * Servlet implementation class UserInsertions
 */
@WebServlet("/UserInsertions")
public class UserInsertions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInsertions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{
		//this variable is used to store the return value of sql queries or specialized
		//return values to signal particular errors
		int check = 0;
		
		//get the parameter 'table' we set in our table edit forms so we are aware
		//of what table needs the edit
		String table = request.getParameter("table");
		
		String listReviewTuples = "/jspsLists/listReviews.jsp";
		String listBlogTuples = "/jspsLists/listBlogs.jsp";
		String listPcmemberTuples = "/jspsLists/listPcmembers.jsp";
		String listAllTables = "/ViewAll.jsp";
				
		System.out.println("table:" + table);

	     try 
	     { 
	    	 switch(table)
	    	 {
	   
	    	 case "blog":
		    		
	    		 	Blogs blog = new Blogs();
	    		 	BlogsDao blogsdao = new BlogsDao();
	    		 	
	    		 	BlogTags blogtag = new BlogTags();
	    		 	BlogTagsDao blogtagdao = new BlogTagsDao();
		 	     	//Papers paper = new Papers();
		 	     	//PaperDao paperdao = new PaperDao();
		    		 
			    	/*if inbutton is valid, user wanted to insert a row,
			    	if upbutton is valid, they'd like to update a row, 
			    	if debutton is valid, they'd like to delete  a row */
				    if(request.getParameter("inbutton") != null)
				    {
				    	System.out.println("insertion");
				    	blog.setBlogSubject(request.getParameter("subject"));
					    blog.setDescription(request.getParameter("description"));
					    String tag = request.getParameter("tag");
				      
					    //add new blog object to database
				        check = blogsdao.add(blog, tag);
				        
				        	
				    }
				    else if (request.getParameter("upbutton") != null)
				    {
				    	System.out.println("update");
				    	
				    	blog.setBlogSubject(request.getParameter("subject"));
					    blog.setDescription(request.getParameter("description"));
						check = blogsdao.update(blog);

				    }
				    else if (request.getParameter("debutton") != null)
				    {
				    	check = blogsdao.delete(Integer.parseInt(request.getParameter("blogid")));
				    	System.out.println("delete");
				    }
		 	     	//Call checking() to find out if our interaction with the database
				    //occurred smoothly or an error occurred
		 	     	checking(request, response, check);
		 	     	
			        //At the end of this method call, find all current papers
		 	     	//and send that list to RequestDispatcher
			        request.setAttribute("BlogList", blogsdao.findall());
					request.getRequestDispatcher(listBlogTuples).forward(request, response);
					
		    		break;
	    	 case "site_user":
		    		
	    		 	SiteUsers siteuser = new SiteUsers();
	    		 	SiteUsersDao siteuserdao = new SiteUsersDao();
		 	     	//Papers paper = new Papers();
		 	     	//PaperDao paperdao = new PaperDao();
		    		 
			    	/*if inbutton is valid, user wanted to insert a row,
			    	if upbutton is valid, they'd like to update a row, 
			    	if debutton is valid, they'd like to delete  a row */
				    if(request.getParameter("inbutton") != null)
				    {
				    	System.out.println("insertion");
				    	
				    	/*siteuser.setFirstName(request.getParameter("firstName"));
				    	siteuser.setLastName(request.getParameter("firstName"));
				    	siteuser.setEmail(email);
				    	siteuser.setE
						paper.setTitle(request.getParameter("title"));
						paper.setAbstract1(request.getParameter("abstract"));
						paper.setPdf(request.getParameter("pdf"));*/
						
				        /*call the function to that adds a tuple to the database and save
				        its return value to find out if the insertion was successful */
				      //  check = paperdao.add(paper);
				        	
				    }
				    else if (request.getParameter("upbutton") != null)
				    {
				    	System.out.println("update");
				    	
				   /* 	paper.setPaperid(Integer.parseInt(request.getParameter("paperid")));
				    	paper.setTitle(request.getParameter("title"));
						paper.setAbstract1(request.getParameter("abstract"));
						paper.setPdf(request.getParameter("pdf"));*/
						
						//check = paperdao.update(paper);

				    }
				    else if (request.getParameter("debutton") != null)
				    {
				    	//check = paperdao.delete(Integer.parseInt(request.getParameter("paperid")));
				    	System.out.println("delete");
				    }
		 	     	//Call checking() to find out if our interaction with the database
				    //occurred smoothly or an error occurred
		 	     	checking(request, response, check);
		 	     	
			        //At the end of this method call, find all current papers
		 	     	//and send that list to RequestDispatcher
			       // request.setAttribute("PaperList", paperdao.findall());
					//request.getRequestDispatcher(listPaperTuples).forward(request, response);
					
		    		break;
	    	 
	    		
	    	default:
	    		System.out.println("UserInsertions.java: Switch(table):  default triggered");	
	    		request.getRequestDispatcher(listAllTables).forward(request, response);
	    	 }
	
		 }
	     catch (Exception e) 
	     {
	         System.out.println(e);
		 }
		
		
	}
	
	protected void checking(HttpServletRequest request, HttpServletResponse response, int check) throws ServletException, IOException 
	{
	    
	    /*If, else statements in the event that an error occurred while attempting 
	    to modify the database */
	    System.out.println("check: " + check);
        if( check < 1)
        {    
        	//the function add will return -10 if a duplicate entry triggered an SQL exception
        	if(check == -10)
        	{
        	  System.out.println("duplicate insertion attempt. check:" + check);
        	  request.setAttribute("err", "You cannot add the same author twice! Insertion was not performed");
        	}
        	else
        	{
        		request.setAttribute("err", "Modification could not be completed. Please ensue that your entries are valid");
        	}
        }
        else
        {
        	request.setAttribute("okay", check + " tuples has been updated");
        }
	}
}