package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.dao.RequestToAdminDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.*;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements RequestToAdminDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 *
 */
public class MysqlRequestToAdminDAO implements RequestToAdminDAO {

    /**
     * String fields which contain sql queries to tables requests_to_admin,
     * request_statuses, request_statuses_lang of MySQL database.
     */
    private static final String GET_ALL_REQUESTS = "SELECT * FROM requests_to_admin";
    private static final String GET_REQUEST_BY_ID = "SELECT * FROM requests_to_admin WHERE id=?";
    private static final String GET_REQUEST_BY_STATUS = "SELECT * FROM requests_to_admin WHERE request_status_id=?";
    private static final String GET_REQUEST_BY_USER_ID = "SELECT * FROM requests_to_admin WHERE user_id=?";
    private static final String ADD_REQUEST = "INSERT INTO requests_to_admin VALUE(DEFAULT, ?, ?, ?, ?)";
    private static final String UPDATE_REQUEST_STATUS_BY_ID = "UPDATE requests_to_admin SET request_status_id=? WHERE id=?";
    private static final String UPDATE_REQUEST_MESSAGE_BY_ID = "UPDATE requests_to_admin SET message=? WHERE id=?";
    private static final String DELETE_REQUEST_BY_ID = "DELETE FROM requests_to_admin WHERE id=?";

    /**
     * String fields which contain column names of requests_to_admin,
     * request_statuses, request_statuses_lang.
     */
    private static final String ENTITY_ID = "id";
    private static final String REQUEST_STATUS_ID = "request_status_id";
    private static final String REQUEST_USER_ID = "user_id";
    private static final String REQUEST_MESSAGE = "message";
    private static final String REQUEST_DATE = "date";

    @Override
    public boolean create(RequestToAdmin request) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(ADD_REQUEST);

            int k = 1;
            statement.setInt(k++, request.getUser().getId());
            statement.setInt(k++, request.getRequestStatus().getId());
            statement.setString(k++, request.getMessage());
            statement.setString(k++, request.getDate());

            if (statement.executeUpdate() > 0) {
                return MysqlDAOFactory.setGeneratedId(request, statement);
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public RequestToAdmin read(Integer id, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        RequestToAdmin request = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_REQUEST_BY_ID);

            int k = 1;
            statement.setInt(k++, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                request = extractRequestToAdmin(resultSet, language);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return request;
    }

    @Override
    public List<RequestToAdmin> readByStatus(RequestStatus requestStatus, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<RequestToAdmin> requests = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_REQUEST_BY_STATUS);

            int k = 1;
            statement.setInt(k++, requestStatus.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RequestToAdmin request = extractRequestToAdmin(resultSet, language);
                requests.add(request);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return requests;
    }

    @Override
    public List<RequestToAdmin> readByUser(User user, Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<RequestToAdmin> requests = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_REQUEST_BY_USER_ID);

            int k = 1;
            statement.setInt(k++, user.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RequestToAdmin request = extractRequestToAdmin(resultSet, language);
                requests.add(request);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return requests;
    }

    @Override
    public List<RequestToAdmin> readAll(Language language) throws DBException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<RequestToAdmin> requests = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_REQUESTS);

            while (resultSet.next()) {
                RequestToAdmin request = extractRequestToAdmin(resultSet, language);
                requests.add(request);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return requests;
    }

    @Override
    public boolean update(RequestToAdmin request) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_REQUEST_MESSAGE_BY_ID);

            int k = 1;
            statement.setString(k++, request.getMessage());
            statement.setInt(k++, request.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public boolean updateStatus(RequestToAdmin request) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_REQUEST_STATUS_BY_ID);

            int k = 1;
            statement.setInt(k++, request.getRequestStatus().getId());
            statement.setInt(k++, request.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    @Override
    public boolean delete(RequestToAdmin request) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_REQUEST_BY_ID);

            int k = 1;
            statement.setInt(k++, request.getId());

            if (statement.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement);
        }
    }

    private RequestToAdmin extractRequestToAdmin(ResultSet resultSet, Language language) throws DBException {
        RequestToAdmin request = new RequestToAdmin();
        try {
            request.setId(resultSet.getInt(ENTITY_ID));
            request.setUser(DAOFactory.getInstance().getUserDAO().read(resultSet.getInt(REQUEST_USER_ID)));
            request.setRequestStatus(DAOFactory.getInstance().getRequestStatusDAO().read(language, resultSet.getInt(REQUEST_STATUS_ID)));
            request.setMessage(resultSet.getString(REQUEST_MESSAGE));
            request.setDate(resultSet.getString(REQUEST_DATE));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return request;
    }
}
