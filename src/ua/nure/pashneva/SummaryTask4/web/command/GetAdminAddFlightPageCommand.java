package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Aircraft;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAdminAddFlightPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetAdminAddFlightPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        try {
            List<Language> languages = DAOFactory.getInstance().getLanguageDAO().readAll();
            LOG.trace("Languages --> " + languages);
            request.setAttribute("languages", languages);
            List<Aircraft> aircraft = DAOFactory.getInstance().getAircraftDAO().readAll();
            LOG.trace("Aircraft --> " + aircraft);
            request.setAttribute("aircraft", aircraft);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_ADMIN_ADD_FLIGHT).forward(request, response);
    }
}
