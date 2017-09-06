package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.FlightStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.List;

/**
 * The data manipulation interface in the tables flight_statuses and flight_statuses_lang.
 *
 * @author Anastasia Pashneva
 *
 */
public interface FlightStatusDAO {

    /**
     * Method of obtaining a flight status from the database.
     *
     * @param id flight status identifier (primary unique key)
     * @param language object of Language class which contains data of current locale.
     * @return object which contains values of all fields from table flight_statuses.
     * @throws DBException
     */
    FlightStatus read(Language language, int id) throws DBException;

    /**
     * Method of obtaining all flight statuses from the database.
     *
     * @param language object of Language class which contains data of current locale.
     * @return object which contains values of all fields from table flight_statuses.
     * @throws DBException
     */
    List<FlightStatus> readAll(Language language) throws DBException;
}
