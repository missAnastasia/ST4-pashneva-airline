package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.Aircraft;

import java.util.List;

/**
 * The data manipulation interface in the table aircraft.
 *
 * @author Anastasia Pashneva
 */
public interface AircraftDAO {

    /**
     * Method of obtaining a aircraft from the database.
     *
     * @param id aircraft identifier (primary unique key)
     * @return object which contains values of all fields from table aircraft.
     * @throws Exception
     */
    Aircraft read(int id) throws Exception;

    /**
     * Method of obtaining a aircraft from the database.
     *
     * @return collection (List) of all aircraft.
     * @throws Exception
     */
    List<Aircraft> readAll() throws Exception;
}
