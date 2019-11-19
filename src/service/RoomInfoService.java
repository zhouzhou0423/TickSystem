package service;

import vo.RoomInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/18 22:20
 */
public interface RoomInfoService {
    List<RoomInfo> queryRoomListByCId(int cinemaId);
    boolean addRoom(int roomId,int rowNum,String rowSit,int cinemaId);
    RoomInfo queryRIdIsExist(int roomId,int cinemaId);
    boolean deleteRoom(int roomId,int cinemaId);
}
