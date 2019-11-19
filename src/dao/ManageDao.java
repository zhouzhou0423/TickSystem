package dao;

import vo.AdmInfo;
import vo.ArrangedInfo;
import vo.CinemaInfo;

import java.util.List;

public interface ManageDao {
	/**
	 * ��¼
	 * @param cinematel
	 * @param cinemapwd
	 * @return
	 * @throws Exception
	 */
	CinemaInfo login(String cinematel, String cinemapwd) throws Exception;

	/**
	 * ��վ����Ա��¼
	 * @param admtel
	 * @param admpwd
	 * @return
	 * @throws Exception
	 */
	AdmInfo webLogin(String admtel, String admpwd) throws Exception;
	/**
	 * ע�����Ա
	 * @param cinemaname
	 * @param cinematel
	 * @param cinemapwd
	 * @return
	 * @throws Exception
	 */
	boolean reg(String cinemaname, String cinematel, String cinemapwd) throws Exception;
	/**
	 * ����ֻ����Ƿ��Ѵ���
	 * @param cinematel
	 * @return
	 * @throws Exception
	 */
	boolean checkCinemaTel(String cinematel,int cinemaId) throws Exception;

	/**
	 * ��ѯӰƬ�����б�
	 * @param cinemaid
	 * @return
	 * @throws Exception
	 */
	List<ArrangedInfo> findArrangeInfo(int cinemaid) throws Exception;

	/**
	 * ����ӰԺ��Ϣ
	 * @param cinemaInfo
	 * @return
	 * @throws Exception
	 */
	boolean updateCinemaInfo(CinemaInfo cinemaInfo) throws Exception;

}
