package dao.impl;

import dao.CommentsInfoDao;
import util.BaseDao;
import vo.CommentsInfo;
import vo.ZanInfo;

import java.util.List;

public class CommentsInfoDaoImpl extends BaseDao implements CommentsInfoDao {

	@Override
	public List<CommentsInfo> findCommentsByMid(int movieid) throws Exception {
		String sql="SELECT * FROM COMMENTS WHERE MOVIEID=? ORDER BY TIME DESC,ZANCOUNT DESC";
		return query(sql, CommentsInfo.class,movieid);
	}

	@Override
	public boolean addComment(int movieid, int userid,
                              String username, String comment, int grade) throws Exception {
		String sql="INSERT INTO COMMENTS (movieid,userid,username,comment,grade) VALUES(?,?,?,?,?)";
		return update(sql, movieid,userid,username,comment,grade);
	}

	@Override
	public List<ZanInfo> queryIsZan(int userId) {
		String sql="SELECT * FROM ZANINFO WHERE USERID=? AND ISLIKE=1";
		return query(sql,ZanInfo.class,userId);
	}

	@Override
	public boolean isExist(int userId, int commentId) {
		String sql="SELECT * FROM ZANINFO WHERE USERID=? AND COMMENTID=?";
		List<ZanInfo> list = query(sql,ZanInfo.class,userId,commentId);
		if(list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateZanState(int userId, int commentId, int isLike) {
		String sql=sql="UPDATE ZANINFO SET ISLIKE=? WHERE USERID=? AND COMMENTID=?";
		return update(sql,isLike,userId,commentId);
	}

	@Override
	public boolean insertZanState(int userId, int commentId, int isLike) {
		String sql="INSERT INTO ZANINFO (USERID,COMMENTID,ISLIKE) VALUES(?,?,?)";
		return update(sql,userId,commentId,isLike);
	}

	@Override
	public boolean updateZanCount(int commentId, int zanCount) {
		String sql="UPDATE COMMENTS SET ZANCOUNT=? WHERE COMMENTID=?";
		return update(sql,zanCount,commentId);
	}

	@Override
	public boolean deleteComment(int commentId) throws Exception {
		String sql = "DELETE FROM COMMENTS WHERE COMMENTID=?";
		return update(sql,commentId);
	}

}
