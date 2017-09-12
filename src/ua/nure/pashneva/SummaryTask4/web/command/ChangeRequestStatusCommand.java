package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestStatus;
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

public class ChangeRequestStatusCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeRequestStatusCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.trace("Command starts");
        String requestId = request.getParameter("request_id");
        LOG.trace("requestId --> " + requestId);
        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }
        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);
            LOG.trace("Language --> " + language);
            if (requestId != null && !(requestId.isEmpty())) {
                RequestToAdmin requestToAdmin = DAOFactory.getInstance().getRequestToAdminDAO().read(Integer.parseInt(requestId), language);
                LOG.trace("requestToAdmin --> " + requestToAdmin);
                if (requestToAdmin != null) {
                    String statusId = request.getParameter("status_id");
                    LOG.trace("statusId --> " + statusId);
                    if (statusId != null && !(statusId.isEmpty())) {
                        RequestStatus requestStatus = DAOFactory.getInstance().getRequestStatusDAO().read(language, Integer.parseInt(statusId));
                        LOG.trace("requestStatus --> " + requestStatus);
                        if (requestStatus != null) {
                            requestToAdmin.setRequestStatus(requestStatus);
                            LOG.trace("requestToAdmin --> " + requestToAdmin);
                            DAOFactory.getInstance().getRequestToAdminDAO().updateStatus(requestToAdmin);
                        }
                    }
                }
            }
        } catch (Exception e) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.failed_update_request");
            throw new AppException(message);
        }
        LOG.trace("Command finished");
        //request.getRequestDispatcher(Path.COMMAND_DISPATCHER_REQUEST_INFO).forward(request, response);
        response.sendRedirect(Path.COMMAND_ADMIN_REQUEST_INFO + requestId);
    }
}
