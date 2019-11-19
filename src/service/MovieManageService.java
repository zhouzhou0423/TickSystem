package service;

import vo.MovieInfo;
import vo.MovieTypeInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/216:41
 */
public interface MovieManageService {
    boolean changeState(int movieid, int cmstate, int cinemaid) throws Exception;
    List<MovieTypeInfo> queryMovieType() throws Exception;
    boolean webAddMovie(String movieName,int timeLimit,String movieIntro,String releaseTime,String typeId,String movieImg,String nation) throws Exception;
    MovieInfo queryMovieByMId(int movieId);
    boolean updateMovieInfo(String movieName,String movieIntro,String releaseTime,String movieImg,String nation,int movieId) throws Exception;
    boolean deleteMovie(int movieId);
}
