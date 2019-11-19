package service.impl;

import dao.CommentsInfoDao;
import dao.impl.CommentsInfoDaoImpl;
import service.CommentsInfoService;
import vo.CommentsInfo;
import vo.ZanInfo;

import java.util.List;

public class CommentsInfoServiceImpl implements CommentsInfoService {
	CommentsInfoDao dao=new CommentsInfoDaoImpl();
	@Override
	public List<CommentsInfo> findCommentsByMid(int movieid) throws Exception {
		// TODO Auto-generated method stub
		return dao.findCommentsByMid(movieid);
	}
	@Override
	public boolean addComment(int movieid, int userid, String username,
                              String comment, int grade) throws Exception {
		// TODO Auto-generated method stub
		return dao.addComment(movieid, userid, username, comment, grade);
	}

	@Override
	public List<ZanInfo> queryIsZan(int userId) {
		return dao.queryIsZan(userId);
	}

	@Override
	public boolean changeZanState(int userId, int commentId, int isLike) {
		boolean flag=dao.isExist(userId,commentId);
		if (flag){
			return dao.updateZanState(userId,commentId,isLike);
		}else {
			return dao.insertZanState(userId,commentId,isLike);
		}

	}

	@Override
	public boolean updateZanCount(int commentId, int zanCount) {
		return dao.updateZanCount(commentId,zanCount);
	}

	@Override
	public boolean deleteComment(int commentId) throws Exception {
		return dao.deleteComment(commentId);
	}

}
