package ua.nure.pashneva.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for saving cookie for session.
 *
 * @author Anastasia Pashneva
 *
 */
public class CookieFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CookieFilter.class);

    public CookieFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");
        LOG.debug("Filter initialization finished");
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");
        LOG.debug("Filter destruction finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.debug("Filter starts");

        HttpServletRequest req = (HttpServletRequest) request;
        LOG.trace("Request uri --> " + req.getRequestURI());

        HttpSession session = req.getSession();
        User userInSession = SessionManager.getAuthorizedUser(session);
        LOG.trace("User in session --> " + userInSession);
        if (userInSession != null) {
            LOG.debug("User in session checking starts");
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }

        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null) {
            String userName = SessionManager.getUserNameInCookie(req);
            if (userName!= null && !(userName.isEmpty())) {
                try {
                    User user = DAOFactory.getInstance().getUserDAO().readByLogin(userName);
                    LOG.trace("User --> " + user);
                    LOG.trace("User role --> " + user.getRole());
                    SessionManager.storeAuthorizedUser(session, user, user.getRole());
                } catch (Exception e) {
                    throw new ServletException(e.getMessage(), e);
                }
                session.setAttribute("COOKIE_CHECKED", "CHECKED");
            }
        }
        LOG.debug("Filter finished");
        chain.doFilter(request, response);
    }
}
