package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * No command.
 * 
 * @author D.Kolesnikov
 * 
 */
public class NoCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;

	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AppException{
		LOG.debug("Command starts");
		
		/*String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		LOG.error("Set the request attribute: errorMessage --> " + errorMessage);*/

		String message = ResourceBundle.getBundle("resources", request.getLocale())
				.getString("message.error.no_such_command");
		LOG.debug("Command finished");
		throw new AppException(message);
	}

}