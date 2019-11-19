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
     * 修改影片状态
     * @param movieid
     * @param cmstate
     * @return
     * @throws Exception
     */
    boolean changeState(int movieid, int cmstate, int cinemaid) throws Exception;
    /**
     * 查询电影所有类型
     * @return
     * @throws Exception
     */
    List<MovieTypeInfo> queryMovieType() throws Exception;

    /**
     * 网站管理员新增影片
     * @return
     * @throws Exception
     */
    boolean webAddMovie(String movieName,int timeLimit,String movieIntro,String releaseTime,String typeId,String movieImg,String nation) throws Exception;

    /**
     * 根据ID查询影片
     * @param movieId
     * @return
     */
    MovieInfo queryMovieByMId(int movieId);

    /**
     * 修改影片信息
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
     * 删除影片
     * @param movieId
     * @return
     */
    boolean deleteMovie(int movieId);
}
