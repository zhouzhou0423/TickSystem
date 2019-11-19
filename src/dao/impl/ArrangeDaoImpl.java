package dao.impl;

import dao.ArrangeDao;
import util.BaseDao;
import vo.ArrangeBaseInfo;
import vo.ArrangedInfo;
import vo.RoomBaseInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/3/3117:39
 */
public class ArrangeDaoImpl extends BaseDao implements ArrangeDao {
    @Override
    public boolean arrangeEdit(int arrangeId,int roomId, String startTime, String endTime, double moviePrice) throws Exception {
        String sql="UPDATE ARRANGEDINFO SET ROOMID=?,STARTTIME=? ,ENDTIME=?,MOVIEPRICE=? WHERE ARRANGEID=?";
        return update(sql,roomId,startTime,endTime,moviePrice,arrangeId);
    }

    @Override
    public List<RoomBaseInfo> findRoomByCinemaId(int cinemaId){
        String sql="SELECT DISTINCT ROOMID,CINEMAID FROM ROOMINFO WHERE CINEMAID=?";
        return query(sql,RoomBaseInfo.class,cinemaId);
    }

    @Override
    public boolean addArrange(ArrangeBaseInfo arrangeBaseInfo) {
        String sql="INSERT INTO ARRANGEDINFO (MOVIEID, CINEMAID, STARTTIME, ENDTIME, ROOMID, MOVIEPRICE, ONDATE) VALUES(?,?,?,?,?,?,?)";
        return update(sql,arrangeBaseInfo.getMovieid(),arrangeBaseInfo.getCinemaid(),arrangeBaseInfo.getStarttime(),arrangeBaseInfo.getEndtime(),arrangeBaseInfo.getRoomid(),arrangeBaseInfo.getMovieprice(),arrangeBaseInfo.getOndate());
    }

    @Override
    public boolean queryRoomArrangeExist(int cinemaId, int roomId,String ondate, String startTime, String endTime) {
        String sql="SELECT * FROM ARRANGEDINFO WHERE CINEMAID=? AND ROOMID=? AND ONDATE=? AND (? BETWEEN STARTTIME AND ENDTIME OR (? < STARTTIME AND ? > STARTTIME))";
        List<ArrangeBaseInfo>list=query(sql,ArrangeBaseInfo.class,cinemaId,roomId,ondate,startTime,startTime,endTime);
        int res= list.size();
        if (res==0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteArrange(int arrangeId) {
        String sql="DELETE FROM ARRANGEDINFO WHERE ARRANGEID=?";
        boolean flag=update(sql,arrangeId);
        if (flag){
            return true;
        }
        return false;
    }

    @Override
    public List<ArrangedInfo> queryArrangeByMovieId(int cinemaId, int movieId,String dateTime) {
        String sql="SELECT A.*,M.MOVIENAME,M.TIMELIMIT,SUM(LENGTH(REPLACE(R.ROWSIT,'_',''))) SUMSIT,(SELECT COUNT(*) FROM SITINFO S WHERE S.STATE=1 AND S.ARRANGEID=A.ARRANGEID GROUP BY A.ARRANGEID) AS SALESIT " +
                "FROM ROOMINFO R " +
                "LEFT JOIN ARRANGEDINFO A ON R.ROOMID = A.ROOMID " +
                "LEFT JOIN CINEMAINFO C ON A.CINEMAID = C.CINEMAID " +
                "LEFT JOIN MOVIEINFO M ON A.MOVIEID = M.MOVIEID WHERE A.CINEMAID=? AND A.MOVIEID=? AND A.ONDATE=? GROUP BY A.ARRANGEID";
        return query(sql, ArrangedInfo.class,cinemaId,movieId,dateTime);
    }
}
