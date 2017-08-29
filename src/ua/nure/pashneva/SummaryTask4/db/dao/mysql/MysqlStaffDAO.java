package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.dao.StaffDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Position;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.Date;
import java.util.List;

/**
 * Class that implements StaffDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
 */
public class MysqlStaffDAO implements StaffDAO {

    /**
     * String fields which contain sql queries to tables staff,
     * users, positions, positions_lang of MySQL database.
     */
    /**
     * String fields which contain column names of staff,
     * users, positions, positions_lang.
     */


    @Override
    public boolean create(Staff staff) throws DBException {
        return false;
    }

    @Override
    public Staff read(int id) throws DBException {
        return null;
    }

    @Override
    public List<Staff> read(Position position) throws DBException {
        return null;
    }

    @Override
    public Staff read(String login) throws DBException {
        return null;
    }

    @Override
    public List<Staff> read(Date date) throws DBException {
        return null;
    }

    @Override
    public List<Staff> readAll() throws DBException {
        return null;
    }

    @Override
    public boolean update(Staff staff) throws DBException {
        return false;
    }

    @Override
    public boolean updatePassword(Staff staff) throws DBException {
        return false;
    }

    @Override
    public boolean updatePosition(Staff staff) throws DBException {
        return false;
    }

    @Override
    public boolean delete(Staff staff) throws DBException {
        return false;
    }
}
