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

public class AddAdminStaffCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AddAdminStaffCommand.class);

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

            String login = request.getParameter("login");
            LOG.trace("Login --> " + login);
            String password = request.getParameter("password");
            LOG.trace("Password --> " + password);
            String firstName = request.getParameter("first_name");
            LOG.trace("First name --> " + firstName);
            String secondName = request.getParameter("second_name");
            LOG.trace("Second name --> " + secondName);
            String postId = request.getParameter("post_id");

            if (login == null || password == null || login.isEmpty() || password.isEmpty() ||
                    firstName == null || firstName.isEmpty() || secondName == null || secondName.isEmpty() ||
                    postId == null || postId.isEmpty()) {
                String message = ResourceBundle.getBundle("resources", new Locale(locale))
                        .getString("message.error.empty_fields");
                throw new AppException(message);
            }

            Post post = DAOFactory.getInstance().getPostDAO().read(language, Integer.parseInt(postId));
            LOG.trace("Post --> " + post);

            User user = new User(login, password, firstName, secondName, Role.STAFF);
            LOG.trace("User --> " + user);

            if (DAOFactory.getInstance().getUserDAO().create(user)) {
                Staff staff = new Staff(user, post);
                LOG.trace("Staff --> " + staff);
                DAOFactory.getInstance().getStaffDAO().create(staff);
            }
        } catch (Exception e) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.failed_add_staff");
            throw new AppException(message);
        }
        LOG.trace("Command finished");
        response.sendRedirect(Path.COMMAND_ADMIN_STAFF);
    }
}
