package service.impl;

import dao.UserInfoDao;
import dao.impl.UserInfoDaoImpl;
import service.UserInfoService;
import vo.CommentsInfo;
import vo.MovieInfo;
import vo.UserInfo;

import java.util.List;


public class UserInfoServiceImpl implements UserInfoService {
	UserInfoDao dao=new UserInfoDaoImpl();

	@Override
	public UserInfo login(String usertel, String userpwd) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(usertel, userpwd);
	}

	@Override
	public boolean reg(String username, String userpwd, String usertel) throws Exception {
		// TODO Auto-generated method stub
		return dao.reg(username, userpwd,usertel);
	}

	@Override
	public boolean checkUserName(String username,int userId) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkUserName(username,userId);
	}

	@Override
	public boolean checkUserTel(String usertel,int userId) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkUserTel(usertel,userId);
	}

	@Override
	public UserInfo findpwd(String username, String usertel) throws Exception {
		// TODO Auto-generated method stub
		return dao.findpwd(username, usertel);
	}

	@Override
	public List<UserInfo> findAllUser() throws Exception {
		return dao.findAllUser();
	}

	@Override
	public boolean editUserInfo(UserInfo userInfo) throws Exception {
		return dao.editUserInfo(userInfo);
	}

	@Override
	public boolean deleteUser(int userId) throws Exception {
		return dao.deleteUser(userId);
	}

	@Override
	public boolean wantLook(int userId, int movieId) throws Exception {
		return dao.wantLook(userId,movieId);
	}

	@Override
	public boolean deleteWantLook(int userId, int movieId) throws Exception {
		return dao.deleteWantLook(userId, movieId);
	}

	@Override
	public List<MovieInfo> queryUserWantLook(int userId) throws Exception {
		return dao.queryUserWantLook(userId);
	}

	@Override
	public List<CommentsInfo> findUserComment(int userId) {
		return dao.findUserComment(userId);
	}

	@Override
	public UserInfo findUser(int userId) {
		return dao.findUser(userId);
	}

}
