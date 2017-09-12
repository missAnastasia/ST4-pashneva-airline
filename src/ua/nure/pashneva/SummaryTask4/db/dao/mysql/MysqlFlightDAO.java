package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.dao.FlightDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.*;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements FlightDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
 */
public class MysqlFlightDAO implements FlightDAO {

    private static final Logger LOG = Logger.getLogger(MysqlFlightDAO.class);
    /**
     * String fields which contain sql queries to tables flights,
     * flights_lang, flight_statuses, flight_statuses_lang,
     * brigades_stuff, aircraft of MySQL database.
     */
    private static final String GET_ALL_FLIGHTS = "SELECT * FROM ((flights f INNER JOIN flights_lang f_l ON f.id=f_l.flight_id) INNER JOIN aircraft a ON f.aircraft_id=a.id) WHERE f_l.lang_id=?";
    private static final String GET_FLIGHT_BY_ID = "SELECT * FROM ((flights f INNER JOIN flights_lang f_l ON f.id=f_l.flight_id) INNER JOIN aircraft a ON f.aircraft_id=a.id) WHERE f.id=? AND f_l.lang_id=?";
    private static final String GET_FLIGHT_BY_NUMBER = "SELECT * FROM ((flights f INNER JOIN flights_lang f_l ON f.id=f_l.flight_id) INNER JOIN aircraft a ON f.aircraft_id=a.id) WHERE f.number=? AND f_l.lang_id=?";
    private static final String GET_FLIGHT_BY_DEPARTURE_POINT = "SELECT * FROM ((flights f INNER JOIN flights_lang f_l ON f.id=f_l.flight_id) INNER JOIN aircraft a ON f.aircraft_id=a.id) WHERE f_l.departure_point=? AND f_l.lang_id=?";
    private static final String GET_FLIGHT_BY_ARRIVAL_POINT = "SELECT * FROM ((flights f INNER JOIN flights_lang f_l ON f.id=f_l.flight_id) INNER JOIN aircraft a ON f.aircraft_id=a.id) WHERE f_l.arrival_point=? AND f_l.lang_id=?";
    private static final String GET_FLIGHT_BY_BRIGADE = "SELECT * FROM ((flights f INNER JOIN flights_lang f_l ON f.id=f_l.flight_id) INNER JOIN aircraft a ON f.aircraft_id=a.id) WHERE f.brigade_id=? AND f_l.lang_id=?";
    private static final String GET_FLIGHT_BY_DATE = "SELECT * FROM ((flights f INNER JOIN flights_lang f_l ON f.id=f_l.flight_id) INNER JOIN aircraft a ON f.aircraft_id=a.id) WHERE f.departure_date=? AND f_l.lang_id=?";
    private static final String GET_FLIGHT_BY_STATUS = "SELECT * FROM ((flights f INNER JOIN flights_lang f_l ON f.id=f_l.flight_id) INNER JOIN aircraft a ON f.aircraft_id=a.id) WHERE f.flight_status_id=? AND f_l.lang_id=?";
    private static final String ADD_FLIGHT = "INSERT INTO flights VALUE(DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_FLIGHT_LANG = "INSERT INTO flights_lang VALUE(DEFAULT, ?, ?, ?, ?)";
    private static final String UPDATE_FLIGHT_BY_ID = "UPDATE flights SET number=?, departure_date=?, departure_time=?, flight_status_id=?, aircraft_id=?, brigade_id=? WHERE id=?";
    private static final String UPDATE_FLIGHT_LANG_BY_ID = "UPDATE flights_lang SET departure_point=?, arrival_point=? WHERE flight_id=? AND lang_id=?";
    private static final String UPDATE_FLIGHT_STATUS_BY_ID = "UPDATE flights SET flight_status_id=? WHERE id=?";
    private static final String UPDATE_FLIGHT_BRIGADE_BY_ID = "UPDATE flights SET brigade_id=? WHERE id=?";
    private static final String DELETE_FLIGHT_BY_ID = "DELETE FROM flights WHERE id=?";
    private static final String DELETE_FLIGHT_LANG_BY_ID = "DELETE FROM flights_lang WHERE flight_id=?";

    /**
     * String fields which contain column names of table flights,
     * flights_lang, flight_statuses, flight_statuses_lang.
     */
    private static final String ENTITY_ID = "f.id";
    private static final String FLIGHT_NUMBER = "f.number";
    private static final String FLIGHT_DEPARTURE_DATE = "f.departure_date";
    private static final String FLIGHT_DEPARTURE_TIME = "f.departure_time";
    private static final String FLIGHT_STATUS_ID = "f.flight_status_id";
    private static final String FLIGHT_AIRCRAFT_ID = "f.aircraft_id";
    private static final String FLIGHT_BRIGADE_ID = "f.brigade_id";
    private static final String FLIGHT_LANG_DEPARTURE_POINT = "f_l.departure_point";
    private static final String FLIGHT_LANG_ARRIVAL_POINT = "f_l.arrival_point";

    @Override
    public boolean create(Flight flight, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getInstance().getConnectionWithoutAutoCommit();
            LOG.debug("statement.executeUpdate() > 0");

            if (flight.getId() != 0) {
                LOG.debug("statement.executeUpdate() > 0");
                return create(connection, flight, language);
            }

            statement = connection.prepareStatement(ADD_FLIGHT,
                    Statement.RETURN_GENERATED_KEYS);
            LOG.debug("statement.executeUpdate() > 0");

            int k = 1;
            statement.setString(k++, flight.getNumber());
            statement.setString(k++, flight.getDate());
            statement.setString(k++, flight.getTime());
            statement.setInt(k++, flight.getFlightStatus().getId());
            statement.setInt(k++, flight.getAircraft().getId());
            statement.setNull(k++, java.sql.Types.INTEGER);
            LOG.debug("statement.executeUpdate() > 0");

            if (statement.executeUpdate() > 0) {
                LOG.debug("statement.executeUpdate() > 0");
                MysqlDAOFactory.setGeneratedId(flight, statement);
                return create(connection, flight, language);
            }
            return false;
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    private boolean create(Connection connection, Flight flight, Language language) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(ADD_FLIGHT_LANG);
        int k = 1;
        statement.setInt(k++, flight.getId());
        statement.setInt(k++, language.getId());
        statement.setString(k++, flight.getDeparturePoint());
        statement.setString(k++, flight.getArrivalPoint());

        if (statement.executeUpdate() > 0) {
            connection.commit();
            return true;
        }
        DBConnection.getInstance().rollback(connection);
        return false;
    }

    @Override
    public Flight read(int id, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_FLIGHT_BY_ID);

            int k = 1;
            statement.setInt(k++, id);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                flight = extractFlight(resultSet, language);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return flight;
    }

    @Override
    public Flight readByNumber(String number, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Flight flight = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_FLIGHT_BY_NUMBER);

            int k = 1;
            statement.setString(k++, number);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                flight = extractFlight(resultSet, language);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return flight;
    }

    @Override
    public List<Flight> readByDeparturePoint(String departure, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_FLIGHT_BY_DEPARTURE_POINT);

            int k = 1;
            statement.setString(k++, departure);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Flight flight = extractFlight(resultSet, language);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return flights;
    }

    @Override
    public List<Flight> readByArrivalPoint(String arrival, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_FLIGHT_BY_ARRIVAL_POINT);

            int k = 1;
            statement.setString(k++, arrival);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Flight flight = extractFlight(resultSet, language);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return flights;
    }

    @Override
    public List<Flight> readByDate(String date, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_FLIGHT_BY_DATE);

            int k = 1;
            statement.setString(k++, date);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Flight flight = extractFlight(resultSet, language);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return flights;
    }

   /* @Override
    public List<Flight> readByTime(String time, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_FLIGHT_BY_);

            int k = 1;
            statement.setString(k++, time);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Flight flight = extractFlight(resultSet);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return flights;
    }*/

    @Override
    public List<Flight> readByBrigade(Brigade brigade, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_FLIGHT_BY_BRIGADE);

            int k = 1;
            statement.setInt(k++, brigade.getId());
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Flight flight = extractFlight(resultSet, language);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return flights;
    }

    @Override
    public List<Flight> readByStaff(Staff staff, Language language) throws DBException {
        List<Flight> flights = new ArrayList<>();
        Brigade brigade = null;
        try {
            brigade = DAOFactory.getInstance().getBrigadeDAO().readByStaff(staff, language);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        flights = readByBrigade(brigade, language);
        return flights;
    }

    @Override
    public List<Flight> readByStatus(FlightStatus flightStatus, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_FLIGHT_BY_STATUS);

            int k = 1;
            statement.setInt(k++, flightStatus.getId());
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Flight flight = extractFlight(resultSet, language);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return flights;
    }

    @Override
    public List<Flight> readAll(Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL_FLIGHTS);

            int k = 1;
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Flight flight = extractFlight(resultSet, language);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return flights;
    }

    @Override
    public boolean update(Flight flight, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnectionWithoutAutoCommit();
            statement = connection.prepareStatement(UPDATE_FLIGHT_BY_ID);

            int k = 1;
            statement.setString(k++, flight.getNumber());
            statement.setString(k++, flight.getDate());
            statement.setString(k++, flight.getTime());
            statement.setInt(k++, flight.getFlightStatus().getId());
            statement.setInt(k++, flight.getAircraft().getId());
            statement.setInt(k++, flight.getBrigade().getId());
            statement.setInt(k++, flight.getId());

            if (statement.executeUpdate() > 0) {
                return update(connection, flight, language);
            }
            return true;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    private boolean update(Connection connection, Flight flight, Language language) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_FLIGHT_LANG_BY_ID);
        int k = 1;
        statement.setString(k++, flight.getDeparturePoint());
        statement.setString(k++, flight.getArrivalPoint());
        statement.setInt(k++, flight.getId());
        statement.setInt(k++, language.getId());

        if (statement.executeUpdate() > 0) {
            connection.commit();
            return true;
        }
        DBConnection.getInstance().rollback(connection);
        return false;
    }

    @Override
    public boolean updateBrigade(Flight flight, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_FLIGHT_BRIGADE_BY_ID);

            int k = 1;
            statement.setInt(k++, flight.getBrigade().getId());
            statement.setInt(k++, flight.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return true;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public boolean updateStatus(Flight flight, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_FLIGHT_STATUS_BY_ID);

            int k = 1;
            statement.setInt(k++, flight.getFlightStatus().getId());
            statement.setInt(k++, flight.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return true;
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public boolean delete(int flightId) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnectionWithoutAutoCommit();
            statement = connection.prepareStatement(DELETE_FLIGHT_LANG_BY_ID);

            int k = 1;
            statement.setInt(k++, flightId);

            if (statement.executeUpdate() > 0) {
                return deleteFlight(connection, flightId);
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    private boolean deleteFlight(Connection connection, int flightId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_FLIGHT_BY_ID);
        int k = 1;
        statement.setInt(k++, flightId);

        if (statement.executeUpdate() > 0) {
            connection.commit();
            return true;
        }
        DBConnection.getInstance().rollback(connection);
        return false;
    }

    private Flight extractFlight(ResultSet resultSet, Language language) throws DBException {
        Flight flight = new Flight();
        try {
            flight.setId(resultSet.getInt(ENTITY_ID));
            flight.setNumber(resultSet.getString(FLIGHT_NUMBER));
            flight.setDate(resultSet.getString(FLIGHT_DEPARTURE_DATE));
            flight.setTime(resultSet.getString(FLIGHT_DEPARTURE_TIME));
            flight.setDeparturePoint(resultSet.getString(FLIGHT_LANG_DEPARTURE_POINT));
            flight.setArrivalPoint(resultSet.getString(FLIGHT_LANG_ARRIVAL_POINT));
            flight.setBrigade(DAOFactory.getInstance().getBrigadeDAO().read(resultSet.getInt(FLIGHT_BRIGADE_ID), language));
            flight.setFlightStatus(DAOFactory.getInstance().getFlightStatusDAO().read(language, resultSet.getInt(FLIGHT_STATUS_ID)));
            flight.setAircraft(DAOFactory.getInstance().getAircraftDAO().read(resultSet.getInt(FLIGHT_AIRCRAFT_ID)));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return flight;
    }
}
