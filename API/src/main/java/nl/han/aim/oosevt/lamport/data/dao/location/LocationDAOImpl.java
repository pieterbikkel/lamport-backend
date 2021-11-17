package nl.han.aim.oosevt.lamport.data.dao.location;

import nl.han.aim.oosevt.lamport.data.entity.Location;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static nl.han.aim.oosevt.lamport.data.util.DatabaseProperties.connectionString;

@Component
public class LocationDAOImpl implements LocationDAO {

    @Override
    public void createLocation(String name, int delay, double longitude, double latitude, int radius, int areaId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL createLocation(?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, name);
            statement.setInt(2, delay);
            statement.setDouble(3, longitude);
            statement.setDouble(4, latitude);
            statement.setInt(5, radius);
            statement.setInt(6, areaId);

            statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public Location getLocationById(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getLocation(?)")) {
            statement.setInt(1, locationId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Location(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("delay"),
                        resultSet.getDouble("longitude"),
                        resultSet.getDouble("latitude"),
                        resultSet.getInt("radius"),
                        resultSet.getInt("areaId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Location> getLocations() {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL getLocations()");
             ResultSet resultSet = statement.executeQuery()) {
            List<Location> foundLocations = new ArrayList<>();
            while (resultSet.next()) {
                final Location foundLocation = new Location(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("delay"),
                        resultSet.getDouble("longitude"),
                        resultSet.getDouble("latitude"),
                        resultSet.getInt("radius"),
                        resultSet.getInt("areaId"));
                foundLocations.add(foundLocation);
            }
            return foundLocations;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateLocation(int locationId, String name, int delay, double longitude, double latitude, int radius, int areaId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL updateLocation(?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, locationId);
            statement.setString(2, name);
            statement.setInt(3, delay);
            statement.setDouble(4, longitude);
            statement.setDouble(5, latitude);
            statement.setInt(6, radius);
            statement.setInt(7, areaId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLocation(int locationId) {
        try (Connection connection = DriverManager.getConnection(connectionString());
             PreparedStatement statement = connection.prepareStatement("CALL deleteLocation(?)")) {
            statement.setInt(1, locationId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
