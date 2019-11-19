package dao;

import vo.OrderInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/20 16:05
 */
public interface OrderDao {
    /**
     * ��ѯ�û�����
     * @param orderId
     * @return
     */
    OrderInfo queryOrder(String orderId);

    /**
     * ֧���ɹ�����¶�����Ϣ
     * @param orderId
     * @param payTime
     * @return
     */
    boolean updateOrder(String orderId,String ticketCode,String payTime);

    /**
     * ��ѯ���ж���
     * @param orderState
     * @return
     */
    List<OrderInfo> queryOrderList(int orderState);
}
