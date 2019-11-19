package dao.impl;

import dao.ManageDao;
import util.BaseDao;
import vo.AdmInfo;
import vo.ArrangedInfo;
import vo.CinemaInfo;

import java.util.List;


public class ManageDaoImpl extends BaseDao implements ManageDao {

	@Override
	public CinemaInfo login(String cinematel, String cinemapwd) throws Exception {
		String sql="SELECT * FROM CINEMAINFO WHERE CINEMATEL=? AND CINEMAPWD=?";
		return queryByOne(sql, CinemaInfo.class,cinematel,cinemapwd);
	}

	@Override
	public AdmInfo webLogin(String admtel, String admpwd) throws Exception {
		String sql="SELECT * FROM ADMINFO WHERE ADMTEL=? AND ADMPWD=?";
		return queryByOne(sql, AdmInfo.class,admtel,admpwd);
	}

	@Override
	public boolean reg(String cinemaname, String cinematel, String cinemapwd) throws Exception {
		String sql="INSERT INTO CINEMAINFO(CINEMANAME,CINEMATEL,CINEMAPWD) VALUES(?,?,?)";
		return update(sql,cinemaname,cinematel,cinemapwd);
	}

	@Override
	public boolean checkCinemaTel(String cinematel,int cinemaId) throws Exception {
		String sql="SELECT * FROM cinemainfo WHERE cinematel=? AND CINEMAID!=?";
		CinemaInfo cinema=queryByOne(sql, CinemaInfo.class,cinematel,cinemaId);
		if(cinema!=null){
			return true;
		}
		return false;
	}

	@Override
	public List<ArrangedInfo> findArrangeInfo(int cinemaid) throws Exception {
		String sql="SELECT A.*,M.MOVIENAME,M.TIMELIMIT,SUM(LENGTH(REPLACE(R.ROWSIT,'_',''))) SUMSIT,(SELECT COUNT(*) FROM SITINFO S WHERE S.STATE!=0 AND S.ARRANGEID=A.ARRANGEID GROUP BY A.ARRANGEID) AS SALESIT " +
				"FROM ROOMINFO R " +
				"LEFT JOIN ARRANGEDINFO A ON R.ROOMID = A.ROOMID " +
				"LEFT JOIN CINEMAINFO C ON A.CINEMAID = C.CINEMAID " +
				"LEFT JOIN MOVIEINFO M ON A.MOVIEID = M.MOVIEID WHERE A.CINEMAID=? GROUP BY A.ARRANGEID";
		return query(sql, ArrangedInfo.class,cinemaid);
	}

	@Override
	public boolean updateCinemaInfo(CinemaInfo cinemaInfo) throws Exception {
		String sql="UPDATE CINEMAINFO SET CINEMAADDR=? , CINEMAIMG=? , CINEMAINTRO=? , STATE=1 WHERE CINEMAID=?";
		return update(sql,cinemaInfo.getCinemaaddr(),cinemaInfo.getCinemaimg(),cinemaInfo.getCinemaintro(),cinemaInfo.getCinemaid());
	}

}
