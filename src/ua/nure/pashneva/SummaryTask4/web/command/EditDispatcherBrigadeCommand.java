package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
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
 * Command for updating brigade in database.
 *
 * @author Anastasia Pashneva
 */
public class EditDispatcherBrigadeCommand extends Command {

    private static final Logger LOG = Logger.getLogger(EditDispatcherBrigadeCommand.class);

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

                String brigadeNumber = request.getParameter("brigade_number");
                LOG.trace("Parameter brigade_number --> " + brigadeNumber);
                if (brigadeNumber == null || brigadeNumber.isEmpty()) {
                    String message = ResourceBundle.getBundle("resources", new Locale(locale))
                            .getString("message.error.empty_fields");
                    throw new AppException(message);
                }

                brigade.setNumber(brigadeNumber);
                brigade.getStaff().clear();

                String[] staffIds = request.getParameterValues("staff_id");
                if (staffIds != null && staffIds.length > 0) {
                    for (int i = 0; i < staffIds.length; i++) {
                        Staff staff = DAOFactory.getInstance().getStaffDAO().read(Integer.parseInt(staffIds[i]), language);
                        brigade.getStaff().add(staff);
                    }
                }
                LOG.trace("Brigade --> " + brigade);
                DAOFactory.getInstance().getBrigadeDAO().update(brigade);
                LOG.info("Brigade updated in DB --> " + brigade);
            }
        } catch (Exception e) {
            String message = ResourceBundle.getBundle("resources", new Locale(locale))
                    .getString("message.error.failed_update_brigade");
            throw new AppException(message);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.COMMAND_DISPATCHER_BRIGADE_INFO).forward(request, response);
    }
}
