package dao;

import vo.CommentsInfo;
import vo.ZanInfo;

import java.util.List;

public interface CommentsInfoDao {
	/**
	 * 根据影片ID查询评论信息
	 * @param movieid
	 * @return
	 * @throws Exception
	 */
	List<CommentsInfo> findCommentsByMid(int movieid) throws Exception;
	/**
	 * 添加评论
	 * @param movieid
	 * @param userid
	 * @param username
	 * @param grade
	 * @return
	 * @throws Exception
	 */
	boolean addComment(int movieid, int userid, String username, String comment, int grade) throws Exception;

	/**
	 * 查询登录用户点赞过的评论
	 * @param userId
	 * @return
	 */
	List<ZanInfo> queryIsZan(int userId);

	/**
	 * 查询表中是否有过记录
	 * @param userId
	 * @param commentId
	 * @return
	 */
	boolean isExist(int userId,int commentId);

	/**
	 * 修改点赞状态
	 * @param userId
	 * @param commentId
	 * @param isLike
	 * @return
	 */
	boolean updateZanState(int userId,int commentId,int isLike);

	/**
	 * 新增点赞信息
	 * @param userId
	 * @param commentId
	 * @param isLike
	 * @return
	 */
	boolean insertZanState(int userId,int commentId,int isLike);

	/**
	 * 更新点赞数目
	 * @param commentId
	 * @param zanCount
	 * @return
	 */
	boolean updateZanCount(int commentId,int zanCount);

	/**
	 * 删除评论
	 * @param commentId
	 * @return
	 * @throws Exception
	 */
	boolean deleteComment(int commentId) throws Exception;
}
