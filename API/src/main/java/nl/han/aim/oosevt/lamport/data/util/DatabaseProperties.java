package nl.han.aim.oosevt.lamport.data.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private static String connectionString;

    public DatabaseProperties() {
        final Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));
            connectionString = properties.getProperty("connectionString");
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }


    public static String connectionString() {
        return connectionString;
    }
}