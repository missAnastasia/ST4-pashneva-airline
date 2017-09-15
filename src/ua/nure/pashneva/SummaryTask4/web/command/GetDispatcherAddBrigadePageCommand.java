package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
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

public class GetDispatcherAddBrigadePageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetDispatcherAddBrigadePageCommand.class);

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

            List<Post> posts = DAOFactory.getInstance().getPostDAO().readAll(language);
            request.setAttribute("posts", posts);
            List<Staff> freeStaff = Staff.readFreeStaff(language);
            Map<Post, List<Staff>> staff = new HashMap<>();
            for (Post p : posts) {
                List<Staff> temp = new ArrayList<>();
                for (Staff s : freeStaff) {
                    if (s.getPost().equals(p)) {
                        temp.add(s);
                    }
                }
                staff.put(p, temp);
            }
            request.setAttribute("staff", staff);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.trace("Command finished");
        request.getRequestDispatcher(Path.PAGE_DISPATCHER_ADD_BRIGADE).forward(request, response);
    }
}
