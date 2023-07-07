package global.goit.services;

import global.goit.connection.Database;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static global.goit.util.Reader.getStringFromSQL;

public class DatabasePopulateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

    public static void main(String[] args) throws SQLException {

        try (Connection connection = Database.getInstance().getConnection()) {
            String injection = "1' or 1 = '1";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client WHERE client_id = '" + injection + "'");
            while (resultSet.next()){
                 LOGGER.info(resultSet.getString("name"));
            }
            String populateDB = getStringFromSQL("sql/populate_db.sql");
            PreparedStatement preparedStatement = connection.prepareStatement(populateDB);
            try {
                preparedStatement.execute();
                LOGGER.info("Initialized successful!");
            } catch (SQLException e) {
                LOGGER.error("Table already exist", e);
            }
        }
    }
}
