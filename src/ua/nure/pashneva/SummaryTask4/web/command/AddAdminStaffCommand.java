package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.*;
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
 * Command for adding new staff to database.
 *
 * @author Anastasia Pashneva
 */
public class AddAdminStaffCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AddAdminStaffCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String login = request.getParameter("login");
        LOG.trace("Parameter login --> " + login);
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        LOG.trace("Parameter first_name --> " + firstName);
        String secondName = request.getParameter("second_name");
        LOG.trace("Parameter second_name --> " + secondName);
        String postId = request.getParameter("post_id");
        LOG.trace("Parameter post_id --> " + postId);

        if (login == null || password == null || login.isEmpty() || password.isEmpty() ||
                firstName == null || firstName.isEmpty() || secondName == null || secondName.isEmpty() ||
                postId == null || postId.isEmpty()) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.empty_fields");
            throw new AppException(message);
        }

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            Post post = DAOFactory.getInstance().getPostDAO().read(language, Integer.parseInt(postId));
            LOG.trace("Post --> " + post);

            User user = new User(login, password, firstName, secondName, Role.STAFF);
            LOG.trace("User --> " + user);

            if (DAOFactory.getInstance().getUserDAO().create(user)) {
                Staff staff = new Staff(user, post);
                LOG.trace("Staff --> " + staff);
                DAOFactory.getInstance().getStaffDAO().create(staff);
                LOG.info("Staff inserted into DB --> " + staff);
            }
        } catch (Exception e) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.failed_add_staff");
            throw new AppException(message);
        }
        LOG.debug("Command finished");
        response.sendRedirect(Path.COMMAND_ADMIN_STAFF);
    }
}
