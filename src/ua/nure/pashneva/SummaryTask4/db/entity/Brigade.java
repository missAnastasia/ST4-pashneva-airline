package ua.nure.pashneva.SummaryTask4.db.entity;

import java.util.List;

/**
 * Objects of this class are strings from the table brigades.
 *
 * @author Anastasia Pashneva
 *
 */
public class Brigade extends Entity{
    private List<Staff> staff;
    private String number;

    public Brigade() {
    }

    /**
     * Constructor of class for creating object without without an identifier.
     *
     * @param staff
     * @param number
     */
    public Brigade(List<Staff> staff, String number) {
        this.staff = staff;
        this.number = number;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brigade)) return false;

        Brigade brigade = (Brigade) o;

        if (!getStaff().equals(brigade.getStaff())) return false;
        return getNumber().equals(brigade.getNumber());
    }

    @Override
    public int hashCode() {
        int result = getStaff().hashCode();
        result = 31 * result + getNumber().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "staff=" + staff +
                ", name='" + number + '\'' +
                ", id=" + id +
                '}';
    }
}
