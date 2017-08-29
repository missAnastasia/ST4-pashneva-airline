package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.dao.RequestToAdminDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.List;

/**
 * Class that implements RequestToAdminDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
 */
public class MysqlRequestToAdminDAO implements RequestToAdminDAO {

    /**
     * String fields which contain sql queries to tables requests_to_admin,
     * request_statuses, request_statuses_lang of MySQL database.
     */
    /**
     * String fields which contain column names of requests_to_admin,
     * request_statuses, request_statuses_lang.
     */


    @Override
    public boolean create(RequestToAdminDAO request) throws DBException {
        return false;
    }

    @Override
    public RequestToAdminDAO read(Integer id) throws DBException {
        return null;
    }

    @Override
    public List<RequestToAdminDAO> read(RequestStatus requestStatus) throws DBException {
        return null;
    }

    @Override
    public List<RequestToAdminDAO> read(User user) throws DBException {
        return null;
    }

    @Override
    public List<RequestToAdminDAO> readAll() throws DBException {
        return null;
    }

    @Override
    public boolean update(RequestToAdminDAO request) throws DBException {
        return false;
    }

    @Override
    public boolean updateStatus(RequestToAdminDAO request) throws DBException {
        return false;
    }

    @Override
    public boolean delete(RequestToAdminDAO request) throws DBException {
        return false;
    }
}
