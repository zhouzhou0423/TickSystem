package service;

import vo.ArrangedInfo;
import vo.OrderInfo;
import vo.RoomInfo;
import vo.SitInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/5 14:55
 */
public interface SitService {
    ArrangedInfo queryArrangeByAId(int arrangeId);
    List<RoomInfo> queryRoomInfo(int cinemaId, int roomId);
    List<SitInfo> querySitInfo(int arrangeId, int roomId);
    boolean updateSitState(SitInfo sitInfo);
    boolean addOrder(OrderInfo orderInfo);
    List<OrderInfo> queryOrderList(int userId,int orderState);
    boolean deleteOrder(String orderId);
    boolean deleteSit(int arrangeId,String sit);
    boolean updateState(int arrangeId,String sitPosition);
}
