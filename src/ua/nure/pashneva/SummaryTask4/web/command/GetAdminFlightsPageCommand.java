package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.FlightStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.comparator.ComparatorFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.search.SearcherFactory;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.*;

/**
 * Command for obtaining adminFlightsView.jsp.
 *
 * @author Anastasia Pashneva
 */
public class GetAdminFlightsPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetAdminFlightsPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String number = request.getParameter("flight_number");
        LOG.trace("Parameter flight_number --> " + number);
        String fromCity = request.getParameter("from_city");
        LOG.trace("Parameter from_city --> " + fromCity);
        String toCity = request.getParameter("to_city");
        LOG.trace("Parameter to_city --> " + toCity);
        String date = request.getParameter("departure_date");
        LOG.trace("Parameter departure_date --> " + date);
        String statusId = request.getParameter("flight_status_id");
        LOG.trace("Parameter flight_status_id --> " + statusId);

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            List<Flight> flights = null;
            if ((number == null || number.isEmpty()) && (fromCity == null || fromCity.isEmpty()) &&
                    (toCity == null || toCity.isEmpty()) && (date == null || date.isEmpty()) &&
                    (statusId == null || statusId.isEmpty())) {
                flights = DAOFactory.getInstance().getFlightDAO().readAll(language);
                if (flights.size() == 0) {
                    String message = ResourceBundle.getBundle("resources", new Locale(locale))
                            .getString("flights_admin_jsp.no_flights");
                    request.setAttribute("message", message);
                }
            } else {
                Map<String, String> params = new HashMap<>();
                if (number != null && !(number.isEmpty())) {
                    params.put("flight_number", number);
                }

                if (fromCity != null && !(fromCity.isEmpty())) {
                    params.put("from_city", fromCity);
                }

                if (toCity != null && !(toCity.isEmpty())) {
                    params.put("to_city", toCity);
                }

                if (date != null && !(date.isEmpty())) {
                    params.put("departure_date", date);
                }

                if (statusId != null && !(statusId.isEmpty())) {
                    params.put("flight_status_id", statusId);
                }

                flights = SearcherFactory.getInstance().getFlightSearcher().search(language, params);
                if (flights.size() == 0) {
                    String message = ResourceBundle.getBundle("resources", new Locale(locale))
                            .getString("message.error.cannot_find_entity");
                    request.setAttribute("message", message);
                }

                for (Map.Entry<String, String> entry : params.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
            }

            Map<String, String> compareValues = new HashMap<>();
            compareValues.put("no_compare", ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("flights_admin_jsp.compare.no_compare"));
            compareValues.put("compare_by_number", ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("flights_admin_jsp.compare.by_number"));
            compareValues.put("compare_by_departure_point", ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("flights_admin_jsp.compare.by_departure_point"));
            compareValues.put("compare_by_arrival_point", ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("flights_admin_jsp.compare.by_arrival_point"));
            LOG.trace("Compare values --> " + compareValues.toString());
            request.setAttribute("compare_values", compareValues);

            String compare = request.getParameter("compare");
            if (compare != null && !(compare.isEmpty())) {
                Comparator<Flight> comparator = ComparatorFactory.getInstance().getFlightComparator(compare);
                if (comparator != null) {
                    flights.sort(comparator);
                    request.setAttribute("compare", compare);
                }
            }

            LOG.trace("Attribute flights --> " + flights);
            request.setAttribute("flights", flights);

            List<FlightStatus> statuses = DAOFactory.getInstance().getFlightStatusDAO().readAll(language);
            LOG.trace("Attribute statuses --> " + flights);
            request.setAttribute("statuses", statuses);

            LOG.info("Flights --> " + flights);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_ADMIN_FLIGHTS).forward(request, response);
    }
}
