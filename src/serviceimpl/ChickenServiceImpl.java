package serviceimpl;

import bean.Chicken;
import service.ChickenService;
import util.JdbcUtil;

public class ChickenServiceImpl implements ChickenService{


	@Override
	public Chicken queryOneChicken(String brand, String name) {
		// TODO Auto-generated method stub
		String sql = "select * from food where brand=? and name=?";
		return JdbcUtil.queryFood(Chicken.class, sql, brand,name).get(0);
	}

}
