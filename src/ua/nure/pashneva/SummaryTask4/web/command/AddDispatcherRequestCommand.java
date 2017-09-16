package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestToAdmin;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Command for adding new request to admin to database.
 *
 * @author Anastasia Pashneva
 */
public class AddDispatcherRequestCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AddDispatcherRequestCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String requestMessage = request.getParameter("message");
        LOG.trace("Parameter message --> " + requestMessage);

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            if (requestMessage != null && !(requestMessage.isEmpty())) {
                RequestToAdmin requestToAdmin = new RequestToAdmin();
                requestToAdmin.setMessage(requestMessage);

                Date currentDate = new Date();
                java.sql.Date date = new java.sql.Date(currentDate.getTime());
                requestToAdmin.setDate(date);

                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                StringBuilder number = new StringBuilder();
                number.append(dateFormat.format(currentDate));

                int requestsByDateCount = DAOFactory.getInstance().getRequestToAdminDAO().readByDate(date, language).size();
                if (requestsByDateCount < 10) {
                    number.append("0");
                }

                number.append(requestsByDateCount + 1);
                requestToAdmin.setNumber(Integer.parseInt(number.toString()));
                requestToAdmin.setUser(SessionManager.getAuthorizedUser(request.getSession()));
                requestToAdmin.setRequestStatus(DAOFactory.getInstance().getRequestStatusDAO().read(language, 3));
                LOG.trace("Request to admin --> " + requestToAdmin);

                DAOFactory.getInstance().getRequestToAdminDAO().create(requestToAdmin);
                LOG.info("Request to admin inserted into db --> " + requestToAdmin);
            }
        } catch (Exception e) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.failed_add_request");
            throw new AppException(message);
        }
        LOG.debug("Command finished");
        response.sendRedirect(Path.COMMAND_DISPATCHER_REQUESTS);
    }
}
