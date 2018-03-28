package table.domains;

import java.sql.Date;

public class Comments {
	
	private int commentId;
	private int blogId;
	private String description;
	private String sentiment;
	private Date date_written;
	private String commenter;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	
	public Date getDate_written() {
		return date_written;
	}
	public void setDate_written(Date date_written) {
		this.date_written = date_written;
	}

	public String getCommenter() {
		return commenter;
	}
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", blogId=" + blogId
				+ ", description=" + description + ", sentiment=" + sentiment
				+ "]";
	}
}
