package util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class StringUtil {
	//{a=1, b=2, c=3} 格式转换成map
	//Map<String, Integer>->食物名，数量
    public static Map<String, Integer> stringToMap(String str) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            Integer value = Integer.parseInt(string.split("=")[1]);
            map.put(key, value);
        }
        return map;
    }
//    
//    //产生订单Id
//    public static String generateId() {
//		return String.valueOf(new Date().getTime());
//	}
    
    //检测字符串是否为Float
    public static boolean isFloat(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }
}
