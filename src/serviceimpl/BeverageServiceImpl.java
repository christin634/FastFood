package serviceimpl;

import bean.Beverage;
import service.BeverageService;
import util.JdbcUtil;

public class BeverageServiceImpl implements BeverageService{

	@Override
	public Beverage queryOneBeverage(String brand, String name) {
		// TODO Auto-generated method stub
		String sql = "select * from food where brand=? and name=?";
		return JdbcUtil.queryFood(Beverage.class, sql, brand,name).get(0);
	}

}
