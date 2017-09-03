package ua.nure.pashneva.SummaryTask4.db.entity.search;

import java.util.List;

public class SearchFactory {
    /**
     * A private class field that stores the SearchFactory object.
     */
    private static SearchFactory instance;

    /**
     * Constructor for the ability to extend this class.
     */
    protected SearchFactory() {
    }

    /**
     * Method for obtaining the SearchFactory object.
     *
     * @return an instance of the SearchFactory.
     * @throws Exception
     */
    public static synchronized SearchFactory getInstance() throws Exception {
        if (instance == null) {
            instance = new SearchFactory();
        }
        return instance;
    }

    public FlightSearcher getFlightSearcher() {
        return new FlightSearcher();
    }
}
