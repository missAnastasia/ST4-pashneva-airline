package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this enum are strings from table request_statuses.
 *
 * @author Anastasia Pashneva
 *
 */
public enum  RequestStatus {
    COMPLETED,
    REJECTED;

    /**
     * Method for obtaining object of enum by its ordinal number.
     *
     * @param ordinal ordinal number of object which must be obtained.
     * @return instance of enum with determined ordinal number.
     */
    public static RequestStatus getRequestStatus(int ordinal) {
        return RequestStatus.values()[ordinal - 1];
    }

    /**
     * Method for obtaining ordinal number of the enum object by its name.
     *
     * @param name name of object.
     * @return int value of ordinal number of object.
     */
    public static int getRequestStatusOrdinal(String name) {
        RequestStatus requestStatus = RequestStatus.valueOf(name) ;
        return requestStatus.ordinal() + 1;
    }

    /**
     * Method for obtaining ordinal number of the enum object by its instance.
     *
     * @param requestStatus object of enum which ordinal number must be obtained.
     * @return int value of ordinal number of object.
     */
    public static int getRequestStatusOrdinal(RequestStatus requestStatus) {
        return requestStatus.ordinal() + 1;
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
