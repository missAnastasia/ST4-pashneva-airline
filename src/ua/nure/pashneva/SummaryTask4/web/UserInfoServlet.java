package ua.nure.pashneva.SummaryTask4.web;

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

@WebServlet(urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserInfoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();


        // Check User has logged on
        //UserAccount loginedUser = SessionManager.getLoginedUser(session);
        User loginedUser = SessionManager.getLoginedUser(session);

        // Not logged in
        if (loginedUser == null) {

            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Store info in request attribute
        request.setAttribute("user", loginedUser);


        // Logined, forward to /WEB-INF/views/changeUserDataView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/changeUserDataView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
