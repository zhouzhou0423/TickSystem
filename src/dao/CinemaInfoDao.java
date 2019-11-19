package dao;

import vo.CinemaInfo;

import java.util.List;


public interface CinemaInfoDao {
	/**
	 * ��������(����ƴ������)
	 * @return
	 * @throws Exception
	 */
	List<CinemaInfo> findAll(String movieId) throws Exception;
	/**
	 * ����������
	 * @return
	 * @throws Exception
	 */
	List<CinemaInfo> findByArea(String keyword,String movieId) throws Exception;
	/**
	 * ��ID����
	 * @return
	 * @throws Exception
	 */
	CinemaInfo findByCinemaID(int cinemaid) throws Exception;

	/**
	 * �޸�ӰԺ��Ϣ
	 * @param cinemaInfo
	 * @return
	 * @throws Exception
	 */
	boolean editCinemaInfo(CinemaInfo cinemaInfo) throws Exception;

	/**
	 * ɾ��ӰԺ
	 * @param cinemaId
	 * @return
	 * @throws Exception
	 */
	boolean deleteCinema(int cinemaId) throws Exception;

	/**
	 * ΪӰԺ���ӰƬ
	 * @param cinemaId
	 * @param movieId
	 * @return
	 * @throws Exception
	 */
	boolean addMovie(int cinemaId,int movieId) throws Exception;

	/**
	 * ɾ��ӰԺ��ӰƬ
	 * @param cinemaId
	 * @param movieId
	 * @return
	 * @throws Exception
	 */
	boolean deleteMovie(int cinemaId,int movieId) throws Exception;
}
