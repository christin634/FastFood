package service;
import bean.Chicken;

public interface ChickenService {
	public Chicken queryOneChicken(String brand,String name);
//	public List<Chicken> queryAllChicken(String brand);
}
