package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.*;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetDispatcherBrigadeInfoPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetDispatcherBrigadeInfoPageCommand.class);

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
            String brigadeId = request.getParameter("brigade_id");
            Brigade brigade = new Brigade();
            if (brigadeId != null && !(brigadeId.isEmpty())) {
                brigade = DAOFactory.getInstance().getBrigadeDAO().read(Integer.parseInt(brigadeId), language);
            }
            LOG.trace("Brigade --> " + brigade);
            request.setAttribute("brigade", brigade);
            request.setAttribute("staff", brigade.getStaff());
            List<Post> posts = DAOFactory.getInstance().getPostDAO().readAll(language);
            List<Post> postItems = new ArrayList<>();
            for (Staff staff : brigade.getStaff()) {
                for (Post post : posts) {
                    if (staff.getPost().equals(post)) {
                        postItems.add(post);
                    }
                }

            }
            request.setAttribute("posts", postItems);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_DISPATCHER_BRIGADE_INFO).forward(request, response);
    }
}
