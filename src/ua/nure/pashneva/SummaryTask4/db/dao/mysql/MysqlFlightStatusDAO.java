package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.FlightStatusDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.FlightStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements FlightStatusDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 */
public class MysqlFlightStatusDAO implements FlightStatusDAO {

    /**
     * String fields which contain sql queries to tables
     * flight_statuses and flight_statuses_lang of MySQL database.
     */
    private static final String GET_FLIGHT_STATUS_BY_ID = "SELECT * FROM (flight_statuses f_s INNER JOIN flight_statuses_lang f_s_l ON f_s.id=f_s_l.flight_status_id) WHERE f_s.id=? AND f_s_l.lang_id=?";
    private static final String GET_ALL_STATUSES = "SELECT * FROM (flight_statuses f_s INNER JOIN flight_statuses_lang f_s_l ON f_s.id=f_s_l.flight_status_id) WHERE f_s_l.lang_id=?";

    /**
     * String fields which contain column names of tables flight_statuses and flight_statuses_lang.
     */
    private static final String ENTITY_ID = "f_s.id";
    private static final String FLIGHT_STATUS_NAME = "f_s_l.name";

    @Override
    public FlightStatus read(Language language, int id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_FLIGHT_STATUS_BY_ID);

        int k = 1;
        statement.setInt(k++, id);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        FlightStatus flightStatus = null;
        if (resultSet.next()) {
            flightStatus = extractFlightStatus(resultSet);
        }
        DBConnection.getInstance().close(connection, statement, resultSet);
        return flightStatus;
    }

    @Override
    public List<FlightStatus> readAll(Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_STATUSES);

        int k = 1;
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<FlightStatus> statuses = new ArrayList<>();
        while (resultSet.next()) {
            FlightStatus flightStatus = extractFlightStatus(resultSet);
            statuses.add(flightStatus);
        }
        DBConnection.getInstance().close(connection, statement, resultSet);
        return statuses;
    }

    /**
     * Private method for obtaining flight status data from ResultSet.
     *
     * @param resultSet instance of ResultSet which contains selected data from tables
     *                 flight_statuses and flight_statuses_lang.
     * @return object of FlightStatus which contains data obtained from ResultSet.
     * @throws Exception
     */
    private static FlightStatus extractFlightStatus(ResultSet resultSet) throws Exception {
        FlightStatus flightStatus = new FlightStatus();
        flightStatus.setId(resultSet.getInt(ENTITY_ID));
        flightStatus.setName(resultSet.getString(FLIGHT_STATUS_NAME));
        return flightStatus;
    }
}
