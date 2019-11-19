package dao;

import vo.MovieInfo;
import vo.MovieTypeInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/216:38
 */
public interface MovieManageDao {

    /**
     * �޸�ӰƬ״̬
     * @param movieid
     * @param cmstate
     * @return
     * @throws Exception
     */
    boolean changeState(int movieid, int cmstate, int cinemaid) throws Exception;
    /**
     * ��ѯ��Ӱ��������
     * @return
     * @throws Exception
     */
    List<MovieTypeInfo> queryMovieType() throws Exception;

    /**
     * ��վ����Ա����ӰƬ
     * @return
     * @throws Exception
     */
    boolean webAddMovie(String movieName,int timeLimit,String movieIntro,String releaseTime,String typeId,String movieImg,String nation) throws Exception;

    /**
     * ����ID��ѯӰƬ
     * @param movieId
     * @return
     */
    MovieInfo queryMovieByMId(int movieId);

    /**
     * �޸�ӰƬ��Ϣ
     * @param movieName
     * @param movieIntro
     * @param releaseTime
     * @param movieImg
     * @param nation
     * @return
     * @throws Exception
     */
    boolean updateMovieInfo(String movieName,String movieIntro,String releaseTime,String movieImg,String nation,int movieId) throws Exception;

    /**
     * ɾ��ӰƬ
     * @param movieId
     * @return
     */
    boolean deleteMovie(int movieId);
}
