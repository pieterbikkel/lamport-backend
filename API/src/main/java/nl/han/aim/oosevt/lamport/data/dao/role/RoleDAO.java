package nl.han.aim.oosevt.lamport.data.dao.role;

import nl.han.aim.oosevt.lamport.data.entity.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getRoles();
    List<Role> getRolesBySearch(String query);
    Role getRoleById(int id);
    void updateRole(int id, String name, List<String> allowedPermissions);
    void deleteRole(int id);
    int getUserCountByRoleId(int roleId);
    void createRole(String name, List<String> allowedPermissions);
}
