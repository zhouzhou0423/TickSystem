package service;

import vo.OrderInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/20 16:08
 */
public interface OrderService {
    OrderInfo queryOrder(String orderId);
    boolean updateOrder(String orderId,String ticketCode,String payTime);
    List<OrderInfo> queryOrderList(int orderState);
}
