package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.db.entity.UserStatus;
import ua.nure.pashneva.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeUserStatusCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeUserStatusCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        // obtain login and password from a request
        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String newStatus = request.getParameter("new_status");
        LOG.trace("Request parameter: newStatus --> " + newStatus);

        if (newStatus == null || newStatus.isEmpty()) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.empty_fields");
            throw new AppException(message);
        }

        User user = (User) request.getSession().getAttribute("user");

        /*user.setUserStatus(UserStatus.valueOf(newStatus));*/

        try {
            //boolean success = DAOFactory.getInstance().getUserDAO().updateStatus(user);
            //LOG.trace("Request parameter: update user --> " + success);
        } catch (Exception e) {
            LOG.debug("catch section");
            throw new AppException(e.getMessage());
        }

        LOG.trace("Update in DB: user --> " + user);

        LOG.debug("Command finished");
        /*response.sendRedirect(Path.COMMAND_USER_INFO);*/
    }
}
