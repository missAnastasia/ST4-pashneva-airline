package ua.nure.pashneva.SummaryTask4.db.entity;

import java.util.Date;

/**
 * Objects of this class are strings from the table staff.
 *
 * @author Anastasia Pashneva
 *
 */
public class Staff extends User{
    private Position position;
    private Date employmentDate;

    public Staff() {
    }

    /**
     * Constructor of class for creating object without without an identifier.
     *
     * @param login
     * @param password
     * @param firstName
     * @param secondName
     * @param role
     * @param position
     * @param employmentDate
     */
    public Staff(String login, String password, String firstName,
                 String secondName, Role role, Position position, Date employmentDate) {
        super(login, password, firstName, secondName, role);
        this.position = position;
        this.employmentDate = employmentDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "position=" + position +
                ", employmentDate=" + employmentDate +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", role=" + role +
                ", id=" + id +
                '}';
    }
}
