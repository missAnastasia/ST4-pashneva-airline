package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestStatus;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.List;

/**
 * The data manipulation interface in the tables request_statuses and request_statuses_lang.
 *
 * @author Anastasia Pashneva
 *
 */
public interface RequestStatusDAO {
    /**
     * Method of obtaining a request status from the database.
     *
     * @param id request status identifier (primary unique key)
     * @param language object of Language class which contains data of current locale.
     * @return object which contains values of all fields from table request_statuses.
     * @throws DBException
     */
    RequestStatus read(Language language, Integer id) throws DBException;

    /**
     * Method of obtaining all request statuses from the database.
     *
     * @param language object of Language class which contains data of current locale.
     * @return object which contains values of all fields from table request_statuses.
     * @throws DBException
     */
    List<RequestStatus> readAll(Language language) throws DBException;

}
