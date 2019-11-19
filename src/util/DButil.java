package util;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DB工具类
 *
 */
public class DButil {
	
	private static ComboPooledDataSource dataSource;
	//使用静态块 初始化dataSource
	static{
		dataSource = new ComboPooledDataSource();
	}
	
	/**
	 * 构造私有
	 */
	private DButil(){
		
	}
	
	/**
	 * 获取QueryRunner对象
	 * @return
	 */
	public static QueryRunner getQueryRunner(){
		//必须给定连接池的类
		return new QueryRunner(dataSource);
	}
	
	/**
	 * 获取conn 在连接池
	 * @return
	 */
	public static Connection geteConn(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
