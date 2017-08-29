package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.RequestStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.List;

/**
 * The data manipulation interface in the table request_to_admin.
 *
 * @author Anastasia Pashneva
 *
 */
public interface RequestToAdminDAO {
    /**
     * Method of creating a request to admin in the database. <br/>
     * After the method is executed the request object contains the value of the identifier (id),
     * this information comes from the DBMS side.
     *
     * @param request object that contains information about a request without an identifier.
     * @return true - request was successfully inserted into database, otherwise - false.
     * @throws DBException
     */
    boolean create(RequestToAdminDAO request) throws DBException;

    /**
     * Method of obtaining a request to admin from the database.
     *
     * @param id request identifier (primary unique key)
     * @return object which contains values of all fields from table request_to_admin.
     * @throws DBException
     */
    RequestToAdminDAO read(Integer id) throws DBException;

    /**
     * Method of obtaining a request to admin from the database.
     *
     * @param requestStatus object of RequestStatus enum which contains
     *                      value of request status (completed/rejected).
     * @return object which contains values of all fields from table request_to_admin.
     * @throws DBException
     */
    List<RequestToAdminDAO> read(RequestStatus requestStatus) throws DBException;

    /**
     * Method of obtaining a request to admin from the database.
     *
     * @param user user login (email address)
     * @return object which contains values of all fields from table request_to_admin.
     * @throws DBException
     */
    List<RequestToAdminDAO> read(User user) throws DBException;

    /**
     * Method of obtaining all requests to admin from the database.
     *
     * @return collection (List) of all users.
     * @throws DBException
     */
    List<RequestToAdminDAO> readAll() throws DBException;

    /**
     * Method of updating request to admin data in database. <br/>
     * All fields are updated except the requestStatus field.
     *
     * @param request object that contains request data.
     *             The identifier must be present.
     * @return true - request data was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean update(RequestToAdminDAO request) throws DBException;

    /**
     * Method of updating request status in database.<br/>
     * Only requestStatus field is updated.
     *
     * @param request object that contains request status data.
     *             The identifier must be present.
     * @return true - request status was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean updateStatus(RequestToAdminDAO request) throws DBException;

    /**
     * Method of deleting request to admin from database.
     *
     * @param request object that contains information about a request which must be deleted.
     * @return true - request was successfully deleted from database, otherwise - false.
     * @throws DBException
     */
    boolean delete(RequestToAdminDAO request) throws DBException;
}