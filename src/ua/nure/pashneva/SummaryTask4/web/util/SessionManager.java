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

public class SessionManager {

    private static final String ATT_NAME_CONNECTION = "connection";
    private static final String ATT_NAME_USER_NAME = "user_login";
    private static final int COOKIE_MAX_AGE = 24 * 60 * 60;
    private static final Logger LOG = Logger.getLogger(SessionManager.class);

    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
        LOG.trace("Connection has been saved --> " + conn);
    }

    // Get the Connection object has been stored in one attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }

    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, User loginedUser, Role userRole) {

        // On the JSP can access ${user}
        session.setAttribute("user", loginedUser);
        LOG.trace("Logined user has been saved into session --> " + loginedUser);
        session.setAttribute("userRole", userRole);
        LOG.trace("User role has been saved into session --> " + userRole);
    }


    // Get the user information stored in the session.
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

    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, User user) {
        LOG.debug("Saving user cookie starts");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getLogin());
        // 1 day (Convert to seconds)
        cookieUserName.setMaxAge(COOKIE_MAX_AGE);
        response.addCookie(cookieUserName);
        LOG.debug("Saving user cookie finished");
    }

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


    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        LOG.debug("Deleting user cookie starts");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
        LOG.debug("Deleting user cookie finished");
    }
}