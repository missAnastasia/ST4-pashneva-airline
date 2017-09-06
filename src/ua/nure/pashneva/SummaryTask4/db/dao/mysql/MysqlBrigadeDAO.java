package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.BrigadeDAO;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.*;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements BrigadeDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
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
    private static final String UPDATE_STAFF_BY_BRIGADE_ID = "UPDATE brigades_staff SET staff_id=? WHERE brigade_id=?";
    private static final String UPDATE_BRIGADE_BY_STAFF_ID = "UPDATE brigades_staff SET brigade_id=? WHERE staff_id=?";
    private static final String DELETE_BRIGADE_BY_ID = "DELETE FROM brigades WHERE id=?";
    private static final String DELETE_STAFF_FROM_BRIGADE_BY_ID = "DELETE FROM brigades_staff WHERE staff_id=?";

    /**
     * String fields which contain column names of table brigades.
     */
    private static final String ENTITY_ID = "b.id";
    private static final String BRIGADE_NUMBER = "b.number";

    @Override
    public boolean create(Brigade brigade) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getInstance().getConnectionWithoutAutoCommit();
            statement = connection.prepareStatement(ADD_BRIGADE);

            int k = 1;
            statement.setInt(k++, brigade.getId());
            statement.setString(k++, brigade.getNumber());

            if (statement.executeUpdate() > 0) {
                if (MysqlDAOFactory.setGeneratedId(brigade, statement)) {
                    return addStaffToBrigade(connection, brigade);
                }
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    private boolean addStaffToBrigade(Connection connection, Brigade brigade) throws SQLException {
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

    @Override
    public Brigade read(Integer id, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Brigade brigade = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BRIGADE_BY_ID);

            int k = 1;
            statement.setInt(k++, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                brigade = extractBrigade(resultSet, language);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return brigade;
    }

    @Override
    public Brigade readByStaff(Staff staff, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Brigade brigade = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_BRIGADE_BY_STAFF);

            int k = 1;
            statement.setInt(k++, staff.getId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                brigade = extractBrigade(resultSet, language);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return brigade;
    }

    @Override
    public List<Brigade> readAll(Language language) throws DBException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Brigade> brigades = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_BRIGADES);

            while (resultSet.next()) {
                Brigade brigade = extractBrigade(resultSet, language);
                brigades.add(brigade);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return brigades;
    }

    @Override
    public boolean update(Brigade brigade) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_BRIGADE_BY_ID);

            int k = 1;
            statement.setString(k++, brigade.getNumber());
            statement.setInt(k++, brigade.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public boolean changeBrigadeForStaff(Brigade brigade, Staff staff) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_BRIGADE_BY_STAFF_ID);

            int k = 1;
            statement.setInt(k++, staff.getId());
            statement.setInt(k++, brigade.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public boolean changeStaffForBrigade(Brigade brigade, Staff staff) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_STAFF_BY_BRIGADE_ID);

            int k = 1;
            statement.setInt(k++, brigade.getId());
            statement.setInt(k++, staff.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public boolean delete(Brigade brigade) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_BRIGADE_BY_ID);

            int k = 1;
            statement.setInt(k++, brigade.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public boolean deleteStaffFromBrigade(Brigade brigade, Staff staff) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_STAFF_FROM_BRIGADE_BY_ID);

            int k = 1;
            statement.setInt(k++, staff.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    private Brigade extractBrigade(ResultSet resultSet, Language language) throws DBException {
        Brigade brigade = new Brigade();
        try {
            brigade.setId(resultSet.getInt(ENTITY_ID));
            brigade.setNumber(resultSet.getString(BRIGADE_NUMBER));
            brigade.setStaff(DAOFactory.getInstance().getStaffDAO().readByBrigadeId(resultSet.getInt(ENTITY_ID), language));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return brigade;
    }
}
