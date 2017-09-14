package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.*;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * The data manipulation interface in the table flights.
 *
 * @author Anastasia Pashneva
 *
 */
public interface FlightDAO {

    /**
     * Method of creating a flight in the database. <br/>
     * After the method is executed the flight object contains the value of the identifier (id),
     * this information comes from the DBMS side.
     *
     * @param flight object that contains information about a flight without an identifier.
     * @param language object of Language class which contains data of current locale.
     * @return true - flight was successfully inserted into database, otherwise - false.
     * @throws DBException
     */
    boolean create(Flight flight, Language language) throws DBException;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param id flight identifier (primary unique key).
     * @return object which contains values of all fields from table flights.
     * @throws DBException
     */
    Flight read(int id, Language language) throws DBException;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param number flight number.
     * @return object which contains values of all fields from table flights.
     * @throws DBException
     */
    Flight readByNumber(String number, Language language) throws DBException;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param departure flight point of departure (city name).
     * @return collection (List) of flights with adjusted point of departure.
     * @throws DBException
     */
    List<Flight> readByDeparturePoint(String departure, Language language) throws DBException;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param arrival flight point of arrival (city name).
     * @return collection (List) of flights with adjusted point of arrival.
     * @throws DBException
     */
    List<Flight> readByArrivalPoint(String arrival, Language language) throws DBException;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param date string which contains a value of flight date in format yyyy-mm-dd.
     * @return collection (List) of flights with adjusted date.
     * @throws DBException
     */
    List<Flight> readByDate(String date, Language language) throws DBException;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param time string which contains a value of flight time in format hh:mm:ss.
     * @return collection (List) of flights with adjusted and time.
     * @throws DBException
     *//*
    List<Flight> readByTime(String time, Language language) throws DBException;*/

    /**
     * Method of obtaining a flight from the database.
     *
     * @param brigade an instance of Brigade class,
     *                  which contains data of flight brigade.
     * @return collection (List) of flights with adjusted brigade.
     * @throws DBException
     */
    List<Flight> readByBrigade(Brigade brigade, Language language) throws DBException;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param staff   an instance of Staff class,
     *                  which contains data of the staff from brigade.
     * @return collection (List) of flights with adjusted brigade and stuff.
     * @throws DBException
     */
    List<Flight> readByStaff(Staff staff, Language language) throws DBException;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param flightStatusId flight status identifier (primary unique key).
     * @return collection (List) of flights with adjusted flight status.
     * @throws DBException
     */
    List<Flight> readByStatus(int flightStatusId, Language language) throws DBException;

    /**
     * Method of obtaining all flights from the database.
     *
     * @return collection (List) of all flights.
     * @throws DBException
     */
    List<Flight> readAll(Language language) throws DBException;

    /**
     * Method of updating flight data in database. <br/>
     * All fields are updated.
     *
     * @param flight object that contains flight data.
     *             The identifier must be present.
     * @return true - flight data was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean update(Flight flight, Language language) throws DBException;

    /**
     * Method of updating flight brigade in database. <br/>
     * Only brigade field is updated.
     *
     * @return true - brigade was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean updateBrigade(Flight flight, Language language) throws DBException;

    /**
     * Method of updating flight status in database. <br/>
     * Only flightStatus field is updated.
     *
     * @return true - flight status was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean updateStatus(Flight flight, Language language) throws DBException;

    /**
     * Method of deleting flight from database.
     *
     * @param flightId flight identifier (primary unique key) which must be deleted.
     * @return true - flight was successfully deleted from database, otherwise - false.
     * @throws DBException
     */
    boolean delete(int flightId) throws DBException;
}
