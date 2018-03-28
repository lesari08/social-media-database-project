package tableDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import table.domains.*;

public class BlogsDao {
	
		
		static String url = "jdbc:mysql://localhost/sampledb"; //changed local host to IP address listed in conf.d file
		static String user = "sampledb";
		static String pass = "pass1234";
		static String tablename = "blog";
		/**
		 * 
		 * @param email
		 * @return
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		public SiteUsers findByUsername(String username) throws ClassNotFoundException, InstantiationException, IllegalAccessException 
		{
			SiteUsers siteuser = new SiteUsers();
		  try 
		  {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connect = DriverManager
				          .getConnection(url,user, pass);
			    String sql = "SELECT * FROM" + " " + tablename + " " + "WHERE username=?";
			    
			    PreparedStatement preparestatement = connect.prepareStatement(sql); 
			    preparestatement.setString(1,username);
			    ResultSet resultSet = preparestatement.executeQuery();
			    //ResultSet resultSet  = preparestatement.executeUpdate();
			    while(resultSet.next())
			    {
			    	String retrievedUsername = resultSet.getString("username");
			    	if(retrievedUsername.equals(username))
			    	{
			    		siteuser.setUserId(resultSet.getInt("userid"));	
			    		siteuser.setFirstName(resultSet.getString("firstname"));
			    		siteuser.setLastName(resultSet.getString("lastname"));
			    		siteuser.setUserName(resultSet.getString("username"));
			    		siteuser.setPassWord(resultSet.getString("password"));	
			    		siteuser.setEmail(resultSet.getString("email"));	
			    	
			    	}
			    }
		  } 
	     catch(SQLException e) 
	     {
			throw new RuntimeException(e);
	     }
	   
			return siteuser;
		}
		
		
		
		
		/**
		 * insert siteuser
		 * @param user
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		public int add(Blogs blog, String tag) throws ClassNotFoundException, InstantiationException, IllegalAccessException 
		{
		    int success;
		    String errorMessage;
		    String emailCheck;
			
			try 
			{
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connect = DriverManager
				         .getConnection(url, user, pass);
				
				String sql = "INSERT INTO" + " " + tablename + "(blog_subject, description)" + " " + "VALUES(?, ?)";
				PreparedStatement preparestatement = connect.prepareStatement(sql);
				
			
			    preparestatement.setString(1, blog.getBlogSubject());
			    preparestatement.setString(2, blog.getDescription());
			    success = preparestatement.executeUpdate();
			    
			    
			    sql = "SELECT MAX(blog_id) FROM blog " ;
				preparestatement = connect.prepareStatement(sql); 
				ResultSet resultSet = preparestatement.executeQuery();
				
				BlogTags blogtag = new BlogTags();
				BlogTagsDao blogtagsdao = new BlogTagsDao();
				while(resultSet.next()){
					
					blogtag.setBlogId(resultSet.getInt("MAX(blog_id)"));
					blogtag.setTag(tag);
					
					
					//blog.setBlogSubject(resultSet.getString("blog_subject"));
					//blog.setDescription(resultSet.getString("description"));
					
		    		blogtagsdao.add(blogtag);
				 }
			    
			    } 
	      catch(SQLException e) 
		  {
				System.out.println(e);
				errorMessage = e.getMessage();
				if(errorMessage.contains("Duplicate"))
				{
					//-10 acts as a signal for attempts to add duplicates
					return -10;	
				}

				//using -2 as a signal that an sql exception occurred to keep the program running
				return -2;
				//throw new RuntimeException(e);	

		  }
			System.out.println("add() end: " + success);
			return success;
		}
		
		//Function used to update an existing tuple 
		public int update(Blogs blog) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
			
			int check = 0;
			try 
			{
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connect = DriverManager
				         .getConnection(url, user, pass);
				
				String sql = "UPDATE" + " " + tablename + " " +
						"SET blog_subject= ?, description = ? WHERE blog_id = ? ";
				PreparedStatement preparestatement = connect.prepareStatement(sql); 
			 
			    preparestatement.setString(1,blog.getBlogSubject());
			    preparestatement.setString(2, blog.getDescription());
			    preparestatement.setInt(3, blog.getBlogId());
			    check = preparestatement.executeUpdate();
			    
			    
			} 
			catch(SQLException e) 
			{
				System.out.println(e);
				
				check = -2;
				return check;
			}
			
			return check;
		}
		public int delete(int blogToDelete) throws ClassNotFoundException, InstantiationException, IllegalAccessException 
		{
		    int resultOfUpdate;
		    String errorMessage;
			
			try 
			{	
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connect = DriverManager
				         .getConnection(url, user, pass);
				
				String sql = "DELETE FROM" + " " + tablename + " " + "WHERE blog_id=?";
				PreparedStatement preparestatement = connect.prepareStatement(sql);
				preparestatement.setInt(1, blogToDelete);
				
				resultOfUpdate = preparestatement.executeUpdate();  
			} 
	       catch(SQLException e) 
		   {
		 		System.out.println(e);
				errorMessage = e.getMessage();
				
				//returning a negative value to signal an SQL exception occurred 
				return -5;
		   }
			
		   System.out.println("delete(): " + resultOfUpdate);
		   return resultOfUpdate;
		}
		
		
		public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException
		{
			System.out.println("find all ()");
			//create a new arraylist to hold our tuple objects
			List<Object> list = new ArrayList<>();
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connect = DriverManager
				          .getConnection(url, user, pass);
				
				
				String sql = "SELECT * FROM" + " " + tablename;
				PreparedStatement preparestatement = connect.prepareStatement(sql); 
				ResultSet resultSet = preparestatement.executeQuery();
				
				while(resultSet.next()){
					Blogs blog = new Blogs();
					blog.setBlogId(resultSet.getInt("blog_id"));
					blog.setBlogSubject(resultSet.getString("blog_subject"));
					blog.setDescription(resultSet.getString("description"));
					blog.setWriter(resultSet.getString("writer"));
					blog.setDate_written(resultSet.getDate("date_written"));
		    		list.add(blog);
				 }
				
				 
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
			return list;
			
		}
			
	

}
