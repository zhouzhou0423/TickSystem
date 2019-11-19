package dao;

import vo.AdmInfo;

public interface AdmInfoDao {
	/**
	 * ��¼
	 * @param admtel
	 * @param admpwd
	 * @return
	 * @throws Exception
	 */
	public AdmInfo login(String admtel, String admpwd) throws Exception;
	/**
	 * ע�����Ա
	 * @param admname
	 * @param admpwd
	 * @param admtel
	 * @return
	 * @throws Exception
	 */
	public boolean reg(String admname, String admpwd, String admtel) throws Exception;
	/**
	 * ����ֻ����Ƿ��Ѵ���
	 * @param admtel
	 * @return
	 * @throws Exception
	 */
	public boolean checkAdmTel(String admtel) throws Exception;

}
