package ua.nure.pashneva.SummaryTask4.db.entity.search;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightSearcher implements Searcher<Flight> {

    private static final Logger LOG = Logger.getLogger(FlightSearcher.class);

    public List<Flight> search(Language language, Map<String, String> params) throws Exception {
        List<Flight> flights = new ArrayList<>();
        LOG.trace("Params --> " + params.toString());

        LOG.debug("for starts");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            LOG.trace("entry.getKey() --> " + entry.getKey());
            switch (entry.getKey()) {
                case "from_city" : {
                    LOG.trace("entry.getValue() --> " + entry.getValue());
                    flights.addAll(searchByDeparturePoint(language, entry.getValue()));
                    break;
                }

                case "to_city" : {
                    LOG.trace("entry.getValue() --> " + entry.getValue());
                    flights.addAll(searchByArrivalPoint(language, entry.getValue()));
                    break;
                }

                case "departure_date" : {
                    LOG.trace("entry.getValue() --> " + entry.getValue());
                    flights.addAll(searchByDepartureDate(language, entry.getValue()));
                    break;
                }

                case "flight_number" : {
                    LOG.trace("entry.getValue() --> " + entry.getValue());
                    Flight temp = searchByNumber(language, entry.getValue());
                    if (temp != null) {
                        flights.add(temp);
                    }
                    break;

                }

                case "flight_status_id" : {
                    LOG.trace("entry.getValue() --> " + entry.getValue());
                    flights.addAll(searchByFlightStatus(language, entry.getValue()));
                    break;
                }
            }
        }
        LOG.trace("Flights --> " + flights.toString());

        if (params.size() > 1 && flights.size() > 0) {
            Set<Flight> flightSet = new HashSet<>();
            LOG.debug("flightSet --> " + flightSet.toString());
            LOG.debug("flights size --> " + flights.size());

            for (int i = 0; i < flights.size(); i++) {
                int count = 0;
                LOG.debug("for i = " + i + ", count --> " + count);
                for (int j = 0; j < flights.size(); j++) {
                    LOG.debug("for j = " + j);
                    if (flights.get(i).equals(flights.get(j))) {
                        count++;
                        LOG.debug("count --> " + count);
                    }
                }

                if (count == params.size()) {
                    LOG.debug("if count < " + params.size());
                    flightSet.add(flights.get(i));
                    LOG.debug("flightSet.add " + flights.get(i).toString());
                }
            }

            LOG.trace("Flight Set --> " + flightSet.toString());
            flights = new ArrayList<>(flightSet);
        }

        LOG.trace("Flights --> " + flights.toString());
        return flights;
    }

    private Flight searchByNumber(Language language, String number) throws Exception {
        return DAOFactory.getInstance().getFlightDAO().readByNumber(number, language);
    }

    private List<Flight> searchByFlightStatus(Language language, String statusId) throws Exception {
        return DAOFactory.getInstance().getFlightDAO().readByStatus(Integer.parseInt(statusId), language);
    }

    private List<Flight> searchByDeparturePoint(Language language, String departurePoint) throws Exception {
        return DAOFactory.getInstance().getFlightDAO().readByDeparturePoint(departurePoint, language);
    }

    private List<Flight> searchByArrivalPoint(Language language, String arrivalPoint) throws Exception {
        return DAOFactory.getInstance().getFlightDAO().readByArrivalPoint(arrivalPoint, language);
    }

    private List<Flight> searchByDepartureDate(Language language, String departureDate) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(departureDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return DAOFactory.getInstance().getFlightDAO().readByDate(sqlDate, language);
        /*Pattern pattern = Pattern.compile("^[0-9]{4}[-][0-9]{2}[-][0-9]{2}$");
        Matcher matcher = pattern.matcher(departureDate);
        if (matcher.find()) {
            return DAOFactory.getInstance().getFlightDAO().readByDate(departureDate, language);
        }
        else {
            return new ArrayList<>();
        }*/
    }
}
