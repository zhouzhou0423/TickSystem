package dao;

import vo.RoleInfo;

/**
 * @author zhou_zhou
 * @date 2019/4/14 17:51
 */
public interface RoleInfoDao {
    /**
     * 新增角色
     * @param roleName
     * @param actorId
     * @param movieId
     * @param isImportant
     * @return
     */
    boolean addRole(String roleName,int actorId,int movieId,int isImportant);

    /**
     * 通过角色ID查询角色信息
     * @param roleId
     * @return
     */
    RoleInfo queryRoleById(int roleId);

    /**
     * 修改角色信息
     * @param roleName
     * @param actorId
     * @param movieId
     * @param isImportant
     * @return
     */
    boolean updateRole(int roleId,String roleName,int actorId,int movieId,int isImportant);

    /**
     * 删除角色
     * @param roleId
     * @return
     * @throws Exception
     */
    boolean deleteRole(int roleId) throws Exception;
}
