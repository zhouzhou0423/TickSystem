package service.impl;

import dao.ActorInfoDao;
import dao.impl.ActorInfoDaoImpl;
import service.ActorInfoService;
import vo.ActorInfo;
import vo.RoleInfo;

import java.util.List;

public class ActorInfoServiceImpl implements ActorInfoService {
	ActorInfoDao dao=new ActorInfoDaoImpl();
	@Override
	public List<RoleInfo> findByMId(int movieId) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByMId(movieId);
	}

	@Override
	public List<ActorInfo> findActorByName(String actorName) throws Exception {
		return dao.findActorByName(actorName);
	}

	@Override
	public boolean addActor(ActorInfo actorInfo) throws Exception {
		return dao.addActor(actorInfo);
	}

	@Override
	public boolean deleteActor(int actorId) throws Exception {
		return dao.deleteActor(actorId);
	}

	@Override
	public boolean editActor(String photo, int actorId) throws Exception {
		return dao.editActor(photo, actorId);
	}

}
