package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestToAdmin;
import ua.nure.pashneva.SummaryTask4.db.entity.comparator.ComparatorFactory;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Command for obtaining adminNewRequestsView.jsp.
 *
 * @author Anastasia Pashneva
 */
public class GetAdminNewRequestsPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetAdminNewRequestsPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            List<RequestToAdmin> requests = DAOFactory.getInstance().getRequestToAdminDAO()
                    .readByStatus(DAOFactory.getInstance().getRequestStatusDAO().read(language, 3), language);

            if (requests.size() == 0) {
                String message = ResourceBundle.getBundle("resources", new Locale(locale))
                        .getString("requests_admin_jsp.no_requests");
                request.setAttribute("message", message);
            }

            Comparator<RequestToAdmin> comparator = ComparatorFactory.getInstance()
                    .getRequestToAdminComparator("compare_by_creation_date");
            if (comparator != null) {
                requests.sort(comparator);
            }

            LOG.trace("Attribute requests --> " + request);
            request.setAttribute("requests", requests);

            LOG.info("New requests to admin --> " + request);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_ADMIN_REQUESTS).forward(request, response);
    }
}
