package ua.nure.pashneva.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

public class JDBCFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(JDBCFilter.class);

    public JDBCFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");
        DAOFactory.setDaoFactoryFCN(fConfig.getInitParameter("DaoFactoryFCN"));
        LOG.debug("Filter initialization finished");
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");
        LOG.debug("Filter destruction finished");
    }


    // Check the target of the request is a servlet?
    private boolean needJDBC(HttpServletRequest request) {

        //
        // Servlet Url-pattern: /spath/*
        //
        // => /spath
        String servletPath = request.getServletPath();
        LOG.trace("servletPath --> " + servletPath);
        // => /abc/mnp
        String pathInfo = request.getPathInfo();
        LOG.trace("pathInfo --> " + pathInfo);


        String urlPattern = servletPath;

        if (pathInfo != null) {
            // => /spath/*
            urlPattern = servletPath + "/*";
            LOG.trace("urlPattern --> " + urlPattern);
        }

        // Key: servletName.
        // Value: ServletRegistration
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();


        // Collection of all servlet in your webapp.
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                LOG.trace("Allow opening connection for --> " + urlPattern);
                LOG.debug("Filter finished");
                return true;
            }
        }
        LOG.trace("Deny opening connection for --> " + urlPattern);
        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.debug("Filter starts");
        HttpServletRequest req = (HttpServletRequest) request;
        //
        // Only open connections for the special requests need
        // connection. (For example, the path to the servlet, JSP, ..)
        //
        // Avoid open connection for commons request
        // (for example: image, css, javascript,... )
        //
        if (this.needJDBC(req)) {
            LOG.trace("Opening connection for --> " + req.getServletPath());
            Connection conn = null;
            try {
                conn = DBConnection.getInstance().getConnection();
                //conn.setAutoCommit(false);
                SessionManager.storeConnection(request, conn);
                LOG.debug("Filter finished");
                chain.doFilter(request, response);
                //conn.commit();

            } catch (Exception e) {
                LOG.trace("Exception message --> " + e.getMessage());
                //DBConnection.getInstance().rollback(conn);
                LOG.debug("Rollback transactions");
                throw new ServletException();
            } finally {
                DBConnection.getInstance().close(conn);
                LOG.debug("Connection has been closed");
            }
        }

        // With commons requests (images, css, html, ..)
        // No need to open the connection.
        else {

            // Allow request to go forward
            // (Go to the next filter or target)
            LOG.trace("No need to open the connection");
            LOG.debug("Filter finished");
            chain.doFilter(request, response);
        }

    }

}