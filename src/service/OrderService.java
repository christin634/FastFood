package service;

import java.util.List;

import bean.Order;

public interface OrderService {
	
	public boolean insert(Order order);
	public List<Order> queryAll();
	public boolean deleteByNumber(String number);
}
