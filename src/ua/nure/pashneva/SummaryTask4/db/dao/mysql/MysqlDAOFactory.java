package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.dao.*;
import ua.nure.pashneva.SummaryTask4.db.entity.Entity;
import ua.nure.pashneva.SummaryTask4.db.entity.FlightStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Realization of DAOFactory for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
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

    public static boolean setGeneratedId(Entity entity, PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            entity.setId(generatedKeys.getInt(1));
            generatedKeys.close();
            return true;
        }
        generatedKeys.close();
        return false;
    }

}
