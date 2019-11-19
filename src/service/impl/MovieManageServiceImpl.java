package service.impl;

import dao.MovieManageDao;
import dao.impl.MovieManageDaoImpl;
import service.MovieManageService;
import vo.MovieInfo;
import vo.MovieTypeInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/216:41
 */
public class MovieManageServiceImpl implements MovieManageService {
    MovieManageDao dao = new MovieManageDaoImpl();
    @Override
    public boolean changeState(int movieid, int cmstate,int cinemaid) throws Exception {
        return dao.changeState(movieid,cmstate,cinemaid);
    }
    @Override
    public List<MovieTypeInfo> queryMovieType() throws Exception {
        return dao.queryMovieType();
    }

    @Override
    public boolean webAddMovie(String movieName, int timeLimit, String movieIntro, String releaseTime, String typeId, String movieImg, String nation) throws Exception {
        return dao.webAddMovie(movieName, timeLimit, movieIntro, releaseTime, typeId, movieImg, nation);
    }

    @Override
    public MovieInfo queryMovieByMId(int movieId) {
        return dao.queryMovieByMId(movieId);
    }

    @Override
    public boolean updateMovieInfo(String movieName, String movieIntro, String releaseTime, String movieImg, String nation,int movieId) throws Exception {
        return dao.updateMovieInfo(movieName, movieIntro, releaseTime, movieImg, nation,movieId);
    }

    @Override
    public boolean deleteMovie(int movieId) {
        return dao.deleteMovie(movieId);
    }
}
