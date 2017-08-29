package ua.nure.pashneva.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.command.Command;
import ua.nure.pashneva.SummaryTask4.web.command.CommandContainer;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet controller.
 * 
 * @author Anastasia Pashneva
 * 
 */
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 2423353715955164816L;

	private static final Logger LOG = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*process(request, response);*/
		doGet(request, response);
	}

	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);

		// execute command and get forward address
		//String forward = Path.PAGE_ERROR_PAGE;
		try {
			command.execute(request, response);
		} catch (AppException ex) {
			/*request.setAttribute("errorMessage", ex.getMessage());
			LOG.trace("Error previousPage --> " + request.getAttribute("previousPage"));

			request.getRequestDispatcher(forward).forward(request, response);*/
            LOG.trace("Exception message --> " + ex.getMessage());
			response.sendRedirect(Path.COMMAND_MESSAGE_ERROR + ex.getMessage());
		}
		LOG.debug("Controller finished");
		/*LOG.trace("Forward address --> " + forward);

		LOG.debug("Controller finished, now go to forward address --> " + forward);*/
		
		// go to forward
		/*request.getRequestDispatcher(forward).forward(request, response);*/
	}

}