package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class UpdateUserInfoCommand extends Command {

    private static final Logger LOG = Logger.getLogger(UpdateUserInfoCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        // obtain login and password from a request
        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);

        /*String newPassword = request.getParameter("new_password");
        LOG.trace("Request parameter: newPassword --> " + newPassword);*/

        String oldPassword = request.getParameter("old_password");
        LOG.trace("Request parameter: oldPassword --> " + oldPassword);

        String firstName = request.getParameter("firstName");
        LOG.trace("Request parameter: firstName --> " + firstName);

        String secondName = request.getParameter("secondName");
        LOG.trace("Request parameter: secondName --> " + secondName);

        if (login == null || oldPassword == null || login.isEmpty() || oldPassword.isEmpty() ||
                firstName == null || secondName == null || firstName.isEmpty() || secondName.isEmpty()) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.empty_fields");
            throw new AppException(message);
        }

        User user = (User) request.getSession().getAttribute("user");

        if (!user.getPassword().equals(oldPassword)) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.wrong_password");
            throw new AppException(message);
        }

        user.setLogin(login);
        user.setFirstName(firstName);
        user.setSecondName(secondName);

        try {
            boolean success = DAOFactory.getInstance().getUserDAO().update(user);
            LOG.trace("Request parameter: update user --> " + success);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }

        LOG.trace("Update in DB: user --> " + user);

        LOG.debug("Command finished");
        response.sendRedirect(Path.COMMAND_USER_INFO);
    }
}
