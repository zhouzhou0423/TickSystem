package service.impl;

import dao.AdmInfoDao;
import dao.impl.AdmInfoDaoImpl;
import service.AdmInfoService;
import vo.AdmInfo;


public class AdmInfoServiceImpl implements AdmInfoService {
	AdmInfoDao dao=new AdmInfoDaoImpl();

	@Override
	public AdmInfo login(String admtel, String admpwd) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(admtel, admpwd);
	}

	@Override
	public boolean reg(String admname, String admpwd, String admtel)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.reg(admname, admpwd, admtel);
	}

	@Override
	public boolean checkAdmTel(String admtel) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkAdmTel(admtel);
	}

}
