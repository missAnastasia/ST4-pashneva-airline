package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestToAdmin;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;

/**
 * Command for obtaining adminNewRequestInfoView.jsp.
 *
 * @author Anastasia Pashneva
 */
public class GetAdminRequestInfoPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetAdminRequestInfoPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String requestId = request.getParameter("request_id");
        LOG.trace("Parameter request_id --> " + requestId);

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            RequestToAdmin requestToAdmin = new RequestToAdmin();
            if (requestId != null && !(requestId.isEmpty())) {
                requestToAdmin = DAOFactory.getInstance().getRequestToAdminDAO().read(Integer.parseInt(requestId), language);
            }

            LOG.trace("Attribute request_item --> " + requestToAdmin);
            request.setAttribute("request_item", requestToAdmin);

            List<RequestStatus> statuses = DAOFactory.getInstance().getRequestStatusDAO().readAll(language);
            LOG.trace("Attribute statuses --> " + statuses);
            request.setAttribute("statuses", statuses);

            LOG.info("Request to admin to obtain info --> " + requestToAdmin);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_ADMIN_REQUEST_INFO).forward(request, response);
    }
}
