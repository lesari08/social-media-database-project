package table.domains;

import java.sql.Date;

public class WriteBlog {
	private Date dateWritten;
	private int blogId;
	private  String userName;
	
	public Date getDateWritten() {
		return dateWritten;
	}
	public void setDateWritten(Date dateWritten) {
		this.dateWritten = dateWritten;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "WriteBlog [dateWritten=" + dateWritten + ", blogId=" + blogId
				+ ", userName=" + userName + "]";
	}
	
	
	

}
