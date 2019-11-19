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
 * ��װ CURD���� ��ɾ�Ĳ�
 * ��ɾ��  ��һ��    update
 * ��ѯ      ��һ��    query
 * ����ʹ��QueryRunner
 * ���ô洢���̡���������
 * @author xz
 *
 */
public class BaseDao {
	//ʹ��Log ��  ���뵱ǰ����ֽ�����Ϣ
	private Logger log = Logger.getLogger(BaseDao.class);
	
	/**
	 * ͳһ��DML���� ��ɾ�� 
	 * @param sql   ִ�е�sql���
	 * @param params sql����Ӧ�Ĳ���
	 * @return       true/false
	 */
	protected boolean update(String sql, Object... params){
		log.info(sql+"=================="+params);
		boolean flag =false;
		//��ȡQueryRunner  �������ӳ�
		QueryRunner qr = DButil.getQueryRunner();
		try {
			int i = qr.update(sql, params);
			if(i>0){
				flag=true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());//��¼��־  �ڿ���̨��� ���Ҽ�¼���ļ�
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * service   ��ȡconnction  ���� �� dao��
	 * ���������service ��   �������ϵĻ��߶��Ĳ��� DML ��ɾ�� 
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
	 * ͳһ�Ĵ����������DML���� ��ɾ�� 
	 * @param sql   ִ�е�sql���
	 * @param params sql����Ӧ�Ĳ���
	 * @return       true/false
	 */
	protected boolean update(Connection conn, String sql, Object... params){
		log.info(sql+"=================="+params);
		boolean flag =false;
		//��ȡQueryRunner  �������ӳ�
		QueryRunner qr = new QueryRunner();
		try {
			int i = qr.update(conn,sql, params);
			if(i>0){
				flag=true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());//��¼��־  �ڿ���̨��� ���Ҽ�¼���ļ�
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * ֧��������Ĳ�ѯ  
	 * @param sql   ��ѯ�����
	 * @param cls   ����ѯ�Ķ�������class
	 * @param params ��ѯ���Ĳ���
	 * @return		���õ����list����
	 */
	protected <T> List<T> query(String sql, Class<T> cls, Object...params){
		log.info(sql+"=================="+params);
		List<T> list = new ArrayList<T>();
		try {
			//��ȡQueryRunner  �������ӳ�
			QueryRunner qr = DButil.getQueryRunner();
			list = qr.query(sql, new BeanListHandler<T>(cls), params);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ֧�����ж���ĵ�����ѯ 
	 * @param sql   ��ѯ��sql���
	 * @param cls   ����ѯ�Ķ����class
	 * @param params sql���Ĳ���ֵ
	 * @return       cls�Ķ���
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
	 * ����ģ����ѯ���е����� 
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
	 * ֧�����еı���в�ѯ���ֵ
	 * @param column    ����
	 * @param tableName ����
	 * @param where     where����  ���� where  deptno=10
	 * @param type      ����  1 �ǲ�ѯ���ֵ        2  �ǻ�ȡ����
	 * @return			int ֵ
	 */
	protected int getMaxOrCount(String column, String tableName, String where, int type){
		int max = 0;
		StringBuilder sb = new StringBuilder("");
		if(type==1){//��ȡ���ֵ
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
			//����ֵֻ֧��BigDecimal 
			BigDecimal bd = qr.query(sql, new ScalarHandler<BigDecimal>());
			//��BigDecimal ת��int / long
			max = bd.intValue();
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return max;
	}
	
	/**
	 * ֧��mysql���е������ҳ��ѯ
	 * @param sql  ��ѯsql��� select * from emp where ??????
	 * @param cls  ����ѯ�Ķ����class
	 * @param page ��ǰҳ��
	 * @param rows ÿҳ������
	 * @param column ������ֶ�
	 * @param type   ���������  desc  asc
	 * @param params sql�Ĳ���
	 * @return
	 */
	protected <T> List<T> queryByPage(String sql, Class<T> cls,
                                      int page, int rows, String column, String type, ArrayList<Object> list){
		// select * from dinnertable where tablename like '%ѧ%'  limit 0 ,3
//		StringBuilder sb=new StringBuilder(sql);
//		sb.append(" limit ?,?");
		StringBuilder sb = new StringBuilder(" select * from ( select rownum rn ,t.* from (");
		sb.append( sql );
		//ƴ������
		if(!"".equals(column) && !"".equals(type)){
			sb.append(" order by ");
			sb.append(column+" "+type);
		}
		sb.append(" ) t ) p where p.rn between ? and ?");
		//�Զ�������ʼ���� �ͽ���������
		int minRow= (page-1)*rows+1;//��ʼ����    2 ҳ 3��     4    (2-1)*3
		int maxRow=rows*page;//ÿҳ������
		//�Ѳ������뵽list
		list.add(minRow);
		list.add(maxRow);
		//ִ�� sql���   ����N��Ĳ���        ���� ==��̬����
		return query(sb.toString(), cls, list.toArray());
	}
}
