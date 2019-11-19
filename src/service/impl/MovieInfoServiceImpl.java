package service.impl;

import dao.MovieInfoDao;
import dao.impl.MovieInfoDaoImpl;
import service.MovieInfoService;
import vo.MovieInfo;
import vo.MovieTypeInfo;

import java.util.List;


public class MovieInfoServiceImpl implements MovieInfoService {
	MovieInfoDao dao=new MovieInfoDaoImpl();

	@Override
	public List<MovieInfo> queryTitleMovie() throws Exception {
		// TODO Auto-generated method stub
		return dao.queryTitleMovie();
	}

	@Override
	public List<MovieInfo> queryHotMovie() throws Exception {
		// TODO Auto-generated method stub
		return dao.queryHotMovie();
	}

	@Override
	public List<MovieInfo> queryWantMovie() throws Exception {
		// TODO Auto-generated method stub
		return dao.queryWantMovie();
	}

	@Override
	public List<MovieInfo> queryTodayTop() throws Exception {
		// TODO Auto-generated method stub
		return dao.queryTodayTop();
	}

	@Override
	public List<MovieInfo> queryTodayMost() throws Exception {
		// TODO Auto-generated method stub
		return dao.queryTodayMost();
	}

	@Override
	public List<MovieInfo> findMoviesByCinemaid(int cinemaid) throws Exception {
		return dao.findMoviesByCinemaid(cinemaid);
	}

	@Override
	public List<MovieTypeInfo> findMovieType() throws Exception {
		return dao.findMovieType();
	}

	@Override
	public List<MovieInfo> findAllMovies() throws Exception {
		return dao.findAllMovies();
	}

	@Override
	public List<MovieInfo> findMoviesBytypeId(int typeId) throws Exception {
		return dao.findMoviesBytypeId(typeId);
	}

	@Override
	public List<MovieInfo> findNoMovies(int cinemaId) throws Exception {
		return dao.findNoMovies(cinemaId);
	}

	@Override
	public boolean queryIsWantLook(int userId,int movieId) {
		return dao.queryIsWantLook(userId,movieId);
	}

	@Override
	public boolean updateWantSeeCount(int movieId,String op) throws Exception {
		return dao.updateWantSeeCount(movieId, op);
	}

	@Override
	public boolean updateCount(int movieId, String op, int num) throws Exception {
		return dao.updateCount(movieId, op, num);
	}

}
