package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.BrigadeDAO;
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
    private static final String GET_ALL_BRIGADES = "SELECT * FROM brigades";
    private static final String GET_BRIGADE_BY_ID = "SELECT * FROM brigades WHERE id=?";
    //private static final String GET_BRIGADE_BY_STAFF = "SELECT * FROM brigades  WHERE login=?";
    private static final String ADD_BRIGADE = "INSERT INTO brigades VALUE(DEFAULT, ?)";
    private static final String UPDATE_BRIGADE_BY_ID = "UPDATE brigades SET name=? WHERE id=?";
    private static final String DELETE_BRIGADE_BY_ID = "DELETE FROM brigades WHERE id=?";

    /**
     * String fields which contain column names of table brigades.
     */
    private static final String ENTITY_ID = "id";
    private static final String BRIGADE_NAME = "name";

    @Override
    public boolean create(Brigade brigade) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(ADD_BRIGADE);

            int k = 1;
            statement.setInt(k++, brigade.getId());
            statement.setString(k++, brigade.getName());

            if (statement.executeUpdate() > 0) {
                return MysqlDAOFactory.setGeneratedId(brigade, statement);
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public Brigade read(Integer id) throws DBException {
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
                brigade = extractBrigade(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return brigade;
    }

    @Override
    public Brigade read(Staff staff) throws DBException {
        return null;
    }

    @Override
    public List<Brigade> readAll() throws DBException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Brigade> brigades = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_BRIGADES);

            while (resultSet.next()) {
                Brigade brigade = extractBrigade(resultSet);
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
            statement.setString(k++, brigade.getName());
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

    private Brigade extractBrigade(ResultSet resultSet) throws DBException {
        Brigade brigade = new Brigade();
        try {
            brigade.setId(resultSet.getInt(ENTITY_ID));
            brigade.setName(resultSet.getString(BRIGADE_NAME));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return brigade;
    }
}
