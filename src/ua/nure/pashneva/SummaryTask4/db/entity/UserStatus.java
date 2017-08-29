package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this enum are strings from table user_statuses.
 *
 * @author Anastasia Pashneva
 *
 */
public enum  UserStatus {
    UNBLOCKED, BLOCKED;

    /**
     * Method for obtaining object of enum by its ordinal number.
     *
     * @param ordinal ordinal number of object which must be obtained.
     * @return instance of enum with determined ordinal number.
     */
    public static UserStatus getUserStatus(int ordinal) {
        return UserStatus.values()[ordinal - 1];
    }

    /**
     * Method for obtaining ordinal number of the enum object by its name.
     *
     * @param name name of object.
     * @return int value of ordinal number of object.
     */
    public static int getUserStatusOrdinal(String name) {
        UserStatus userStatus = UserStatus.valueOf(name) ;
        return userStatus.ordinal() + 1;
    }

    /**
     * Method for obtaining ordinal number of the enum object by its instance.
     *
     * @param userStatus object of enum which ordinal number must be obtained.
     * @return int value of ordinal number of object.
     */
    public static int getUserStatusOrdinal(UserStatus userStatus) {
        return userStatus.ordinal() + 1;
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
