package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

public class ChangeBrigadeCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeBrigadeCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.trace("Command starts");
        try {
            String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
            if (locale == null) {
                locale = request.getLocale().getLanguage();
                LOG.trace("Current locale --> " + locale);
            }
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);
            LOG.trace("Language --> " + language);
            String flightId = request.getParameter( "flight_id");
            LOG.trace("flightId --> " + flightId);
            if (flightId != null && !(flightId.isEmpty())) {
                int brigadeId = Integer.parseInt(request.getParameter("new_brigade_id"));
                LOG.trace("brigadeId --> " + brigadeId);
                Brigade brigade = DAOFactory.getInstance().getBrigadeDAO().read(brigadeId, language);
                LOG.trace("brigade --> " + brigade.toString());
                Flight flight = DAOFactory.getInstance().getFlightDAO().read(Integer.parseInt(flightId), language);
                if (flight != null) {
                    LOG.trace("flight --> " + flight.toString());
                    flight.setBrigade(brigade);
                    LOG.trace("new brigade --> " + brigade.toString());
                    DAOFactory.getInstance().getFlightDAO().updateBrigade(flight, language);
                    LOG.trace("updated in db --> " + flight.toString());
                }
            }
            LOG.trace("Command finished");
            request.getRequestDispatcher(Path.COMMAND_DISPATCHER_FLIGHT_INFO).forward(request, response);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
    }
}
