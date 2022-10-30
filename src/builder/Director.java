package builder;

import java.util.List;

import bean.Food;

public class Director {
	private MealBuilder builder;//聚合
	
	public Director(MealBuilder builder) {
		super();
		this.builder = builder;
	}
	public Meal construct(List<Food> foods) {
		return builder.buildeMeal(foods);
	}
	

}
