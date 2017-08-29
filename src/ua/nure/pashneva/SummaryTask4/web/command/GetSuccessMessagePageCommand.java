package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetSuccessMessagePageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetSuccessMessagePageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String message = request.getParameter("message");
        LOG.trace("Request parameter: message --> " + message);
        request.setAttribute("message", message);
        request.getRequestDispatcher(Path.PAGE_SUCCESS_PAGE).forward(request, response);
        LOG.debug("Command finished");
    }
}
