package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.comparator.ComparatorFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.search.SearchFactory;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetDispatcherFlightsPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetDispatcherFlightsPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        try {
            String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
            if (locale == null) {
                locale = request.getLocale().getLanguage();
                LOG.trace("Current locale --> " + locale);
            }
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);
            LOG.trace("Language --> " + language);

            String number = request.getParameter("flight_number");
            LOG.trace("Flight number --> " + number);
            String fromCity = request.getParameter("from_city");
            LOG.trace("From city --> " + fromCity);
            String toCity = request.getParameter("to_city");
            LOG.trace("To city --> " + toCity);
            String date = request.getParameter("departure_date");
            LOG.trace("Departure date --> " + date);

            List<Flight> flights = null;

            if ((number == null || number.isEmpty()) && (fromCity == null || fromCity.isEmpty()) &&
                    (toCity == null || toCity.isEmpty()) && (date == null || date.isEmpty())) {
                flights = DAOFactory.getInstance().getFlightDAO().readAll(language);
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
                flights = SearchFactory.getInstance().getFlightSearcher().search(language, params);
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
            }
            LOG.trace("Flights --> " + flights.toString());

            String compare = request.getParameter("compare");
            if (compare != null && !(compare.isEmpty())) {
                Comparator<Flight> comparator = ComparatorFactory.getInstance().getFlightComparator(compare);
                if (comparator != null) {
                    flights.sort(comparator);
                    request.setAttribute("compare", compare);
                }
            }
            if (flights.size() == 0) {
                String message = ResourceBundle.getBundle("resources", new Locale(locale))
                        .getString("message.error.cannot_find_entity");
                request.setAttribute("message", message);
            }
            LOG.trace("Flights --> " + flights.toString());
            request.setAttribute("flights", flights);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        request.getRequestDispatcher(Path.PAGE_DISPATCHER_FLIGHTS).forward(request, response);
        LOG.debug("Command finished");
    }
}
