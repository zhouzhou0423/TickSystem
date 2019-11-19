package service.impl;

import dao.CinemaInfoDao;
import dao.impl.CinemaInfoDaoImpl;
import service.CinemaInfoService;
import vo.CinemaInfo;

import java.util.List;


public class CinemaInfoServiceImpl implements CinemaInfoService {
	CinemaInfoDao dao=new CinemaInfoDaoImpl();

	@Override
	public List<CinemaInfo> findAll(String movieId) throws Exception {
		return dao.findAll(movieId);
	}

	@Override
	public List<CinemaInfo> findByArea(String keyword,String movieId) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByArea(keyword,movieId);
	}

	@Override
	public CinemaInfo findByCinemaID(int cinemaid) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByCinemaID(cinemaid);
	}

	@Override
	public boolean editCinemaInfo(CinemaInfo cinemaInfo) throws Exception {
		return dao.editCinemaInfo(cinemaInfo);
	}

	@Override
	public boolean deleteCinema(int cinemaId) throws Exception {
		return dao.deleteCinema(cinemaId);
	}

	@Override
	public boolean addMovie(int cinemaId, int movieId) throws Exception {
		return dao.addMovie(cinemaId, movieId);
	}

	@Override
	public boolean deleteMovie(int cinemaId, int movieId) throws Exception {
		return dao.deleteMovie(cinemaId, movieId);
	}
}
