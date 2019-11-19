package dao;

import vo.ActorInfo;
import vo.RoleInfo;

import java.util.List;


public interface ActorInfoDao {
	/**
	 * 按照影片id查询演员信息
	 * @param movieId
	 * @return
	 * @throws Exception
	 */
	List<RoleInfo> findByMId(int movieId) throws Exception;

	/**
	 * 查询是否存在同名演员
	 * @return
	 * @throws Exception
	 */
	List<ActorInfo> findActorByName(String actorName) throws Exception;

	/**
	 * 新增演员
	 * @param actorInfo
	 * @return
	 * @throws Exception
	 */
	boolean addActor(ActorInfo actorInfo) throws Exception;

	/**
	 * 删除演员
	 * @param actorId
	 * @return
	 * @throws Exception
	 */
	boolean deleteActor(int actorId) throws Exception;

	/**
	 * 修改演员信息
	 * @param photo
	 * @param actorId
	 * @return
	 * @throws Exception
	 */
	boolean editActor(String photo,int actorId) throws Exception;
}
