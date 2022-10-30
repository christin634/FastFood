package service;

import bean.User;

public interface UserService {
	public User queryUser(String username,String password,int type);
	public User queryByNumber(String number);
	public boolean deleteByNumber(String number);
	public boolean add(User user);
	public boolean update(String number,String password);
	public boolean update(User user);
}
