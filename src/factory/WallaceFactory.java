package factory;

import bean.Beverage;
import bean.Chicken;
import bean.Hamburg;
import bean.Snack;
import service.BeverageService;
import service.ChickenService;
import service.HamburgService;
import service.SnackService;
import serviceimpl.BeverageServiceImpl;
import serviceimpl.ChickenServiceImpl;
import serviceimpl.HamburgServiceImpl;
import serviceimpl.SnackServiceImpl;

public class WallaceFactory implements Factory {
	HamburgService hamburgService = new HamburgServiceImpl();
	SnackService snackService = new SnackServiceImpl();
	ChickenService chickenService=new ChickenServiceImpl();
	BeverageService beverageService = new BeverageServiceImpl();

	@Override
	public Hamburg produceHamburg(String name) {
		// TODO Auto-generated method stub
		return hamburgService.queryOneHamburg("Wallace", name);
	}

	@Override
	public Snack produceSnack(String name) {
		// TODO Auto-generated method stub
		return snackService.queryOneSnack("Wallace", name);
	}

	@Override
	public Chicken produceChicken(String name) {
		// TODO Auto-generated method stub
		return chickenService.queryOneChicken("Wallace", name);
	}

	@Override
	public Beverage produceBeverage(String name) {
		// TODO Auto-generated method stub
		return beverageService.queryOneBeverage("Wallace", name);
	}

}
