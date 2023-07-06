package global.goit.services;

import global.goit.connection.Database;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static global.goit.util.Reader.getStringFromSQL;

public class DatabaseInitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitService.class);

    public static void main(String[] args) throws SQLException {
        BasicConfigurator.configure();

        try (Connection connection = Database.getInstance().getConnection()) {
            String initializationDB = getStringFromSQL("sql/init_db.sql");
            PreparedStatement preparedStatement = connection.prepareStatement(initializationDB);
            try {
                preparedStatement.execute();
                LOGGER.info("Initialized successful!");
            } catch (SQLException e) {
                LOGGER.error("Wrong query...", e);
            }
        }
    }
}
