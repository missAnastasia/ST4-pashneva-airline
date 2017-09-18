package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.comparator.ComparatorFactory;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.*;

/**
 * Command for obtaining staffFlightsView.jsp.
 *
 * @author Anastasia Pashneva
 */
public class GetStaffFlightsPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetStaffFlightsPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            List<Flight> flights = DAOFactory.getInstance().getFlightDAO()
                    .readByStaff(DAOFactory.getInstance().getStaffDAO()
                    .readByUserId(SessionManager.getAuthorizedUser(request.getSession())
                    .getId(), language), language);
            List<Flight> actualFlights = new ArrayList<>();
            for (Flight f : flights) {
                if (f.getFlightStatus().getId() != 6) {
                    actualFlights.add(f);
                }
            }

            if (actualFlights.size() == 0) {
                String message = ResourceBundle.getBundle("resources", new Locale(locale))
                        .getString("flights_staff_jsp.no_flights");
                request.setAttribute("message", message);
            }

            Comparator<Flight> comparator = ComparatorFactory.getInstance().getFlightComparator("compare_by_departure_date");
            if (comparator != null) {
                flights.sort(comparator);
            }

            LOG.trace("Attribute flights --> " + actualFlights);
            request.setAttribute("flights", actualFlights);

            LOG.info("Flights --> " + actualFlights);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        request.getRequestDispatcher(Path.PAGE_STAFF_FLIGHTS).forward(request, response);
        LOG.debug("Command finished");
    }
}
