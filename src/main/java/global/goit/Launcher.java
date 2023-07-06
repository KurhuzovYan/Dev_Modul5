package global.goit;

import global.goit.connection.Database;
import global.goit.services.DatabaseQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class Launcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) throws SQLException {
        LOGGER.info(new DatabaseQueryService().findMaxProjectsClient().toString());
        LOGGER.info(new DatabaseQueryService().findLongestProject().toString());
        LOGGER.info(new DatabaseQueryService().findMaxSalary().toString());
        new DatabaseQueryService().findYoungestAndOldest().stream()
                .forEach(person -> LOGGER.info(person.toString()));
        new DatabaseQueryService().findProjectPrices().stream()
                .forEach(project -> LOGGER.info(project.toString()));
    }
}
