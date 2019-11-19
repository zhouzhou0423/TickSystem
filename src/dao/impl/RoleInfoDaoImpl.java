package dao.impl;

import dao.RoleInfoDao;
import util.BaseDao;
import vo.RoleInfo;

/**
 * @author zhou_zhou
 * @date 2019/4/15 9:22
 */
public class RoleInfoDaoImpl extends BaseDao implements RoleInfoDao {
    @Override
    public boolean addRole(String roleName, int actorId, int movieId, int isImportant) {
        String sql = "INSERT INTO ROLEINFO (ROLENAME,ACTORID,MOVIEID,ISIMPORTANT) VALUES (?,?,?,?)";
        return update(sql,roleName,actorId,movieId,isImportant);
    }

    @Override
    public RoleInfo queryRoleById(int roleId) {
        String sql = "SELECT R.*,A.ACTORNAME,A.PHOTO,M.MOVIENAME FROM ROLEINFO R,MOVIEINFO M,ACTORINFO A WHERE R.MOVIEID=M.MOVIEID AND R.ACTORID=A.ACTORID AND R.ROLEID=?";
        return queryByOne(sql,RoleInfo.class,roleId);
    }

    @Override
    public boolean updateRole(int roleId, String roleName, int actorId, int movieId, int isImportant) {
        String sql = "UPDATE ROLEINFO SET ROLENAME=?,ACTORID=?,MOVIEID=?,ISIMPORTANT=? WHERE ROLEID=?";
        return update(sql,roleName,actorId,movieId,isImportant,roleId);
    }

    @Override
    public boolean deleteRole(int roleId) throws Exception {
        String sql="DELETE FROM ROLEINFO WHERE ROLEID=?";
        return update(sql,roleId);
    }
}
