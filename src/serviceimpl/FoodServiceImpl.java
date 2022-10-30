package serviceimpl;

import java.util.List;

import bean.Food;
import service.FoodService;
import util.JdbcUtil;

public class FoodServiceImpl implements FoodService {

	@Override
	public List<Food> queryAllFood(String brand) {
		// TODO Auto-generated method stub
		String sql = "select * from food where brand=?";
		return JdbcUtil.queryFood(Food.class, sql, brand);
	}

	@Override
	public void update(Food food) {
		// TODO Auto-generated method stub
		String sql = "update food set price=? where name=? and brand=?";
		JdbcUtil.executeUpdate(sql, food.getPrice(),food.getName(),food.getBrand());
	}

	@Override
	public void delete(Food food) {
		// TODO Auto-generated method stub
		String sql = "delete from food where name=? and brand=?";
		JdbcUtil.executeUpdate(sql, food.getName(),food.getBrand());
	}

	@Override
	public boolean add(Food food) {
		// TODO Auto-generated method stub
		String sql = "insert into food values(null,?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, food.getName(),food.getType(),food.getBrand(),food.getPrice());
	}

	@Override
	public Food queryByNameAndBrand(String name, String brand) {
		// TODO Auto-generated method stub
		String sql = "select * from food where name=? and brand = ?";
		List<Food> foods = JdbcUtil.queryFood(Food.class, sql, name,brand);
		if(foods.size()>0) {
			return foods.get(0);
		}
		return null;
	}

}
