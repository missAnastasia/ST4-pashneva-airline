package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.DAOFactory;
import ua.nure.pashneva.SummaryTask4.db.dao.RequestToAdminDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestToAdmin;

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
    private static final String GET_REQUEST_BY_NUMBER = "SELECT * FROM requests_to_admin WHERE number=?";
    private static final String GET_REQUEST_BY_STATUS = "SELECT * FROM requests_to_admin WHERE request_status_id=?";
    private static final String GET_REQUEST_BY_DATE = "SELECT * FROM requests_to_admin WHERE date=?";
    private static final String GET_REQUEST_BY_USER_ID = "SELECT * FROM requests_to_admin WHERE user_id=?";
    private static final String ADD_REQUEST = "INSERT INTO requests_to_admin VALUE(DEFAULT, ?, ?, ?, ?, ?)";
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
    private static final String REQUEST_NUMBER = "number";

    @Override
    public boolean create(RequestToAdmin request) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(ADD_REQUEST,
                Statement.RETURN_GENERATED_KEYS);

        int k = 1;
        statement.setInt(k++, request.getNumber());
        statement.setInt(k++, request.getUser().getId());
        statement.setInt(k++, request.getRequestStatus().getId());
        statement.setString(k++, request.getMessage());
        statement.setDate(k++, request.getDate());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            MysqlDAOFactory.setGeneratedId(request, statement);
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public RequestToAdmin read(int id, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_REQUEST_BY_ID);

        int k = 1;
        statement.setInt(k++, id);

        ResultSet resultSet = statement.executeQuery();

        RequestToAdmin request = null;
        if (resultSet.next()) {
            request = extractRequestToAdmin(resultSet, language);
        }
        DBConnection.getInstance().close(connection, statement, resultSet);
        return request;
    }

    @Override
    public List<RequestToAdmin> readByStatus(RequestStatus requestStatus, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_REQUEST_BY_STATUS);

        int k = 1;
        statement.setInt(k++, requestStatus.getId());

        ResultSet resultSet = statement.executeQuery();

        List<RequestToAdmin> requests = new ArrayList<>();
        while (resultSet.next()) {
            RequestToAdmin request = extractRequestToAdmin(resultSet, language);
            requests.add(request);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return requests;
    }

    @Override
    public List<RequestToAdmin> readByDate(Date date, Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_REQUEST_BY_DATE);

        int k = 1;
        statement.setDate(k++, date);

        ResultSet resultSet = statement.executeQuery();

        List<RequestToAdmin> requests = new ArrayList<>();
        while (resultSet.next()) {
            RequestToAdmin request = extractRequestToAdmin(resultSet, language);
            requests.add(request);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return requests;
    }

    @Override
    public List<RequestToAdmin> readAll(Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_REQUESTS);

        List<RequestToAdmin> requests = new ArrayList<>();
        while (resultSet.next()) {
            RequestToAdmin request = extractRequestToAdmin(resultSet, language);
            requests.add(request);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return requests;
    }

    @Override
    public boolean update(RequestToAdmin request) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_REQUEST_MESSAGE_BY_ID);

        int k = 1;
        statement.setString(k++, request.getMessage());
        statement.setInt(k++, request.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public boolean updateStatus(RequestToAdmin request) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_REQUEST_STATUS_BY_ID);

        int k = 1;
        statement.setInt(k++, request.getRequestStatus().getId());
        statement.setInt(k++, request.getId());

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }

        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    @Override
    public boolean delete(int requestId) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_REQUEST_BY_ID);

        int k = 1;
        statement.setInt(k++, requestId);

        boolean result = false;
        if (statement.executeUpdate() > 0) {
            result = true;
        }
        DBConnection.getInstance().close(connection, statement);
        return result;
    }

    private RequestToAdmin extractRequestToAdmin(ResultSet resultSet, Language language) throws Exception {
        RequestToAdmin request = new RequestToAdmin();
        request.setId(resultSet.getInt(ENTITY_ID));
        request.setUser(DAOFactory.getInstance().getUserDAO().read(resultSet.getInt(REQUEST_USER_ID)));
        request.setRequestStatus(DAOFactory.getInstance().getRequestStatusDAO().read(language, resultSet.getInt(REQUEST_STATUS_ID)));
        request.setMessage(resultSet.getString(REQUEST_MESSAGE));
        request.setDate(resultSet.getDate(REQUEST_DATE));
        request.setNumber(resultSet.getInt(REQUEST_NUMBER));
        return request;
    }
}
