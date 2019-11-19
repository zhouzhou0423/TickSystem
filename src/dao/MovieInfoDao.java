package dao;

import vo.MovieInfo;
import vo.MovieTypeInfo;

import java.util.List;


public interface MovieInfoDao {
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

	/**
	 *
	 * ����ӰԺID��ѯ��ӰԺ��ӰƬ��Ϣ
	 * @param cinemaid
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> findMoviesByCinemaid(int cinemaid) throws Exception;

	/**
	 * ��ѯӰƬ�����б�
	 * @return
	 * @throws Exception
	 */
	List<MovieTypeInfo> findMovieType() throws Exception;

	/**
	 * ��ѯ����ӰƬ
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> findAllMovies() throws Exception;

	/**
	 * ͨ������Id��ѯӰƬ
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> findMoviesBytypeId(int typeId) throws Exception;

	/**
	 * ��ѯ�û��Ƿ�����뿴�б�
	 * @param userId
	 * @param movieId
	 * @return
	 */
	boolean queryIsWantLook(int userId,int movieId);
	/**
	 * չʾ��վ�ж�ӰԺû�е�ӰƬ
	 * @param cinemaId
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> findNoMovies(int cinemaId) throws Exception;

	/**
	 * �����뿴����
	 * @param movieId
	 *@param op ������add���� delete ����
	 * @return
	 * @throws Exception
	 */
	boolean updateWantSeeCount(int movieId,String op) throws Exception;

	/**
	 * ����Ʊ��
	 * @param movieId
	 * @param op add���� delete ����
	 * @param num
	 * @return
	 * @throws Exception
	 */
	boolean updateCount(int movieId,String op,int num) throws Exception;

}
