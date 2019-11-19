package dao;

import vo.RoomInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/18 22:17
 */
public interface RoomInfoDao {
    /**
     * 查询影院影厅列表
     * @param cinemaId
     * @return
     */
    List<RoomInfo> queryRoomListByCId(int cinemaId);

    /**
     * 添加影厅
     * @param cinemaId
     * @param rowNum
     * @param rowSit
     * @return
     */
    boolean addRoom(int roomId,int rowNum,String rowSit,int cinemaId);

    /**
     * 查询影厅编号是否已存在
     * @param roomId
     * @param cinemaId
     * @return
     */
    RoomInfo queryRIdIsExist(int roomId,int cinemaId);

    /**
     * 删除影厅
     * @param roomId
     * @param cinemaId
     * @return
     */
    boolean deleteRoom(int roomId,int cinemaId);
}
