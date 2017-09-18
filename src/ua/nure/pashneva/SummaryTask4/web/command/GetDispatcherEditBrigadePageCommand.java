package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Post;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Command for obtaining dispatcherEditBrigadeView.jsp.
 *
 * @author Anastasia Pashneva
 */
public class GetDispatcherEditBrigadePageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetDispatcherEditBrigadePageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String brigadeId = request.getParameter("brigade_id");
        LOG.trace("Parameter brigade_id --> " + brigadeId);

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            if (brigadeId != null && !(brigadeId.isEmpty())) {
                Brigade brigade = DAOFactory.getInstance().getBrigadeDAO().read(Integer.parseInt(brigadeId), language);

                List<Post> posts = DAOFactory.getInstance().getPostDAO().readAll(language);
                LOG.trace("Attribute posts --> " + posts);
                request.setAttribute("posts", posts);

                List<Staff> freeStaff = DAOFactory.getInstance().getStaffDAO().readFreeStaff(language);
                Map<Post, List<Staff>> staff = new HashMap<>();
                for (Post p : posts) {
                    List<Staff> temp = new ArrayList<>();
                    for (Staff s : brigade.getStaff()) {
                        if (s.getPost().equals(p)) {
                            temp.add(s);
                        }
                    }
                    for (Staff s : freeStaff) {
                        if (s.getPost().equals(p)) {
                            temp.add(s);
                        }
                    }
                    staff.put(p, temp);
                }

                LOG.trace("Attribute staff --> " + staff);
                request.setAttribute("staff", staff);

                LOG.trace("Attribute brigade --> " + brigade);
                request.setAttribute("brigade", brigade);

                LOG.info("Brigade to update --> " + brigade);
            }
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_DISPATCHER_EDIT_BRIGADE).forward(request, response);
    }
}
