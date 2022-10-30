package serviceimpl;

import java.util.List;

import bean.Clerk;
import service.ClerkService;
import util.JdbcUtil;

public class ClerkServiceImpl implements ClerkService {

	@Override
	public List<Clerk> queryAll() {
		// TODO Auto-generated method stub
		String sql = "select * from clerk";
		return JdbcUtil.query(Clerk.class, sql);
	}

	@Override
	public boolean deleteByNumber(String number) {
		// TODO Auto-generated method stub
		String sql = "delete from clerk where number=?";
		return JdbcUtil.executeUpdate(sql,number);
	}

	@Override
	public boolean add(Clerk clerk) {
		// TODO Auto-generated method stub
		String sql = "insert into clerk values(?,?,?,?,?)";
		return JdbcUtil.executeUpdate(sql,clerk.getNumber(),clerk.getName(),clerk.getAge(),clerk.getGender(),clerk.getPhonenumber());
	}

	@Override
	public boolean update(Clerk clerk) {
		// TODO Auto-generated method stub
		String sql = "update clerk set name=?,age=?,gender=?,phonenumber=? where number=?";
		return JdbcUtil.executeUpdate(sql,clerk.getName(),clerk.getAge(),clerk.getGender(),clerk.getPhonenumber(),clerk.getNumber());
	}

	@Override
	public Clerk queryByNumber(String number) {
		// TODO Auto-generated method stub
		String sql = "select * from clerk where number=?";
		List<Clerk> clerks = JdbcUtil.query(Clerk.class, sql,number);
		if(clerks.size()>0) {
			return clerks.get(0);
		}
		return null;
	}

}
