package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.RequestStatusDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestStatus;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlRequestStatusDAO implements RequestStatusDAO {

    /**
     * String fields which contain sql queries to tables
     * tables request_statuses and request_statuses_lang of MySQL database.
     */
    private static final String GET_REQUEST_STATUS_BY_ID = "SELECT * FROM (request_statuses r_s INNER JOIN request_statuses_lang r_s_l ON r_s.id=r_s_l.request_status_id) WHERE r_s.id=? AND r_s_l.lang_id=?";
    private static final String GET_ALL_STATUSES = "SELECT * FROM (request_statuses r_s INNER JOIN request_statuses_lang r_s_l ON r_s.id=r_s_l.request_status_id) WHERE r_s_l.lang_id=?";

    /**
     * String fields which contain column names of tables request_statuses and request_statuses_lang.
     */
    private static final String ENTITY_ID = "r_s.id";
    private static final String REQUEST_STATUS_NAME = "r_s_l.name";

    @Override
    public RequestStatus read(Language language, Integer id) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        RequestStatus requestStatus = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_REQUEST_STATUS_BY_ID);

            int k = 1;
            statement.setInt(k++, id);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                requestStatus = extractRequestStatus(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return requestStatus;
    }

    @Override
    public List<RequestStatus> readAll(Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<RequestStatus> statuses = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL_STATUSES);

            int k = 1;
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RequestStatus requestStatus = extractRequestStatus(resultSet);
                statuses.add(requestStatus);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return statuses;
    }

    private static RequestStatus extractRequestStatus(ResultSet resultSet) throws DBException {
        RequestStatus requestStatus = new RequestStatus();
        try {
            requestStatus.setId(resultSet.getInt(ENTITY_ID));
            requestStatus.setName(resultSet.getString(REQUEST_STATUS_NAME));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return requestStatus;
    }
}
