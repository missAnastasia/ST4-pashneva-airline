package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteAdminFlightCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteAdminFlightCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.trace("Command starts");
        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }
        String flightId = request.getParameter("flight_id");
        LOG.trace("flightId --> " + flightId);
        if (flightId != null && !(flightId.isEmpty())) {
            try {
                DAOFactory.getInstance().getFlightDAO().delete(Integer.parseInt(flightId));
            } catch (Exception e) {
                String message = ResourceBundle.getBundle("resources", new Locale(locale))
                        .getString("message.error.failed_delete_flight");
                throw new AppException(message);
            }
        }

        LOG.trace("Command finished");
        response.sendRedirect(Path.COMMAND_ADMIN_FLIGHTS);
    }
}
