package dao.impl;

import dao.MovieManageDao;
import util.BaseDao;
import vo.MovieInfo;
import vo.MovieTypeInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/216:38
 */
public class MovieManageDaoImpl extends BaseDao implements MovieManageDao {
    @Override
    public boolean changeState(int movieid, int cmstate,int cinemaid) throws Exception {
        String sql="UPDATE CINEMAMOVIEINFO SET CMSTATE=? WHERE MOVIEID=? AND CINEMAID=?";
        return update(sql,cmstate,movieid,cinemaid);
    }
    @Override
    public List<MovieTypeInfo> queryMovieType() throws Exception {
        String sql="SELECT * FROM MOVIETYPE";
        return query(sql,MovieTypeInfo.class);
    }

    @Override
    public boolean webAddMovie(String movieName,int timeLimit,String movieIntro,String releaseTime,String typeId,String movieImg,String nation) throws Exception {
        String sql = "INSERT INTO MOVIEINFO (MOVIENAME,TIMELIMIT,MOVIEINTRO,RELEASETIME,TYPEID,MOVIEIMG,NATION) VALUES (?,?,?,?,?,?,?)";
        return update(sql,movieName,timeLimit,movieIntro,releaseTime,typeId,movieImg,nation);
    }

    @Override
    public MovieInfo queryMovieByMId(int movieId) {
        String sql = "SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE M.MOVIEID=? AND FIND_IN_SET(T.TYPEID,M.TYPEID) GROUP BY M.MOVIEID";
        return queryByOne(sql,MovieInfo.class,movieId);
    }

    @Override
    public boolean updateMovieInfo(String movieName, String movieIntro, String releaseTime, String movieImg, String nation,int movieId) throws Exception {
        String sql = "UPDATE MOVIEINFO SET MOVIENAME=?,MOVIEINTRO=?,RELEASETIME=?,MOVIEIMG=?,NATION=? WHERE MOVIEID=?";
        return update(sql, movieName, movieIntro, releaseTime, movieImg, nation,movieId);
    }

    @Override
    public boolean deleteMovie(int movieId) {
        String sql = "DELETE FROM MOVIEINFO WHERE MOVIEID=?";
        return update(sql,movieId);
    }
}
