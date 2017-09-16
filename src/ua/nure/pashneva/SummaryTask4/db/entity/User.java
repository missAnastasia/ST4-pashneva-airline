package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this class are strings from the table users.
 *
 * @author Anastasia Pashneva
 */
public class User extends Entity{

    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private Role role;

    public User() {
    }

    /**
     * Constructor of class for creating object without an identifier.
     *
     * @param login
     * @param password
     * @param firstName
     * @param secondName
     * @param role
     */
    public User(String login, String password,
                 String firstName, String secondName,
                 Role role) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!getLogin().equals(user.getLogin())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (!getFirstName().equals(user.getFirstName())) return false;
        if (!getSecondName().equals(user.getSecondName())) return false;
        return getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getSecondName().hashCode();
        result = 31 * result + getRole().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", role=" + role +
                ", id=" + id +
                '}';
    }
}
