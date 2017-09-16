package ua.nure.pashneva.SummaryTask4.db.entity;

import org.apache.log4j.Logger;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Objects of this class are strings from the table flights.
 *
 * @author Anastasia Pashneva
 */
public class Flight extends Entity {

    private String number;
    private Date date;
    private Time time;
    private String departurePoint;
    private String arrivalPoint;
    private Brigade brigade;
    private FlightStatus flightStatus;
    private Aircraft aircraft;

    private static final Logger LOG = Logger.getLogger(Flight.class);

    public Flight() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (!getNumber().equals(flight.getNumber())) return false;
        if (!getDate().equals(flight.getDate())) return false;
        if (!getDeparturePoint().equals(flight.getDeparturePoint())) return false;
        if (!getArrivalPoint().equals(flight.getArrivalPoint())) return false;
        if (getBrigade() != null ? !getBrigade().equals(flight.getBrigade()) : flight.getBrigade() != null)
            return false;
        if (getFlightStatus() != flight.getFlightStatus()) return false;
        return getAircraft().equals(flight.getAircraft());
    }

    @Override
    public int hashCode() {
        int result = getNumber().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getDeparturePoint().hashCode();
        result = 31 * result + getArrivalPoint().hashCode();
        result = 31 * result + (getBrigade() != null ? getBrigade().hashCode() : 0);
        result = 31 * result + getFlightStatus().hashCode();
        result = 31 * result + getAircraft().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "number='" + number + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", departurePoint='" + departurePoint + '\'' +
                ", arrivalPoint='" + arrivalPoint + '\'' +
                ", brigade=" + brigade +
                ", flightStatus=" + flightStatus +
                ", aircraft=" + aircraft +
                ", id=" + id +
                '}';
    }

    /**
     * Static method for obtaining java.sql.Date object with flight departure date
     * from input string.
     *
     * @param input string with contains date and time in certain format.
     * @return java.sql.Date object with flight departure date.
     * @throws ParseException
     */
    public static java.sql.Date getDateFromString(String input) throws ParseException {
        String date = input.split("T")[0];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new Date(dateFormat.parse(date).getTime());
    }

    /**
     * Static method for obtaining java.sql.Time object with flight departure time
     * from input string.
     *
     * @param input string with contains date and time in certain format.
     * @return java.sql.Time object with flight departure time.
     * @throws ParseException
     */
    public static Time getTimeFromString(String input) throws ParseException {
        String time = input.split("T")[1];
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        return new Time(dateFormat.parse(time).getTime());
    }

    /**
     * Static method for obtaining string which concatenates string values of
     * java.sql.Date object with flight departure date and
     * java.sql.Time object with flight departure time in certain format.
     *
     * @param inputDate java.sql.Date object with flight departure date.
     * @param inputTime java.sql.Time object with flight departure time.
     * @return string with departure date and time values.
     * @throws ParseException
     */
    public static String getDateAndTimeFromStrings(java.sql.Date inputDate, java.sql.Time inputTime) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm");
        return dateFormat.format(inputDate) + "T" + timeFormat.format(inputTime);
    }
}
