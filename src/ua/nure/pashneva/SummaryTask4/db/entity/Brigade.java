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
    private String name;

    public Brigade() {
    }

    /**
     * Constructor of class for creating object without without an identifier.
     *
     * @param staff
     * @param name
     */
    public Brigade(List<Staff> staff, String name) {
        this.staff = staff;
        this.name = name;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brigade)) return false;

        Brigade brigade = (Brigade) o;

        if (!getStaff().equals(brigade.getStaff())) return false;
        return getName().equals(brigade.getName());
    }

    @Override
    public int hashCode() {
        int result = getStaff().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "staff=" + staff +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
