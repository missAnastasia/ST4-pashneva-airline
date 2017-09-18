package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this class are strings from the table staff.
 *
 * @author Anastasia Pashneva
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
}
