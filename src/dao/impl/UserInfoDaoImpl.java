package dao.impl;

import dao.UserInfoDao;
import util.BaseDao;
import vo.CommentsInfo;
import vo.MovieInfo;
import vo.UserInfo;

import java.util.List;


public class UserInfoDaoImpl extends BaseDao implements UserInfoDao {
	
	@Override
	public UserInfo login(String usertel, String userpwd) throws Exception {
		String sql="SELECT * FROM USERINFO WHERE USERTEL=? AND USERPWD=?";
		return queryByOne(sql, UserInfo.class,usertel,userpwd);
	}

	@Override
	public boolean checkUserName(String username,int userId) throws Exception {
		String sql="SELECT * FROM USERINFO WHERE USERNAME=? AND USERID!=?";
		UserInfo user=queryByOne(sql, UserInfo.class,username,userId);
		if(user!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean reg(String username, String userpwd, String usertel) throws Exception {
		String sql="INSERT INTO USERINFO(USERNAME,USERPWD,USERTEL) VALUES(?,?,?)";
		return update(sql, username,userpwd,usertel);
	}

	@Override
	public boolean checkUserTel(String usertel,int userId) throws Exception {
		String sql="SELECT * FROM USERINFO WHERE USERTEL=? AND USERID!=?";
		UserInfo user= queryByOne(sql, UserInfo.class,usertel,userId);
		if(user!=null){
			return true;
		}
		return false;
	}

	@Override
	public UserInfo findpwd(String username, String usertel) throws Exception {
		String sql="SELECT * FROM USERINFO WHERE USERNAME=? AND USERTEL=?";
		return queryByOne(sql, UserInfo.class, username,usertel);
	}

	@Override
	public List<UserInfo> findAllUser() throws Exception {
		String sql = "SELECT * FROM USERINFO";
		return query(sql,UserInfo.class);
	}

	@Override
	public boolean editUserInfo(UserInfo userInfo) throws Exception {
		String sql = "UPDATE USERINFO SET USERNAME=?,USERPWD=?,USERTEL=?,USERPHOTO=? WHERE USERID=?";
		return update(sql,userInfo.getUsername(),userInfo.getUserpwd(),userInfo.getUsertel(),userInfo.getUserphoto(),userInfo.getUserid());
	}

	@Override
	public boolean deleteUser(int userId) throws Exception {
		String sql="DELETE FROM USERINFO WHERE USERID=?";
		return update(sql,userId);
	}

	@Override
	public boolean wantLook(int userId, int movieId) throws Exception {
		String sql = "INSERT INTO USERLIKEINFO (USERID,MOVIEID) VALUES(?,?)";
		return update(sql,userId,movieId);
	}

	@Override
	public boolean deleteWantLook(int userId, int movieId) throws Exception {
		String sql = "DELETE FROM USERLIKEINFO WHERE USERID=? AND MOVIEID=?";
		return update(sql,userId,movieId);
	}

	@Override
	public List<MovieInfo> queryUserWantLook(int userId) throws Exception {
		String sql = "SELECT M.*,GROUP_CONCAT(T.TYPE) TYPENAME FROM MOVIEINFO M,MOVIETYPE T WHERE FIND_IN_SET(T.TYPEID,M.TYPEID) AND M.movieid IN (SELECT MOVIEID FROM userlikeinfo WHERE USERID=?) GROUP BY M.MOVIEID ORDER BY M.RELEASETIME";
		return query(sql,MovieInfo.class,userId);
	}

	@Override
	public List<CommentsInfo> findUserComment(int userId) {
		String sql="SELECT C.*,M.MOVIENAME FROM COMMENTS C,MOVIEINFO M WHERE C.MOVIEID=M.MOVIEID AND C.USERID=? ORDER BY C.TIME DESC,C.ZANCOUNT DESC;";
		return query(sql,CommentsInfo.class,userId);
	}

	@Override
	public UserInfo findUser(int userId) {
		String sql = "SELECT * FROM USERINFO WHERE USERID=?";
		return queryByOne(sql,UserInfo.class,userId);
	}
}
