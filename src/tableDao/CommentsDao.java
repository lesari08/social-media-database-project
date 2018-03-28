package tableDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import table.domains.*;


public class CommentsDao {
	
		
		static String url = "jdbc:mysql://localhost/sampledb"; //changed local host to IP address listed in conf.d file
		static String user = "sampledb";
		static String pass = "pass1234";
		static String tablename = "comments";
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
		public int add(SiteUsers user) throws ClassNotFoundException, InstantiationException, IllegalAccessException 
		{
		    int success;
		    String errorMessage;
		    String emailCheck;
			
			try 
			{
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection connect = DriverManager
				         .getConnection(url, user, pass);
				
				String sql = "INSERT INTO" + " " + tablename + " " + "VALUES(?,?,?)";
				PreparedStatement preparestatement = connect.prepareStatement(sql);
				
				/*check if the email is a reasonable length, originally when this stipulation
				was not included, empty strings were accepted into the table, bypassing the NOT NULL 
				constraint that was put on email. Makes sense in hindsight since technically 
				an empty string =/= null */
				emailCheck =  user.getEmail();
				if(emailCheck.length() < 6)
				{
				   return -20;
				}
			    preparestatement.setString(1, user.getEmail());
				
			    preparestatement.setString(2,user.getName());
			    preparestatement.setString(3,user.getAffiliation());
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
					Comments comment = new Comments();
					comment.setCommentId(resultSet.getInt("comment_id"));
					comment.setBlogId(resultSet.getInt("blog_id"));
					comment.setDescription(resultSet.getString("description"));
					comment.setSentiment(resultSet.getString("sentiment"));
					comment.setDate_written(resultSet.getDate("date_written"));
					comment.setCommenter(resultSet.getString("commenter"));
					
		    		list.add(comment);
				 }
				
				 
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
			return list;
			
		}
			
	

}
