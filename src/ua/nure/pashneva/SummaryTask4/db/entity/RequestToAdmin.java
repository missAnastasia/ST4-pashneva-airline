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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestToAdmin)) return false;
        if (!super.equals(o)) return false;

        RequestToAdmin that = (RequestToAdmin) o;

        if (!getUser().equals(that.getUser())) return false;
        if (getRequestStatus() != that.getRequestStatus()) return false;
        return getMessage().equals(that.getMessage());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getRequestStatus().hashCode();
        result = 31 * result + getMessage().hashCode();
        return result;
    }
}
