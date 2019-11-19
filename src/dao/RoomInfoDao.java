package dao;

import vo.RoomInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/18 22:17
 */
public interface RoomInfoDao {
    /**
     * ��ѯӰԺӰ���б�
     * @param cinemaId
     * @return
     */
    List<RoomInfo> queryRoomListByCId(int cinemaId);

    /**
     * ���Ӱ��
     * @param cinemaId
     * @param rowNum
     * @param rowSit
     * @return
     */
    boolean addRoom(int roomId,int rowNum,String rowSit,int cinemaId);

    /**
     * ��ѯӰ������Ƿ��Ѵ���
     * @param roomId
     * @param cinemaId
     * @return
     */
    RoomInfo queryRIdIsExist(int roomId,int cinemaId);

    /**
     * ɾ��Ӱ��
     * @param roomId
     * @param cinemaId
     * @return
     */
    boolean deleteRoom(int roomId,int cinemaId);
}
