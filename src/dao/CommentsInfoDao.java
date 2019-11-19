package dao;

import vo.CommentsInfo;
import vo.ZanInfo;

import java.util.List;

public interface CommentsInfoDao {
	/**
	 * ����ӰƬID��ѯ������Ϣ
	 * @param movieid
	 * @return
	 * @throws Exception
	 */
	List<CommentsInfo> findCommentsByMid(int movieid) throws Exception;
	/**
	 * �������
	 * @param movieid
	 * @param userid
	 * @param username
	 * @param grade
	 * @return
	 * @throws Exception
	 */
	boolean addComment(int movieid, int userid, String username, String comment, int grade) throws Exception;

	/**
	 * ��ѯ��¼�û����޹�������
	 * @param userId
	 * @return
	 */
	List<ZanInfo> queryIsZan(int userId);

	/**
	 * ��ѯ�����Ƿ��й���¼
	 * @param userId
	 * @param commentId
	 * @return
	 */
	boolean isExist(int userId,int commentId);

	/**
	 * �޸ĵ���״̬
	 * @param userId
	 * @param commentId
	 * @param isLike
	 * @return
	 */
	boolean updateZanState(int userId,int commentId,int isLike);

	/**
	 * ����������Ϣ
	 * @param userId
	 * @param commentId
	 * @param isLike
	 * @return
	 */
	boolean insertZanState(int userId,int commentId,int isLike);

	/**
	 * ���µ�����Ŀ
	 * @param commentId
	 * @param zanCount
	 * @return
	 */
	boolean updateZanCount(int commentId,int zanCount);

	/**
	 * ɾ������
	 * @param commentId
	 * @return
	 * @throws Exception
	 */
	boolean deleteComment(int commentId) throws Exception;
}
