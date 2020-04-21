package model;

public class User {
	
	public User(String username, String password, String email, int age, String dob) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.dob = dob;
	}
	private int iD;
	private String username;
	private String password;
	private String email;
	private int age;
	private String dob;
	
	
	public User(int iD,String username, String password, String email, int age, String dob) {
		this.iD = iD;
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.dob = dob;
	}
	
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
