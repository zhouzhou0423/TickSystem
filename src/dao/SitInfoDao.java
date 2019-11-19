package dao;

import vo.ArrangedInfo;
import vo.OrderInfo;
import vo.RoomInfo;
import vo.SitInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/5 14:47
 */
public interface SitInfoDao {
    /**
     * 根据影院ID和场次Id查询祖选择的影片信息
     * @param arrangeId
     * @return
     */
    ArrangedInfo queryArrangeByAId(int arrangeId);

    /**
     * 查询影厅信息
     * @param cinemaId
     * @param roomId
     * @return
     */
    List<RoomInfo> queryRoomInfo(int cinemaId, int roomId);

    /**
     * 查询某场次中已被选择或已被购买的位置
     * @param arrangeId
     * @param roomId
     * @return
     */
    List<SitInfo> querySitInfo(int arrangeId, int roomId);

    /**
     * 更新座位状态
     * @param sitInfo
     * @return
     */
    boolean updateSitState(SitInfo sitInfo);

    /**
     * 生成订单
     * @param orderInfo
     * @return
     */
    boolean addOrder(OrderInfo orderInfo);

    /**
     * 查询用户订单
     * @param userId
     * @return
     */
    List<OrderInfo> queryOrderList(int userId,int orderState);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    boolean deleteOrder(String orderId);

    /**
     * 释放座位
     * @param arrangeId
     * @param sit
     * @return
     */
    boolean deleteSit(int arrangeId,String sit);

    /**
     * 成功付款后改变座位状态
     * @param arrangeId
     * @param sitPosition
     * @return
     */
    boolean updateState(int arrangeId,String sitPosition);
}
