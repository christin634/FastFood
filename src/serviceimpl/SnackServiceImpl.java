package serviceimpl;

import bean.Snack;
import service.SnackService;
import util.JdbcUtil;

public class SnackServiceImpl implements SnackService{

	@Override
	public Snack queryOneSnack(String brand, String name) {
		// TODO Auto-generated method stub
		String sql = "select * from food where brand=? and name=?";
		return JdbcUtil.queryFood(Snack.class, sql, brand,name).get(0);
	}

}
