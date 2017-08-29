package ua.nure.pashneva.SummaryTask4.web.command;

import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetProductsPageCommand extends Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        try {
            //request.setAttribute("products", DAOFactory.getInstance().getCarDAO().readAll());
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
        request.getRequestDispatcher(Path.PAGE_PRODUCTS).forward(request, response);
    }
}
