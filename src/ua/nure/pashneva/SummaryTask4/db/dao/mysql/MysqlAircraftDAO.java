package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.AircraftDAO;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Aircraft;
import ua.nure.pashneva.SummaryTask4.db.entity.Post;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.*;
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
    public Aircraft read(int id) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Aircraft aircraft = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_AIRCRAFT_BY_ID);

            int k = 1;
            statement.setInt(k++, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                aircraft = extractAircraft(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return aircraft;
    }

    @Override
    public List<Aircraft> readAll() throws DBException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Aircraft> aircraftList = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_AIRCRAFT);

            while (resultSet.next()) {
                Aircraft aircraft = extractAircraft(resultSet);
                aircraftList.add(aircraft);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return aircraftList;
    }

    private Aircraft extractAircraft(ResultSet resultSet) throws DBException {
        Aircraft aircraft = new Aircraft();
        try {
            aircraft.setId(resultSet.getInt(ENTITY_ID));
            aircraft.setTypeName(resultSet.getString(AIRCRAFT_NAME));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return aircraft;
    }
}
