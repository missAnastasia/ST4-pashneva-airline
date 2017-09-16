package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.*;
import ua.nure.pashneva.SummaryTask4.db.entity.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Realization of DAOFactory for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 */
public class MysqlDAOFactory extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new MysqlUserDAO();
    }

    @Override
    public LanguageDAO getLanguageDAO() {
        return new MysqlLanguageDAO();
    }

    @Override
    public AircraftDAO getAircraftDAO() {
        return new MysqlAircraftDAO();
    }

    @Override
    public StaffDAO getStaffDAO() {
        return new MysqlStaffDAO();
    }

    @Override
    public BrigadeDAO getBrigadeDAO() {
        return new MysqlBrigadeDAO();
    }

    @Override
    public FlightDAO getFlightDAO() {
        return new MysqlFlightDAO();
    }

    @Override
    public RequestToAdminDAO getRequestToAdminDAO() {
        return new MysqlRequestToAdminDAO();
    }

    @Override
    public FlightStatusDAO getFlightStatusDAO() {
        return new MysqlFlightStatusDAO();
    }

    @Override
    public RequestStatusDAO getRequestStatusDAO() {
        return new MysqlRequestStatusDAO();
    }

    @Override
    public PostDAO getPostDAO() {
        return new MysqlPostDAO();
    }

    /**
     * Static method for obtaining generated entity identifiers value returned by PreparedStatement object.
     *
     * @param entity object of Entity child which identifier must be obtained.
     * @param statement object of PreparedStatement which returns ResultSet og generated keys.
     * @throws SQLException
     */
    static void setGeneratedId(Entity entity, PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        try {
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt(1));
            }
        } finally {
            generatedKeys.close();
        }
    }
}
