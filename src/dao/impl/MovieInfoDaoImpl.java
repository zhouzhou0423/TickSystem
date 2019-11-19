package dao.impl;

import dao.MovieInfoDao;
import util.BaseDao;
import vo.MovieInfo;
import vo.MovieTypeInfo;
import vo.UserLikeInfo;

import java.util.List;


public class MovieInfoDaoImpl extends BaseDao implements MovieInfoDao {
	
	@Override
	public List<MovieInfo> queryTitleMovie() throws Exception {
		//查询首页轮播图影片信息(按照票房排序)
		//String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.STATE!=-1 GROUP BY MOVIEID ORDER BY M.COUNTNUMBER DESC LIMIT 3";
        String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME,(SELECT GROUP_CONCAT(A.ACTORNAME)FROM ROLEINFO R,ACTORINFO A WHERE R.MOVIEID=M.MOVIEID AND R.ACTORID=A.ACTORID AND R.ISIMPORTANT=0 GROUP BY R.ISIMPORTANT) MOVIEDIRECTOR,(SELECT GROUP_CONCAT(A.ACTORNAME) FROM ROLEINFO R,ACTORINFO A WHERE R.MOVIEID=M.MOVIEID AND R.ACTORID=A.ACTORID AND R.ISIMPORTANT=1 GROUP BY R.ISIMPORTANT) MOVIEACTOR FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) GROUP BY MOVIEID ORDER BY M.COUNTNUMBER DESC LIMIT 7";
        return query(sql, MovieInfo.class);
	}

	@Override
	public List<MovieInfo> queryHotMovie() throws Exception {
		//查询首页正在热映影片(按照票房和评分排序)
		//String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.STATE=0  GROUP BY MOVIEID ORDER BY M.COUNTNUMBER DESC,M.GRADE DESC LIMIT 3";
        String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.RELEASETIME<= SYSDATE() GROUP BY MOVIEID ORDER BY M.COUNTNUMBER DESC,M.GRADE DESC LIMIT 8";
        return query(sql, MovieInfo.class);
	}

	@Override
	public List<MovieInfo> queryWantMovie() throws Exception {
		//查询首页即将上映影片(按照上映时间和想看人数排序)
		//String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.STATE=1  GROUP BY MOVIEID ORDER BY M.COUNTNUMBER DESC,M.GRADE DESC LIMIT 3";
        String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.RELEASETIME > SYSDATE() GROUP BY MOVIEID ORDER BY M.COUNTNUMBER DESC,M.GRADE DESC LIMIT 8";
        return query(sql, MovieInfo.class);
	}

	@Override
	public List<MovieInfo> queryTodayTop() throws Exception {
		//String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.STATE=0  GROUP BY MOVIEID ORDER BY M.COUNTNUMBER DESC,M.GRADE DESC LIMIT 10";
        String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.RELEASETIME<= SYSDATE() GROUP BY MOVIEID ORDER BY M.COUNTNUMBER DESC,M.GRADE DESC LIMIT 10";
        return query(sql, MovieInfo.class);
	}

	@Override
	public List<MovieInfo> queryTodayMost() throws Exception {
        //String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.STATE=1  GROUP BY MOVIEID ORDER BY M.COUNTNUMBER DESC,M.GRADE DESC LIMIT 10";
        String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.RELEASETIME > SYSDATE() GROUP BY MOVIEID ORDER BY M.COUNTNUMBER DESC,M.GRADE DESC LIMIT 10";
		return query(sql, MovieInfo.class);
	}

	@Override
	public List<MovieInfo> findMoviesByCinemaid(int cinemaid) throws Exception {
        //String sql="SELECT M.* ,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE CINEMAID=? AND STATE!=-1 AND FIND_IN_SET(T.TYPEID,M.TYPEID) GROUP BY M.MOVIEID ORDER BY M.RELEASETIME";
        //String sql="SELECT M.* ,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE CINEMAID=? AND FIND_IN_SET(T.TYPEID,M.TYPEID) GROUP BY M.MOVIEID ORDER BY M.RELEASETIME";
		//String sql="SELECT M.* ,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE M.MOVIEID IN(SELECT MOVIEID FROM CINEMAMOVIEINFO WHERE CINAMEID=?) AND FIND_IN_SET(T.TYPEID,M.TYPEID) GROUP BY M.MOVIEID ORDER BY M.RELEASETIME";
		String sql="SELECT M.* ,GROUP_CONCAT(T.TYPE) TYPENAME,C.CMSTATE CMSTATE FROM MOVIEINFO M LEFT JOIN MOVIETYPE T ON  FIND_IN_SET(T.TYPEID,M.TYPEID)" +
				" LEFT JOIN CINEMAMOVIEINFO C ON M.MOVIEID = C.MOVIEID" +
				" WHERE C.CINEMAID=? GROUP BY M.MOVIEID ORDER BY M.RELEASETIME;";
		return query(sql, MovieInfo.class,cinemaid);
	}

	@Override
	public List<MovieTypeInfo> findMovieType() throws Exception {
		String sql="SELECT * FROM MOVIETYPE";
		return query(sql,MovieTypeInfo.class);
	}

	@Override
	public List<MovieInfo> findAllMovies() throws Exception {
		String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) GROUP BY M.MOVIEID ORDER BY M.RELEASETIME";
		return query(sql,MovieInfo.class);
	}

	@Override
	public List<MovieInfo> findMoviesBytypeId(int typeId) throws Exception {
		String sql="SELECT * FROM MOVIEINFO WHERE FIND_IN_SET(?,TYPEID) ORDER BY RELEASETIME";
		return query(sql,MovieInfo.class,typeId);
	}

	@Override
	public boolean queryIsWantLook(int userId,int movieId) {
		String sql = "SELECT * FROM USERLIKEINFO WHERE USERID=? AND MOVIEID=?";
		UserLikeInfo userLikeInfo = queryByOne(sql,UserLikeInfo.class,userId,movieId);
		if(userLikeInfo!=null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<MovieInfo> findNoMovies(int cinemaId) throws Exception {
		String sql="SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.MOVIEID NOT IN (SELECT C.MOVIEID FROM CINEMAMOVIEINFO C WHERE CINEMAID=?) GROUP BY M.MOVIEID ORDER BY M.RELEASETIME";
		return query(sql,MovieInfo.class,cinemaId);
	}

	@Override
	public boolean updateWantSeeCount(int movieId,String op) throws Exception {
		String sql = "";
		if (op.equals("add")){
			sql="UPDATE MOVIEINFO SET WANTSEECOUNT=WANTSEECOUNT+1 WHERE MOVIEID=?";
		}else if (op.equals("delete")){
			sql="UPDATE MOVIEINFO SET WANTSEECOUNT=WANTSEECOUNT-1 WHERE MOVIEID=?";
		}
		return update(sql,movieId);
	}

	@Override
	public boolean updateCount(int movieId, String op,int num) throws Exception {
		String sql = "";
		if (op.equals("add")){
			sql="UPDATE MOVIEINFO SET COUNTNUMBER=COUNTNUMBER+? WHERE MOVIEID=?";
		}else if (op.equals("delete")){
			sql="UPDATE MOVIEINFO SET COUNTNUMBER=COUNTNUMBER-? WHERE MOVIEID=?";
		}
		return update(sql,movieId,num);
	}

}
