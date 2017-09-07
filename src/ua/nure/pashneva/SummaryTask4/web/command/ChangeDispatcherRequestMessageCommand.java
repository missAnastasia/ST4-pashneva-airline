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
import java.util.Date;
import java.util.ResourceBundle;

public class ChangeDispatcherRequestMessageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeDispatcherRequestMessageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.trace("Command starts");
        String requestId = request.getParameter("request_id");
        try {
            String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
            if (locale == null) {
                locale = request.getLocale().getLanguage();
                LOG.trace("Current locale --> " + locale);
            }
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);
            LOG.trace("Language --> " + language);
            if (requestId != null && !(requestId.isEmpty())) {
                RequestToAdmin requestToAdmin = DAOFactory.getInstance().getRequestToAdminDAO().read(Integer.parseInt(requestId), language);
                if (requestToAdmin != null) {
                    String message = request.getParameter("message");
                    if (message != null && !(message.isEmpty())) {
                        requestToAdmin.setMessage(message);
                        DAOFactory.getInstance().getRequestToAdminDAO().update(requestToAdmin);
                    }
                }
            }
        } catch (Exception e) {
            String message = ResourceBundle.getBundle("resources", request.getLocale())
                    .getString("message.error.failed_update_request");
            throw new AppException(message);
        }
        LOG.trace("Command finished");
        //request.getRequestDispatcher(Path.COMMAND_GET_DISPATCHER_REQUEST_INFO).forward(request, response);
        response.sendRedirect(Path.COMMAND_GET_DISPATCHER_REQUEST_INFO + requestId);
    }
}
