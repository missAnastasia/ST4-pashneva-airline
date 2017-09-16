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

public class AddDispatcherRequestCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AddDispatcherRequestCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.trace("Command starts");
        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }
        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);
            LOG.trace("Language --> " + language);

            String requestMessage = request.getParameter("message");
            LOG.trace("requestMessage --> " + requestMessage);
            if (requestMessage != null && !(requestMessage.isEmpty())) {
                RequestToAdmin requestToAdmin = new RequestToAdmin();
                requestToAdmin.setMessage(requestMessage);

                Date currentDate = new Date();
                java.sql.Date date = new java.sql.Date(currentDate.getTime());
                requestToAdmin.setDate(date);
                LOG.trace("Date --> " + requestToAdmin.getDate());

                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                StringBuilder number = new StringBuilder();
                number.append(dateFormat.format(currentDate));
                LOG.trace("Number --> " + number.toString());
                int requestsByDateCount = DAOFactory.getInstance().getRequestToAdminDAO().readByDate(date, language).size();
                if (requestsByDateCount < 10) {
                    number.append("0");
                    LOG.trace("Number --> " + number.toString());
                }
                number.append(requestsByDateCount + 1);
                LOG.trace("Number --> " + number.toString());
                requestToAdmin.setNumber(Integer.parseInt(number.toString()));
                LOG.trace("Number --> " + requestToAdmin.getNumber());

                requestToAdmin.setUser(SessionManager.getAuthorizedUser(request.getSession()));
                LOG.trace("Request to admin from user --> " + requestToAdmin.getUser());

                requestToAdmin.setRequestStatus(DAOFactory.getInstance().getRequestStatusDAO().read(language, 3));
                LOG.trace("Request status --> " + requestToAdmin.getRequestStatus());

                LOG.trace("New Request to admin --> " + requestToAdmin);
                DAOFactory.getInstance().getRequestToAdminDAO().create(requestToAdmin);
                LOG.trace("Inserted into db --> " + requestToAdmin);
            }

        } catch (Exception e) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.failed_add_request");
            throw new AppException(message);
        }
        LOG.trace("Command finished");
        response.sendRedirect(Path.COMMAND_DISPATCHER_REQUESTS);
    }
}
