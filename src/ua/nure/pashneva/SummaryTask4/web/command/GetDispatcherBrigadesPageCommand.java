package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Command for obtaining dispatcherBrigadesView.jsp.
 *
 * @author Anastasia Pashneva
 */
public class GetDispatcherBrigadesPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetDispatcherBrigadesPageCommand.class);

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

            List<Brigade> brigades = DAOFactory.getInstance().getBrigadeDAO().readAll(language);
            if (brigades.size() == 0) {
                String message = ResourceBundle.getBundle("resources", new Locale(locale))
                        .getString("brigades_dispatcher_jsp.no_brigades");
                request.setAttribute("message", message);
            }
            LOG.trace("Attribute brigades --> " + brigades);
            request.setAttribute("brigades", brigades);

            LOG.info("Brigades --> " + brigades);
        } catch (Exception e) {
            throw new AppException(e.getMessage(), e);
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_DISPATCHER_BRIGADES).forward(request, response);
    }
}
