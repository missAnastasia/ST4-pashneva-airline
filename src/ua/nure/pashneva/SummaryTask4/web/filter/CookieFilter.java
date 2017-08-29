package ua.nure.pashneva.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CookieFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CookieFilter.class);

    public CookieFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");
        // no op
        LOG.debug("Filter destruction finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        LOG.debug("Filter starts");

        HttpServletRequest req = (HttpServletRequest) request;
        LOG.trace("Request uri --> " + req.getRequestURI());

        HttpSession session = req.getSession();

        //UserAccount userInSession = SessionManager.getLoginedUser(session);
        User userInSession = SessionManager.getLoginedUser(session);
        LOG.trace("userInSession --> " + userInSession);
        if (userInSession != null) {
            LOG.debug("userInSession checking starts");
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }


        // Connection was created in JDBCFilter.
        Connection conn = SessionManager.getStoredConnection(request);


        // Flag check cookie
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null && conn != null) {
            String userName = SessionManager.getUserNameInCookie(req);
            try {
                //UserAccount user = DBUtils.findUser(conn, userName);
                User user = DAOFactory.getInstance().getUserDAO().read(userName);

                LOG.trace("user --> " + user);
                LOG.trace("user --> " + user.getRole());
                SessionManager.storeLoginedUser(session, user, user.getRole());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Mark checked.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
        LOG.debug("Filter finished");
        chain.doFilter(request, response);
    }

}
