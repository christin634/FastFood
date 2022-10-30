package service;

import bean.Beverage;

public interface BeverageService {
	public Beverage queryOneBeverage(String brand,String name);
//	public List<Beverage> queryBeverage(String brand);
}
