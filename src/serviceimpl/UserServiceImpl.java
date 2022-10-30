package serviceimpl;

import java.util.List;

import bean.User;
import service.UserService;
import util.JdbcUtil;

public class UserServiceImpl implements UserService {

	@Override
	public User queryUser(String username, String password, int type) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username=? and password=? and type=?";
		List<User> users = JdbcUtil.query(User.class,sql, username,password,type);
		if(users.size()>0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public boolean deleteByNumber(String number) {
		// TODO Auto-generated method stub
		String sql="delete from user where number=?";
		return JdbcUtil.executeUpdate(sql, number);
	}

	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub
		String sql="insert into user values(?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, user.getNumber(),user.getUsername(),user.getPassword(),user.getType());
	}

	@Override
	public boolean update(String number,String password) {
		// TODO Auto-generated method stub
		String sql="update user set password=? where number=?";
		return JdbcUtil.executeUpdate(sql, password,number);
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		String sql="update user set username=? , password=? where number=?";
		return JdbcUtil.executeUpdate(sql, user.getUsername(),user.getPassword(),user.getNumber());
	}

	@Override
	public User queryByNumber(String number) {
		// TODO Auto-generated method stub
		String sql = "select * from user where number=?";
		List<User> users = JdbcUtil.query(User.class,sql, number);
		if(users.size()>0) {
			return users.get(0);
		}
		return null;
	}

}
