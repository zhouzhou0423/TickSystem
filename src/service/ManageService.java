package service;


import vo.AdmInfo;
import vo.ArrangedInfo;
import vo.CinemaInfo;

import java.util.List;


public interface ManageService {
	CinemaInfo login(String cinematel, String cinemapwd) throws Exception;
	AdmInfo webLogin(String admtel, String admpwd) throws Exception;
	boolean reg(String cinemaname, String cinematel, String cinemapwd) throws Exception;
	boolean checkCinemaTel(String cinematel,int cinemaId) throws Exception;
	List<ArrangedInfo> findArrangeInfo(int cinemaid) throws Exception;
	boolean updateCinemaInfo(CinemaInfo cinemaInfo) throws Exception;
}
