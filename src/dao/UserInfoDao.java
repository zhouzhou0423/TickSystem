package dao;

import vo.CommentsInfo;
import vo.MovieInfo;
import vo.UserInfo;

import java.util.List;

public interface UserInfoDao {
	/**
	 * �û���¼
	 * @param usertel
	 * @param userpwd
	 * @return
	 * @throws Exception
	 */
	UserInfo login(String usertel, String userpwd) throws Exception;
	/**
	 * �û�ע��
	 * @param username
	 * @param userpwd
	 * @return
	 * @throws Exception
	 */
	boolean reg(String username, String userpwd, String usertel) throws Exception;
	/**
	 * �û����Ƿ��Ѵ���
	 * @param username
	 * @param userId �ų����ID
	 * @return
	 * @throws Exception
	 */
	boolean checkUserName(String username,int userId) throws Exception;
	/**
	 * ����ֻ����Ƿ��Ѵ���
	 * @param usertel
	 * @param userId �ų����ID
	 * @return
	 * @throws Exception
	 */
	boolean checkUserTel(String usertel,int userId) throws Exception;
	/**
	 * �����û�����
	 * @param username
	 * @param usertel
	 * @return
	 * @throws Exception
	 */
	UserInfo findpwd(String username, String usertel) throws Exception;

	/**
	 * ��ѯ�����û�
	 * @return
	 * @throws Exception
	 */
	List<UserInfo> findAllUser() throws Exception;

	/**
	 * �޸��û���Ϣ
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	boolean editUserInfo(UserInfo userInfo) throws Exception;

	/**
	 * ɾ���û�
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean deleteUser(int userId) throws Exception;

	/**
	 * �û�����뿴����
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean wantLook(int userId,int movieId) throws Exception;

	/**
	 * ɾ���뿴
	 * @param userId
	 * @param movieId
	 * @return
	 * @throws Exception
	 */
	boolean deleteWantLook(int userId,int movieId) throws Exception;

	/**
	 * ��ѯ�û����뿴�б�
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<MovieInfo> queryUserWantLook(int userId) throws Exception;

	/**
	 * ��ѯ�û�����
	 * @param userId
	 * @return
	 */
	List<CommentsInfo> findUserComment(int userId);

	/**
	 * �����û�
	 * @param userId
	 * @return
	 */
	UserInfo findUser(int userId);

}
