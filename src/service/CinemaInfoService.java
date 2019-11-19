package service;


import vo.CinemaInfo;

import java.util.List;


public interface CinemaInfoService{
	List<CinemaInfo> findAll(String movieId) throws Exception;
	List<CinemaInfo> findByArea(String keyword,String movieId) throws Exception;
	CinemaInfo findByCinemaID(int cinemaid) throws Exception;
	boolean editCinemaInfo(CinemaInfo cinemaInfo) throws Exception;
	boolean deleteCinema(int cinemaId) throws Exception;
	boolean addMovie(int cinemaId,int movieId) throws Exception;
	boolean deleteMovie(int cinemaId,int movieId) throws Exception;
}
