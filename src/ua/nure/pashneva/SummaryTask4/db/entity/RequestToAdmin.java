package ua.nure.pashneva.SummaryTask4.db.entity;

import java.sql.Date;

/**
 * Objects of this class are strings from the table request_to_admin.
 *
 * @author Anastasia Pashneva
 */
public class RequestToAdmin extends Entity {

    private int number;
    private User user;
    private RequestStatus requestStatus;
    private String message;
    private Date date;

    public RequestToAdmin() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestToAdmin)) return false;
        if (!super.equals(o)) return false;

        RequestToAdmin that = (RequestToAdmin) o;

        if (getNumber() != that.getNumber()) return false;
        if (!getUser().equals(that.getUser())) return false;
        if (!getRequestStatus().equals(that.getRequestStatus())) return false;
        if (!getMessage().equals(that.getMessage())) return false;
        return getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getNumber();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getRequestStatus().hashCode();
        result = 31 * result + getMessage().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RequestToAdmin{" +
                "number=" + number +
                ", user=" + user +
                ", requestStatus=" + requestStatus +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", id=" + id +
                '}';
    }
}
