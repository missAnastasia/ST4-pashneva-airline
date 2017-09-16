package ua.nure.pashneva.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.entity.Role;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Security filter. Enables to separate access to commands.
 * 
 * @author Anastasia Pashneva
 * 
 */
public class CommandAccessFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);

	/**
	 * Commands access
	 */
	private Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
	private List<String> commons = new ArrayList<String>();	
	private List<String> outOfControl = new ArrayList<String>();
	
	public void destroy() {
		LOG.debug("Filter destruction starts");
		LOG.debug("Filter destruction finished");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LOG.debug("Filter starts");
		
		if (accessAllowed(request)) {
			LOG.debug("Filter finished");
			chain.doFilter(request, response);
		} else {
			String message = ResourceBundle.getBundle("resources", request.getLocale())
					.getString("message.error.no_permissions");
			LOG.trace("Set the request attribute: message --> " + message);
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(Path.COMMAND_MESSAGE_ERROR + message);
		}
	}

    /**
     * Method for checking permissions for request.
     *
     * @param request object of ServletRequest which permissions must be checked.
     * @return true - access allowed, otherwise - false.
     */
	private boolean accessAllowed(ServletRequest request) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;

		String commandName = request.getParameter("command");
		if (commandName == null || commandName.isEmpty()) {
			return false;
		}
		
		if (outOfControl.contains(commandName)) {
			return true;
		}
		
		HttpSession session = httpRequest.getSession(false);
		if (session == null) { 
			return false;
		}
		
		Role userRole = (Role)session.getAttribute("userRole");
		if (userRole == null) {
			return false;
		}
		
		return accessMap.get(userRole).contains(commandName)
				|| commons.contains(commandName);

	}

	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("Filter initialization starts");

		accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
		accessMap.put(Role.STAFF, asList(fConfig.getInitParameter("staff")));
		accessMap.put(Role.DISPATCHER, asList(fConfig.getInitParameter("dispatcher")));
		LOG.trace("Access map --> " + accessMap);

		commons = asList(fConfig.getInitParameter("common"));
		LOG.trace("Common commands --> " + commons);

		outOfControl = asList(fConfig.getInitParameter("out-of-control"));
		LOG.trace("Out of control commands --> " + outOfControl);
		
		LOG.debug("Filter initialization finished");
	}
	
	/**
	 * Extracts parameter values from string.
	 * 
	 * @param str parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;		
	}
}