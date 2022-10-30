package service;
import java.util.List;

import bean.Clerk;

public interface ClerkService {
	
	public List<Clerk> queryAll();
	public Clerk queryByNumber(String number);
	public boolean deleteByNumber(String number);
	public boolean add(Clerk clerk);
	public boolean update(Clerk clerk);
}
