package dao;

import vo.CinemaInfo;

import java.util.List;


public interface CinemaInfoDao {
	/**
	 * 查找所有(按照拼音排序)
	 * @return
	 * @throws Exception
	 */
	List<CinemaInfo> findAll(String movieId) throws Exception;
	/**
	 * 按地区查找
	 * @return
	 * @throws Exception
	 */
	List<CinemaInfo> findByArea(String keyword,String movieId) throws Exception;
	/**
	 * 按ID查找
	 * @return
	 * @throws Exception
	 */
	CinemaInfo findByCinemaID(int cinemaid) throws Exception;

	/**
	 * 修改影院信息
	 * @param cinemaInfo
	 * @return
	 * @throws Exception
	 */
	boolean editCinemaInfo(CinemaInfo cinemaInfo) throws Exception;

	/**
	 * 删除影院
	 * @param cinemaId
	 * @return
	 * @throws Exception
	 */
	boolean deleteCinema(int cinemaId) throws Exception;

	/**
	 * 为影院添加影片
	 * @param cinemaId
	 * @param movieId
	 * @return
	 * @throws Exception
	 */
	boolean addMovie(int cinemaId,int movieId) throws Exception;

	/**
	 * 删除影院的影片
	 * @param cinemaId
	 * @param movieId
	 * @return
	 * @throws Exception
	 */
	boolean deleteMovie(int cinemaId,int movieId) throws Exception;
}
