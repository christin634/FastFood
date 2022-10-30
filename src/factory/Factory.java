package factory;

import bean.Beverage;
import bean.Chicken;
import bean.Hamburg;
import bean.Snack;

public interface Factory {
	// 生产汉堡
	public Hamburg produceHamburg(String name);
	// 生产薯条
	public Snack produceSnack(String name);
	// 生产鸡类
	public Chicken produceChicken(String name);
	// 生产饮料
	public Beverage produceBeverage(String name);
}
