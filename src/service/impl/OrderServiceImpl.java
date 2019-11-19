package service.impl;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import service.OrderService;
import vo.OrderInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/20 16:09
 */
public class OrderServiceImpl implements OrderService {
    OrderDao dao = new OrderDaoImpl();
    @Override
    public OrderInfo queryOrder(String orderId) {
        return dao.queryOrder(orderId);
    }

    @Override
    public boolean updateOrder(String orderId, String ticketCode,String payTime) {
        return dao.updateOrder(orderId,ticketCode,payTime);
    }

    @Override
    public List<OrderInfo> queryOrderList(int orderState) {
        return dao.queryOrderList(orderState);
    }
}
