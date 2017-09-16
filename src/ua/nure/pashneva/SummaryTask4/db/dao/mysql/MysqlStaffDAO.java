package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.dao.StaffDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements StaffDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 */
public class MysqlStaffDAO implements StaffDAO {

    /**
     * String fields which contain sql queries to tables staff,
     * positions, positions_lang of MySQL database.
     */
    private static final String GET_ALL_STAFF = "SELECT * FROM ((staff s INNER JOIN posts p ON s.post_id=p.id) INNER JOIN posts_lang p_l ON p.id=p_l.post_id) WHERE p_l.lang_id=?";
    private static final String GET_STAFF_BY_ID = "SELECT * FROM ((staff s INNER JOIN posts p ON s.post_id=p.id) INNER JOIN posts_lang p_l ON p.id=p_l.post_id) WHERE s.id=? AND p_l.lang_id=?";
    private static final String GET_STAFF_BY_BRIGADE_ID = "SELECT * FROM brigades_staff WHERE brigade_id=?";
    private static final String GET_STAFF_BY_USER_ID = "SELECT * FROM ((staff s INNER JOIN posts p ON s.post_id=p.id) INNER JOIN posts_lang p_l ON p.id=p_l.post_id) WHERE s.user_id=? AND p_l.lang_id=?";
    private static final String GET_STAFF_BY_POST = "SELECT * FROM ((staff s INNER JOIN posts p ON s.post_id=p.id) INNER JOIN posts_lang p_l ON p.id=p_l.post_id)  WHERE s.post_id=? AND p_l.lang_id=?";
    private static final String ADD_STAFF = "INSERT INTO staff VALUE(DEFAULT, ?, ?)";
    private static final String UPDATE_STAFF_BY_ID = "UPDATE staff SET user_id=?, post_id=? WHERE id=?";
    private static final String DELETE_STAFF_BY_ID = "DELETE FROM staff WHERE id=?";

    /**
     * String fields which contain column names of staff,
     * positions, positions_lang.
     */
    private static final String ENTITY_ID = "s.id";
    private static final String STAFF_POST_ID = "s.post_id";
    private static final String STAFF_USER_ID = "s.user_id";
    private static final String STAFF_ID = "staff_id";

    @Override
    public boolean create(Staff staff) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(ADD_STAFF,
                Statement.RETURN_GENERATED_KEYS);

        int k = 1;
        statement.setInt(k++, staff.getUser().getId());
        statement.setInt(k++, staff.getPost().getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            MysqlDAOFactory.setGeneratedId(staff, statement);
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public Staff read(int id, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_STAFF_BY_ID);

        int k = 1;
        statement.setInt(k++, id);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        Staff staff = null;
        if (resultSet.next()) {
            staff = extractStaff(resultSet, language);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return staff;
    }

    @Override
    public List<Staff> readByPost(int postId, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_STAFF_BY_POST);

        int k = 1;
        statement.setInt(k++, postId);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<Staff> staff = new ArrayList<>();
        while (resultSet.next()) {
            Staff tempStaff = extractStaff(resultSet, language);
            staff.add(tempStaff);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return staff;
    }

    @Override
    public Staff readByUserId(int userId, Language language) throws Exception {
        User user = DAOFactory.getInstance().getUserDAO().read(userId);

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_STAFF_BY_USER_ID);

        int k = 1;
        statement.setInt(k++, user.getId());
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        Staff staff = null;
        if (resultSet.next()) {
            staff = extractStaff(resultSet, language);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return staff;
    }

    @Override
    public List<Staff> readByBrigadeId(int brigadeId, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_STAFF_BY_BRIGADE_ID);

        int k = 1;
        statement.setInt(k++, brigadeId);

        ResultSet resultSet = statement.executeQuery();

        List<Staff> staff = new ArrayList<>();
        while (resultSet.next()) {
            int staffId = resultSet.getInt(STAFF_ID);
            Staff tempStaff = read(staffId, language);
            staff.add(tempStaff);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return staff;
    }

    @Override
    public List<Staff> readFreeStaff(Language language) throws Exception {
        List<Staff> freeStaff = new ArrayList<>();
        List<Staff> allStaff = readAll(language);
        for (Staff s : allStaff) {
            if (DAOFactory.getInstance().getBrigadeDAO().readByStaff(s, language) == null) {
                freeStaff.add(s);
            }
        }
        return freeStaff;
    }

    @Override
    public List<Staff> readAll(Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_STAFF);

        int k = 1;
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<Staff> staff = new ArrayList<>();
        while (resultSet.next()) {
            Staff tempStaff = extractStaff(resultSet, language);
            staff.add(tempStaff);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return staff;
    }

    @Override
    public boolean update(Staff staff) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_STAFF_BY_ID);

        int k = 1;
        statement.setInt(k++, staff.getUser().getId());
        statement.setInt(k++, staff.getPost().getId());
        statement.setInt(k++, staff.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public boolean delete(int staffId) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_STAFF_BY_ID);

        int k = 1;
        statement.setInt(k++, staffId);

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    /**
     * Private method for obtaining staff data from ResultSet.
     *
     * @param resultSet instance of ResultSet which contains selected data from
     *                  table staff.
     * @param language object of Language class which contains data about current locale.
     * @return object of Staff which contains data obtained from ResultSet.
     * @throws Exception
     */
    private Staff extractStaff(ResultSet resultSet, Language language) throws Exception {
        Staff staff = new Staff();
        staff.setId(resultSet.getInt(ENTITY_ID));
        staff.setUser(DAOFactory.getInstance().getUserDAO().read(resultSet.getInt(STAFF_USER_ID)));
        staff.setPost(DAOFactory.getInstance().getPostDAO().read(language, resultSet.getInt(STAFF_POST_ID)));
        return staff;
    }
}
