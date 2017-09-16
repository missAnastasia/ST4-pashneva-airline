package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.User;

import java.util.List;

/**
 * The data manipulation interface in the table users.
 *
 * @author Anastasia Pashneva
 */
public interface UserDAO {

    /**
     * Method of creating a user in the database. <br/>
     * After the method is executed the user object contains the value of the identifier (id),
     * this information comes from the DBMS side.
     *
     * @param user object that contains information about a user without an identifier.
     * @return true - user was successfully inserted into database, otherwise - false.
     * @throws Exception
     */
    boolean create(User user) throws Exception;

    /**
     * Method of obtaining a user from the database.
     *
     * @param id user identifier (primary unique key)
     * @return object which contains values of all fields from table users.
     * @throws Exception
     */
    User read(int id) throws Exception;

    /**
     * Method of obtaining a user from the database.
     *
     * @param login user login (email address)
     * @return object which contains values of all fields from table users.
     * @throws Exception
     */
    User readByLogin(String login) throws Exception;

    /**
     * Method of obtaining all users from the database.
     *
     * @return collection (List) of all users.
     * @throws Exception
     */
    List<User> readAll() throws Exception;

    /**
     * Method of updating user data in database. <br/>
     * All fields are updated except the password
     * userStatus and role fields.
     *
     * @param user object that contains user data.
     *             The identifier must be present.
     * @return true - user data was successfully updated in database, otherwise - false.
     * @throws Exception
     */
    boolean update(User user) throws Exception;

    /**
     * Method of updating user password in database. <br/>
     * Only password field is updated.
     *
     * @param user object that contains user data.
     *             The identifier must be present.
     * @return true - user password was successfully updated in database, otherwise - false.
     * @throws Exception
     */
    boolean updatePassword(User user) throws Exception;

    /**
     * Method of deleting user from database.
     *
     * @param user object that contains information about a user who must be deleted.
     * @return true - user was successfully deleted from database, otherwise - false.
     * @throws Exception
     */
    boolean delete(User user) throws Exception;
}
