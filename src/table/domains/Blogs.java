package table.domains;

import java.sql.Date;

public class Blogs {
	
	private int blogId;
	private String blogSubject;
	private String description;
	private String writer;
	private Date date_written;
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogSubject() {
		return blogSubject;
	}
	public void setBlogSubject(String blogSubject) {
		this.blogSubject = blogSubject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDate_written() {
		return date_written;
	}
	public void setDate_written(Date date_written) {
		this.date_written = date_written;
	}
	@Override
	public String toString() {
		return "Blogs [blogId=" + blogId + ", blogSubject=" + blogSubject
				+ ", description=" + description + "]";
	}

	
}
