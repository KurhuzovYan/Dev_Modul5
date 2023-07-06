package global.goit.connection;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public final static String DB_URL = "jdbc:postgresql://35.238.176.199:5432/database_yan";
    public final static String DB_USER = "dev12";
    public final static String DB_PASSWORD = "dev12thebest";
    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);
    private static Database instance;
    private static Connection CONNECTION;

    private Database() {
        BasicConfigurator.configure();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Don`t have a db driver!", e);
        }
        try {
            CONNECTION = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            LOGGER.info("Connection is successful");
        } catch (SQLException e) {
            LOGGER.error("Connection problem..", e);
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public static Connection getConnection() {
        return CONNECTION;
    }

}
