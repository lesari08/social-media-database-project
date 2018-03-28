package tableDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import table.domains.*;


public class BlogTagsDao {
	
		
		static String url = "jdbc:mysql://localhost/sampledb"; //changed local host to IP address listed in conf.d file
		static String user = "sampledb";
		static String pass = "pass1234";
		static String tablename = "blog_tag";
		/**
		 * 
		 * @param email
		 * @return
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		public SiteUsers findByEmail(String email) throws ClassNotFoundException, InstantiationException, IllegalAccessException 
		{
			SiteUsers siteuser = new SiteUsers();
		  try 
		  {
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connect = DriverManager
				          .getConnection(url,user, pass);
			    String sql = "SELECT * FROM" + " " + tablename + " " + "WHERE email=?";
			    
			    PreparedStatement preparestatement = connect.prepareStatement(sql); 
			    preparestatement.setString(1,email);
			    ResultSet resultSet = preparestatement.executeQuery();
			    //ResultSet resultSet  = preparestatement.executeUpdate();
			    while(resultSet.next())
			    {
			    	String em = resultSet.getString("email");
			    	if(em.equals(email))
			    	{
			    		siteuser.setEmail(resultSet.getString("email"));	
			    		siteuser.setUserName(resultSet.getString("username"));
			    		siteuser.setFirstName(resultSet.getString("firstname"));
			    		siteuser.setLastName(resultSet.getString("lastname"));
			    		siteuser.setUserId(resultSet.getInt("userid"));	
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
		public int add(BlogTags blogtag) throws ClassNotFoundException, InstantiationException, IllegalAccessException 
		{
		    int success;
		    String errorMessage;

			try 
			{
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connect = DriverManager
				         .getConnection(url, user, pass);
				
				String sql = "INSERT INTO" + " " + tablename + " " + "VALUES(?,?)";
				PreparedStatement preparestatement = connect.prepareStatement(sql);

			    preparestatement.setInt(1, blogtag.getBlogId());
			    preparestatement.setString(2,blogtag.getTag());
			    success = preparestatement.executeUpdate();
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
		
		public int delete(String emailToDelete) throws ClassNotFoundException, InstantiationException, IllegalAccessException 
		{
		    int resultOfUpdate;
		    String errorMessage;
			
			try 
			{	
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connect = DriverManager
				         .getConnection(url, user, pass);
				
				String sql = "DELETE FROM" + " " + tablename + " " + "WHERE email=?";
				PreparedStatement preparestatement = connect.prepareStatement(sql);
				preparestatement.setString(1, emailToDelete);
				
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
					BlogTags blogtag = new BlogTags();
					blogtag.setBlogId(resultSet.getInt("blog_id"));
					blogtag.setTag(resultSet.getString("tag"));
					
		    		list.add(blogtag);
				 }
				
				 
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
			return list;
			
		}
			
	

}
