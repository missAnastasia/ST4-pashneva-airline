package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this enum are strings from table positions.
 *
 * @author Anastasia Pashneva
 *
 */
public enum Position {
    PILOT,
    NAVIGATING_OFFICER,
    AIR_MECHANIC,
    STEWARDESS;

    /**
     * Method for obtaining object of enum by its ordinal number.
     *
     * @param ordinal ordinal number of object which must be obtained.
     * @return instance of enum with determined ordinal number.
     */
    public static Position getPosition(int ordinal) {
        return Position.values()[ordinal - 1];
    }

    /**
     * Method for obtaining ordinal number of the enum object by its name.
     *
     * @param name name of object.
     * @return int value of ordinal number of object.
     */
    public static int getPositionOrdinal(String name) {
        Position position = Position.valueOf(name) ;
        return position.ordinal() + 1;
    }

    /**
     * Method for obtaining ordinal number of the enum object by its instance.
     *
     * @param position object of enum which ordinal number must be obtained.
     * @return int value of ordinal number of object.
     */
    public static int getPositionOrdinal(Position position) {
        return position.ordinal() + 1;
    }

    /**
     * Method for obtaining name of enum object.
     *
     * @return string value of the enum object name.
     */
    public String getName() {
        return name().toLowerCase();
    }
}
