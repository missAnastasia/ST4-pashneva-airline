package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetHomePageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetHomePageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOG.debug("Command starts");

        response.sendRedirect(Path.PAGE_HOME);
        LOG.debug("Command finished");
    }
}
