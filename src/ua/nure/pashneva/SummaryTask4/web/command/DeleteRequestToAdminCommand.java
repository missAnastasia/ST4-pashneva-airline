package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestToAdmin;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

public class DeleteRequestToAdminCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteRequestToAdminCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.trace("Command starts");
        String requestId = request.getParameter("request_id");
        LOG.trace("requestId --> " + requestId);
        if (requestId != null && !(requestId.isEmpty())) {
                try {
                    DAOFactory.getInstance().getRequestToAdminDAO().delete(Integer.parseInt(requestId));
                } catch (Exception e) {
                    String message = ResourceBundle.getBundle("resources", request.getLocale())
                            .getString("message.error.failed_delete_request");
                    throw new AppException(message);
                }
        }

        LOG.trace("Command finished");
        response.sendRedirect(Path.COMMAND_DISPATCHER_REQUESTS);
    }
}
