package ua.nure.pashneva.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.web.command.Command;
import ua.nure.pashneva.SummaryTask4.web.command.CommandContainer;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.sql.Date;


public class RedirectController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(RedirectController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        process(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command --> " + command);

        StringBuilder path = new StringBuilder(Path.COMMAND + commandName);

        String compare = request.getParameter("compare");
        if (compare != null && !(compare.isEmpty())) {
            path.append("&compare=").append(compare);
        }

        /*String numberAttr = (String) request.getAttribute("number");
        if (numberAttr != null && !(numberAttr.isEmpty())) {
            path.append("&number=").append(numberAttr);
        }

        String fromCityAttr = (String) request.getAttribute("from_city");
        if (fromCityAttr != null && !(fromCityAttr.isEmpty())) {
            path.append("&from_city=").append(fromCityAttr);
        }

        String toCityAttr = (String) request.getAttribute("to_city");
        if (toCityAttr != null && !(toCityAttr.isEmpty())) {
            path.append("&to_city=").append(toCityAttr);
        }

        Date dateAttr = (Date) request.getAttribute("departure_date");
        if (dateAttr != null) {
            path.append("&departure_date=").append(dateAttr.toString());
        }*/

        String fromCity = request.getParameter("from_city");
        if (fromCity != null && (!fromCity.isEmpty())) {
            path.append("&from_city=").append(fromCity);
        }

        String toCity = request.getParameter("to_city");
        if (toCity != null && (!toCity.isEmpty())) {
            path.append("&to_city=").append(toCity);
        }

        String date = request.getParameter("departure_date");
        if (date != null && (!date.isEmpty())) {
            path.append("&departure_date=").append(date);
        }

        String flightNumber = request.getParameter("flight_number");
        if (flightNumber != null && (!flightNumber.isEmpty())) {
            path.append("&flight_number=").append(flightNumber);
        }



        String flightStatusId = request.getParameter("flight_status_id");
        if (flightStatusId != null && (!flightStatusId.isEmpty())) {
            path.append("&flight_status_id=").append(flightStatusId);
        }

        String brigadeId = request.getParameter("brigade_id");
        if (brigadeId != null && (!brigadeId.isEmpty())) {
            path.append("&brigade_id=").append(brigadeId);
        }

        String requestNumber = request.getParameter("request_number");
        if (requestNumber != null && (!requestNumber.isEmpty())) {
            path.append("&request_number=").append(requestNumber);
        }

        String requestId = request.getParameter("request_id");
        if (requestId != null && (!requestId.isEmpty())) {
            path.append("&request_id=").append(requestId);
        }

        String staffId = request.getParameter("staff_id");
        if (staffId != null && (!staffId.isEmpty())) {
            path.append("&staff_id=").append(staffId);
        }

        String postId = request.getParameter("post_id");
        if (postId != null && (!postId.isEmpty())) {
            path.append("&post_id=").append(postId);
        }

        LOG.trace("Path --> " + path);
        response.sendRedirect(path.toString());
    }
}