package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.dao.FlightDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.FlightStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.Date;
import java.util.List;

/**
 * Class that implements FlightDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
 */
public class MysqlFlightDAO implements FlightDAO {

    /**
     * String fields which contain sql queries to tables flights,
     * flights_lang, flight_statuses, flight_statuses_lang,
     * brigades_stuff, aircraft of MySQL database.
     */
    /**
     * String fields which contain column names of table flights,
     * flights_lang, flight_statuses, flight_statuses_lang.
     */

    @Override
    public boolean create(Flight flight) throws DBException {
        return false;
    }

    @Override
    public Flight read(Integer id) throws DBException {
        return null;
    }

    @Override
    public List<Flight> read(String departure) throws DBException {
        return null;
    }

    @Override
    public List<Flight> read(Date date) throws DBException {
        return null;
    }

    @Override
    public List<Flight> read(Brigade brigade) throws DBException {
        return null;
    }

    @Override
    public List<Flight> read(Brigade brigade, Staff staff) throws DBException {
        return null;
    }

    @Override
    public List<Flight> read(FlightStatus flightStatus) throws DBException {
        return null;
    }

    @Override
    public List<Flight> readAll() throws DBException {
        return null;
    }

    @Override
    public boolean update(Flight flight) throws DBException {
        return false;
    }

    @Override
    public boolean update(FlightStatus flightStatus, Brigade brigade) throws DBException {
        return false;
    }

    @Override
    public boolean delete(Flight flight) throws DBException {
        return false;
    }
}
