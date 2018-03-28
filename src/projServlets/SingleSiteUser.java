package projServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import table.domains.BlogTags;
import table.domains.Blogs;
import table.domains.SiteUsers;
import tableDao.BlogTagsDao;
import tableDao.BlogsDao;
import tableDao.SiteUsersDao;

/**
 * Servlet implementation class SingleSiteUser
 */
@WebServlet("/SingleSiteUser")
public class SingleSiteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleSiteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//get the parameter 'table' we set in our table edit forms so we are aware
				//of what table needs the edit
				String table = request.getParameter("table");
				
				String listReviewTuples = "/jspsLists/listReviews.jsp";

			     try 
			     { 
			    
				    		
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
					
						
				 	     	//Call checking() to find out if our interaction with the database
						    //occurred smoothly or an error occurred
				 	     	checking(request, response, check);
				 	     	
					        //At the end of this method call, find all current papers
				 	     	//and send that list to RequestDispatcher
					        request.setAttribute("BlogList", blogsdao.findall());
							request.getRequestDispatcher(listBlogTuples).forward(request, response);
		
			
				 }
			     catch (Exception e) 
			     {
			         System.out.println(e);
				 }
	}

}
