package dao;

import vo.CommentsInfo;
import vo.MovieInfo;
import vo.UserInfo;

import java.util.List;

public interface UserInfoDao {
	/**
	 * 用户登录
	 * @param usertel
	 * @param userpwd
	 * @return
	 * @throws Exception
	 */
	UserInfo login(String usertel, String userpwd) throws Exception;
	/**
	 * 用户注册
	 * @param username
	 * @param userpwd
	 * @return
	 * @throws Exception
	 */
	boolean reg(String username, String userpwd, String usertel) throws Exception;
	/**
	 * 用户名是否已存在
	 * @param username
	 * @param userId 排除项的ID
	 * @return
	 * @throws Exception
	 */
	boolean checkUserName(String username,int userId) throws Exception;
	/**
	 * 检查手机号是否已存在
	 * @param usertel
	 * @param userId 排除项的ID
	 * @return
	 * @throws Exception
	 */
	boolean checkUserTel(String usertel,int userId) throws Exception;
	/**
	 * 查找用户密码
	 * @param username
	 * @param usertel
	 * @return
	 * @throws Exception
	 */
	UserInfo findpwd(String username, String usertel) throws Exception;

	/**
	 * 查询所有用户
	 * @return
	 * @throws Exception
	 */
	List<UserInfo> findAllUser() throws Exception;

	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	boolean editUserInfo(UserInfo userInfo) throws Exception;

	/**
	 * 删除用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean deleteUser(int userId) throws Exception;

	/**
	 * 用户添加想看功能
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean wantLook(int userId,int movieId) throws Exception;

	/**
	 * 删除想看
	 * @param userId
	 * @param movieId
	 * @return
	 * @throws Exception
	 */
	boolean deleteWantLook(int userId,int movieId) throws Exception;

	/**
	 * 查询用户的想看列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryUserWantLook(int userId) throws Exception;

	/**
	 * 查询用户评论
	 * @param userId
	 * @return
	 */
	List<CommentsInfo> findUserComment(int userId);

	/**
	 * 查找用户
	 * @param userId
	 * @return
	 */
	UserInfo findUser(int userId);

}
