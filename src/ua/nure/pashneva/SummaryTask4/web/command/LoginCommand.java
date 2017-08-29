package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Role;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.db.entity.UserStatus;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Login command.
 * 
 * @author D.Kolesnikov
 * 
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from a request

		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);

		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
		    String message = ResourceBundle.getBundle("resources", request.getLocale())
                    .getString("message.error.empty_fields");
			throw new AppException(message);
		}

		User user = null;
		try {
			user = DAOFactory.getInstance().getUserDAO().read(login);
		} catch (Exception e) {
            String message = ResourceBundle.getBundle("resources", request.getLocale())
                    .getString("message.error.cannot_load_data_source");
            throw new AppException(message);
		}
		LOG.trace("Found in DB: user --> " + user);

		if (user == null) {
            String message = ResourceBundle.getBundle("resources", request.getLocale())
                    .getString("message.error.cannot_find_user_with_login");
			throw new AppException(message);
		}

        if (!password.equals(user.getPassword())) {
            String message = ResourceBundle.getBundle("resources", request.getLocale())
                    .getString("message.error.wrong_password");
            throw new AppException(message);
        }

		Role userRole = user.getRole();
		LOG.trace("userRole --> " + userRole);

		if (user.getUserStatus().equals(UserStatus.BLOCKED)) {
			String message = ResourceBundle.getBundle("resources", request.getLocale())
					.getString("message.error.blocked_account");
			throw new AppException(message);
		}

        /*String userCaptchaResponse = request.getParameter("jcaptcha");
        boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);
        if(!captchaPassed){
            throw new AppException("Wrong captcha!");
        }*/

        SessionManager.storeLoginedUser(session, user, userRole);
        //session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        //session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember= "Y".equals(rememberMeStr);

        // If user checked "Remember me".
        if(remember)  {
            SessionManager.storeUserCookie(response,user);
        }

		// Else delete cookie.
		else  {
            SessionManager.deleteUserCookie(response);
        }

        LOG.debug("Command finished");

        // Sending email
        /*Sender sslSender = new Sender("miss.anastasia.1408@properties.com", "anastasia_main_mail_1408");
        sslSender.send("This is Subject", "SSL: This is text!", "miss.anastasia.1408@properties.com", "anastasiia.pashnieva@nure.ua");
*/
		/*response.sendRedirect(Path.PAGE_HOME);*/

        /*try {
            System.out.println(DAOFactory.getInstance().getCarDAO().readAll().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        if (userRole == Role.ADMIN) {
            response.sendRedirect(Path.COMMAND_ADMIN_USER_ACCOUNTS);

        }

        if (userRole == Role.CLIENT) {
            //forward = Path.COMMAND_LIST_MENU;
            response.sendRedirect(Path.COMMAND_CONDITIONS);
        }


    }

}