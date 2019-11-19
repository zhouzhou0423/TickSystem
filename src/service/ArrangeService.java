package service;

import vo.ArrangeBaseInfo;
import vo.ArrangedInfo;
import vo.RoomBaseInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/3/3116:02
 */
public interface ArrangeService {
    ArrangedInfo findArrangeBy(int cinemaid,int arrangeId) throws Exception;
    boolean arrangeEdit(int arrangeId,int roomId, String startTime,String endTime,double moviePrice) throws Exception;
    List<RoomBaseInfo> findRoomByCinemaId(int cinemaId);
    boolean addArrange(ArrangeBaseInfo arrangeBaseInfo);
    boolean queryRoomArrangeExist(int cinemaId,int roomId,String ondate,String startTime,String endTime);
    boolean deleteArrange(int arrangeId);
    List<ArrangedInfo> queryArrangeByMovieId(int cinemaId, int movieId,String dateTime);
}
