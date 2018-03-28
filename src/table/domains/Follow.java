package table.domains;

public class Follow {
	private String follower;
	private String being_followed;
	
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
	public String getBeing_followed() {
		return being_followed;
	}
	public void setBeing_followed(String being_followed) {
		this.being_followed = being_followed;
	}
	
	@Override
	public String toString() {
		return "Follow [follower=" + follower + ", being_followed="
				+ being_followed + "]";
	}
	
	

}
