package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.Brigade;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.db.entity.UserStatus;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.List;

public interface BrigadeDAO {
    /**
     * Method of creating a brigade in the database. <br/>
     * After the method is executed the brigade object contains the value of the identifier (id),
     * this information comes from the DBMS side.
     *
     * @param brigade object that contains information about a brigade without an identifier.
     * @return true - brigade was successfully inserted into database, otherwise - false.
     * @throws DBException
     */
    boolean create(Brigade brigade) throws DBException;

    /**
     * Method of obtaining a brigade from the database.
     *
     * @param id brigade identifier (primary unique key)
     * @return object which contains values of all fields from table brigades.
     * @throws DBException
     */
    Brigade read(Integer id) throws DBException;

    /**
     * Method of obtaining a brigade from the database.
     *
     * @param staff an instance of Staff class,
     *                  which contains data of the stuff.
     * @return object which contains values of all fields from table brigades.
     * @throws DBException
     */
    Brigade readByStaff(Staff staff) throws DBException;

    /**
     * Method of obtaining all brigades from the database.
     *
     * @return collection (List) of all brigades.
     * @throws DBException
     */
    List<Brigade> readAll() throws DBException;

    /**
     * Method of updating brigade data in database. <br/>
     * All fields are updated.
     *
     * @param brigade object that contains brigade data.
     *             The identifier must be present.
     * @return true - brigade data was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean update(Brigade brigade) throws DBException;

    /**
     * Method of updating brigades_staff data in database. <br/>
     * Only brigade_id field is updated.
     *
     * @param brigade object that contains brigade data.
     *             The identifier must be present.
     * @param staff object that contains staff data.
     *             The identifier must be present.
     * @return true - brigades_staff data was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean changeBrigadeForStaff(Brigade brigade, Staff staff) throws DBException;

    /**
     * Method of updating brigades_staff data in database. <br/>
     * Only staff_id field is updated.
     *
     * @param brigade object that contains brigade data.
     *             The identifier must be present.
     * @param staff object that contains staff data.
     *             The identifier must be present.
     * @return true - brigades_staff data was successfully updated in database, otherwise - false.
     * @throws DBException
     */
    boolean changeStaffForBrigade(Brigade brigade, Staff staff) throws DBException;

    /**
     * Method of deleting brigade from database.
     *
     * @param brigade object that contains information about a brigade which must be deleted.
     * @return true - brigade was successfully deleted from database, otherwise - false.
     * @throws DBException
     */
    boolean delete(Brigade brigade) throws DBException;

    /**
     * Method of deleting staff from brigade.
     *
     * @param brigade object that contains information about a brigade.
     * @param staff object that contains information about staff who must be deleted from brigade.
     * @return true - staff was successfully deleted from brigade, otherwise - false.
     * @throws DBException
     */
    boolean deleteStaffFromBrigade(Brigade brigade, Staff staff) throws DBException;
}
