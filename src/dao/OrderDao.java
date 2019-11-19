package dao;

import vo.OrderInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/20 16:05
 */
public interface OrderDao {
    /**
     * 查询用户订单
     * @param orderId
     * @return
     */
    OrderInfo queryOrder(String orderId);

    /**
     * 支付成功后更新订单信息
     * @param orderId
     * @param payTime
     * @return
     */
    boolean updateOrder(String orderId,String ticketCode,String payTime);

    /**
     * 查询所有订单
     * @param orderState
     * @return
     */
    List<OrderInfo> queryOrderList(int orderState);
}
