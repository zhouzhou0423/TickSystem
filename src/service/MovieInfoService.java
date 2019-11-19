package service;


import vo.MovieInfo;
import vo.MovieTypeInfo;

import java.util.List;


public interface MovieInfoService{
	/**
	 * ��ѯ��ҳ�ֲ�ͼӰƬ��Ϣ(δ�¼ܵ�ӰƬ�а���Ʊ������)
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryTitleMovie() throws Exception;
	/**
	 * ��ѯ��ҳ������ӳӰƬ(����Ʊ������������)
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryHotMovie() throws Exception;
	/**
	 * ��ѯ��ҳ������ӳӰƬ(������ӳʱ����뿴��������)
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryWantMovie() throws Exception;
	/**
	 * ����Ʊ��(top 10)
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryTodayTop() throws Exception;
	/**
	 * �����ڴ�(top 10)
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
