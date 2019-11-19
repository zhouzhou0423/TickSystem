package dao;

import vo.AdmInfo;
import vo.ArrangedInfo;
import vo.CinemaInfo;

import java.util.List;

public interface ManageDao {
	/**
	 * 登录
	 * @param cinematel
	 * @param cinemapwd
	 * @return
	 * @throws Exception
	 */
	CinemaInfo login(String cinematel, String cinemapwd) throws Exception;

	/**
	 * 网站管理员登录
	 * @param admtel
	 * @param admpwd
	 * @return
	 * @throws Exception
	 */
	AdmInfo webLogin(String admtel, String admpwd) throws Exception;
	/**
	 * 注册管理员
	 * @param cinemaname
	 * @param cinematel
	 * @param cinemapwd
	 * @return
	 * @throws Exception
	 */
	boolean reg(String cinemaname, String cinematel, String cinemapwd) throws Exception;
	/**
	 * 检查手机号是否已存在
	 * @param cinematel
	 * @return
	 * @throws Exception
	 */
	boolean checkCinemaTel(String cinematel,int cinemaId) throws Exception;

	/**
	 * 查询影片场次列表
	 * @param cinemaid
	 * @return
	 * @throws Exception
	 */
	List<ArrangedInfo> findArrangeInfo(int cinemaid) throws Exception;

	/**
	 * 完善影院信息
	 * @param cinemaInfo
	 * @return
	 * @throws Exception
	 */
	boolean updateCinemaInfo(CinemaInfo cinemaInfo) throws Exception;

}
