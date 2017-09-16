package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;

import java.sql.Date;
import java.util.List;

/**
 * The data manipulation interface in the table flights.
 *
 * @author Anastasia Pashneva
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
     * @throws Exception
     */
    boolean create(Flight flight, Language language) throws Exception;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param id flight identifier (primary unique key).
     * @param language object of Language class which contains data about current locale.
     * @return object which contains values of all fields from table flights.
     * @throws Exception
     */
    Flight read(int id, Language language) throws Exception;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param number flight number.
     * @param language object of Language class which contains data about current locale.
     * @return object which contains values of all fields from table flights.
     * @throws Exception
     */
    Flight readByNumber(String number, Language language) throws Exception;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param departure flight point of departure (city name).
     * @param language object of Language class which contains data about current locale.
     * @return collection (List) of flights with adjusted point of departure.
     * @throws Exception
     */
    List<Flight> readByDeparturePoint(String departure, Language language) throws Exception;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param arrival flight point of arrival (city name).
     * @param language object of Language class which contains data about current locale.
     * @return collection (List) of flights with adjusted point of arrival.
     * @throws Exception
     */
    List<Flight> readByArrivalPoint(String arrival, Language language) throws Exception;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param date object of java.sql.Data which contains a value of flight date.
     * @param language object of Language class which contains data about current locale.
     * @return collection (List) of flights with adjusted date.
     * @throws Exception
     */
    List<Flight> readByDate(Date date, Language language) throws Exception;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param brigade an instance of Brigade class,
     *                  which contains data of flight brigade.
     * @param language object of Language class which contains data about current locale.
     * @return collection (List) of flights with adjusted brigade.
     * @throws Exception
     */
    List<Flight> readByBrigade(Brigade brigade, Language language) throws Exception;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param staff   an instance of Staff class,
     *                  which contains data of the staff from brigade.
     * @param language object of Language class which contains data about current locale.
     * @return collection (List) of flights with adjusted brigade and stuff.
     * @throws Exception
     */
    List<Flight> readByStaff(Staff staff, Language language) throws Exception;

    /**
     * Method of obtaining a flight from the database.
     *
     * @param flightStatusId flight status identifier (primary unique key).
     * @param language object of Language class which contains data about current locale.
     * @return collection (List) of flights with adjusted flight status.
     * @throws Exception
     */
    List<Flight> readByStatus(int flightStatusId, Language language) throws Exception;

    /**
     * Method of obtaining all flights from the database.
     *
     * @param language object of Language class which contains data about current locale.
     * @return collection (List) of all flights.
     * @throws Exception
     */
    List<Flight> readAll(Language language) throws Exception;

    /**
     * Method of updating flight data in database. <br/>
     * All fields are updated.
     *
     * @param flight object that contains flight data.
     *             The identifier must be present.
     * @param language object of Language class which contains data about current locale.
     * @return true - flight data was successfully updated in database, otherwise - false.
     * @throws Exception
     */
    boolean update(Flight flight, Language language) throws Exception;

    /**
     * Method of updating flight brigade in database. <br/>
     * Only brigade field is updated.
     *
     * @param language object of Language class which contains data about current locale.
     * @return true - brigade was successfully updated in database, otherwise - false.
     * @throws Exception
     */
    boolean updateBrigade(Flight flight, Language language) throws Exception;

    /**
     * Method of updating flight status in database. <br/>
     * Only flightStatus field is updated.
     *
     * @param language object of Language class which contains data about current locale.
     * @return true - flight status was successfully updated in database, otherwise - false.
     * @throws Exception
     */
    boolean updateStatus(Flight flight, Language language) throws Exception;

    /**
     * Method of deleting flight from database.
     *
     * @param flightId flight identifier (primary unique key) which must be deleted.
     * @return true - flight was successfully deleted from database, otherwise - false.
     * @throws Exception
     */
    boolean delete(int flightId) throws Exception;
}
