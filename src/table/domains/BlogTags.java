package table.domains;

public class BlogTags {
	
	private int blogId;
	private String tag;
	
	
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	@Override
	public String toString() {
		return "BlogTags [blogId=" + blogId + ", tag=" + tag + "]";
	}
	
	
	

}
