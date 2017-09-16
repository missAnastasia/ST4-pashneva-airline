package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestToAdmin;
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
 * Command for changing message of request to admin in database.
 *
 * @author Anastasia Pashneva
 */
public class ChangeDispatcherRequestMessageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeDispatcherRequestMessageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String requestId = request.getParameter("request_id");
        LOG.trace("Parameter request_id --> " + requestId);

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }
        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            if (requestId != null && !(requestId.isEmpty())) {
                RequestToAdmin requestToAdmin = DAOFactory.getInstance().getRequestToAdminDAO().read(Integer.parseInt(requestId), language);
                if (requestToAdmin != null) {
                    String message = request.getParameter("message");
                    if (message != null && !(message.isEmpty())) {
                        requestToAdmin.setMessage(message);
                        LOG.trace("Request to admin --> " + requestToAdmin);
                        DAOFactory.getInstance().getRequestToAdminDAO().update(requestToAdmin);
                        LOG.info("Request to admin updated in DB --> " + requestToAdmin);
                    }
                }
            }
        } catch (Exception e) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.failed_update_request");
            throw new AppException(message);
        }
        LOG.debug("Command finished");
        response.sendRedirect(Path.COMMAND_DISPATCHER_REQUEST_INFO + requestId);
    }
}
