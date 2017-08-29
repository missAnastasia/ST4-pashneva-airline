package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this class are strings from the table request_to_admin.
 *
 * @author Anastasia Pashneva
 *
 */
public class RequestToAdmin extends Entity {

    private User user;
    private String description;
    private RequestStatus requestStatus;

    /**
     * Constructor of class for creating object without without an identifier.
     *
     * @param user
     * @param description
     * @param requestStatus
     */
    public RequestToAdmin(User user, String description, RequestStatus requestStatus) {
        this.user = user;
        this.description = description;
        this.requestStatus = requestStatus;
    }

    public RequestToAdmin() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
