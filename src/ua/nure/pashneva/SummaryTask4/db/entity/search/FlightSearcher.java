package ua.nure.pashneva.SummaryTask4.db.entity.search;

import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Realization of Searcher for Flight entity.
 *
 * @author Anastasia Pashneva
 */
public class FlightSearcher implements Searcher<Flight> {

    @Override
    public List<Flight> search(Language language, Map<String, String> params) throws Exception {
        List<Flight> flights = new ArrayList<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "from_city" : {
                    flights.addAll(searchByDeparturePoint(language, entry.getValue()));
                    break;
                }
                case "to_city" : {
                    flights.addAll(searchByArrivalPoint(language, entry.getValue()));
                    break;
                }
                case "departure_date" : {
                    flights.addAll(searchByDepartureDate(language, entry.getValue()));
                    break;
                }
                case "flight_number" : {
                    Flight temp = searchByNumber(language, entry.getValue());
                    if (temp != null) {
                        flights.add(temp);
                    }
                    break;
                }
                case "flight_status_id" : {
                    flights.addAll(searchByFlightStatus(language, entry.getValue()));
                    break;
                }
            }
        }

        if (params.size() > 1 && flights.size() > 0) {
            Set<Flight> flightSet = new HashSet<>();

            for (int i = 0; i < flights.size(); i++) {
                int count = 0;
                for (int j = 0; j < flights.size(); j++) {
                    if (flights.get(i).equals(flights.get(j))) {
                        count++;
                    }
                }

                if (count == params.size()) {
                    flightSet.add(flights.get(i));
                }
            }
            flights = new ArrayList<>(flightSet);
        }
        return flights;
    }

    /**
     * Private method for obtaining flight by its number.
     *
     * @param language object of Language class which contains data of current locale.
     * @param number string which contains value of flight number.
     * @return object of Flight class which was obtained from database.
     * @throws Exception
     */
    private Flight searchByNumber(Language language, String number) throws Exception {
        return DAOFactory.getInstance().getFlightDAO().readByNumber(number, language);
    }

    /**
     * Private method for obtaining flights by its flight status identifier.
     *
     * @param language object of Language class which contains data of current locale.
     * @param statusId string which contains value of flight status identifier.
     * @return collection (List) of obtained from database Flight objects.
     * @throws Exception
     */
    private List<Flight> searchByFlightStatus(Language language, String statusId) throws Exception {
        return DAOFactory.getInstance().getFlightDAO().readByStatus(Integer.parseInt(statusId), language);
    }

    /**
     * Private method for obtaining flights by its departure point.
     *
     * @param language object of Language class which contains data of current locale.
     * @param departurePoint string which contains value of flight departure point.
     * @return collection (List) of obtained from database Flight objects.
     * @throws Exception
     */
    private List<Flight> searchByDeparturePoint(Language language, String departurePoint) throws Exception {
        return DAOFactory.getInstance().getFlightDAO().readByDeparturePoint(departurePoint, language);
    }

    /**
     * Private method for obtaining flights by its arrival point.
     *
     * @param language object of Language class which contains data of current locale.
     * @param arrivalPoint string which contains value of flight arrival point.
     * @return collection (List) of obtained from database Flight objects.
     * @throws Exception
     */
    private List<Flight> searchByArrivalPoint(Language language, String arrivalPoint) throws Exception {
        return DAOFactory.getInstance().getFlightDAO().readByArrivalPoint(arrivalPoint, language);
    }

    /**
     * Private method for obtaining flights by its departure date.
     *
     * @param language object of Language class which contains data of current locale.
     * @param departureDate string which contains value of flight departure date.
     * @return collection (List) of obtained from database Flight objects.
     * @throws Exception
     */
    private List<Flight> searchByDepartureDate(Language language, String departureDate) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(departureDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return DAOFactory.getInstance().getFlightDAO().readByDate(sqlDate, language);
    }
}
