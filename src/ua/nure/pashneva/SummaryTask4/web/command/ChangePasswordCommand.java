package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

public class ChangePasswordCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangePasswordCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        // obtain login and password from a request


        String newPassword = request.getParameter("new_password");
        LOG.trace("Request parameter: newPassword --> " + newPassword);

        String oldPassword = request.getParameter("old_password");
        LOG.trace("Request parameter: oldPassword --> " + oldPassword);


        if (oldPassword == null || oldPassword.isEmpty() ||
                newPassword == null || newPassword.isEmpty()) {
            String message = ResourceBundle.getBundle("resources", request.getLocale())
                    .getString("message.error.empty_fields");
            throw new AppException(message);
        }

        User user = (User) request.getSession().getAttribute("user");

        if (!user.getPassword().equals(oldPassword)) {
            String message = ResourceBundle.getBundle("resources", request.getLocale())
                    .getString("message.error.wrong_password");
            throw new AppException(message);
        }

        if (newPassword.equals(oldPassword)) {
            String message = ResourceBundle.getBundle("resources", request.getLocale())
                    .getString("message.error.same_password");
            throw new AppException(message);
        }

        user.setPassword(newPassword);

        try {
            boolean success = DAOFactory.getInstance().getUserDAO().updatePassword(user);
            LOG.trace("Request parameter: update user --> " + success);
        } catch (Exception e) {
            LOG.debug("catch section");
            throw new AppException(e.getMessage());
        }

        LOG.trace("Update in DB: user --> " + user);

        LOG.debug("Command finished");
        response.sendRedirect(Path.COMMAND_USER_INFO);
    }
}
