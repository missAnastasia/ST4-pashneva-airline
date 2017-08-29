package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.dao.BrigadeDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.List;

/**
 * Class that implements BrigadeDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
 */
public class MysqlBrigadeDAO implements BrigadeDAO {

    /**
     * String fields which contain sql queries to table brigades of MySQL database.
     */
    /**
     * String fields which contain column names of table brigades.
     */

    @Override
    public boolean create(Brigade brigade) throws DBException {
        return false;
    }

    @Override
    public Brigade read(Integer id) throws DBException {
        return null;
    }

    @Override
    public Brigade read(Staff staff) throws DBException {
        return null;
    }

    @Override
    public List<Brigade> readAll() throws DBException {
        return null;
    }

    @Override
    public boolean update(Brigade brigade) throws DBException {
        return false;
    }

    @Override
    public boolean delete(Brigade brigade) throws DBException {
        return false;
    }
}
