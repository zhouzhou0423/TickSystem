package service;

import vo.ActorInfo;
import vo.RoleInfo;

import java.util.List;

public interface ActorInfoService {
	List<RoleInfo> findByMId(int movieId) throws Exception;
	List<ActorInfo> findActorByName(String actorName) throws Exception;
	boolean addActor(ActorInfo actorInfo) throws Exception;
	boolean deleteActor(int actorId) throws Exception;
	boolean editActor(String photo,int actorId) throws Exception;
}
