package ua.nure.pashneva.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.entity.Flight;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.exception.AppException;
import ua.nure.pashneva.SummaryTask4.web.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditAdminFlightCommand extends Command {

    private static final Logger LOG = Logger.getLogger(EditAdminFlightCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.trace("Command starts");
        String locale = (String) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale().getLanguage();
            LOG.trace("Current locale --> " + locale);
        }
        String flightId = request.getParameter("flight_id");
        if (flightId != null && !(flightId.isEmpty())) {
            try {
                Language currentLanguage = DAOFactory.getInstance().getLanguageDAO().readByPrefix(locale);
                LOG.trace("Language --> " + currentLanguage);

                List<Language> languages = DAOFactory.getInstance().getLanguageDAO().readAll();
                LOG.trace("Languages --> " + languages);

                String number = request.getParameter("flight_number");
                LOG.trace("number --> " + number);
                String aircraftId = request.getParameter("aircraft_id");
                LOG.trace("aircraftId --> " + aircraftId);
                String dateTime = request.getParameter("date_time");
                LOG.trace("dateTime --> " + dateTime);

                if (number == null || number.isEmpty() || aircraftId == null || aircraftId.isEmpty() || dateTime == null ||
                        dateTime.isEmpty()) {
                    String message = ResourceBundle.getBundle("resources", new Locale(locale))
                            .getString("message.error.empty_fields");
                    throw new AppException(message);
                }

                Flight flight = new Flight();
                flight.setId(Integer.parseInt(flightId));
                flight.setNumber(number);
                flight.setFlightStatus(DAOFactory.getInstance().getFlightStatusDAO().read(currentLanguage, 10));
                flight.setAircraft(DAOFactory.getInstance().getAircraftDAO().read(Integer.parseInt(aircraftId)));
                flight.setDate(Flight.getDateFromString(dateTime));
                flight.setTime(Flight.getTimeFromString(dateTime));

                LOG.trace("Flight --> " + flight);

                for (Language language : languages) {
                    String langFrom = request.getParameter(language.getPrefix() + "_from");
                    LOG.trace("langFrom --> " + langFrom);
                    String langTo = request.getParameter(language.getPrefix() + "_to");
                    LOG.trace("langTo --> " + langTo);

                    if (langFrom == null || langFrom.isEmpty() || langTo == null ||
                            langTo.isEmpty()) {
                        String message = ResourceBundle.getBundle("resources", new Locale(locale))
                                .getString("message.error.empty_fields");
                        throw new AppException(message);
                    }

                    flight.setDeparturePoint(langFrom);
                    flight.setArrivalPoint(langTo);
                    LOG.trace("Flight_lang --> " + flight);
                    DAOFactory.getInstance().getFlightDAO().update(flight, language);
                    LOG.trace("Flight_lang --> " + flight);
                }

            } catch (Exception e) {
                String message = ResourceBundle.getBundle("resources", new Locale(locale))
                        .getString("message.error.failed_update_flight");
                throw new AppException(message);
            }
        }
        LOG.trace("Command finished");
        response.sendRedirect(Path.COMMAND_ADMIN_FLIGHT_INFO + flightId);
    }
}
