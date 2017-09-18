package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.mail.MailManager;
import ua.nure.pashneva.SummaryTask4.web.util.Path;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Command for preparing to change user password in database
 * by sending confirmation message to user e-mail address.
 *
 * @author Anastasia Pashneva
 */
public class ChangePasswordCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangePasswordCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String newPassword = request.getParameter("new_password");
        String oldPassword = request.getParameter("old_password");
        if (oldPassword == null || oldPassword.isEmpty() ||
                newPassword == null || newPassword.isEmpty()) {
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

        if (newPassword.equals(oldPassword)) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.same_password");
            throw new AppException(message);
        }

        user.setPassword(newPassword);

        try {
            SessionManager.storeUserToConfirmNewPassword(request.getSession(), user);
            LOG.trace("Stored into session: user --> " + user);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }

        MailManager.sendNewPasswordConfirmationMail(user.getLogin(), request);
        LOG.info("Sent email to --> " + user.getLogin());

        LOG.debug("Command finished");
        response.sendRedirect(Path.COMMAND_MESSAGE_SUCCESS +
                ResourceBundle.getBundle("resources", new Locale(locale))
                        .getString("message.success.confirm_registration"));
    }
}
