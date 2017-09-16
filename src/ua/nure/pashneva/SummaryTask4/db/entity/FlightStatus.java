package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this class are strings from table flight_statuses.
 *
 * @author Anastasia Pashneva
 */
public class FlightStatus extends Entity {

    private String name;

    public FlightStatus() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FlightStatus{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
