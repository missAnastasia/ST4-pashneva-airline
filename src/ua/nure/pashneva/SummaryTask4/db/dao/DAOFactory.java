package ua.nure.pashneva.SummaryTask4.db.dao;

/**
 * Abstract factory for obtaining DAOFactory implementations.<br/>
 * This class allows to switch from one database to another.
 *
 * @author Anastasia Pashneva
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

    /**
     * Method for obtaining DAO for the Language entity.
     *
     * @return The implementation of the LanguageDAO
     *          which is determined by the factory settings {@link DAOFactory}.
     */
    public abstract LanguageDAO getLanguageDAO();

    /**
     * Method for obtaining DAO for the Aircraft entity.
     *
     * @return The implementation of the AircraftDAO
     *          which is determined by the factory settings {@link DAOFactory}.
     */
    public abstract AircraftDAO getAircraftDAO();

    /**
     * Method for obtaining DAO for the Staff entity.
     *
     * @return The implementation of the StaffDAO
     *          which is determined by the factory settings {@link DAOFactory}.
     */
    public abstract StaffDAO getStaffDAO();

    /**
     * Method for obtaining DAO for the Brigade entity.
     *
     * @return The implementation of the BrigadeDAO
     *          which is determined by the factory settings {@link DAOFactory}.
     */
    public abstract BrigadeDAO getBrigadeDAO();

    /**
     * Method for obtaining DAO for the Flight entity.
     *
     * @return The implementation of the FlightDAO
     *          which is determined by the factory settings {@link DAOFactory}.
     */
    public abstract FlightDAO getFlightDAO();

    /**
     * Method for obtaining DAO for the RequestToAdmin entity.
     *
     * @return The implementation of the RequestToAdminDAO
     *          which is determined by the factory settings {@link DAOFactory}.
     */
    public abstract RequestToAdminDAO getRequestToAdminDAO();

    /**
     * Method for obtaining DAO for the FlightStatus entity.
     *
     * @return The implementation of the FlightStatusDAO
     *          which is determined by the factory settings {@link DAOFactory}.
     */
    public abstract FlightStatusDAO getFlightStatusDAO();

    /**
     * Method for obtaining DAO for the RequestStatus entity.
     *
     * @return The implementation of the RequestStatusDAO
     *          which is determined by the factory settings {@link DAOFactory}.
     */
    public abstract RequestStatusDAO getRequestStatusDAO();

    /**
     * Method for obtaining DAO for the Post entity.
     *
     * @return The implementation of the PostDAO
     *          which is determined by the factory settings {@link DAOFactory}.
     */
    public abstract PostDAO getPostDAO();

}