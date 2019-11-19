package util;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DB������
 *
 */
public class DButil {
	
	private static ComboPooledDataSource dataSource;
	//ʹ�þ�̬�� ��ʼ��dataSource
	static{
		dataSource = new ComboPooledDataSource();
	}
	
	/**
	 * ����˽��
	 */
	private DButil(){
		
	}
	
	/**
	 * ��ȡQueryRunner����
	 * @return
	 */
	public static QueryRunner getQueryRunner(){
		//����������ӳص���
		return new QueryRunner(dataSource);
	}
	
	/**
	 * ��ȡconn �����ӳ�
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
