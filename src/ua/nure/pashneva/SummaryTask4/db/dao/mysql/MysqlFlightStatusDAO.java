package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.FlightStatusDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.FlightStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlFlightStatusDAO implements FlightStatusDAO {

    /**
     * String fields which contain sql queries to tables
     * tables flight_statuses and flight_statuses_lang of MySQL database.
     */
    private static final String GET_FLIGHT_STATUS_BY_ID = "SELECT * FROM (flight_statuses f_s INNER JOIN flight_statuses_lang f_s_l ON f_s.id=f_s_l.flight_status_id) WHERE f_s.id=? AND f_s_l.lang_id=?";
    private static final String GET_ALL_STATUSES = "SELECT * FROM (flight_statuses f_s INNER JOIN flight_statuses_lang f_s_l ON f_s.id=f_s_l.flight_status_id) WHERE f_s_l.lang_id=?";

    /**
     * String fields which contain column names of tables flight_statuses and flight_statuses_lang.
     */
    private static final String ENTITY_ID = "f_s.id";
    private static final String FLIGHT_STATUS_NAME = "f_s_l.name";

    @Override
    public FlightStatus read(Language language, Integer id) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        FlightStatus flightStatus = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_FLIGHT_STATUS_BY_ID);

            int k = 1;
            statement.setInt(k++, id);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                flightStatus = extractFlightStatus(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return flightStatus;
    }

    @Override
    public List<FlightStatus> readAll(Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<FlightStatus> statuses = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL_STATUSES);

            int k = 1;
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                FlightStatus flightStatus = extractFlightStatus(resultSet);
                statuses.add(flightStatus);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return statuses;
    }

    private static FlightStatus extractFlightStatus(ResultSet resultSet) throws DBException {
        FlightStatus flightStatus = new FlightStatus();
        try {
            flightStatus.setId(resultSet.getInt(ENTITY_ID));
            flightStatus.setName(resultSet.getString(FLIGHT_STATUS_NAME));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return flightStatus;
    }
}
