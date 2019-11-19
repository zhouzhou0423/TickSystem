package dao.impl;

import dao.RoomInfoDao;
import util.BaseDao;
import vo.RoomInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/18 22:19
 */
public class RoomInfoDaoImpl  extends BaseDao implements RoomInfoDao {
    @Override
    public List<RoomInfo> queryRoomListByCId(int cinemaId) {
        String sql = "SELECT *,SUM(LENGTH(REPLACE(ROWSIT,'_',''))) TOTALSIT,COUNT(ROWNUM) ROWCOUNTNUM ,MAX(LENGTH(ROWSIT)) COLUMNNUM FROM ROOMINFO WHERE CINEMAID=? GROUP BY ROOMID;";
        return query(sql,RoomInfo.class,cinemaId);
    }

    @Override
    public boolean addRoom(int roomId, int rowNum, String rowSit,int cinemaId) {
        String sql = "INSERT INTO ROOMINFO (ROOMID,ROWNUM,ROWSIT,CINEMAID) VALUES (?,?,?,?)";
        return update(sql,roomId,rowNum,rowSit,cinemaId);
    }

    @Override
    public RoomInfo queryRIdIsExist(int roomId, int cinemaId) {
        String sql = "SELECT * FROM ROOMINFO WHERE ROOMID=? AND CINEMAID=?";
        return queryByOne(sql,RoomInfo.class,roomId,cinemaId);
    }

    @Override
    public boolean deleteRoom(int roomId, int cinemaId) {
        String sql = "DELETE FROM ROOMINFO WHERE ROOMID=? AND CINEMAID=?";
        return update(sql,roomId,cinemaId);
    }
}
