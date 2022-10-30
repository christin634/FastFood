package util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import bean.Food;

public class ConstUtil {

	public static final String receiptPath = "E:/Java/Idea/FastFood/src/receipt/";
	public static final String imgPath = "E:/Java/Idea/FastFood/src/view/img/";
	public static final String menberHintText = "输入11位手机号/8位会员卡号";
	public static final String dateHintText = "年-月-日";
	public static Vector<String> order_columnNames = new Vector<>();//clerk-order表头
	public static Vector<String> food_columnNames = new Vector<>();//food表头
	public static Vector<String> meal_order_columnNames = new Vector<>();//admin-order表头
	public static Vector<String> clerk_columnNames = new Vector<>();//clerk表头
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日期格式化
	public static Map<String, Integer> foodMapKFC = new HashMap<>();
	public static Map<String, Integer> foodMapWallace = new HashMap<>();
	
	static {
		order_columnNames.add("序号");
		order_columnNames.add("名称");
		order_columnNames.add("品牌");
		order_columnNames.add("数量");
		order_columnNames.add("单价");
		food_columnNames.add("序号");
		food_columnNames.add("名称");
		food_columnNames.add("单价");
		foodMapKFC = getNameToTypeMap("KFC");
		foodMapWallace = getNameToTypeMap("Wallace");
		meal_order_columnNames.add("订单编号");
		meal_order_columnNames.add("日期");
		meal_order_columnNames.add("内容");
		meal_order_columnNames.add("价格");
		clerk_columnNames.add("工号");
		clerk_columnNames.add("姓名");
		clerk_columnNames.add("性别");
		clerk_columnNames.add("年龄");
		clerk_columnNames.add("电话");
	}
	//食物->type映射
	public static Map<String, Integer> getNameToTypeMap(String brand) {
		Map<String, Integer> name_type = new HashMap<>();
		List<Food> foods = JdbcUtil.queryFood(Food.class, "select * from food where brand=?",brand);
		for (Food food : foods) {
			name_type.put(food.getName(), food.getType());
		}
		return name_type;
	}
}
