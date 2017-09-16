package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Post;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.db.entity.search.SearcherFactory;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.*;

/**
 * Command for obtaining adminStaffView.jsp.
 *
 * @author Anastasia Pashneva
 */
public class GetAdminStaffPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetAdminStaffPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String postToSearchId = request.getParameter("post_id");
        LOG.trace("Parameter post_id --> " + postToSearchId);

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            List<Staff> staffList = null;
            if (postToSearchId == null || postToSearchId.isEmpty()) {
                staffList = DAOFactory.getInstance().getStaffDAO().readAll(language);
                if (staffList.size() == 0) {
                    String message = ResourceBundle.getBundle("resources", request.getLocale())
                            .getString("staff_admin_jsp.no_staff");
                    request.setAttribute("message", message);
                }
            } else {
                Map<String, String> params = new HashMap<>();
                params.put("post_id", postToSearchId);
                staffList = SearcherFactory.getInstance().getStaffSearcher().search(language, params);

                for (Map.Entry<String, String> entry : params.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }

                if (staffList.size() == 0) {
                    String message = ResourceBundle.getBundle("resources", new Locale(locale))
                            .getString("message.error.cannot_find_entity");
                    request.setAttribute("message", message);
                }
            }

            List<Post> posts = DAOFactory.getInstance().getPostDAO().readAll(language);
            LOG.trace("Attribute posts --> " + posts);
            request.setAttribute("posts", posts);

            LOG.trace("Attribute staff --> " + staffList);
            request.setAttribute("staff", staffList);

            LOG.info("Staff --> " + staffList);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_ADMIN_STAFF).forward(request, response);
    }
}
