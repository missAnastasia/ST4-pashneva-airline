package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.UserDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Role;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.db.entity.UserStatus;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.*;
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
    private static final String GET_USER_BY_STATUS = "SELECT * FROM users  WHERE user_status_id=?";
    private static final String ADD_USER = "INSERT INTO users VALUE(DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET login=?, first_name=?, second_name=? WHERE id=?";
    private static final String UPDATE_USER_PASSWORD_BY_ID = "UPDATE users SET password=? WHERE id=?";
    private static final String UPDATE_USER_STATUS_BY_ID = "UPDATE users SET user_status_id=? WHERE id=?";
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
    private static final String USER_STATUS_ID = "user_status_id";

    @Override
    public boolean create(User user) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(ADD_USER);

            int k = 1;
            statement.setString(k++, user.getLogin());
            statement.setString(k++, user.getPassword());
            statement.setString(k++, user.getFirstName());
            statement.setString(k++, user.getSecondName());
            statement.setInt(k++, Role.getRoleOrdinal(user.getRole()));
            /*statement.setInt(k++, UserStatus.getUserStatusOrdinal(user.getUserStatus()));*/

            if (statement.executeUpdate() > 0) {
                generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, generatedKeys);
        }
    }

    @Override
    public User read(Integer id) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_USER_BY_ID);

            int k = 1;
            statement.setInt(k++, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public User read(String login) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_USER_BY_LOGIN);

            int k = 1;
            statement.setString(k++, login);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public List<User> read(UserStatus userStatus) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_USER_BY_STATUS);

            int k = 1;
            statement.setInt(k++, UserStatus.getUserStatusOrdinal(userStatus));

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = extractUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return users;
    }

    @Override
    public List<User> readAll() throws DBException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_USERS);

            while (resultSet.next()) {
                User user = extractUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return users;
    }

    @Override
    public boolean update(User user) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_USER_BY_ID);

            int k = 1;
            statement.setString(k++, user.getLogin());
            statement.setString(k++, user.getFirstName());
            statement.setString(k++, user.getSecondName());
            statement.setInt(k++, user.getId());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public boolean updatePassword(User user) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_ID);

            int k = 1;

            statement.setString(k++, user.getPassword());
            statement.setInt(k++, user.getId());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    /*@Override
    public boolean updateStatus(User user) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_USER_STATUS_BY_ID);

            int k = 1;

            *//*statement.setInt(k++, UserStatus.getUserStatusOrdinal(user.getUserStatus()));*//*
            statement.setInt(k++, user.getId());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }*/

    @Override
    public boolean delete(User user) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_USER_BY_ID);

            int k = 1;
            statement.setInt(k++, user.getId());

            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    private User extractUser(ResultSet resultSet) throws DBException {
        User user = new User();
        try {
            user.setId(resultSet.getInt(ENTITY_ID));
            user.setLogin(resultSet.getString(USER_LOGIN));
            user.setPassword(resultSet.getString(USER_PASSWORD));
            user.setFirstName(resultSet.getString(USER_FIRST_NAME));
            user.setSecondName(resultSet.getString(USER_SECOND_NAME));
            user.setRole(Role.getRole(resultSet.getInt(USER_ROLE_ID)));
            /*user.setUserStatus(UserStatus.getUserStatus(resultSet.getInt(USER_STATUS_ID)));*/
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return user;
    }
}
