package ua.nure.pashneva.SummaryTask4.web;

import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.web.util.SessionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = { "/doLogin" })
public class DoLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DoLoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember= "Y".equals(rememberMeStr);


        //UserAccount user = null;
        User user = null;

        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null
                || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = SessionManager.getStoredConnection(request);
            try {

                //user = DBUtils.findUser(conn, userName, password);
                user = DAOFactory.getInstance().getUserDAO().readByLogin(userName);

                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        // If error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
            //user = new UserAccount();
            user = new User();

            //user.setUserName(userName);
            user.setLogin(userName);

            user.setPassword(password);


            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);


            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/view/loginView.jsp");

            dispatcher.forward(request, response);
        }

        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            //SessionManager.storeLoginedUser(session, user);

            // If user checked "Remember me".
            if(remember)  {
                SessionManager.storeUserCookie(response,user);
            }

            // Else delete cookie.
            else  {
                SessionManager.deleteUserCookie(response);
            }

            // Redirect to userInfo page.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}