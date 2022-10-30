package builder;

import java.util.List;
import java.util.Map;

import bean.Beverage;
import bean.Chicken;
import bean.Food;
import bean.Hamburg;
import bean.Snack;
import factory.WallaceFactory;
import util.ConstUtil;

public class WallaceMealBuilder extends MealBuilder{

	public WallaceMealBuilder() {
		// TODO Auto-generated constructor stub
		this.factory = new WallaceFactory();
	}
	
	//传来的
	@Override
	public Meal buildeMeal(List<Food> foods) {
		// TODO Auto-generated method stub
		Meal meal = new Meal();
		float price = 0f;
		Map<String, Integer> name_type = ConstUtil.foodMapWallace;
		for (Food food : foods) {
			String foodnameString = food.getName();
		    switch (name_type.get(food.getName())) {
				case 1:
					Hamburg hamburg = factory.produceHamburg(foodnameString);
					meal.addFood(hamburg);
					price+=hamburg.getPrice();
					break;
				case 2:
					Chicken chicken = factory.produceChicken(foodnameString);
						meal.addFood(chicken);
						price+=chicken.getPrice();
					break;
				case 3:
					Snack snack = factory.produceSnack(foodnameString);
						meal.addFood(snack);
						price+=snack.getPrice();
					break;
				case 4:
					Beverage beverage = factory.produceBeverage(foodnameString);
						meal.addFood(beverage);
						price+=beverage.getPrice();
					break;
				default:
					break;
			}
		}
		meal.setPrice(price);
		return meal;
	}

}
