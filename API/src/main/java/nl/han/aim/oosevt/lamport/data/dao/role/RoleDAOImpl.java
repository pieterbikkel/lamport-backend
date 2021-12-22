package nl.han.aim.oosevt.lamport.data.dao.role;

import nl.han.aim.oosevt.lamport.data.entity.Role;
import nl.han.aim.oosevt.lamport.data.util.DatabaseProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.han.aim.oosevt.lamport.data.util.DatabaseProperties.connectionString;

@Component
public class RoleDAOImpl implements RoleDAO {

    private static final Logger LOGGER = Logger.getLogger(RoleDAOImpl.class.getName());

    @Override
    public List<Role> getRoles() {
        try (
                Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
                PreparedStatement statement = connection.prepareStatement("CALL getRoles()");
                ResultSet resultSet = statement.executeQuery()
        ) {
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                roles.add(roleFromResultSet(resultSet, connection));
            }
            return roles;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getRoles::A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    private Role roleFromResultSet(ResultSet resultSet, Connection connection) throws SQLException {
        final int id = resultSet.getInt("role_id");
        final String name = resultSet.getString("role_name");

        Role role = new Role(id, name, getRolePermissions(connection, id));

        return role;
    }

    @Override
    public List<Role> getRolesBySearch(String query) {
        try (
            Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("CALL getRolesBySearch(?)")
        ) {
            statement.setString(1, query);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Role> roles = new ArrayList<>();
                while (resultSet.next()) {
                    roles.add(roleFromResultSet(resultSet, connection));
                }
                return roles;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getRolesBySearch::A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Role getRoleById(int id) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getRoleById(?)")) {
            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    final int roleId = resultSet.getInt("role_id");
                    return roleFromResultSet(resultSet, connection);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getRoleById::A database error occurred!", e);
        }

        return null;
    }

    private List<String> getRolePermissions(Connection connection, int roleId) {
        try(PreparedStatement statement = connection.prepareStatement("CALL getPermissionsByRoleId(?)")) {
            statement.setInt(1, roleId);
            try(ResultSet resultSet = statement.executeQuery()) {
                final ArrayList<String> toReturn = new ArrayList<>();
                while(resultSet.next()) {
                    toReturn.add(resultSet.getString("permission"));
                }
                return toReturn;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getRolePermissions::A database error occurred!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public void updateRole(int id, String name, List<String> allowedPermissions) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateRole(?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, String.join(",", allowedPermissions));

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "updateRole::A database error occurred!", e);
        }
    }

    @Override
    public void deleteRole(int roleId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteRole(?)")) {
            statement.setInt(1, roleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "deleteRole::A database error occured!", e);
        }

    }

    @Override
    public int getUserCountByRoleId(int roleId) {
        try (Connection connection = DriverManager.getConnection(DatabaseProperties.connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getUserCountByRoleId   (?)")) {

            statement.setInt(1, roleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return resultSet.getInt("count");
                }
                return 0;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getUsersByRoleId::A database error occurred!", e);
        }
        return 0;
    }

    @Override
    public void createRole(String name, List<String> allowedPermissions) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createRole(?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, String.join(",", allowedPermissions));

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "createRole::A database error occurred!", e);
        }
    }
}
