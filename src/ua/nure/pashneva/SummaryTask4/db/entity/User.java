package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this class are strings from the table users.
 *
 * @author Anastasia Pashneva
 */
public class User {

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private Role role;
    private UserStatus userStatus;

    public User() {
    }

    /**
     * Constructor of class for creating object without without an identifier.
     *
     * @param login
     * @param password
     * @param firstName
     * @param secondName
     * @param role
     * @param userStatus
     */
    public User(String login, String password,
                String firstName, String secondName,
                Role role, UserStatus userStatus) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = role;
        this.userStatus = userStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
