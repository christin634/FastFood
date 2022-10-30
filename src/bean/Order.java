package bean;

public class Order {
	private String number;
	private String content;
	private String date;
	private float price;
	public Order() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [number=" + number + ", content=" + content + ", date=" + date + ", price=" + price + "]";
	}
	public Order(String number, String content, String date, float price) {
		super();
		this.number = number;
		this.content = content;
		this.date = date;
		this.price = price;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}


}
