package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this class are strings from the table request_to_admin.
 *
 * @author Anastasia Pashneva
 *
 */
public class RequestToAdmin extends Entity {

    private User user;
    private RequestStatus requestStatus;
    private String message;

    /**
     * Constructor of class for creating object without without an identifier.
     *
     * @param user
     * @param message
     * @param requestStatus
     */
    public RequestToAdmin(User user, RequestStatus requestStatus, String message) {
        this.user = user;
        this.requestStatus = requestStatus;
        this.message = message;
    }

    public RequestToAdmin() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
