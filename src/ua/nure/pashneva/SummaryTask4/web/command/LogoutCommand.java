package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.web.util.Path;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command for proceed user log out.
 *
 * @author Anastasia Pashneva
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
            SessionManager.deleteUserCookie(response);
		}
		LOG.debug("Command finished");
        response.sendRedirect(Path.PAGE_LOGIN);
	}
}