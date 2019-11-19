package dao;

import vo.AdmInfo;

public interface AdmInfoDao {
	/**
	 * 登录
	 * @param admtel
	 * @param admpwd
	 * @return
	 * @throws Exception
	 */
	public AdmInfo login(String admtel, String admpwd) throws Exception;
	/**
	 * 注册管理员
	 * @param admname
	 * @param admpwd
	 * @param admtel
	 * @return
	 * @throws Exception
	 */
	public boolean reg(String admname, String admpwd, String admtel) throws Exception;
	/**
	 * 检查手机号是否已存在
	 * @param admtel
	 * @return
	 * @throws Exception
	 */
	public boolean checkAdmTel(String admtel) throws Exception;

}
