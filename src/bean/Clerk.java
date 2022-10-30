package bean;

public class Clerk {

	private String number;
	private String name;
	private int age;
	private String gender;
	private String phonenumber;
	
	public Clerk() {
		// TODO Auto-generated constructor stub
	}

	public Clerk(String number, String name, int age, String gender, String phonenumber) {
		super();
		this.number = number;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phonenumber = phonenumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	
}
