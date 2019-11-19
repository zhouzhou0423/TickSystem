package dao;

import vo.ActorInfo;
import vo.CinemaInfo;
import vo.CommentsInfo;
import vo.RoleInfo;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/12 11:12
 */
public interface WebAdmDao {
    /**
     * 查询所有审核通过的影院
     * @return
     */
    List<CinemaInfo> findAllCinema();

    /**
     * 查询所有待审核的影院
     * @return
     */
    List<CinemaInfo> findNewCinema();

    /**
     * 影院审核
     * @param cinemaId
     * @return
     */
    boolean changeCinemaState(int cinemaId);

    /**
     * 查询所有评论
     * @return
     */
    List<CommentsInfo> findAllComment();

    /**
     * 查询所有演员
     * @return
     */
    List<ActorInfo> findAllActor();

    /**
     * 查询所有角色
     * @return
     */
    List<RoleInfo> findAllRole();
}
