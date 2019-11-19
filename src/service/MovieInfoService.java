package service;


import vo.MovieInfo;
import vo.MovieTypeInfo;

import java.util.List;


public interface MovieInfoService{
	/**
	 * 查询首页轮播图影片信息(未下架的影片中按照票房排序)
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryTitleMovie() throws Exception;
	/**
	 * 查询首页正在热映影片(按照票房和评分排序)
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryHotMovie() throws Exception;
	/**
	 * 查询首页即将上映影片(按照上映时间和想看人数排序)
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryWantMovie() throws Exception;
	/**
	 * 今日票房(top 10)
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryTodayTop() throws Exception;
	/**
	 * 最受期待(top 10)
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryTodayMost() throws Exception;

	List<MovieInfo> findMoviesByCinemaid(int cinemaid) throws Exception;
	List<MovieTypeInfo> findMovieType() throws Exception;
	List<MovieInfo> findAllMovies() throws Exception;
	List<MovieInfo> findMoviesBytypeId(int typeId) throws Exception;
	List<MovieInfo> findNoMovies(int cinemaId) throws Exception;
	boolean queryIsWantLook(int userId,int movieId);
	boolean updateWantSeeCount(int movieId,String op) throws Exception;
	boolean updateCount(int movieId,String op,int num) throws Exception;
}
