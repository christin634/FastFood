package serviceimpl;

import bean.Hamburg;
import service.HamburgService;
import util.JdbcUtil;

public class HamburgServiceImpl implements HamburgService{

	@Override
	public Hamburg queryOneHamburg(String brand, String name) {
		// TODO Auto-generated method stub
		String sql = "select * from food where brand=? and name=?";
		return JdbcUtil.queryFood(Hamburg.class, sql, brand,name).get(0);
	}

}
