package ua.nure.pashneva.SummaryTask4.web.util;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.entity.Role;
import ua.nure.pashneva.SummaryTask4.db.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

/**
 * Class which provides opportunities to store data into session.
 *
 * @author Anastasia Pashneva
 */
public class SessionManager {

    /**
     * String fields with names of session attributes which should be created.
     */
    private static final String ATT_NAME_CONNECTION = "connection";
    private static final String ATT_NAME_USER_NAME = "user_login";
    private static final int COOKIE_MAX_AGE = 24 * 60 * 60;
    private static final Logger LOG = Logger.getLogger(SessionManager.class);

    /**
     * Method for storing connection in request attribute.
     *
     * @param request object of ServletRequest for storing connection.
     * @param conn object of Connection class which must be stored.
     */
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
        LOG.trace("Connection has been saved --> " + conn);
    }

    /**
     * Method for obtaining the Connection object from the ServletRequest object.
     *
     * @param request object of ServletRequest for obtaining connection as attribute.
     * @return object of Connection class which has been obtained from request.
     */
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }

    /**
     * Method for storing user that is logged in system.
     *
     * @param session object of HttpSession for storing the user.
     * @param loginedUser object that contains data of logged in user.
     * @param userRole object which contains value of user role.
     */
    public static void storeLoginedUser(HttpSession session, User loginedUser, Role userRole) {
        session.setAttribute("user", loginedUser);
        LOG.trace("Logined user has been saved into session --> " + loginedUser);
        session.setAttribute("userRole", userRole);
        LOG.trace("User role has been saved into session --> " + userRole);
    }


    /**
     * Method for obtaining the logged in user object.
     *
     * @param session object of HttpSession which contains user object as attribute.
     * @return object which contains data of logged in user.
     */
    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("user");
        return loginedUser;
    }

    public static void storeUserToConfirmRegistration(HttpSession session, User userToConfirm) {
        session.setAttribute("userToConfirm", userToConfirm);
        LOG.trace("User to confirm has been saved in session --> " + userToConfirm);
    }

    public static User getUserToConfirmRegistration(HttpSession session) {
        User userToConfirm = (User) session.getAttribute("userToConfirm");
        return userToConfirm;
    }

    /**
     * Method for storing logged in user information in cookie.
     *
     * @param response object of HttpServletResponse with cookie.
     * @param user object with user data which must be stored in cookie.
     */
    public static void storeUserCookie(HttpServletResponse response, User user) {
        LOG.debug("Saving user cookie starts");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getLogin());
        // 1 day (Convert to seconds)
        cookieUserName.setMaxAge(COOKIE_MAX_AGE);
        response.addCookie(cookieUserName);
        LOG.debug("Saving user cookie finished");
    }

    /**
     * Method for obtaining name of logged in user from cookie.
     *
     * @param request object of HttpServletRequest which contains cookies.
     * @return user login.
     */
    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Method for deleting cookie.
     * @param response object of HttpServletResponse for deleting cookie.
     */
    public static void deleteUserCookie(HttpServletResponse response) {
        LOG.debug("Deleting user cookie starts");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
        LOG.debug("Deleting user cookie finished");
    }
}