package service.impl;

import dao.RoleInfoDao;
import dao.impl.RoleInfoDaoImpl;
import service.RoleInfoService;
import vo.RoleInfo;

/**
 * @author zhou_zhou
 * @date 2019/4/15 9:24
 */
public class RoleInfoServiceImpl implements RoleInfoService {
    RoleInfoDao dao = new RoleInfoDaoImpl();
    @Override
    public boolean addRole(String roleName, int actorId, int movieId, int isImportant) {
        return dao.addRole(roleName, actorId, movieId, isImportant);
    }

    @Override
    public RoleInfo queryRoleById(int roleId) {
        return dao.queryRoleById(roleId);
    }

    @Override
    public boolean updateRole(int roleId, String roleName, int actorId, int movieId, int isImportant) {
        return dao.updateRole(roleId, roleName, actorId, movieId, isImportant);
    }

    @Override
    public boolean deleteRole(int roleId) throws Exception {
        return dao.deleteRole(roleId);
    }
}
