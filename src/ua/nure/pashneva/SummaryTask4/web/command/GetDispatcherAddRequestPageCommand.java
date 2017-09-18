package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for obtaining dispatcherAddRequestView.jsp.
 *
 * @author Anastasia Pashneva
 */
public class GetDispatcherAddRequestPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetDispatcherAddRequestPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        LOG.trace("Command finished");
        request.getRequestDispatcher(Path.PAGE_DISPATCHER_ADD_REQUEST).forward(request, response);
    }
}
