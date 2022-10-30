package builder;

import java.util.List;

import bean.Food;
import factory.*;

public abstract class MealBuilder {
	protected Factory factory;
	public abstract Meal buildeMeal(List<Food> foods);
}
