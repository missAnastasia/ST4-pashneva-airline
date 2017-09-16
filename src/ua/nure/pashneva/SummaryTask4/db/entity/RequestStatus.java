package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this class are strings from table request_statuses.
 *
 * @author Anastasia Pashneva
 */
public class  RequestStatus extends Entity {

    private String name;

    public RequestStatus() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RequestStatus{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
