package strategy;

public class MemberShip implements Strategy{
	private Long id;
	private String phone;
	private String cardnum;
	private float discount;
	
	public MemberShip() {
		// TODO Auto-generated constructor stub
	}

	public MemberShip(String phone, String cardnum, float discount) {
		super();
		this.phone = phone;
		this.cardnum = cardnum;
		this.discount = discount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	@Override
	public float discount(float price) {//打折后价格
		// TODO Auto-generated method stub
		return price*discount;
	}

}
