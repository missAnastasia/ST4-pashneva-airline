package ua.nure.pashneva.SummaryTask4.db.entity;

import java.util.Date;

/**
 * Objects of this class are strings from the table flights.
 *
 * @author Anastasia Pashneva
 *
 */
public class Flight {
    private String id;
    private Date date;
    private Brigade brigade;
    private FlightStatus flightStatus;
    private String aircraftType;

    public Flight() {
    }

    public Flight(String id, Date date, Brigade brigade,
                  FlightStatus flightStatus, String aircraftType) {
        this.id = id;
        this.date = date;
        this.brigade = brigade;
        this.flightStatus = flightStatus;
        this.aircraftType = aircraftType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", brigade=" + brigade +
                ", flightStatus=" + flightStatus +
                ", aircraftType='" + aircraftType + '\'' +
                '}';
    }
}
