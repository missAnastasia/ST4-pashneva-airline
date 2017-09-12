package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Aircraft;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAdminEditFlightPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetAdminAddFlightPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String flightId = request.getParameter("flight_id");
        if (flightId != null && !(flightId.isEmpty())) {
            try {
                List<Language> languages = DAOFactory.getInstance().getLanguageDAO().readAll();
                LOG.trace("Languages --> " + languages);
                request.setAttribute("languages", languages);
                List<Aircraft> aircraft = DAOFactory.getInstance().getAircraftDAO().readAll();
                LOG.trace("Aircraft --> " + aircraft);
                request.setAttribute("aircraft", aircraft);
                Map<Language, List<String>> flightLang = new HashMap<>();
                Flight flight = new Flight();
                for (Language language : languages) {
                    flight = DAOFactory.getInstance().getFlightDAO().read(Integer.parseInt(flightId), language);
                    List<String> temp = new ArrayList<>();
                    temp.add(flight.getDeparturePoint());
                    temp.add(flight.getArrivalPoint());
                    flightLang.put(language, temp);
                }
                request.setAttribute("flight", flight);
                LOG.trace("flight --> " + flight);
                request.setAttribute("flightLang", flightLang);
                LOG.trace("flightLang --> " + flightLang.toString());
                String dateTime = Flight.getDateAndTimeFromStrings(flight.getDate(), flight.getTime());
                request.setAttribute("date_time", dateTime);
                LOG.trace("date_time --> " + dateTime);
            } catch (Exception e) {
                throw new AppException(e.getMessage(), e);
            }
        }
        LOG.debug("Command finished");
        request.getRequestDispatcher(Path.PAGE_ADMIN_EDIT_FLIGHT).forward(request, response);
    }
}
