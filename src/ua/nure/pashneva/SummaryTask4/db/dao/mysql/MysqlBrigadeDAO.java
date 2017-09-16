package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.BrigadeDAO;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements BrigadeDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 */
public class MysqlBrigadeDAO implements BrigadeDAO {

    /**
     * String fields which contain sql queries to table brigades of MySQL database.
     */
    private static final String GET_ALL_BRIGADES = "SELECT * FROM brigades b";
    private static final String GET_BRIGADE_BY_ID = "SELECT * FROM brigades b WHERE b.id=?";
    private static final String GET_BRIGADE_BY_STAFF = "SELECT * FROM (brigades b INNER JOIN brigades_staff b_s ON b.id=b_s.brigade_id) WHERE b_s.staff_id=?";
    private static final String ADD_BRIGADE = "INSERT INTO brigades VALUE(DEFAULT, ?)";
    private static final String ADD_BRIGADES_STAFF = "INSERT INTO brigades_staff VALUE(DEFAULT, ?, ?)";
    private static final String UPDATE_BRIGADE_BY_ID = "UPDATE brigades SET number=? WHERE id=?";
    private static final String DELETE_BRIGADE_BY_ID = "DELETE FROM brigades WHERE id=?";
    private static final String DELETE_ALL_STAFF_FROM_BRIGADE = "DELETE FROM brigades_staff WHERE brigade_id=?";

    /**
     * String fields which contain column names of table brigades.
     */
    private static final String ENTITY_ID = "b.id";
    private static final String BRIGADE_NUMBER = "b.number";

    @Override
    public boolean create(Brigade brigade) throws Exception {
        Connection connection = DBConnection.getInstance().getConnectionWithoutAutoCommit();
        PreparedStatement statement = connection.prepareStatement(ADD_BRIGADE,
                Statement.RETURN_GENERATED_KEYS);

        int k = 1;
        statement.setString(k++, brigade.getNumber());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            MysqlDAOFactory.setGeneratedId(brigade, statement);
            result = addStaffToBrigade(connection, brigade);
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public Brigade read(int id, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_BRIGADE_BY_ID);

        int k = 1;
        statement.setInt(k++, id);

        ResultSet resultSet = statement.executeQuery();

        Brigade brigade = null;
        if (resultSet.next()) {
            brigade = extractBrigade(resultSet, language);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return brigade;
    }

    @Override
    public Brigade readByStaff(Staff staff, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_BRIGADE_BY_STAFF);

        int k = 1;
        statement.setInt(k++, staff.getId());

        ResultSet resultSet = statement.executeQuery();

        Brigade brigade = null;
        if (resultSet.next()) {
            brigade = extractBrigade(resultSet, language);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return brigade;
    }

    @Override
    public List<Brigade> getAvailableBrigades(Date flightDate, Language language) throws Exception {
        List<Brigade> unavailableBrigades = new ArrayList<>();
        List<Brigade> availableBrigades = DAOFactory.getInstance().getBrigadeDAO().readAll(language);
        List<Flight> sameDateFlights = DAOFactory.getInstance().getFlightDAO().readByDate(flightDate, language);

        for (Brigade brigade : availableBrigades) {
            for (Flight flight : sameDateFlights) {
                if (!(flight.equals(this)) && brigade.equals(flight.getBrigade())) {
                    unavailableBrigades.add(brigade);
                }
            }
        }

        availableBrigades.removeAll(unavailableBrigades);
        return availableBrigades;
    }

    @Override
    public List<Brigade> readAll(Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(GET_ALL_BRIGADES);

        List<Brigade> brigades = new ArrayList<>();
        while (resultSet.next()) {
            Brigade brigade = extractBrigade(resultSet, language);
            brigades.add(brigade);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return brigades;
    }

    @Override
    public boolean update(Brigade brigade) throws Exception {
        Connection connection = DBConnection.getInstance().getConnectionWithoutAutoCommit();
        PreparedStatement statement = connection.prepareStatement(UPDATE_BRIGADE_BY_ID);

        int k = 1;
        statement.setString(k++, brigade.getNumber());
        statement.setInt(k++, brigade.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            if (deleteAllStaffFromBrigade(connection, brigade)) {
                result = addStaffToBrigade(connection, brigade);
            }
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_BRIGADE_BY_ID);

        int k = 1;
        statement.setInt(k++, id);

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    /**
     * Private method for inserting data of belonging staff to adjusted brigade into table brigades_staff.
     *
     * @param connection object with information about connection to database.
     * @param brigade object which contains data of brigade with staff whose data must be
     *                inserted into database.
     * @return true - brigades staff data added to table brigades_staff, otherwise - false.
     * @throws Exception
     */
    private boolean addStaffToBrigade(Connection connection, Brigade brigade) throws Exception {
        PreparedStatement statement = connection.prepareStatement(ADD_BRIGADES_STAFF);

        for (Staff staff : brigade.getStaff()) {
            int k = 1;
            statement.setInt(k++, brigade.getId());
            statement.setInt(k++, staff.getId());

            if (!(statement.executeUpdate() > 0)) {
                DBConnection.getInstance().rollback(connection);
                return false;
            }
        }

        connection.commit();
        return true;
    }

    /**
     * Private method for deleting all data from the table brigades_staff with adjusted brigade.
     *
     * @param connection object with information about connection to database.
     * @param brigade object which contains data of brigade with staff whose data must be
     *                deleted from database.
     * @return true - brigades staff data deleted from table brigades_staff, otherwise - false.
     * @throws Exception
     */
    private boolean deleteAllStaffFromBrigade(Connection connection, Brigade brigade) throws Exception {
        PreparedStatement statement = connection.prepareStatement(DELETE_ALL_STAFF_FROM_BRIGADE);

        int k = 1;
        statement.setInt(k++, brigade.getId());

        if (!(statement.executeUpdate() > 0)) {
            DBConnection.getInstance().rollback(connection);
            return false;
        }
        return true;
    }

    /**
     * Private method for obtaining brigade data from ResultSet.
     *
     * @param resultSet instance of ResultSet which contains selected data from table brigades.
     * @param language object of Language class which contains data about current locale.
     * @return object of Brigade which contains data obtained from ResultSet.
     * @throws Exception
     */
    private Brigade extractBrigade(ResultSet resultSet, Language language) throws Exception {
        Brigade brigade = new Brigade();
        brigade.setId(resultSet.getInt(ENTITY_ID));
        brigade.setNumber(resultSet.getString(BRIGADE_NUMBER));
        brigade.setStaff(DAOFactory.getInstance().getStaffDAO().readByBrigadeId(resultSet.getInt(ENTITY_ID), language));
        return brigade;
    }
}
