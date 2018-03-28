package projServlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileReader;

import com.opencsv.CSVReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class populateTables
 */
@WebServlet("/populateTables")
public class populateTables extends HttpServlet {

	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	private static final long serialVersionUID = 1L;

	public populateTables() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// String url = "jdbc:mysql://192.168.0.158:3306/sampledb"; //changed
		// local host to IP address listed in conf.d file
		String url = "jdbc:mysql://localhost/sampledb"; // i noticed that using
														// my actual ip address
														// instead of local did
														// not work on campus
														// wifi
		String user = "sampledb";
		String pass = "pass1234";
		// String fileName = "'write1Tuples.txt'";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection(url, user, pass);
			statement = connect.createStatement();

			// Drop database if it exists before creating it
			preparedStatement = connect
					.prepareStatement("DROP DATABASE IF EXISTS sampledb");
			preparedStatement.executeUpdate();

			// Create database
			preparedStatement = connect
					.prepareStatement("CREATE DATABASE IF NOT EXISTS sampledb");
			preparedStatement.executeUpdate();

			// use database just created;
			preparedStatement = connect.prepareStatement("USE sampledb");
			preparedStatement.executeUpdate();

			/*********************************************************/
			/******************* create tables ***********************/
			/*******************************************************/
			// create user
			preparedStatement = connect
					.prepareStatement(" CREATE TABLE IF NOT EXISTS site_user ( "
							+ "	user_id INTEGER  PRIMARY KEY AUTO_INCREMENT, "
							+ "    first_name VARCHAR(50) NOT NULL, "
							+ "last_name VARCHAR(50) NOT NULL, "
							+ "username VARCHAR(50)  NOT NULL,"
							+ "password VARCHAR(50)  NOT NULL,"
							+ "email VARCHAR(50) NOT NULL,"
							+ " UNIQUE (username), " + " UNIQUE(email) ) "

					);
			preparedStatement.executeUpdate();

			System.out.println("blog table");
			// create blog

			preparedStatement = connect
					.prepareStatement("CREATE TABLE IF NOT EXISTS blog ( "
							+ "blog_id INTEGER PRIMARY KEY AUTO_INCREMENT,"
							+ "blog_subject VARCHAR(50),"
							+ "description VARCHAR(100),"
							+ "writer VARCHAR(50) NOT NULL,"
							+ "date_written DATE, "
							+ "FOREIGN KEY(writer) REFERENCES site_user(username) ON UPDATE CASCADE ON DELETE CASCADE )");

			preparedStatement.executeUpdate();

			System.out.println("blog tag");
			// create blog_tag
			preparedStatement = connect
					.prepareStatement("CREATE TABLE IF NOT EXISTS blog_tag ( "
							+ "  blog_id INTEGER,"
							+ "	tag VARCHAR(50), "
							+ "	UNIQUE(blog_id, tag), "
							+ "FOREIGN KEY(blog_id) REFERENCES blog(blog_id) ON UPDATE CASCADE ON DELETE CASCADE"
							+ ")");
			preparedStatement.executeUpdate();

			System.out.println("write_blog");
			// create write_blog
			preparedStatement = connect
					.prepareStatement(" CREATE TABLE IF NOT EXISTS write_blog ("
							+ "date_written DATE,"
							+ " blog_id INTEGER,"
							+ "username VARCHAR(50),"
							+ "UNIQUE(blog_id, username), "
							+ "FOREIGN KEY(blog_id) REFERENCES blog(blog_id) ON UPDATE CASCADE ON DELETE CASCADE, "
							+ "FOREIGN KEY(username) REFERENCES site_user(username) ON UPDATE CASCADE ON DELETE CASCADE"
							+ ")");
			preparedStatement.executeUpdate();

			System.out.println("hobbies");

			// create hobbies
			preparedStatement = connect
					.prepareStatement(" CREATE TABLE IF NOT EXISTS hobbies ("
							+ " username VARCHAR(50) NOT NULL,"
							+ " email VARCHAR(50) NOT NULL,"
							+ " hobby ENUM ('hiking', 'swimming', 'calligraphy', "
							+ "'bowling', 'movie', 'cooking', 'dancing') NOT NULL,"
							+ " UNIQUE(username, email, hobby),"
							+ "FOREIGN KEY(email) REFERENCES site_user(email) ON UPDATE CASCADE ON DELETE CASCADE, "
							+ " FOREIGN KEY(username) REFERENCES site_user(username) ON UPDATE CASCADE ON DELETE CASCADE"
							+ ")");
			preparedStatement.executeUpdate();

			System.out.println("follow");
			// create follow
			preparedStatement = connect
					.prepareStatement(" CREATE TABLE IF NOT EXISTS follow ("
							+

							"follower VARCHAR(50),"
							+ "being_followed VARCHAR(50),"
							+ "UNIQUE(follower, being_followed),"
							+ "FOREIGN KEY(follower) REFERENCES site_user(username) ON UPDATE CASCADE ON DELETE CASCADE,"
							+ "FOREIGN KEY(being_followed) REFERENCES site_user(username) ON UPDATE CASCADE ON DELETE CASCADE"
							+ ")");
			preparedStatement.executeUpdate();

			// create comments
			preparedStatement = connect
					.prepareStatement(" CREATE TABLE IF NOT EXISTS comments ("
							+ "comment_id INTEGER PRIMARY KEY AUTO_INCREMENT, "
							+ "blog_id INTEGER NOT NULL, "
							+ "description VARCHAR(100), "
							+ "sentiment ENUM('positive', 'negative'), "
							+ "date_written DATE, "
							+ "commenter VARCHAR(50) NOT NULL, "
							+ "FOREIGN KEY(commenter) REFERENCES site_user(username) ON UPDATE CASCADE ON DELETE CASCADE,"
							+ " FOREIGN KEY(blog_id) REFERENCES blog(blog_id) ON UPDATE CASCADE ON DELETE CASCADE"
							+ ")");
			preparedStatement.executeUpdate();

			// **************************************
			// INSERTIONS
			// **************************************
			System.out.println("insert");

			  preparedStatement = connect
			          .prepareStatement( 
			        		  "INSERT IGNORE INTO site_user(first_name,last_name,username, password, email) " +
			        		  "VALUES " +
			        		  "('Mark', 'Twain', 'mtwain1', 'pass1234', 'mtwain@gmail.com'), " +
			        		  "('May', 'Cho', 'mcho12', 'pass1234', 'mcho@gmail.com')," +
			        		  "('Adrian', 'Martell', 'add34', 'pass1234', 'aMmrtell@gmail.com')," +
							  "('Sammie', 'Vasquez', 'sambam44', 'pass1234', 'svasquez@wsu.edu')," +
							  "('Maggie', 'Smith', 'msmith22', 'pass1234', 'msmith@yahoo.com'), " +
			        		  "('Karl', 'Marx', 'karlo33', 'pass1234', 'kmarx@gmail.com')," +
			        		  "('Annalise', 'Keating', 'annK40', 'pass1234', 'ak@middleton.edu')," +
							  "('Sam', 'Smith', 'sammys01', 'pass1234', 'ssmith@hotmail.com')," +
							  "('Kevin', 'McLean', 'kdot12', 'pass1234', 'kmclean@gmail.com')," +
							  "('Stephen', 'Randall', 'stv22','pass1234', 'srandall@gmail.com')," +
							  "('Kevin', 'Lannister', 'kevll46','pass1234', 'klannister@gmail.com')," +
							   "('Ahmed', 'Paulo', 'ahmedical1','pass1234', 'apaulo@hotmail.com')," +
			        		  "('Jessica', 'Fladger', 'flggirl22', 'pass1234', 'jfladger@yahoo.com')" 
			        		  );
		  preparedStatement.executeUpdate();	

			preparedStatement = connect
					.prepareStatement("INSERT IGNORE INTO blog(blog_subject, description, writer, date_written)"
							+ "VALUES"
							+ "('Movies', 'discussion of top films in 2000s','mcho12', '2017-01-31' ), "
							+ "('Computer Science', 'Discussing the role of neural networks in...','mcho12', '2018-01-30'),"
							+ "('Weather', 'Why the southern hemisphere will see', 'sambam44', '2017-04-03'),"
							+ "('Books', 'List of books that will teach you....', 'stv22', '2018-01-29'),"
							+ "('Food', 'Best places for Soups and Sandwiches in Detroit', 'stv22', '2018-01-29'), "
							+ "('News', 'top news headlights for the week', 'mtwain1', '2017-01-28'), "
							+ "('Movies', 'Why marvel runs the box office', 'mcho12', '2018-01-29'),"
							+ "('Weather', 'Winter storms have been picking up since...','mcho12', '2017-02-03'),"
							+ "('Philsophy', 'Karl Marx is infamous for his prediction that the ma...', 'annK40', '2017-04-03'),"
							+ "('Books', 'Books that need to be in your collection', 'msmith22', '2017-04-03'),"
							+ "('Food', 'Why milk prices have been increading since... ', 'add34', '2018-01-03')");

			preparedStatement.executeUpdate();

			preparedStatement = connect
					.prepareStatement("INSERT IGNORE INTO blog_tag(blog_id, tag)"
							+ "VALUES"
							+ "(1, 'Movies'), "
							+ "(2,'Computer Science'),"
							+ "(3, 'Weather'),"
							+ "(4, 'Books'),"
							+ "(4, 'History'),"
							+ "(5, 'Food'),"
							+ "(1, 'Flicks'), "
							+ "(2,'Learning'),"
							+ "(6, 'News'),"
							+ "(9, 'TopLists'),"
							+ "(8, 'TopLists'),"
							+ "(9, 'Books'),"
							+ "(7, 'Marvel'),"
							+ "(7, 'Comics'),"
							+ "(7, 'Movies'),"
							+ "(10, 'Food')");

			preparedStatement.executeUpdate();

/*			preparedStatement = connect
					.prepareStatement("INSERT IGNORE INTO write_blog(blog_id,username, date_written)"
							+ "VALUES"
							+ "(1, 'mcho12', '2017-01-31'), "
							+ "(2,'mcho12', '2017-02-03'),"
							+ "(3, 'sambam44', '2017-04-03'),"
							+ "(4, 'stv22', '2018-02-03'),"
							+ "(4, 'stv22', '2018-02-04'),"
							+ "(5, 'mtwain1', '2017-01-28'),"
							+ "(6, 'mcho12', '2017-01-31'), "
							+ "(7,'mcho12', '2017-02-03'),"
							+ "(8, 'msmith22', '2017-04-03'),"
							+ "(9, 'add34', '2018-02-03'),"
							+ "(10, 'add34', '2018-02-04'),"
							+ "(9, 'karlo33', '2017-01-28')");

			preparedStatement.executeUpdate();*/

			System.out.println("insert hobbies ");
			preparedStatement = connect
					.prepareStatement("INSERT IGNORE INTO hobbies(username, email, hobby)"
							+ "VALUES"
							+ "('mcho12','mcho@gmail.com', 'hiking'), "
							+ "('mcho12','mcho@gmail.com', 'swimming'),"
							+ "('sambam44','svasquez@gmail.com', 'cooking'),"
							+ "('stv22','stomp@gmail.com', 'hiking'),"
							+ "('stv22','stomp@gmail.com', 'calligraphy'),"
							+ "('mtwain1','mtwain@gmail.com','bowling'),"
							+ "('add34','add34@gmail.com', 'hiking'), "
							+ "('flggirl22','jflag@yahoo.com', 'swimming'),"
							+ "('flggirl22','jflag@yahoo.com', 'cooking'),"
							+ "('karlo33','kMarx@gmail.com', 'dancing'),"
							+ "('sammys01','ssmith@hotmail.com', 'calligraphy'),"
							+ "('kdot12','kMclean@gmail.com','bowling')");

			preparedStatement.executeUpdate();

			preparedStatement = connect
					.prepareStatement("INSERT IGNORE INTO follow(follower, being_followed) "
							+ "VALUES "
							+ "('mtwain1','mcho12'), "
							+ "('mcho12','mtwain1'),"
							+ "('add34', 'mtwain1'),"
							+ "('mtwain1', 'add34'),"
							+ "('sambam44','mtwain1'),"
							+ "('mtwain1','sambam44'),"
							+ "('stv22','mtwain1'),"
							+ "('flggirl22','mcho12'), "
							+ "('sammys01','mtwain1'),"
							+ "('mcho12', 'sammys01'),"
							+ "('add34', 'flggirl22'),"
							+ "('karlo33','kdot12'),"
							+ "('kdot12','kcarlo33')," + "('stv22','kdot12')");
			preparedStatement.executeUpdate();

			preparedStatement = connect
					.prepareStatement("INSERT IGNORE INTO comments( blog_id, description, sentiment, date_written, commenter) "
							+ "VALUES "
							+ "(1, 'this blog brings up great points','positive', '2018-03-11', 'mtwain1'), "
							+ "(1, 'though the first half makes sense im not sure... ','negative', '2018-03-12', 'add34'), "
							+ "(2, 'when you brough up the point about...','positive', '2017-03-11', 'mtwain1' ), "
							+ "(3, 'This is totally wrong','negative', '2017-03-11', 'mcho12'), "
							+ "(1, 'this reminds me of a post from jane carter about...','positive', '2018-03-13', 'kdot12'),"
							+ "(5, 'i love this blog','positive', '2018-03-11', 'mtwain1'), "
							+ "(6, 'i completely disagree','negative', '2018-03-11', 'mcho12'), "
							+ "(7, 'i didnt like this at first but you changed...','positive', '2018-03-11', 'flggirl22'), "
							+ "(10, 'I dont think the logic follows','negative', '2018-03-11', 'add34'), "
							+ "(9, 'wow, fantastic piece','positive', '2018-03-10', 'kdot12')");
			preparedStatement.executeUpdate();

			// After the database is populated, have request dispatcher object
			// open up /viewAll.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/viewAll.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close();
		}

	}

	private static void readCsv() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/sampledb"; // i noticed that using
														// my actual ip address
														// instead of local did
														// not work on campus
														// wifi
		String user = "sampledb";
		String pass = "pass1234";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connect = DriverManager.getConnection(url, user, pass);

		try (CSVReader reader = new CSVReader(new FileReader("upload.csv"), ',');
				Connection connection = connect;)
		// Connection connection = DBConnection.getConnection();)
		{
			String insertQuery = "Insert into txn_tbl (txn_id,txn_amount, card_number, terminal_id) values (null,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(insertQuery);
			String[] rowData = null;
			int i = 0;
			while ((rowData = reader.readNext()) != null) {
				for (String data : rowData) {
					pstmt.setString((i % 3) + 1, data);

					if (++i % 3 == 0)
						pstmt.addBatch();// add batch

					if (i % 30 == 0)// insert when the batch size is 10
						pstmt.executeBatch();
				}
			}
			System.out.println("Data Successfully Uploaded");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void readCsvUsingLoad() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/sampledb"; // i noticed that using
														// my actual ip address
														// instead of local did
														// not work on campus
														// wifi
		String user = "sampledb";
		String pass = "pass1234";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connect = DriverManager.getConnection(url, user, pass);

		try (Connection connection = connect;)
		// Connection connection = DBConnection.getConnection())
		{

			String loadQuery = "LOAD DATA LOCAL INFILE '"
					+ "C:\\Users\\Les\\input.csv"
					+ "' INTO TABLE site_user FIELDS TERMINATED BY '\t'"
					+ " LINES TERMINATED BY '\n' ";
			System.out.println(loadQuery);
			Statement stmt = connection.createStatement();
			stmt.execute(loadQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// close the resultSet
	private static void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
