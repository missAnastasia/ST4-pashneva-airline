package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.AircraftDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Aircraft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements AircraftDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
 */
public class MysqlAircraftDAO implements AircraftDAO {

    /**
     * String fields which contain sql queries to table
     * aircraft of MySQL database.
     */
    private static final String GET_AIRCRAFT_BY_ID = "SELECT * FROM aircraft WHERE id=?";
    private static final String GET_ALL_AIRCRAFT = "SELECT * FROM aircraft";

    /**
     * String fields which contain column names of table aircraft.
     */
    private static final String ENTITY_ID = "id";
    private static final String AIRCRAFT_NAME = "type";

    @Override
    public Aircraft read(int id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_AIRCRAFT_BY_ID);

        int k = 1;
        statement.setInt(k++, id);

        ResultSet resultSet = statement.executeQuery();

        Aircraft aircraft = null;
        if (resultSet.next()) {
            aircraft = extractAircraft(resultSet);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return aircraft;
    }

    @Override
    public List<Aircraft> readAll() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_AIRCRAFT);

        List<Aircraft> aircraftList = new ArrayList<>();
        while (resultSet.next()) {
            Aircraft aircraft = extractAircraft(resultSet);
            aircraftList.add(aircraft);
        }
        DBConnection.getInstance().close(connection, statement, resultSet);
        return aircraftList;
    }

    private Aircraft extractAircraft(ResultSet resultSet) throws Exception {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(resultSet.getInt(ENTITY_ID));
        aircraft.setTypeName(resultSet.getString(AIRCRAFT_NAME));
        return aircraft;
    }
}
