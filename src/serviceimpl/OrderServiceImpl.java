package serviceimpl;

import java.util.List;

import bean.Order;
import service.OrderService;
import util.JdbcUtil;

public class OrderServiceImpl implements OrderService {

	@Override
	public boolean insert(Order order) {
		// TODO Auto-generated method stub
		String sql = "insert into meal_order(number,content,date,price) values(?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, order.getNumber(),order.getContent(),order.getDate(),order.getPrice());
	}

	@Override
	public List<Order> queryAll() {
		// TODO Auto-generated method stub
		String sql = "select * from meal_order";
		return JdbcUtil.query(Order.class,sql);
	}

	@Override
	public boolean deleteByNumber(String number) {
		// TODO Auto-generated method stub
		String sql = "delete from meal_order where number=?";
		return JdbcUtil.executeUpdate(sql, number);
	}
	
//	public static void main(String[] args) {
//		String sql = "select * from meal_order where date>?";
//		List<Order> order= JdbcUtil.query(Order.class,sql,"2022-10-14");
//		System.out.println(order.get(0));
//	}
}
