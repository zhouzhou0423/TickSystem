package dao.impl;

import dao.WebAdmDao;
import util.BaseDao;
import vo.ActorInfo;
import vo.CinemaInfo;
import vo.CommentsInfo;
import vo.RoleInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/12 11:13
 */
public class WebAdmDaoImpl extends BaseDao implements WebAdmDao {
    @Override
    public List<CinemaInfo> findAllCinema() {
        String sql="SELECT * FROM CINEMAINFO WHERE STATE=0";
        return query(sql,CinemaInfo.class);
    }

    @Override
    public List<CinemaInfo> findNewCinema() {
        String sql="SELECT * FROM CINEMAINFO WHERE STATE=1";
        return query(sql,CinemaInfo.class);
    }

    @Override
    public boolean changeCinemaState(int cinemaId) {
        String sql="UPDATE CINEMAINFO SET STATE=0 WHERE CINEMAID=?";
        return update(sql,cinemaId);
    }

    @Override
    public List<CommentsInfo> findAllComment() {
        String sql="SELECT C.*,M.MOVIENAME FROM COMMENTS C,MOVIEINFO M WHERE C.MOVIEID=M.MOVIEID ORDER BY C.TIME DESC,C.ZANCOUNT DESC";
        return query(sql,CommentsInfo.class);
    }

    @Override
    public List<ActorInfo> findAllActor() {
        String sql = "SELECT * FROM ACTORINFO";
        return query(sql,ActorInfo.class);
    }

    @Override
    public List<RoleInfo> findAllRole() {
        String sql = "SELECT R.*,A.ACTORNAME,A.PHOTO,M.MOVIENAME FROM ROLEINFO R,MOVIEINFO M,ACTORINFO A WHERE R.MOVIEID=M.MOVIEID AND R.ACTORID=A.ACTORID ORDER BY R.MOVIEID,R.ISIMPORTANT";
        return query(sql,RoleInfo.class);
    }
}
