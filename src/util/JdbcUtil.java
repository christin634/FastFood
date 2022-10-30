package util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import bean.Food;
import strategy.MemberShip;


/**
 * 类说明：
 * 	用于加载驱动获取链接，然后返回结果集
 */

public class JdbcUtil {

    /**
     * 配置
     */
    private static Properties pro = new Properties();
    /** 驱动名称 */
    private static String DRIVER_NAME;
    /** 数据库链接地址 */
    private static String url;
    /** 用户名 */
    private static String userName;
    /** 密码 */
    private static String password ;

    //加载驱动
    static{
        InputStream is = null;
        try{
            is = JdbcUtil.class.getResourceAsStream("db.properties");
            //加载文件
            pro.load(is);
            url = pro.getProperty("url");
            DRIVER_NAME = pro.getProperty("driverClass");
            userName = pro.getProperty("username");
            password = pro.getProperty("password");
            Class.forName(DRIVER_NAME);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取链接
     * @return Connection
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    /** 增删改的通用方法
     * @paramString sql  要执行的sql
      * @paramObject[] obj    对象类型的数组  里面存放着 sql执行的占位符参数
      *               【name，age，id】
     *                【id】
      *               【name，age】
      *         Object... 可变参数
     * */
    public static boolean executeUpdate(String sql,Object... args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            for (int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            int i = ps.executeUpdate();

            if (i>0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            close(conn,ps,null);
        }
        return false;
    }

    /**
     * Food相关通用的查询操作
     *  返回的都是List集合
     * @return
     */
	public static <T> List<T> queryFood(Class<T> clazz,String sql, Object... args){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			// 获取结果集的元数据 :ResultSetMetaData
			ResultSetMetaData rsmd = rs.getMetaData();
			// 通过ResultSetMetaData获取结果集中的列数
			int columnCount = rsmd.getColumnCount();
			//创建集合对象
			ArrayList<T> list = new ArrayList<T>();
			while (rs.next()) {
				T t = clazz.newInstance();
				// 处理结果集一行数据中的每一个列:给t对象指定的属性赋值
				for (int i = 1; i <= columnCount; i++) {
					// 获取列值
					Object columValue = rs.getObject(i);
					// 获取每个列的列名
					String columnLabel = rsmd.getColumnLabel(i);
					// 给t对象指定的columnLabel属性，赋值为columValue：通过反射
					//由于不能直接获取其父类私有成员，故选择使用父类
					Field field = Food.class.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, columValue);

				}
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}	 
	
	/**
     * 通用的查询操作
     *  返回的都是List集合
     * @return
     */
	public static <T> List<T> query(Class<T> clazz,String sql, Object... args){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			// 获取结果集的元数据 :ResultSetMetaData
			ResultSetMetaData rsmd = rs.getMetaData();
			// 通过ResultSetMetaData获取结果集中的列数
			int columnCount = rsmd.getColumnCount();
			//创建集合对象
			ArrayList<T> list = new ArrayList<T>();
			while (rs.next()) {
				T t = clazz.newInstance();
				// 处理结果集一行数据中的每一个列:给t对象指定的属性赋值
				for (int i = 1; i <= columnCount; i++) {
					// 获取列值
					Object columValue = rs.getObject(i);
					// 获取每个列的列名
					String columnLabel = rsmd.getColumnLabel(i);
					// 给t对象指定的columnLabel属性，赋值为columValue：通过反射
					Field field=clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, columValue);
				}
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}	 
    /**
     * 关流的方法，接收任意多个任意类型的流对象
     * 如果关闭的流对象有关闭的先后顺序
     * 请将要先关闭的流对象放在前方
     *
     * 所有流对象的顶级父接口都是AutoCloseable
     * @param t    要关闭的流对象，可以是一个或多个（也可以是零个）
     *
     */
    @SafeVarargs
    private static <T>void close(T...t){
        //循环关流
        for(T tmp:t) {
            //关闭流对象
            if(tmp instanceof AutoCloseable) {
                try {
                    ((AutoCloseable)tmp).close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
