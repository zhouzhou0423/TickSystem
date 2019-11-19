package dao;

import vo.RoleInfo;

/**
 * @author zhou_zhou
 * @date 2019/4/14 17:51
 */
public interface RoleInfoDao {
    /**
     * ������ɫ
     * @param roleName
     * @param actorId
     * @param movieId
     * @param isImportant
     * @return
     */
    boolean addRole(String roleName,int actorId,int movieId,int isImportant);

    /**
     * ͨ����ɫID��ѯ��ɫ��Ϣ
     * @param roleId
     * @return
     */
    RoleInfo queryRoleById(int roleId);

    /**
     * �޸Ľ�ɫ��Ϣ
     * @param roleName
     * @param actorId
     * @param movieId
     * @param isImportant
     * @return
     */
    boolean updateRole(int roleId,String roleName,int actorId,int movieId,int isImportant);

    /**
     * ɾ����ɫ
     * @param roleId
     * @return
     * @throws Exception
     */
    boolean deleteRole(int roleId) throws Exception;
}
