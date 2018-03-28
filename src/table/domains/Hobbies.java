package table.domains;

public class Hobbies {

	private String userName;
	private String email;
	private String hobby;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	@Override
	public String toString() {
		return "Hobbies [userName=" + userName + ", email=" + email
				+ ", hobby=" + hobby + "]";
	}
	
	
}
