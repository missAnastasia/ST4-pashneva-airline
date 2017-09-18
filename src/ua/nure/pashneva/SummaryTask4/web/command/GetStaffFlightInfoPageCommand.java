package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.FlightStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;

/**
 * Command for obtaining staffFlightInfoView.jsp.
 *
 * @author Anastasia Pashneva
 */
public class GetStaffFlightInfoPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetStaffFlightInfoPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String flightId = request.getParameter("flight_id");
        LOG.trace("Parameter flight_id --> " + flightId);

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            Flight flight = new Flight();
            if (flightId != null && !(flightId.isEmpty())) {
                flight = DAOFactory.getInstance().getFlightDAO().read(Integer.parseInt(flightId), language);
            }

            LOG.trace("Attribute flight --> " + flight);
            request.setAttribute("flight", flight);

            List<FlightStatus> statuses = DAOFactory.getInstance().getFlightStatusDAO().readAll(language);
            LOG.trace("Attribute flight_statuses --> " + statuses);
            request.setAttribute("flight_statuses", statuses);

            List<Brigade> brigades = DAOFactory.getInstance().getBrigadeDAO().readAll(language);
            LOG.trace("Attribute brigades --> " + brigades);
            request.setAttribute("brigades", brigades);

            LOG.info("Flight to obtain info --> " + flight);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_STAFF_FLIGHT_INFO).forward(request, response);
    }
}
