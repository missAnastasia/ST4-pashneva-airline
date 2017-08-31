package ua.nure.pashneva.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class that provides the ability to receive and close a connection to a database.
 *
 * @author Anastasia Pashneva
 *
 */
public class DBConnection {

    private static DBConnection instance;
    private static final Logger LOG = Logger.getLogger(DBConnection.class);

    private DataSource dataSource;

    /**
     * Method for obtaining the DBConnection object. <br/>
     * Only one instance of the class is created.
     *
     * @return instance of DBConnection class
     */
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private DBConnection() {
    }

    /**
     * Method for obtaining Connection object.
     *
     * @return an instance of Connection class, which opens connection to database from datasource.
     */
    public Connection getConnection() throws DBException {
        Connection conn = null;
        try {
            Context initContext = new InitialContext();
            LOG.trace("InitialContext has been initialized");
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/db");
            LOG.trace("DataSource has been initialized");
            conn = dataSource.getConnection();
            LOG.trace("Connection has been gotten --> " + conn);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return conn;
    }

    /**
     * Method for obtaining Connection object without autocommit.
     *
     * @return an instance of Connection class, which opens connection to database from datasource.
     */
    public Connection getConnectionWithoutAutoCommit() throws DBException {
        Connection conn = null;
        try {
            Context initContext = new InitialContext();
            LOG.trace("InitialContext has been initialized");
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/db");
            LOG.trace("DataSource has been initialized");
            conn = dataSource.getConnection();
            LOG.trace("Connection has been gotten --> " + conn);
            conn.setAutoCommit(false);
            LOG.trace("Connection autoCommit --> " + conn.getAutoCommit());
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            LOG.trace("Connection transactionIsolation --> " + conn.getTransactionIsolation());
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return conn;
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
     * Method for closing statement.
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
     * Method for closing result set.
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
}
