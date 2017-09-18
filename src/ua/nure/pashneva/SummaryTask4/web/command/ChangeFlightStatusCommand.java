package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
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

/**
 * Command for changing flight status of flight in database.
 *
 * @author Anastasia Pashneva
 */
public class ChangeFlightStatusCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeFlightStatusCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String flightId = request.getParameter( "flight_id");
        LOG.trace("Parameter flight_id --> " + flightId);

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            if (flightId != null && !(flightId.isEmpty())) {
                int flightStatusId = Integer.parseInt(request.getParameter("new_flight_status_id"));
                LOG.trace("Parameter new_flight_status_id --> " + flightStatusId);
                FlightStatus flightStatus = DAOFactory.getInstance().getFlightStatusDAO().read(language, flightStatusId);

                Flight flight = DAOFactory.getInstance().getFlightDAO().read(Integer.parseInt(flightId), language);
                if (flight != null) {
                    flight.setFlightStatus(flightStatus);
                    LOG.trace("Flight status --> " + flightStatus);
                    DAOFactory.getInstance().getFlightDAO().updateStatus(flight, language);
                    LOG.info("Flight updated in DB --> " + flight);
                }
            }
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.COMMAND_DISPATCHER_FLIGHT_INFO).forward(request, response);
    }
}
