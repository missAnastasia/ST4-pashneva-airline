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

public class GetAdminStaffPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetAdminStaffPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        try {
            String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
            if (locale == null) {
                locale = request.getLocale().getLanguage();
                LOG.trace("Current locale --> " + locale);
            }
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);
            LOG.trace("Language --> " + language);

            String postToSearchId = request.getParameter("post_id");
            LOG.trace("Post to search id --> " + postToSearchId);

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
            LOG.trace("Staff --> " + staffList.toString());

            /*LOG.trace("Flights --> " + flights.toString());*/
            /*Comparator<RequestToAdmin> comparator = ComparatorFactory.getInstance().getRequestToAdminComparator("compare_by_creation_date");
            if (comparator != null) {
                requests.sort(comparator);
            }*/
            List<Post> posts = DAOFactory.getInstance().getPostDAO().readAll(language);
            LOG.trace("Posts --> " + posts.toString());
            request.setAttribute("posts", posts);
            request.setAttribute("staff", staffList);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        request.getRequestDispatcher(Path.PAGE_ADMIN_STAFF).forward(request, response);
        LOG.debug("Command finished");
    }
}
