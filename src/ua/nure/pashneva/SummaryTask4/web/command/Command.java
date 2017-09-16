package ua.nure.pashneva.SummaryTask4.web.command;

import ua.nure.pashneva.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author Anastasia Pashneva
 */
public abstract class Command implements Serializable {

    private static final long serialVersionUID = 8879403039606311780L;

    /**
     * Execution method for command.
     *
     * @param request object of HttpServletRequest class which contains data of client request.
     * @param response object of HttpServletResponse class which contains data of response to client.
     * @throws IOException
     * @throws ServletException
     * @throws AppException
     */
    public abstract void execute(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ServletException, AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
