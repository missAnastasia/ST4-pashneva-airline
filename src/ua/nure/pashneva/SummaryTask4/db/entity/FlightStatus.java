package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this enum are strings from table flight_statuses.
 *
 * @author Anastasia Pashneva
 *
 */
public class FlightStatus extends Entity {
    /*BOARDING,
    CANCELLED,
    CHECK_IN,
    SCHEDULED,
    DELAYED,
    DEPARTED,
    GATE_OPEN,
    GATE_CLOSING,
    GATE_CLOSED;*/
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

    /**
     * Method for obtaining object of enum by its ordinal number.
     *
     * @param ordinal ordinal number of object which must be obtained.
     * @return instance of enum with determined ordinal number.
     *//*
    public static FlightStatus getFlightStatus(int ordinal) {
        return FlightStatus.values()[ordinal - 1];
    }

    *//**
     * Method for obtaining ordinal number of the enum object by its name.
     *
     * @param name name of object.
     * @return int value of ordinal number of object.
     *//*
    public static int getFlightStatusOrdinal(String name) {
        FlightStatus flightStatus = FlightStatus.valueOf(name) ;
        return flightStatus.ordinal() + 1;
    }

    *//**
     * Method for obtaining ordinal number of the enum object by its instance.
     *
     * @param flightStatus object of enum which ordinal number must be obtained.
     * @return int value of ordinal number of object.
     *//*
    public static int getFlightStatusOrdinal(FlightStatus flightStatus) {
        return flightStatus.ordinal() + 1;
    }

    *//**
     * Method for obtaining name of enum object.
     *
     * @return string value of the enum object name.
     *//*
    public String getName() {
        return name().toLowerCase();
    }*/
}
