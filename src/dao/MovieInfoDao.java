package dao;

import vo.MovieInfo;
import vo.MovieTypeInfo;

import java.util.List;


public interface MovieInfoDao {
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

	/**
	 *
	 * 根据影院ID查询该影院的影片信息
	 * @param cinemaid
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> findMoviesByCinemaid(int cinemaid) throws Exception;

	/**
	 * 查询影片类型列表
	 * @return
	 * @throws Exception
	 */
	List<MovieTypeInfo> findMovieType() throws Exception;

	/**
	 * 查询所有影片
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> findAllMovies() throws Exception;

	/**
	 * 通过类型Id查询影片
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> findMoviesBytypeId(int typeId) throws Exception;

	/**
	 * 查询用户是否加入想看列表
	 * @param userId
	 * @param movieId
	 * @return
	 */
	boolean queryIsWantLook(int userId,int movieId);
	/**
	 * 展示网站有而影院没有的影片
	 * @param cinemaId
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> findNoMovies(int cinemaId) throws Exception;

	/**
	 * 更新想看人数
	 * @param movieId
	 *@param op 操作：add新增 delete 减少
	 * @return
	 * @throws Exception
	 */
	boolean updateWantSeeCount(int movieId,String op) throws Exception;

	/**
	 * 更新票房
	 * @param movieId
	 * @param op add新增 delete 减少
	 * @param num
	 * @return
	 * @throws Exception
	 */
	boolean updateCount(int movieId,String op,int num) throws Exception;

}
