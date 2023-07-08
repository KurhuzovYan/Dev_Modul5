package global.goit.services;

import global.goit.connection.Database;
import global.goit.dto.tables.Client;
import global.goit.dto.tables.Project;
import global.goit.dto.tables.ProjectWorker;
import global.goit.dto.tables.Worker;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static global.goit.dto.tables.Client.getClients;
import static global.goit.dto.tables.Project.getProjects;
import static global.goit.dto.tables.ProjectWorker.getProjectWorkers;
import static global.goit.dto.tables.Worker.getWorkers;
import static global.goit.util.Reader.getStringFromSQL;

public class DatabasePopulateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

    public static void main(String[] args) {

        try (Connection connection = Database.getInstance().getConnection()) {

            List<Worker> workers = getWorkers();
            PreparedStatement preparedStatementForWorkers = connection.prepareStatement(
                    "INSERT INTO worker (name, birthday, level, salary)\n VALUES (?, ?, ?, ?);");
            for (Worker worker : workers) {
                preparedStatementForWorkers.setString(1, worker.getName());
                preparedStatementForWorkers.setDate(2, worker.getBirthday());
                preparedStatementForWorkers.setString(3, worker.getLevel());
                preparedStatementForWorkers.setInt(4, worker.getSalary());
                preparedStatementForWorkers.addBatch();
            }

            List<Client> clients = getClients();
            PreparedStatement preparedStatementForClients = connection.prepareStatement(
                    "INSERT INTO client (name)\n VALUES (?);");
            for (Client client : clients) {
                preparedStatementForClients.setString(1, client.getName());
                preparedStatementForClients.addBatch();
            }

            List<Project> projects = getProjects();
            PreparedStatement preparedStatementForProjects = connection.prepareStatement(
                    "INSERT INTO project (client_id, start_date, finish_date) \n VALUES (?, ?, ?);");
            for (Project project : projects) {
                preparedStatementForProjects.setInt(1, project.getClientId());
                preparedStatementForProjects.setDate(2, project.getStartDate());
                preparedStatementForProjects.setDate(3, project.getFinishDate());
                preparedStatementForProjects.addBatch();
            }

            List<ProjectWorker> projectWorkers = getProjectWorkers();
            PreparedStatement preparedStatementProjectWorkers = connection.prepareStatement(
                    "INSERT INTO project_worker (project_id, worker_id) \n VALUES (?, ?);");
            for (ProjectWorker projectWorker : projectWorkers) {
                preparedStatementProjectWorkers.setInt(1, projectWorker.getProjectId());
                preparedStatementProjectWorkers.setInt(2, projectWorker.getWorkerId());
                preparedStatementProjectWorkers.addBatch();
            }
            try {
                preparedStatementForWorkers.executeBatch();
                preparedStatementForClients.executeBatch();
                preparedStatementForProjects.executeBatch();
                preparedStatementProjectWorkers.executeBatch();
                LOGGER.info("Initialized successful!");
            } catch (SQLException e) {
                LOGGER.error("Initialization failed", e);
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
}
