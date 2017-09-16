package ua.nure.pashneva.SummaryTask4.web.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.pashneva.SummaryTask4.db.entity.Role;
import ua.nure.pashneva.SummaryTask4.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionManagerTest extends Mockito {

    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;
    User user;
    Role role;

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        user = new User();
        role = Role.STAFF;
    }

    @Test
    public void storeLoginedUser() throws Exception {
        SessionManager.storeAuthorizedUser(session, user, role);
        verify(session, atLeast(1)).setAttribute("user", user);
        verify(session, atLeast(1)).setAttribute("userRole", role);
    }

    @Test
    public void getLoginedUser() throws Exception {
        SessionManager.getAuthorizedUser(session);
        verify(session, atLeast(1)).getAttribute("user");
    }

    @Test
    public void storeUserToConfirmNewPassword() throws Exception {
        SessionManager.storeUserToConfirmNewPassword(session, user);
        verify(session, atLeast(1)).setAttribute("userToConfirm", user);
    }

    @Test
    public void storeUserCookie() throws Exception {
        SessionManager.storeUserCookie(response, user);
    }

    @Test
    public void getUserNameInCookie() throws Exception {
        SessionManager.getUserNameInCookie(request);
        verify(request, atLeast(1)).getCookies();
    }

    @Test
    public void deleteUserCookie() throws Exception {
        SessionManager.deleteUserCookie(response);
    }
}