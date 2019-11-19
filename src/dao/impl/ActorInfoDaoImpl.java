package dao.impl;

import dao.ActorInfoDao;
import util.BaseDao;
import vo.ActorInfo;
import vo.RoleInfo;

import java.util.List;


public class ActorInfoDaoImpl extends BaseDao implements ActorInfoDao {

	@Override
	public List<RoleInfo> findByMId(int movieId) throws Exception {
		String sql="SELECT R.*,A.ACTORNAME,A.PHOTO FROM ROLEINFO R LEFT JOIN ACTORINFO A ON R.ACTORID=A.ACTORID WHERE R.MOVIEID=? ORDER BY R.ISIMPORTANT";
		return query(sql, RoleInfo.class,movieId);
	}

	@Override
	public List<ActorInfo> findActorByName(String actorName) throws Exception {
		String sql="SELECT * FROM ACTORINFO WHERE ACTORNAME=?";
		return query(sql,ActorInfo.class,actorName);
	}

	@Override
	public boolean addActor(ActorInfo actorInfo) throws Exception {
		String sql = "INSERT INTO ACTORINFO (ACTORNAME,PHOTO) VALUES (?,?)";
		return update(sql,actorInfo.getActorname(),actorInfo.getPhoto());
	}

	@Override
	public boolean deleteActor(int actorId) throws Exception {
		String sql="DELETE FROM ACTORINFO WHERE ACTORID=?";
		return update(sql,actorId);
	}

	@Override
	public boolean editActor(String photo,int actorId) throws Exception {
		String sql="UPDATE ACTORINFO SET PHOTO=? WHERE ACTORID=?";
		return update(sql,photo,actorId);
	}

}
