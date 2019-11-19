package dao;

import vo.ActorInfo;
import vo.RoleInfo;

import java.util.List;


public interface ActorInfoDao {
	/**
	 * ����ӰƬid��ѯ��Ա��Ϣ
	 * @param movieId
	 * @return
	 * @throws Exception
	 */
	List<RoleInfo> findByMId(int movieId) throws Exception;

	/**
	 * ��ѯ�Ƿ����ͬ����Ա
	 * @return
	 * @throws Exception
	 */
	List<ActorInfo> findActorByName(String actorName) throws Exception;

	/**
	 * ������Ա
	 * @param actorInfo
	 * @return
	 * @throws Exception
	 */
	boolean addActor(ActorInfo actorInfo) throws Exception;

	/**
	 * ɾ����Ա
	 * @param actorId
	 * @return
	 * @throws Exception
	 */
	boolean deleteActor(int actorId) throws Exception;

	/**
	 * �޸���Ա��Ϣ
	 * @param photo
	 * @param actorId
	 * @return
	 * @throws Exception
	 */
	boolean editActor(String photo,int actorId) throws Exception;
}
