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
     * ��ѯ�������ͨ����ӰԺ
     * @return
     */
    List<CinemaInfo> findAllCinema();

    /**
     * ��ѯ���д���˵�ӰԺ
     * @return
     */
    List<CinemaInfo> findNewCinema();

    /**
     * ӰԺ���
     * @param cinemaId
     * @return
     */
    boolean changeCinemaState(int cinemaId);

    /**
     * ��ѯ��������
     * @return
     */
    List<CommentsInfo> findAllComment();

    /**
     * ��ѯ������Ա
     * @return
     */
    List<ActorInfo> findAllActor();

    /**
     * ��ѯ���н�ɫ
     * @return
     */
    List<RoleInfo> findAllRole();
}
