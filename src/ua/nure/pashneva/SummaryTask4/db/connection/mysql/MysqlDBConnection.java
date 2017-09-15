package ua.nure.pashneva.SummaryTask4.db.connection.mysql;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class MysqlDBConnection extends DBConnection {

    private static final Logger LOG = Logger.getLogger(MysqlDBConnection.class);

    @Override
    public Connection getConnection() throws Exception {
        Context initContext = new InitialContext();
        LOG.trace("InitialContext has been initialized");
        dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/db_mysql");
        LOG.trace("DataSource has been initialized");
        Connection conn = dataSource.getConnection();
        LOG.trace("Connection has been gotten --> " + conn);
        return conn;
    }

    @Override
    public Connection getConnectionWithoutAutoCommit() throws Exception {
        Context initContext = new InitialContext();
        LOG.trace("InitialContext has been initialized");
        dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/db_mysql");
        LOG.trace("DataSource has been initialized");
        Connection conn = dataSource.getConnection();
        LOG.trace("Connection has been gotten --> " + conn);
        conn.setAutoCommit(false);
        LOG.trace("Connection autoCommit --> " + conn.getAutoCommit());
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        LOG.trace("Connection transactionIsolation --> " + conn.getTransactionIsolation());
        return conn;
    }
}
