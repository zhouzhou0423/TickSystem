package service.impl;

import dao.ArrangeDao;
import dao.impl.ArrangeDaoImpl;
import service.ArrangeService;
import service.ManageService;
import vo.ArrangeBaseInfo;
import vo.ArrangedInfo;
import vo.RoomBaseInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/3/3116:09
 */
public class ArrangeServiceImpl implements ArrangeService {
    ArrangeDao arrangeDao=new ArrangeDaoImpl();
    @Override
    public ArrangedInfo findArrangeBy(int cinemaid,int arrangeId) throws Exception {
        ManageService manageService=new ManageServiceImpl();
        List<ArrangedInfo> list=manageService.findArrangeInfo(cinemaid);
        ArrangedInfo arrangedInfo=null;
        for (int i = 0; i <list.size() ; i++) {
            if(list.get(i).getArrangeid()==arrangeId){
                arrangedInfo=list.get(i);
                break;
            }
        }
        return arrangedInfo;
    }

    @Override
    public boolean arrangeEdit(int arrangeId, int roomId, String startTime, String endTime, double moviePrice) throws Exception {
        return arrangeDao.arrangeEdit(arrangeId,roomId,startTime,endTime,moviePrice);
    }

    @Override
    public List<RoomBaseInfo> findRoomByCinemaId(int cinemaId) {
        return arrangeDao.findRoomByCinemaId(cinemaId);
    }

    @Override
    public boolean addArrange(ArrangeBaseInfo arrangeBaseInfo) {
        return arrangeDao.addArrange(arrangeBaseInfo);
    }

    @Override
    public boolean queryRoomArrangeExist(int cinemaId, int roomId, String ondate,String startTime, String endTime) {
        return arrangeDao.queryRoomArrangeExist(cinemaId,roomId,ondate,startTime,endTime);
    }

    @Override
    public boolean deleteArrange(int arrangeId) {
        return arrangeDao.deleteArrange(arrangeId);
    }

    @Override
    public List<ArrangedInfo> queryArrangeByMovieId(int cinemaId, int movieId,String dateTime) {
        return arrangeDao.queryArrangeByMovieId(cinemaId,movieId,dateTime);
    }
}
