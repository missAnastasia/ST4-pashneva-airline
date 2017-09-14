package ua.nure.pashneva.SummaryTask4.db.entity.search;

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

    public FlightSearcher getFlightSearcher() {
        return new FlightSearcher();
    }
    public StaffSearcher getStaffSearcher() {
        return new StaffSearcher();
    }
}
