package dao.impl;

import dao.SitInfoDao;
import util.BaseDao;
import vo.ArrangedInfo;
import vo.OrderInfo;
import vo.RoomInfo;
import vo.SitInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/5 14:50
 */
public class SitInfoDaoImpl extends BaseDao implements SitInfoDao {
    @Override
    public ArrangedInfo queryArrangeByAId(int arrangeId) {
        String sql="SELECT A.*,M.MOVIENAME,M.TIMELIMIT,SUM(LENGTH(REPLACE(R.ROWSIT,'_',''))) SUMSIT,(SELECT COUNT(*) FROM SITINFO S WHERE S.STATE=1 AND S.ARRANGEID=A.ARRANGEID GROUP BY A.ARRANGEID) AS SALESIT " +
                "FROM ROOMINFO R " +
                "LEFT JOIN ARRANGEDINFO A ON R.ROOMID = A.ROOMID " +
                "LEFT JOIN CINEMAINFO C ON A.CINEMAID = C.CINEMAID " +
                "LEFT JOIN MOVIEINFO M ON A.MOVIEID = M.MOVIEID WHERE A.ARRANGEID=? GROUP BY A.ARRANGEID";
        return queryByOne(sql, ArrangedInfo.class,arrangeId);
    }

    @Override
    public List<RoomInfo> queryRoomInfo(int cinemaId, int roomId) {
        String sql = "SELECT * FROM ROOMINFO WHERE CINEMAID=? AND ROOMID=? ORDER BY ROWNUM";
        return query(sql,RoomInfo.class,cinemaId,roomId);
    }

    @Override
    public List<SitInfo> querySitInfo(int arrangeId, int roomId) {
        String sql = "SELECT * FROM SITINFO WHERE ARRANGEID=? AND ROOMID=? AND STATE!=0";
        return query(sql,SitInfo.class,arrangeId,roomId);
    }

    @Override
    public boolean updateSitState(SitInfo sitInfo) {
        String sql = "INSERT INTO SITINFO (ROOMID,SITPOSITION,STATE,ARRANGEID) VALUES (?,?,?,?)";
        return update(sql,sitInfo.getRoomid(),sitInfo.getSitposition(),sitInfo.getState(),sitInfo.getArrangeid());
    }

    @Override
    public boolean addOrder(OrderInfo orderInfo) {
        String sql = "INSERT INTO ORDERINFO (ORDERID,USERID,ARRANGEID,SITPOSITION,ORDERSTATE,ORDERTIME,NUM,TOTALPRICE) VALUES (?,?,?,?,?,?,?,?)";
        return update(sql,orderInfo.getOrderid(),orderInfo.getUserid(),orderInfo.getArrangeid(),orderInfo.getSitposition(),orderInfo.getOrderstate(),orderInfo.getOrdertime(),orderInfo.getNum(),orderInfo.getTotalprice());
    }

    @Override
    public List<OrderInfo> queryOrderList(int userId, int orderState) {
        String sql = "SELECT O.*,A.CINEMAID,C.CINEMANAME,A.ROOMID,A.STARTTIME, A.ENDTIME, A.ROOMID, A.MOVIEPRICE, A.ONDATE,M.movieid,M.moviename FROM ORDERINFO O LEFT JOIN arrangedinfo A ON O.ARRANGEID=A.ARRANGEID LEFT JOIN CINEMAINFO C ON A.CINEMAID = C.CINEMAID LEFT JOIN movieinfo m on A.MOVIEID = m.movieid WHERE USERID=? AND ORDERSTATE=?";
        return query(sql,OrderInfo.class,userId,orderState);
    }

    @Override
    public boolean deleteOrder(String orderId) {
        String sql = "DELETE FROM ORDERINFO WHERE ORDERID=?";
        return update(sql,orderId);
    }

    @Override
    public boolean deleteSit(int arrangeId, String sit) {
        String sql = "DELETE FROM SITINFO WHERE ARRANGEID=? AND SITPOSITION=?";
        return update(sql,arrangeId,sit);
    }

    @Override
    public boolean updateState(int arrangeId,String sitPosition) {
        String sql = "UPDATE SITINFO SET STATE=1 WHERE arrangeId=? and sitPosition=?";
        return update(sql,arrangeId,sitPosition);
    }
}
