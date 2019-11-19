package dao;

import vo.ArrangeBaseInfo;
import vo.ArrangedInfo;
import vo.RoomBaseInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/3/3117:39
 */
public interface ArrangeDao {
    /**
     * 编辑场次
     * @param arrangeId
     * @param roomId
     * @param startTime
     * @param endTime
     * @param moviePrice
     * @return
     * @throws Exception
     */
    boolean arrangeEdit(int arrangeId,int roomId, String startTime,String endTime,double moviePrice) throws Exception;

    /**
     * 查询影院所有影厅
     * @param cinemaId
     * @return
     * @throws Exception
     */
    List<RoomBaseInfo> findRoomByCinemaId(int cinemaId);

    /**
     * 新增场次
     * @param arrangeBaseInfo
     * @return
     */
    boolean addArrange(ArrangeBaseInfo arrangeBaseInfo);

    /**
     * 查询是否存在冲突场次
     * @param cinemaId
     * @param roomId
     * @param startTime
     * @param endTime
     * @return
     */
    boolean queryRoomArrangeExist(int cinemaId,int roomId,String ondate,String startTime,String endTime);

    /**
     * 删除场次
     * @param arrangeId
     * @return
     */
    boolean deleteArrange(int arrangeId);

    /**
     * 根据影片id和影院编号查询场次
     * @param cinemaId
     * @param movieId
     * @param dateTime
     * @return
     */
    List<ArrangedInfo> queryArrangeByMovieId(int cinemaId, int movieId,String dateTime);
}
