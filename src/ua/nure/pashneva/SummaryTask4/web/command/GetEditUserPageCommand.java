package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.entity.Role;
import ua.nure.pashneva.SummaryTask4.db.entity.UserStatus;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetEditUserPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetEditUserPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        request.setAttribute("roles", Role.values());
        request.setAttribute("statuses", UserStatus.values());
        request.getRequestDispatcher(Path.PAGE_EDIT_USER).forward(request, response);

        LOG.debug("Command finished");
    }
}
