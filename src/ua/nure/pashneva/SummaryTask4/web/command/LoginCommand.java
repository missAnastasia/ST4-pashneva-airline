package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Role;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Command for proceed user log in.
 *
 * @author Anastasia Pashneva
 */
public class LoginCommand extends Command {

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

        String locale = (String) Config.get(session, Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

		String login = request.getParameter("login");
		LOG.trace("Parameter login --> " + login);

        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
		    String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.empty_fields");
			throw new AppException(message);
		}

		User user = null;
		try {
			user = DAOFactory.getInstance().getUserDAO().readByLogin(login);
		} catch (Exception e) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.cannot_load_data_source");
            throw new AppException(message);
		}
		LOG.trace("Found in DB: user --> " + user);

		if (user == null) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.cannot_find_user_with_login");
			throw new AppException(message);
		}

        if (!password.equals(user.getPassword())) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.wrong_password");
            throw new AppException(message);
        }

		Role userRole = user.getRole();
        SessionManager.storeAuthorizedUser(session, user, userRole);
        LOG.trace("Set the session attribute user --> " + user);
        LOG.trace("Set the session attribute userRole --> " + userRole);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        String rememberMe = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMe);

        if (remember) {
            SessionManager.storeUserCookie(response, user);
        } else {
            SessionManager.deleteUserCookie(response);
        }
        LOG.debug("Command finished");

        if (userRole == Role.ADMIN) {
            response.sendRedirect(Path.COMMAND_ADMIN_FLIGHTS);
        }

        if (userRole == Role.DISPATCHER) {
            response.sendRedirect(Path.COMMAND_DISPATCHER_FLIGHTS);
        }

        if (userRole == Role.STAFF) {
            response.sendRedirect(Path.COMMAND_STAFF_FLIGHTS);
		}
    }
}