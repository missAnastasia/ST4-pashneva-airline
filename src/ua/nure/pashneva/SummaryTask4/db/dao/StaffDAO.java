package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.Position;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.Date;
import java.util.List;

/**
 * The data manipulation interface in the table staff.
 *
 * @author Anastasia Pashneva
 *
 */
public interface StaffDAO {

    /**
     * Method of creating a staff in the database. <br/>
     * After the method is executed the staff object contains the value of the identifier (id),
     * this information comes from the DBMS side.
     *
     * @param staff object that contains information about a staff without an identifier.
     * @return true - staff was successfully inserted into database, otherwise - false.
     * @throws DBException
     */
    boolean create(Staff staff) throws DBException;

    /**
     * Method of obtaining a staff from the database.
     *
     * @param id staff identifier (primary unique key)
     * @return object which contains values of all fields from table staff.
     * @throws DBException
     */
    Staff read(int id) throws DBException;

    /**
     * Method of obtaining a staff from the database.
     *
     * @param position an instance of Position enum,
     *                  which contains a value of staff position.
     * @return collection (List) of objects which contains values of all fields from table staff
     *         with adjusted position.
     * @throws DBException
     */
    List<Staff> read(Position position) throws DBException;

    /**
     * Method of obtaining a staff from the database.
     *
     * @param login e-mail address of the staff
     * @return collection (List) of objects which contains values of all fields from table staff
     *         with adjusted user login.
     * @throws DBException
     */
    Staff read(String login) throws DBException;

    /**
     * Method of obtaining a staff from the database.
     *
     * @param date object which contains the date of employment of the staff.
     * @return collection (List) of objects which contains values of all fields from table staff
     *         with adjusted date of employment.
     * @throws DBException
     */
    List<Staff> read(Date date) throws DBException;

    /**
     * Method of obtaining all staff from the database.
     *
     * @return collection (List) of all staff.
     * @throws DBException
     */
    List<Staff> readAll() throws DBException;

    /**
     * Method of updating staff data in database. <br/>
     * All fields are updated except the password
     * and position fields.
     *
     * @param staff object that contains staff data.
     *             The identifier must be present.
     * @return true - staff data was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean update(Staff staff) throws DBException;

    /**
     * Method of updating staff password in database. <br/>
     * Only password field is updated.
     *
     * @param staff object that contains staff data.
     *             The identifier must be present.
     * @return true - staff password was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean updatePassword(Staff staff) throws DBException;

    /**
     * Method of updating staff position in database.<br/>
     * Only position_id field is updated.
     *
     * @param staff object that contains staff data.
     *             The identifier must be present.
     * @return true - staff position was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean updatePosition(Staff staff) throws DBException;

    /**
     * Method of deleting user from database.
     *
     * @param staff object that contains information about a staff who must be deleted.
     * @return true - staff was successfully deleted from database, otherwise - false.
     * @throws DBException
     */
    boolean delete(Staff staff) throws DBException;
}
