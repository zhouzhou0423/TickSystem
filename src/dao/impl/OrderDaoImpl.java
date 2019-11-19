package dao.impl;

import dao.OrderDao;
import util.BaseDao;
import vo.OrderInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/20 16:07
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public OrderInfo queryOrder(String orderId) {

        String sql = "SELECT O.*,A.CINEMAID,C.CINEMANAME,A.ROOMID,A.STARTTIME, A.ENDTIME, A.ROOMID, A.MOVIEPRICE, A.ONDATE,M.MOVIEID,M.MOVIENAME FROM ORDERINFO O LEFT JOIN ARRANGEDINFO A ON O.ARRANGEID=A.ARRANGEID LEFT JOIN CINEMAINFO C ON A.CINEMAID = C.CINEMAID LEFT JOIN MOVIEINFO M ON A.MOVIEID = M.MOVIEID WHERE ORDERID=?";
        return queryByOne(sql,OrderInfo.class,orderId);
    }

    @Override
    public boolean updateOrder(String orderId,String ticketCode, String payTime) {
        String sql = "UPDATE ORDERINFO SET ORDERSTATE=1,ticketcode=?,PAYTIME=? WHERE ORDERID=?";
        return update(sql,ticketCode,payTime,orderId);
    }

    @Override
    public List<OrderInfo> queryOrderList(int orderState) {
        String sql = "SELECT O.*,A.CINEMAID,C.CINEMANAME,A.ROOMID,A.STARTTIME, A.ENDTIME, A.ROOMID, A.MOVIEPRICE, A.ONDATE,M.movieid,M.moviename FROM ORDERINFO O LEFT JOIN arrangedinfo A ON O.ARRANGEID=A.ARRANGEID LEFT JOIN CINEMAINFO C ON A.CINEMAID = C.CINEMAID LEFT JOIN movieinfo m on A.MOVIEID = m.movieid AND ORDERSTATE=?";
        return query(sql,OrderInfo.class,orderState);
    }
}
