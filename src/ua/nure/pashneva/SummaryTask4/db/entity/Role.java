package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this enum are strings from table roles.
 *
 * @author Anastasia Pashneva
 */
public enum Role {

    ADMIN,
    DISPATCHER,
    STAFF;

    /**
     * Method for obtaining object of enum by its ordinal number.
     *
     * @param ordinal ordinal number of object which must be obtained.
     * @return instance of enum with determined ordinal number.
     */
    public static Role getRole(int ordinal) {
        return Role.values()[ordinal - 1];
    }

    /**
     * Method for obtaining ordinal number of the enum object by its instance.
     *
     * @param role object of enum which ordinal number must be obtained.
     * @return int value of ordinal number of object.
     */
    public static int getRoleOrdinal(Role role) {
        return role.ordinal() + 1;
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
