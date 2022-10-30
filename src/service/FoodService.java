package service;

import java.util.List;

import bean.Food;

public interface FoodService {

	public List<Food> queryAllFood(String brand);
	public Food queryByNameAndBrand(String name,String brand);
	
	public void update(Food food);
	public void delete(Food food);
	
	public boolean add(Food food);
}
