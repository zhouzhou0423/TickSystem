package service.impl;

import dao.SitInfoDao;
import dao.impl.SitInfoDaoImpl;
import service.SitService;
import vo.ArrangedInfo;
import vo.OrderInfo;
import vo.RoomInfo;
import vo.SitInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/5 14:55
 */
public class SitServiceImpl implements SitService {
    SitInfoDao dao = new SitInfoDaoImpl();
    @Override
    public ArrangedInfo queryArrangeByAId(int arrangeId) {
        return dao.queryArrangeByAId(arrangeId);
    }

    @Override
    public List<RoomInfo> queryRoomInfo(int cinemaId, int roomId) {
        return dao.queryRoomInfo(cinemaId, roomId);
    }

    @Override
    public List<SitInfo> querySitInfo(int arrangeId, int roomId) {
        return dao.querySitInfo(arrangeId, roomId);
    }

    @Override
    public boolean updateSitState(SitInfo sitInfo) {
        return dao.updateSitState(sitInfo);
    }

    @Override
    public boolean addOrder(OrderInfo orderInfo) {
        return dao.addOrder(orderInfo);
    }

    @Override
    public List<OrderInfo> queryOrderList(int userId, int orderState) {
        return dao.queryOrderList(userId,orderState);
    }

    @Override
    public boolean deleteOrder(String orderId) {
        return dao.deleteOrder(orderId);
    }

    @Override
    public boolean deleteSit(int arrangeId, String sit) {
        return dao.deleteSit(arrangeId, sit);
    }

    @Override
    public boolean updateState(int arrangeId, String sitPosition) {
        return dao.updateState(arrangeId, sitPosition);
    }

}
