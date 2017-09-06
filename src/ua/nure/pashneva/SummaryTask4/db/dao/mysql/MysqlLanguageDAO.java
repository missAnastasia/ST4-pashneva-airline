package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.LanguageDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlLanguageDAO implements LanguageDAO {

    private static final String GET_ALL_LANGS = "SELECT * FROM languages";
    private static final String GET_LANG_BY_ID = "SELECT * FROM languages WHERE id=?";
    private static final String GET_LANG_BY_NAME = "SELECT * FROM languages WHERE name=?";
    private static final String GET_LANG_BY_PREFIX = "SELECT * FROM languages WHERE prefix=?";
    private static final String ADD_LANG = "INSERT INTO languages VALUE(DEFAULT, ?, ?)";
    private static final String UPDATE_LANG_BY_ID = "UPDATE languages SET (name=?, prefix=?) WHERE id=?";
    private static final String DELETE_LANG_BY_ID = "DELETE FROM languages WHERE id=?";

    private static final String ENTITY_ID = "id";
    private static final String LANG_NAME = "name";
    private static final String LANG_PREFIX = "prefix";

    @Override
    public boolean create(Language language)  throws DBException {
        return false;
    }

    @Override
    public Language read(int id)  throws DBException {
        return null;
    }

    @Override
    public Language readByName(String name) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Language language = new Language();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_LANG_BY_NAME);

            int k = 1;
            statement.setString(k++, name);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                language = extractLanguage(resultSet);
            }
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return language;
    }

    @Override
    public Language readByPrefix(String prefix) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Language language = new Language();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_LANG_BY_PREFIX);

            int k = 1;
            statement.setString(k++, prefix);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                language = extractLanguage(resultSet);
            }
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return language;
    }

    @Override
    public List<Language> readAll() throws DBException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Language> languages = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_LANGS);

            while (resultSet.next()) {
                Language language = extractLanguage(resultSet);
                languages.add(language);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return languages;
    }

    @Override
    public boolean update(Language language) {
        return false;
    }

    @Override
    public boolean delete(Language language) {
        return false;
    }

    private Language extractLanguage(ResultSet resultSet) throws DBException {
        Language language = new Language();
        try {
            language.setId(resultSet.getInt(ENTITY_ID));
            language.setName(resultSet.getString(LANG_NAME));
            language.setPrefix(resultSet.getString(LANG_PREFIX));
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
        return language;
    }
}
