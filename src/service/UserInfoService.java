package service;


import vo.CommentsInfo;
import vo.MovieInfo;
import vo.UserInfo;

import java.util.List;


public interface UserInfoService{
	UserInfo login(String usertel, String userpwd) throws Exception;
	boolean reg(String username, String userpwd, String usertel) throws Exception;
	boolean checkUserName(String username,int userId) throws Exception;
	boolean checkUserTel(String usertel,int userId) throws Exception;
	UserInfo findpwd(String username, String usertel) throws Exception;
	List<UserInfo> findAllUser() throws Exception;
	boolean editUserInfo(UserInfo userInfo) throws Exception;
	boolean deleteUser(int userId) throws Exception;
	boolean wantLook(int userId,int movieId) throws Exception;
	boolean deleteWantLook(int userId,int movieId) throws Exception;
	List<MovieInfo> queryUserWantLook(int userId) throws Exception;
	List<CommentsInfo> findUserComment(int userId);
	UserInfo findUser(int userId);
}
