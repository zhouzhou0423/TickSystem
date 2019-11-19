package service.impl;

import dao.ManageDao;
import dao.impl.ManageDaoImpl;
import service.ManageService;
import vo.AdmInfo;
import vo.ArrangedInfo;
import vo.CinemaInfo;

import java.util.List;


public class ManageServiceImpl implements ManageService {
	ManageDao dao=new ManageDaoImpl();

	@Override
	public CinemaInfo login(String cinematel, String cinemapwd) throws Exception {
		return dao.login(cinematel, cinemapwd);
	}

	@Override
	public AdmInfo webLogin(String admtel, String admpwd) throws Exception {
		return dao.webLogin(admtel,admpwd);
	}

	@Override
	public boolean reg(String cinemaname, String cinematel, String cinemapwd) throws Exception {
		return dao.reg(cinemaname, cinematel, cinemapwd);
	}

	@Override
	public boolean checkCinemaTel(String cinematel,int cinemaId) throws Exception {
		return dao.checkCinemaTel(cinematel,cinemaId);
	}

	@Override
	public List<ArrangedInfo> findArrangeInfo(int cinemaid) throws Exception {
		return dao.findArrangeInfo(cinemaid);
	}

	@Override
	public boolean updateCinemaInfo(CinemaInfo cinemaInfo) throws Exception {
		return dao.updateCinemaInfo(cinemaInfo);
	}
}
