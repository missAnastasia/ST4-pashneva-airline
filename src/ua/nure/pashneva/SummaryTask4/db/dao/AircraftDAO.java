package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.Aircraft;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

/**
 * The data manipulation interface in the table aircraft.
 *
 * @author Anastasia Pashneva
 *
 */
public interface AircraftDAO {
    /**
     * Method of obtaining a aircraft from the database.
     *
     * @param id aircraft identifier (primary unique key)
     * @return object which contains values of all fields from table aircraft.
     * @throws DBException
     */
    Aircraft read(Integer id) throws DBException;
}
