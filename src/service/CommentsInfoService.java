package service;

import vo.CommentsInfo;
import vo.ZanInfo;

import java.util.List;

public interface CommentsInfoService {
	List<CommentsInfo> findCommentsByMid(int movieid) throws Exception;
	boolean addComment(int movieid, int userid, String username, String comment, int grade) throws Exception;
	List<ZanInfo> queryIsZan(int userId);
	boolean changeZanState(int userId,int commentId,int isLike);
	boolean updateZanCount(int commentId,int zanCount);
	boolean deleteComment(int commentId) throws Exception;
}
