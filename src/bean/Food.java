package bean;

public class Food {
	private Long id;
	private String name;//食物名称
	private int type;//大类下的小类，如汉堡、小吃
	private String brand;//品牌
	private float price;//单价
	
	public Food() {}
	
	public Food(String name, int type, String brand, float price) {
		this.name = name;
		this.type = type;
		this.brand = brand;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
