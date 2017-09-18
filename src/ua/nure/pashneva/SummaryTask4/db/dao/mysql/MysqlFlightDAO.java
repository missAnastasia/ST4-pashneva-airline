package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.dao.FlightDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements FlightDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 */
public class MysqlFlightDAO implements FlightDAO {

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
    private static final String UPDATE_FLIGHT_BY_ID = "UPDATE flights SET number=?, departure_date=?, departure_time=?, aircraft_id=? WHERE id=?";
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
    public boolean create(Flight flight, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnectionWithoutAutoCommit();

        boolean result = false;
        if (flight.getId() != 0) {
            result = create(connection, flight, language);
            DBConnection.getInstance().close(connection);
            return result;
        }

        PreparedStatement statement = connection.prepareStatement(ADD_FLIGHT,
                Statement.RETURN_GENERATED_KEYS);

        int k = 1;
        statement.setString(k++, flight.getNumber());
        statement.setDate(k++, flight.getDate());
        statement.setTime(k++, flight.getTime());
        statement.setInt(k++, flight.getFlightStatus().getId());
        statement.setInt(k++, flight.getAircraft().getId());
        statement.setNull(k++, java.sql.Types.INTEGER);

        if (statement.executeUpdate() > 0) {
            MysqlDAOFactory.setGeneratedId(flight, statement);
            result =  create(connection, flight, language);
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public Flight read(int id, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_FLIGHT_BY_ID);

        int k = 1;
        statement.setInt(k++, id);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        Flight flight = null;
        if (resultSet.next()) {
            flight = extractFlight(resultSet, language);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return flight;
    }

    @Override
    public Flight readByNumber(String number, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_FLIGHT_BY_NUMBER);

        int k = 1;
        statement.setString(k++, number);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        Flight flight = null;
        if (resultSet.next()) {
            flight = extractFlight(resultSet, language);
        }

       DBConnection.getInstance().close(connection, statement, resultSet);
       return flight;
    }

    @Override
    public List<Flight> readByDeparturePoint(String departure, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_FLIGHT_BY_DEPARTURE_POINT);

        int k = 1;
        statement.setString(k++, departure);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<Flight> flights = new ArrayList<>();
        while (resultSet.next()) {
            Flight flight = extractFlight(resultSet, language);
            flights.add(flight);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return flights;
    }

    @Override
    public List<Flight> readByArrivalPoint(String arrival, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_FLIGHT_BY_ARRIVAL_POINT);

        int k = 1;
        statement.setString(k++, arrival);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<Flight> flights = new ArrayList<>();
        while (resultSet.next()) {
            Flight flight = extractFlight(resultSet, language);
            flights.add(flight);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return flights;
    }

    @Override
    public List<Flight> readByDate(Date date, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_FLIGHT_BY_DATE);

        int k = 1;
        statement.setDate(k++, date);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<Flight> flights = new ArrayList<>();
        while (resultSet.next()) {
            Flight flight = extractFlight(resultSet, language);
            flights.add(flight);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return flights;
    }

    @Override
    public List<Flight> readByBrigade(Brigade brigade, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_FLIGHT_BY_BRIGADE);

        int k = 1;
        statement.setInt(k++, brigade.getId());
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<Flight> flights = new ArrayList<>();
        while (resultSet.next()) {
            Flight flight = extractFlight(resultSet, language);
            flights.add(flight);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return flights;
    }

    @Override
    public List<Flight> readByStaff(Staff staff, Language language) throws Exception {
        Brigade brigade = DAOFactory.getInstance().getBrigadeDAO().readByStaff(staff, language);
        List<Flight> flights = readByBrigade(brigade, language);
        return flights;
    }

    @Override
    public List<Flight> readByStatus(int flightStatusId, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_FLIGHT_BY_STATUS);

        int k = 1;
        statement.setInt(k++, flightStatusId);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<Flight> flights = new ArrayList<>();
        while (resultSet.next()) {
            Flight flight = extractFlight(resultSet, language);
            flights.add(flight);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return flights;
    }

    @Override
    public List<Flight> readAll(Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_FLIGHTS);

        int k = 1;
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<Flight> flights = new ArrayList<>();
        while (resultSet.next()) {
            Flight flight = extractFlight(resultSet, language);
            flights.add(flight);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return flights;
    }

    @Override
    public boolean update(Flight flight, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnectionWithoutAutoCommit();
        PreparedStatement statement = connection.prepareStatement(UPDATE_FLIGHT_BY_ID);

        int k = 1;
        statement.setString(k++, flight.getNumber());
        statement.setDate(k++, flight.getDate());
        statement.setTime(k++, flight.getTime());
        statement.setInt(k++, flight.getAircraft().getId());
        statement.setInt(k++, flight.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = update(connection, flight, language);
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public boolean updateBrigade(Flight flight, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_FLIGHT_BRIGADE_BY_ID);

        int k = 1;
        if (flight.getBrigade() != null) {
            statement.setInt(k++, flight.getBrigade().getId());
        } else {
            statement.setNull(k++, java.sql.Types.INTEGER);
        }
        statement.setInt(k++, flight.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public boolean updateStatus(Flight flight, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_FLIGHT_STATUS_BY_ID);

        int k = 1;
        statement.setInt(k++, flight.getFlightStatus().getId());
        statement.setInt(k++, flight.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public boolean delete(int flightId) throws Exception {
        Connection connection = DBConnection.getInstance().getConnectionWithoutAutoCommit();
        PreparedStatement statement = connection.prepareStatement(DELETE_FLIGHT_LANG_BY_ID);

        int k = 1;
        statement.setInt(k++, flightId);

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = deleteFlight(connection, flightId);
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    /**
     * Private method for adding localized flight data into table flights_lang.
     *
     * @param connection object with information about connection to database.
     * @param flight object with localized flight data.
     * @param language object of Language class which contains data of current locale.
     * @return true - localized flight data added to database, otherwise - false.
     * @throws Exception
     */
    private boolean create(Connection connection, Flight flight, Language language) throws Exception {
        PreparedStatement statement = connection.prepareStatement(ADD_FLIGHT_LANG);

        int k = 1;
        statement.setInt(k++, flight.getId());
        statement.setInt(k++, language.getId());
        statement.setString(k++, flight.getDeparturePoint());
        statement.setString(k++, flight.getArrivalPoint());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            connection.commit();
            result = true;
        } else {
            DBConnection.getInstance().rollback(connection);
        }
        return result;
    }

    /**
     * Private method for updating localized flight data in table flights_lang.
     *
     * @param connection object with information about connection to database.
     * @param flight object with localized flight data.
     * @param language object of Language class which contains data of current locale.
     * @return true - localized flight data updated in database, otherwise - false.
     * @throws Exception
     */
    private boolean update(Connection connection, Flight flight, Language language) throws Exception {
        PreparedStatement statement = connection.prepareStatement(UPDATE_FLIGHT_LANG_BY_ID);

        int k = 1;
        statement.setString(k++, flight.getDeparturePoint());
        statement.setString(k++, flight.getArrivalPoint());
        statement.setInt(k++, flight.getId());
        statement.setInt(k++, language.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            connection.commit();
            result = true;
        } else {
            DBConnection.getInstance().rollback(connection);
        }
        return result;
    }

    /**
     * Private method for deleting localized flight data from table flights_lang.
     *
     * @param connection object with information about connection to database.
     * @param flightId flight identifier which data must be deleted from table flights_lang.
     * @return true - localized flight data deleted from database, otherwise - false.
     * @throws Exception
     */
    private boolean deleteFlight(Connection connection, int flightId) throws Exception {
        PreparedStatement statement = connection.prepareStatement(DELETE_FLIGHT_BY_ID);

        int k = 1;
        statement.setInt(k++, flightId);

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            connection.commit();
            result = true;
        } else {
            DBConnection.getInstance().rollback(connection);
        }
        return result;
    }

    /**
     * Private method for obtaining flight data from ResultSet.
     *
     * @param resultSet instance of ResultSet which contains selected data from tables
     *                  flights and flights_lang.
     * @param language object of Language class which contains data about current locale.
     * @return object of Flight which contains data obtained from ResultSet.
     * @throws Exception
     */
    private Flight extractFlight(ResultSet resultSet, Language language) throws Exception {
        Flight flight = new Flight();
        flight.setId(resultSet.getInt(ENTITY_ID));
        flight.setNumber(resultSet.getString(FLIGHT_NUMBER));
        flight.setDate(resultSet.getDate(FLIGHT_DEPARTURE_DATE));
        flight.setTime(resultSet.getTime(FLIGHT_DEPARTURE_TIME));
        flight.setDeparturePoint(resultSet.getString(FLIGHT_LANG_DEPARTURE_POINT));
        flight.setArrivalPoint(resultSet.getString(FLIGHT_LANG_ARRIVAL_POINT));
        flight.setBrigade(DAOFactory.getInstance().getBrigadeDAO().read(resultSet.getInt(FLIGHT_BRIGADE_ID), language));
        flight.setFlightStatus(DAOFactory.getInstance().getFlightStatusDAO().read(language, resultSet.getInt(FLIGHT_STATUS_ID)));
        flight.setAircraft(DAOFactory.getInstance().getAircraftDAO().read(resultSet.getInt(FLIGHT_AIRCRAFT_ID)));
        return flight;
    }
}
