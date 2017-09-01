package ua.nure.pashneva.SummaryTask4.db.entity;

import java.sql.Date;

/**
 * Objects of this class are strings from the table flights.
 *
 * @author Anastasia Pashneva
 *
 */
public class Flight extends Entity {
    private String number;
    private Date date;
    private String departurePoint;
    private String arrivalPoint;
    private Brigade brigade;
    private FlightStatus flightStatus;
    private Aircraft aircraft;

    public Flight() {
    }

    public Flight(String number, Date date, String departurePoint,
                  String arrivalPoint, Brigade brigade,
                  FlightStatus flightStatus, Aircraft aircraft) {
        this.number = number;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                ", departurePoint='" + departurePoint + '\'' +
                ", arrivalPoint='" + arrivalPoint + '\'' +
                ", brigade=" + brigade +
                ", flightStatus=" + flightStatus +
                ", aircraft=" + aircraft +
                ", id=" + id +
                '}';
    }
}
