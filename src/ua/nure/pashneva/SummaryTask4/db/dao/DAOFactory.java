package ua.nure.pashneva.SummaryTask4.db.dao;

/**
 * Abstract factory for obtaining DAOFactory implementations.<br/>
 * This class allows to switch from one database to another.
 *
 * @author Anastasia Pashneva
 *
 */
public abstract class DAOFactory {

    /**
     * A private class field that stores the DAOFactory object.
     */
    private static DAOFactory instance;

    /**
     * Contains the full qualified class name whose object will return {@link #getInstance()}.
     */
    private static String daoFactoryFCN;

    /**
     * Constructor for the ability to extend this class.
     */
    protected DAOFactory() {
    }

    public static void setDaoFactoryFCN(String daoFactoryFCN) {
        instance = null;
        DAOFactory.daoFactoryFCN = daoFactoryFCN;
    }

    /**
     * Method for obtaining the DAOFactory object.
     * The factory settings determines which DAOFactory implementation will be returned.
     *
     * @return an instance of the DAOFactory child whose name is contained in {@link #daoFactoryFCN}.
     * @throws Exception
     */
    public static synchronized DAOFactory getInstance() throws Exception {
        if (instance == null) {
            Class<?> clazz = Class.forName(DAOFactory.daoFactoryFCN);
            instance = (DAOFactory) clazz.newInstance();
        }
        return instance;
    }

    /**
     * Method for obtaining DAO for the User entity.
     *
     * @return The implementation of the UserDAO
     *          which is determined by the factory settings {@link DAOFactory}.
     */
    public abstract UserDAO getUserDAO();
    public abstract LanguageDAO getLanguageDAO();
    public abstract AircraftDAO getAircraftDAO();
    public abstract StaffDAO getStaffDAO();
    public abstract BrigadeDAO getBrigadeDAO();
    public abstract FlightDAO getFlightDAO();
    public abstract RequestToAdminDAO getRequestToAdminDAO();
    public abstract FlightStatusDAO getFlightStatusDAO();
    public abstract RequestStatusDAO getRequestStatusDAO();
    public abstract PostDAO getPostDAO();

}