package ua.nure.pashneva.SummaryTask4.db.connection.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Realization of MysqlDBConnection for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 */
public class MysqlDBConnection extends DBConnection {

    @Override
    public Connection getConnection() throws Exception {
        Context initContext = new InitialContext();
        dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/db_mysql");
        Connection conn = dataSource.getConnection();
        return conn;
    }

    @Override
    public Connection getConnectionWithoutAutoCommit() throws Exception {
        Context initContext = new InitialContext();
        dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/db_mysql");
        Connection conn = dataSource.getConnection();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return conn;
    }
}
