package ua.nure.pashneva.SummaryTask4.db.connection;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Abstract class that provides the ability to receive and close connections
 * to databases of different DBMS from DBConnection implementations.
 *
 * @author Anastasia Pashneva
 */
public abstract class DBConnection {

    private static final Logger LOG = Logger.getLogger(DBConnection.class);

    /**
     * Private static class field that stores the DBConnection object.
     */
    private static DBConnection instance;

    /**
     * Private static class field that contains the full qualified class name
     * whose object will return {@link #getInstance()}.
     */
    private static String dbConnectionFCN;

    /**
     * Protected class field with obtained by implementation DataSource object.
     */
    protected DataSource dataSource;

    /**
     * Method for obtaining the DBConnection object. <br/>
     * The class settings determines which DBConnection implementation will be returned.
     *
     * @return an instance of the DBConnection child whose name is contained in {@link #dbConnectionFCN}.
     * @throws Exception
     */
    public static synchronized DBConnection getInstance() throws Exception {
        if (instance == null) {
            Class<?> clazz = Class.forName(DBConnection.dbConnectionFCN);
            instance = (DBConnection) clazz.newInstance();
        }
        return instance;
    }

    protected DBConnection() {
    }

    public static void setDBConnectionFCN(String dbConnectionFCN) {
        instance = null;
        DBConnection.dbConnectionFCN = dbConnectionFCN;
    }

    /**
     * Method for closing connection to database, statement and result set.
     *
     * @param connection object with information about connection to database.
     * @param statement object which is an instance of Statement class.
     * @param resultSet object that contains data set obtained from database.
     */
    public void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(connection);
    }

    /**
     * Method for closing connection to database and statement.
     *
     * @param connection object with information about connection to database.
     * @param statement object which is an instance of Statement class.
     */
    public void close(Connection connection, Statement statement) {
        close(statement);
        close(connection);
    }

    /**
     * Method for closing connection to database.
     *
     * @param connection object with information about connection to database.
     */
    public void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close connection", ex);
            }
        }
    }

    /**
     * Private method for closing statement.
     *
     * @param statement object which is an instance of Statement class.
     */
    private void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close statement", ex);
            }
        }
    }

    /**
     * Private method for closing result set.
     *
     * @param resultSet object that contains data set obtained from database.
     */
    private void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close resultSet", ex);
            }
        }
    }

    /**
     * Method for rollback transaction to database and deny all changes that were made in this transaction. <br/>
     * This method should be used only when auto-commit mode has been disabled.
     *
     * @param connection object with information about connection to database
     *                   which transaction must be rollback.
     */
    public void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    /**
     * Method for obtaining Connection object.
     *
     * @return an instance of Connection class, which opens connection to database from datasource.
     * @throws Exception
     */
    public abstract Connection getConnection() throws Exception;

    /**
     * Method for obtaining Connection object without autocommit.
     *
     * @return an instance of Connection class, which opens connection to database from datasource.
     * @throws Exception
     */
    public abstract Connection getConnectionWithoutAutoCommit() throws Exception;
}
