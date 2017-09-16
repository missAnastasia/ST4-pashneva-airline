package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.RequestStatusDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.RequestStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements RequestStatusDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 */
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
    public RequestStatus read(Language language, int id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_REQUEST_STATUS_BY_ID);

        int k = 1;
        statement.setInt(k++, id);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        RequestStatus requestStatus = null;
        if (resultSet.next()) {
            requestStatus = extractRequestStatus(resultSet);
        }
        DBConnection.getInstance().close(connection, statement, resultSet);
        return requestStatus;
    }

    @Override
    public List<RequestStatus> readAll(Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_STATUSES);

        int k = 1;
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<RequestStatus> statuses = new ArrayList<>();
        while (resultSet.next()) {
            RequestStatus requestStatus = extractRequestStatus(resultSet);
            statuses.add(requestStatus);
        }
        DBConnection.getInstance().close(connection, statement, resultSet);
        return statuses;
    }

    /**
     * Private method for obtaining request status data from ResultSet.
     *
     * @param resultSet instance of ResultSet which contains selected data from
     *                  tables request_statuses and request_statuses_lang.
     * @return object of RequestStatus which contains data obtained from ResultSet.
     * @throws Exception
     */
    private static RequestStatus extractRequestStatus(ResultSet resultSet) throws Exception {
        RequestStatus requestStatus = new RequestStatus();
        requestStatus.setId(resultSet.getInt(ENTITY_ID));
        requestStatus.setName(resultSet.getString(REQUEST_STATUS_NAME));
        return requestStatus;
    }
}
