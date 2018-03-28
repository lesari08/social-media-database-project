package table.domains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SiteUsers {
		
		private int userId;  
		private String firstName;
		private String lastName;
		private String userName;
		private String passWord;
		private String email;
		
		

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassWord() {
			return passWord;
		}

		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		
		
		@Override
		public String toString() {
			return "SiteUsers [userId=" + userId + ", firstName=" + firstName
					+ ", lastName=" + lastName + ", userName=" + userName
					+ ", passWord=" + passWord + ", email=" + email + "]";
		}
	

	
}
