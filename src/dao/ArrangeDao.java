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
     * �༭����
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
     * ��ѯӰԺ����Ӱ��
     * @param cinemaId
     * @return
     * @throws Exception
     */
    List<RoomBaseInfo> findRoomByCinemaId(int cinemaId);

    /**
     * ��������
     * @param arrangeBaseInfo
     * @return
     */
    boolean addArrange(ArrangeBaseInfo arrangeBaseInfo);

    /**
     * ��ѯ�Ƿ���ڳ�ͻ����
     * @param cinemaId
     * @param roomId
     * @param startTime
     * @param endTime
     * @return
     */
    boolean queryRoomArrangeExist(int cinemaId,int roomId,String ondate,String startTime,String endTime);

    /**
     * ɾ������
     * @param arrangeId
     * @return
     */
    boolean deleteArrange(int arrangeId);

    /**
     * ����ӰƬid��ӰԺ��Ų�ѯ����
     * @param cinemaId
     * @param movieId
     * @param dateTime
     * @return
     */
    List<ArrangedInfo> queryArrangeByMovieId(int cinemaId, int movieId,String dateTime);
}
