package ua.nure.pashneva.SummaryTask4.db.entity.search;

/**
 * Abstract factory for obtaining SearcherFactory implementations.
 *
 * @author Anastasia Pashneva
 */
public class SearcherFactory {
    /**
     * A private class field that stores the SearcherFactory object.
     */
    private static SearcherFactory instance;

    /**
     * Constructor for the ability to extend this class.
     */
    protected SearcherFactory() {
    }

    /**
     * Method for obtaining the SearcherFactory object.
     *
     * @return an instance of the SearcherFactory.
     * @throws Exception
     */
    public static synchronized SearcherFactory getInstance() throws Exception {
        if (instance == null) {
            instance = new SearcherFactory();
        }
        return instance;
    }

    /**
     * Method for obtaining Searcher for the Flight entity.
     *
     * @return The implementation of the FlightSearcher.
     */
    public FlightSearcher getFlightSearcher() {
        return new FlightSearcher();
    }

    /**
     * Method for obtaining Searcher for the Staff entity.
     *
     * @return The implementation of the StaffSearcher.
     */
    public StaffSearcher getStaffSearcher() {
        return new StaffSearcher();
    }
}
