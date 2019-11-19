package service;


import vo.AdmInfo;


public interface AdmInfoService{
	public AdmInfo login(String admtel, String admpwd) throws Exception;
	public boolean reg(String admname, String admpwd, String admtel) throws Exception;
	public boolean checkAdmTel(String admtel) throws Exception;
}
