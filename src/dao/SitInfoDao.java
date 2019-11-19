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
     * ����ӰԺID�ͳ���Id��ѯ��ѡ���ӰƬ��Ϣ
     * @param arrangeId
     * @return
     */
    ArrangedInfo queryArrangeByAId(int arrangeId);

    /**
     * ��ѯӰ����Ϣ
     * @param cinemaId
     * @param roomId
     * @return
     */
    List<RoomInfo> queryRoomInfo(int cinemaId, int roomId);

    /**
     * ��ѯĳ�������ѱ�ѡ����ѱ������λ��
     * @param arrangeId
     * @param roomId
     * @return
     */
    List<SitInfo> querySitInfo(int arrangeId, int roomId);

    /**
     * ������λ״̬
     * @param sitInfo
     * @return
     */
    boolean updateSitState(SitInfo sitInfo);

    /**
     * ���ɶ���
     * @param orderInfo
     * @return
     */
    boolean addOrder(OrderInfo orderInfo);

    /**
     * ��ѯ�û�����
     * @param userId
     * @return
     */
    List<OrderInfo> queryOrderList(int userId,int orderState);

    /**
     * ɾ������
     * @param orderId
     * @return
     */
    boolean deleteOrder(String orderId);

    /**
     * �ͷ���λ
     * @param arrangeId
     * @param sit
     * @return
     */
    boolean deleteSit(int arrangeId,String sit);

    /**
     * �ɹ������ı���λ״̬
     * @param arrangeId
     * @param sitPosition
     * @return
     */
    boolean updateState(int arrangeId,String sitPosition);
}
