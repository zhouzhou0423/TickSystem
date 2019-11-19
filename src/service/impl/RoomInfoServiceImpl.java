package service.impl;

import dao.RoomInfoDao;
import dao.impl.RoomInfoDaoImpl;
import service.RoomInfoService;
import vo.RoomInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/18 22:20
 */
public class RoomInfoServiceImpl implements RoomInfoService {
    RoomInfoDao dao = new RoomInfoDaoImpl();
    @Override
    public List<RoomInfo> queryRoomListByCId(int cinemaId) {
        return dao.queryRoomListByCId(cinemaId);
    }

    @Override
    public boolean addRoom(int roomId, int rowNum, String rowSit, int cinemaId) {
        return dao.addRoom(roomId, rowNum, rowSit, cinemaId);
    }

    @Override
    public RoomInfo queryRIdIsExist(int roomId, int cinemaId) {
        return dao.queryRIdIsExist(roomId, cinemaId);
    }

    @Override
    public boolean deleteRoom(int roomId, int cinemaId) {
        return dao.deleteRoom(roomId, cinemaId);
    }
}
