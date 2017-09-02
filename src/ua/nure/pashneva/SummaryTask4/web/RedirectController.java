package ua.nure.pashneva.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.web.command.Command;
import ua.nure.pashneva.SummaryTask4.web.command.CommandContainer;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RedirectController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(RedirectController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        process(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command --> " + command);

        StringBuilder path = new StringBuilder(Path.COMMAND + commandName);

        String compare = request.getParameter("compare");
        if (compare != null && !(compare.isEmpty())) {
            path.append("&compare=").append(compare);
        }

        LOG.trace("Path --> " + path);
        response.sendRedirect(path.toString());
    }
}