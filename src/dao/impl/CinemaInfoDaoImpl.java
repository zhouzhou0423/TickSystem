package dao.impl;

import dao.CinemaInfoDao;
import util.BaseDao;
import vo.CinemaInfo;

import java.util.List;


public class CinemaInfoDaoImpl extends BaseDao implements CinemaInfoDao {

	@Override
	public List<CinemaInfo> findAll(String movieId) throws Exception {
		//String sql="SELECT C.*,(SELECT MIN(A.MOVIEPRICE) FROM ARRANGEDINFO A WHERE A.CINEMAID=C.CINEMAID GROUP BY C.CINEMAID) LOWPRICE FROM CINEMAINFO C WHERE (FIND_IN_SET(?,C.MOVIEIDS) or ?='') ORDER BY CONVERT(C.CINEMANAME USING GBK)";
		String sql="SELECT C.*,(SELECT MIN(A.MOVIEPRICE) FROM ARRANGEDINFO A WHERE A.CINEMAID=C.CINEMAID GROUP BY C.CINEMAID) LOWPRICE FROM CINEMAINFO C WHERE (FIND_IN_SET(?,(SELECT GROUP_CONCAT(CM.MOVIEID) MOVIEIDS FROM CINEMAMOVIEINFO CM  WHERE C.CINEMAID=CM.CINEMAID)) OR ?='') ORDER BY CONVERT(C.CINEMANAME USING GBK)";
		return query(sql, CinemaInfo.class,movieId,movieId);
	}

	@Override
	public List<CinemaInfo> findByArea(String keyword,String movieId) throws Exception {
		String sql="SELECT C.*,(SELECT MIN(A.MOVIEPRICE) FROM ARRANGEDINFO A WHERE A.CINEMAID=C.CINEMAID GROUP BY C.CINEMAID) LOWPRICE FROM CINEMAINFO C WHERE(FIND_IN_SET(?,(SELECT GROUP_CONCAT(CM.MOVIEID) MOVIEIDS FROM CINEMAMOVIEINFO CM  WHERE C.CINEMAID=CM.CINEMAID)) or ?='') AND C.CINEMAADDR LIKE '%"+keyword+"%' ORDER BY CONVERT(C.CINEMANAME USING gbk)";
		return query(sql, CinemaInfo.class,movieId,movieId);
	}

	@Override
	public CinemaInfo findByCinemaID(int cinemaid) throws Exception {
		String sql="SELECT C.*,(SELECT MIN(A.MOVIEPRICE) FROM ARRANGEDINFO A WHERE A.CINEMAID=C.CINEMAID GROUP BY C.CINEMAID) LOWPRICE FROM CINEMAINFO C WHERE C.CINEMAID=?";
		return queryByOne(sql, CinemaInfo.class, cinemaid);
	}

	@Override
	public boolean editCinemaInfo(CinemaInfo cinemaInfo) throws Exception {
		String sql = "UPDATE CINEMAINFO SET CINEMANAME=?,CINEMATEL=?,CINEMAADDR=?,CINEMAINTRO=?,CINEMAIMG=?,CINEMAPWD=? WHERE CINEMAID=?";
		return update(sql,cinemaInfo.getCinemaname(),cinemaInfo.getCinematel(),cinemaInfo.getCinemaaddr(),cinemaInfo.getCinemaintro(),cinemaInfo.getCinemaimg(),cinemaInfo.getCinemapwd(),cinemaInfo.getCinemaid());
	}

	@Override
	public boolean deleteCinema(int cinemaId) throws Exception {
		String sql = "DELETE FROM CINEMAINFO WHERE CINEMAID=?";
		return update(sql,cinemaId);
	}

	@Override
	public boolean addMovie(int cinemaId, int movieId) throws Exception {
		String sql = "INSERT INTO CINEMAMOVIEINFO (CINEMAID,MOVIEID) VALUES (?,?)";
		return update(sql,cinemaId,movieId);
	}

	@Override
	public boolean deleteMovie(int cinemaId, int movieId) throws Exception {
		String sql = "DELETE FROM CINEMAMOVIEINFO WHERE CINEMAID=? AND MOVIEID=?";
		return update(sql,cinemaId,movieId);
	}

}
