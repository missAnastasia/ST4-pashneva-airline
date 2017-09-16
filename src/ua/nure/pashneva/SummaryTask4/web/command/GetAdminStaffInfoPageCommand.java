package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * Command for obtaining adminStaffInfoView.jsp.
 *
 * @author Anastasia Pashneva
 */
public class GetAdminStaffInfoPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetAdminStaffInfoPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }

        String staffId = request.getParameter("staff_id");
        LOG.trace("Parameter staff_id --> " + staffId);

        try {
            Language language = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);

            Staff staff = new Staff();
            if (staffId != null && !(staffId.isEmpty())) {
                staff = DAOFactory.getInstance().getStaffDAO().read(Integer.parseInt(staffId), language);
            }

            LOG.trace("Attribute staff_item --> " + staff);
            request.setAttribute("staff_item", staff);

            LOG.info("Staff to obtain info --> " + staff);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_ADMIN_STAFF_INFO).forward(request, response);
    }
}
