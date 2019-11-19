package util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * 封装 CURD操作 增删改查
 * 增删改  是一类    update
 * 查询      是一类    query
 * 都是使用QueryRunner
 * 调用存储过程。。。。。
 * @author xz
 *
 */
public class BaseDao {
	//使用Log 类  传入当前类的字节码信息
	private Logger log = Logger.getLogger(BaseDao.class);
	
	/**
	 * 统一的DML操作 增删改 
	 * @param sql   执行的sql语句
	 * @param params sql语句对应的参数
	 * @return       true/false
	 */
	protected boolean update(String sql, Object... params){
		log.info(sql+"=================="+params);
		boolean flag =false;
		//获取QueryRunner  带了连接池
		QueryRunner qr = DButil.getQueryRunner();
		try {
			int i = qr.update(sql, params);
			if(i>0){
				flag=true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());//记录日志  在控制台输出 并且记录到文件
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * service   获取connction  传递 到 dao中
	 * 事物操作在service 层   两条以上的或者多表的操作 DML 增删改 
	 *   EemService   
	 *     Conn conn=DButil.getConn(); dataSource
	 *     String sql1=update
	 *     String sql2=update 
	 *     try{
		 *     conn.setAutioCommit(false);
		 *     flag1 = dao.update(conn,sql1,params);
		 *     int i =1/0;
		 *     flag2 = dao.update(conn,sql2,params);
		 *     if(f1 && f2)  {conn.commit()}
	 *     }cathch(e){
	 *     	conn.rollBack();
	 *     }conn.setAutioCommit(true);
	 *     con.close();
	 *     
	 *  EmpDao   dao.update(conn,sql,params);
	 * 
	 * 统一的带事物操作的DML操作 增删改 
	 * @param sql   执行的sql语句
	 * @param params sql语句对应的参数
	 * @return       true/false
	 */
	protected boolean update(Connection conn, String sql, Object... params){
		log.info(sql+"=================="+params);
		boolean flag =false;
		//获取QueryRunner  带了连接池
		QueryRunner qr = new QueryRunner();
		try {
			int i = qr.update(conn,sql, params);
			if(i>0){
				flag=true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());//记录日志  在控制台输出 并且记录到文件
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 支持所有类的查询  
	 * @param sql   查询的语句
	 * @param cls   被查询的对象的类的class
	 * @param params 查询语句的参数
	 * @return		对用的类的list集合
	 */
	protected <T> List<T> query(String sql, Class<T> cls, Object...params){
		log.info(sql+"=================="+params);
		List<T> list = new ArrayList<T>();
		try {
			//获取QueryRunner  带了连接池
			QueryRunner qr = DButil.getQueryRunner();
			list = qr.query(sql, new BeanListHandler<T>(cls), params);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 支持所有对象的单个查询 
	 * @param sql   查询的sql语句
	 * @param cls   被查询的对象的class
	 * @param params sql语句的参数值
	 * @return       cls的对象
	 */
	protected <T> T queryByOne(String sql, Class<T> cls, Object...params){
		log.info(sql+"=================="+params);
		T bean = null;
		try {
			QueryRunner qr = DButil.getQueryRunner();
			bean = qr.query(sql, new BeanHandler<T>(cls), params);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 根据模糊查询所有的总数 
	 * @param sql
	 * @param params
	 * @return
	 */
	protected int getCount(String sql, Object... params){
		int max = 0;
		StringBuilder sb = new StringBuilder(" select count(*) from ( ");
		sb.append(sql);
		sb.append(" ) tab");
		sql = sb.toString();
		log.info(sql);
		try {
			QueryRunner qr = DButil.getQueryRunner();
			BigDecimal bd = qr.query(sql, new ScalarHandler<BigDecimal>(), params);
			max = bd.intValue();
//			long l =qr.query(sql, new ScalarHandler<Long>(), params);
//			max = (int) l;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}
	
	
	/**
	 * 支持所有的表的列查询最大值
	 * @param column    列明
	 * @param tableName 标名
	 * @param where     where条件  例如 where  deptno=10
	 * @param type      类型  1 是查询最大值        2  是获取总数
	 * @return			int 值
	 */
	protected int getMaxOrCount(String column, String tableName, String where, int type){
		int max = 0;
		StringBuilder sb = new StringBuilder("");
		if(type==1){//获取最大值
			sb.append(" select max( ");
		}else if(type==2){
			sb.append(" select count( ");
		}
		sb.append(column);
		sb.append(" ) from ");
		sb.append(tableName);
		if(!"".equals(where)&&null!=where){
			sb.append(where);
		}
		String sql = sb.toString();
		log.info(sql);
		QueryRunner qr = DButil.getQueryRunner();
		try {
			//返回值只支持BigDecimal 
			BigDecimal bd = qr.query(sql, new ScalarHandler<BigDecimal>());
			//把BigDecimal 转成int / long
			max = bd.intValue();
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return max;
	}
	
	/**
	 * 支持mysql所有的排序分页查询
	 * @param sql  查询sql语句 select * from emp where ??????
	 * @param cls  被查询的对象的class
	 * @param page 当前页码
	 * @param rows 每页的条数
	 * @param column 排序的字段
	 * @param type   排序的类型  desc  asc
	 * @param params sql的参数
	 * @return
	 */
	protected <T> List<T> queryByPage(String sql, Class<T> cls,
                                      int page, int rows, String column, String type, ArrayList<Object> list){
		// select * from dinnertable where tablename like '%学%'  limit 0 ,3
//		StringBuilder sb=new StringBuilder(sql);
//		sb.append(" limit ?,?");
		StringBuilder sb = new StringBuilder(" select * from ( select rownum rn ,t.* from (");
		sb.append( sql );
		//拼接排序
		if(!"".equals(column) && !"".equals(type)){
			sb.append(" order by ");
			sb.append(column+" "+type);
		}
		sb.append(" ) t ) p where p.rn between ? and ?");
		//自动计算起始条数 和结束的条数
		int minRow= (page-1)*rows+1;//起始条数    2 页 3条     4    (2-1)*3
		int maxRow=rows*page;//每页的条数
		//把参数加入到list
		list.add(minRow);
		list.add(maxRow);
		//执行 sql语句   带了N多的参数        数组 ==动态参数
		return query(sb.toString(), cls, list.toArray());
	}
}
