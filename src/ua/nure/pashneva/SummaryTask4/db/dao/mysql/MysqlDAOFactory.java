package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.dao.LanguageDAO;
import ua.nure.pashneva.SummaryTask4.db.dao.UserDAO;

/**
 * Realization of DAOFactory for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
 */
public class MysqlDAOFactory extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new MysqlUserDAO();
    }

    @Override
    public LanguageDAO getLanguageDAO() {
        return new MysqlLanguageDAO();
    }

}
