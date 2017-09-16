package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Command for deleting staff from database.
 *
 * @author Anastasia Pashneva
 */
public class DeleteAdminStaffCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteAdminStaffCommand.class);

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

        if (staffId != null && !(staffId.isEmpty())) {
            try {
                DAOFactory.getInstance().getStaffDAO().delete(Integer.parseInt(staffId));
                LOG.info("Deleted from DB with staffId --> " + staffId);
            } catch (Exception e) {
                String message = ResourceBundle.getBundle("resources", new Locale(locale))
                        .getString("message.error.failed_delete_staff");
                throw new AppException(message);
            }
        }
        LOG.debug("Command finished");
        response.sendRedirect(Path.COMMAND_ADMIN_STAFF);
    }
}
