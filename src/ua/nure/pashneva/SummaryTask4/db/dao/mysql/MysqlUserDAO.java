package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.UserDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Role;
import ua.nure.pashneva.SummaryTask4.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements UserDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 */
public class MysqlUserDAO  implements UserDAO {

    /**
     * String fields which contain sql queries to table users of MySQL database.
     */
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users  WHERE login=?";
    private static final String ADD_USER = "INSERT INTO users VALUE(DEFAULT, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET login=?, first_name=?, second_name=? WHERE id=?";
    private static final String UPDATE_USER_PASSWORD_BY_ID = "UPDATE users SET password=? WHERE id=?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";

    /**
     * String fields which contain column names of table users.
     */
    private static final String ENTITY_ID = "id";
    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_SECOND_NAME = "second_name";
    private static final String USER_ROLE_ID = "role_id";

    @Override
    public boolean create(User user) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(ADD_USER,
                Statement.RETURN_GENERATED_KEYS);

        int k = 1;
        statement.setString(k++, user.getFirstName());
        statement.setString(k++, user.getSecondName());
        statement.setString(k++, user.getLogin());
        statement.setString(k++, user.getPassword());
        statement.setInt(k++, Role.getRoleOrdinal(user.getRole()));

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            MysqlDAOFactory.setGeneratedId(user, statement);
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public User read(int id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);

        int k = 1;
        statement.setInt(k++, id);

        ResultSet resultSet = statement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = extractUser(resultSet);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return user;
    }

    @Override
    public User readByLogin(String login) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN);

        int k = 1;
        statement.setString(k++, login);

        ResultSet resultSet = statement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = extractUser(resultSet);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return user;
    }

    @Override
    public List<User> readAll() throws Exception {
        Connection  connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);

        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = extractUser(resultSet);
            users.add(user);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return users;
    }

    @Override
    public boolean update(User user) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BY_ID);

        int k = 1;
        statement.setString(k++, user.getLogin());
        statement.setString(k++, user.getFirstName());
        statement.setString(k++, user.getSecondName());
        statement.setInt(k++, user.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public boolean updatePassword(User user) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_ID);

        int k = 1;

        statement.setString(k++, user.getPassword());
        statement.setInt(k++, user.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public boolean delete(User user) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID);

        int k = 1;
        statement.setInt(k++, user.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    /**
     * Private method for obtaining user data from ResultSet.
     *
     * @param resultSet instance of ResultSet which contains selected data from
     *                  tables users.
     * @return object of User which contains data obtained from ResultSet.
     * @throws Exception
     */
    private User extractUser(ResultSet resultSet) throws Exception {
        User user = new User();
        user.setId(resultSet.getInt(ENTITY_ID));
        user.setLogin(resultSet.getString(USER_LOGIN));
        user.setPassword(resultSet.getString(USER_PASSWORD));
        user.setFirstName(resultSet.getString(USER_FIRST_NAME));
        user.setSecondName(resultSet.getString(USER_SECOND_NAME));
        user.setRole(Role.getRole(resultSet.getInt(USER_ROLE_ID)));
        return user;
    }
}
