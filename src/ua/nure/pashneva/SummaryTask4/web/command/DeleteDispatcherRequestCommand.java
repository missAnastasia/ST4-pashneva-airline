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

/**
 * Command for deleting request to admin from database.
 *
 * @author Anastasia Pashneva
 */
public class DeleteDispatcherRequestCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteDispatcherRequestCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String requestId = request.getParameter("request_id");
        LOG.trace("Parameter request_id --> " + requestId);

        if (requestId != null && !(requestId.isEmpty())) {
            try {
                DAOFactory.getInstance().getRequestToAdminDAO().delete(Integer.parseInt(requestId));
                LOG.info("Deleted from DB with requestId --> " + requestId);
            } catch (Exception e) {
                   String message = ResourceBundle.getBundle("resources", new Locale(locale))
                           .getString("message.error.failed_delete_request");
                throw new AppException(message);
            }
        }
        LOG.debug("Command finished");
        response.sendRedirect(Path.COMMAND_DISPATCHER_REQUESTS);
    }
}
