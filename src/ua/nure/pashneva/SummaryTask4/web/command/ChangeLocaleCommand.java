package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * Command for changing locale in current session.
 *
 * @author Anastasia Pashneva
 */
public class ChangeLocaleCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = request.getParameter("locale");
        LOG.trace("Parameter locale --> " + locale);
        if (locale != null || !locale.isEmpty()) {
            Config.set(request.getSession(), Config.FMT_LOCALE, locale);
        }
        request.getSession().setAttribute("currentLocale", locale);
        LOG.info("Current locale --> " + locale);

        LOG.debug("Command finished");
        response.sendRedirect(Path.PAGE_SETTINGS);
    }
}
