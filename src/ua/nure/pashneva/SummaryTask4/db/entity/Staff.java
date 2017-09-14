package ua.nure.pashneva.SummaryTask4.db.entity;

import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Objects of this class are strings from the table staff.
 *
 * @author Anastasia Pashneva
 *
 */
public class Staff extends Entity{
    private User user;
    private Post post;

    public Staff() {
    }

    /**
     * Constructor of class for creating object without without an identifier.
     *
     * @param user
     * @param post
     */
    public Staff(User user, Post post) {
        this.user = user;
        this.post = post;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        if (!super.equals(o)) return false;

        Staff staff = (Staff) o;

        if (!getUser().equals(staff.getUser())) return false;
        return (getPost() != staff.getPost());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getPost().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "user=" + user +
                ", post=" + post +
                ", id=" + id +
                '}';
    }

    public static List<Staff> readFreeStaff(Language language) throws Exception {
        List<Staff> freeStaff = new ArrayList<>();
        List<Staff> allStaff = DAOFactory.getInstance().getStaffDAO().readAll(language);
        for (Staff s : allStaff) {
            if (DAOFactory.getInstance().getBrigadeDAO().readByStaff(s, language) == null) {
                freeStaff.add(s);
            }
        }
        return freeStaff;
    }
}
