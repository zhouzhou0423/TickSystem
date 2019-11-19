package service;

import vo.RoleInfo;

/**
 * @author zhou_zhou
 * @date 2019/4/15 9:24
 */
public interface RoleInfoService {
    boolean addRole(String roleName,int actorId,int movieId,int isImportant);
    RoleInfo queryRoleById(int roleId);
    boolean updateRole(int roleId,String roleName,int actorId,int movieId,int isImportant);
    boolean deleteRole(int roleId) throws Exception;
}
