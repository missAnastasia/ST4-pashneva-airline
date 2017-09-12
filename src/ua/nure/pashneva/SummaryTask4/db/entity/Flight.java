package ua.nure.pashneva.SummaryTask4.db.entity;

import sun.tracing.dtrace.DTraceProviderFactory;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Objects of this class are strings from the table flights.
 *
 * @author Anastasia Pashneva
 *
 */
public class Flight extends Entity {
    private String number;
    private String date;
    private String time;
    private String departurePoint;
    private String arrivalPoint;
    private Brigade brigade;
    private FlightStatus flightStatus;
    private Aircraft aircraft;

    public Flight() {
    }

    public Flight(String number, String date, String time,
                  String departurePoint, String arrivalPoint, Brigade brigade,
                  FlightStatus flightStatus, Aircraft aircraft) {
        this.number = number;
        this.date = date;
        this.time = time;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        this.brigade = brigade;
        this.flightStatus = flightStatus;
        this.aircraft = aircraft;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public static String getDateFromString(String input) {
        return input.split("T")[0];
    }

    public static String getTimeFromString(String input) {
        return input.split("T")[1];
    }

    public static String getDateAndTimeFromStrings(String inputDate, String inputTime) {
        return inputDate + "T" + inputTime;
    }
}
