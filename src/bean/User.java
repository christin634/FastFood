package bean;

public class User {

	private String number;//编号--4位
	private String username;
	private String password;
	private int type;//用户类型 
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String number, String username, String password, int type) {
		super();
		this.number = number;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	

}
