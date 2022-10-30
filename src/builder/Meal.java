package builder;

import java.util.ArrayList;
import java.util.List;
import bean.Food;

public class Meal {
	private float price;
	private List<Food> foods = new ArrayList<>();
	
	public void addFood(Food food) {
		foods.add(food);
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
}
