package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.dao.StaffDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Post;
import ua.nure.pashneva.SummaryTask4.db.entity.Staff;
import ua.nure.pashneva.SummaryTask4.db.entity.User;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Class that implements StaffDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
 */
public class MysqlStaffDAO implements StaffDAO {

    /**
     * String fields which contain sql queries to tables staff,
     * positions, positions_lang of MySQL database.
     */
    private static final String GET_ALL_STAFF = "SELECT * FROM ((staff s INNER JOIN posts p ON s.post_id=p.id) INNER JOIN posts_lang p_l ON p.id=p_l.post_id) WHERE p_l.lang_id=?";
    private static final String GET_STAFF_BY_ID = "SELECT * FROM ((staff s INNER JOIN posts p ON s.post_id=p.id) INNER JOIN posts_lang p_l ON p.id=p_l.post_id) WHERE s.id=? AND p_l.lang_id=?";
    //private static final String GET_STAFF_BY_BRIGADE_ID = "SELECT * FROM (((staff s INNER JOIN posts p ON s.post_id=p.id) INNER JOIN posts_lang p_l ON p.id=p_l.post_id) INNER JOIN brigades_staff b_s ON s.id=b_s.staff_id) WHERE b_s.brigade_id=?";
    private static final String GET_STAFF_BY_BRIGADE_ID = "SELECT * FROM brigades_staff WHERE brigade_id=?";
    private static final String GET_STAFF_BY_USER_ID = "SELECT * FROM ((staff s INNER JOIN posts p ON s.post_id=p.id) INNER JOIN posts_lang p_l ON p.id=p_l.post_id) WHERE s.user_id=? AND p_l.lang_id=?";
    private static final String GET_STAFF_BY_POST = "SELECT * FROM ((staff s INNER JOIN posts p ON s.post_id=p.id) INNER JOIN posts_lang p_l ON p.id=p_l.post_id)  WHERE s.post_id=? AND p_l.lang_id=?";
    private static final String GET_STAFF_BY_DATE = "SELECT * FROM ((staff s INNER JOIN posts p ON s.post_id=p.id) INNER JOIN posts_lang p_l ON p.id=p_l.post_id)  WHERE s.employment_date=?";
    private static final String GET_STAFF_WITH_BRIGADE_BY_STAFF_ID = "SELECT * FROM brigades_staff WHERE staff_id=?";
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
    private static final String STAFF_DATE = "s.employment_date";
    private static final String STAFF_ID = "staff_id";

    @Override
    public boolean create(Staff staff) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(ADD_STAFF,
                    Statement.RETURN_GENERATED_KEYS);

            int k = 1;
            statement.setInt(k++, staff.getUser().getId());
            statement.setInt(k++, staff.getPost().getId());

            if (statement.executeUpdate() > 0) {
                MysqlDAOFactory.setGeneratedId(staff, statement);
            }
            return true;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public Staff read(int id, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Staff staff = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_STAFF_BY_ID);

            int k = 1;
            statement.setInt(k++, id);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                staff = extractStaff(resultSet, language);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return staff;
    }

    @Override
    public List<Staff> readByPost(int postId, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Staff> staff = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_STAFF_BY_POST);

            int k = 1;
            statement.setInt(k++, postId);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Staff tempStaff = extractStaff(resultSet, language);
                staff.add(tempStaff);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return staff;
    }

    @Override
    public Staff readByUserLogin(String login, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Staff staff = null;
        User user = null;

        try {
            user = DAOFactory.getInstance().getUserDAO().readByLogin(login);

            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_STAFF_BY_USER_ID);

            int k = 1;
            statement.setInt(k++, user.getId());
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                staff = extractStaff(resultSet, language);
            }
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return staff;
    }

    @Override
    public List<Staff> readByBrigadeId(int brigadeId, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Staff> staff = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_STAFF_BY_BRIGADE_ID);

            int k = 1;
            statement.setInt(k++, brigadeId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int staffId = resultSet.getInt(STAFF_ID);
                Staff tempStaff = read(staffId, language);
                staff.add(tempStaff);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return staff;
    }

    @Override
    public List<Staff> readAll(Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Staff> staff = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL_STAFF);

            int k = 1;
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Staff tempStaff = extractStaff(resultSet, language);
                staff.add(tempStaff);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return staff;
    }

    @Override
    public boolean update(Staff staff) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_STAFF_BY_ID);

            int k = 1;
            statement.setInt(k++, staff.getUser().getId());
            statement.setInt(k++, staff.getPost().getId());
            statement.setInt(k++, staff.getId());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public boolean updatePassword(Staff staff) throws DBException {
        return false;
    }

    @Override
    public boolean updatePosition(Staff staff) throws DBException {
        return false;
    }

    @Override
    public boolean delete(int staffId) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_STAFF_BY_ID);

            int k = 1;
            statement.setInt(k++, staffId);

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    private boolean setGeneratedId(Staff staff, PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            staff.setId(generatedKeys.getInt(1));
            generatedKeys.close();
            return true;
        }
        generatedKeys.close();
        return false;
    }

    private Staff extractStaff(ResultSet resultSet, Language language) throws DBException {
        Staff staff = new Staff();
        try {
            staff.setId(resultSet.getInt(ENTITY_ID));
            staff.setUser(DAOFactory.getInstance().getUserDAO().read(resultSet.getInt(STAFF_USER_ID)));
            staff.setPost(DAOFactory.getInstance().getPostDAO().read(language, resultSet.getInt(STAFF_POST_ID)));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return staff;
    }
}
