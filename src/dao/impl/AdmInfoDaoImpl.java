package dao.impl;

import dao.AdmInfoDao;
import util.BaseDao;
import vo.AdmInfo;


public class AdmInfoDaoImpl extends BaseDao implements AdmInfoDao {

	@Override
	public AdmInfo login(String admtel, String admpwd) throws Exception {
		String sql="SELECT * FROM ADMINFO WHERE ADMTEL=? AND ADMPWD=?";
		return queryByOne(sql, AdmInfo.class,admtel,admpwd);
	}

	@Override
	public boolean reg(String admname, String admpwd, String admtel)
			throws Exception {
		String sql="INSERT INTO ADMINFO(ADMNAME,ADMPWD,ADMTEL) VALUES(?,?,?)";
		return update(sql, admname,admpwd,admtel);
	}

	@Override
	public boolean checkAdmTel(String admtel) throws Exception {
		String sql="SELECT * FROM ADMINFO WHERE ADMTEL=?";
		AdmInfo adm=queryByOne(sql, AdmInfo.class,admtel);
		if(adm!=null){
			return true;
		}
		return false;
	}
	
}
