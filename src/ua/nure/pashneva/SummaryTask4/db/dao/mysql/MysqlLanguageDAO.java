package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.LanguageDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
    public Language readByName(String name) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_LANG_BY_NAME);

        int k = 1;
        statement.setString(k++, name);

        ResultSet resultSet = statement.executeQuery();

        Language language = new Language();
        if (resultSet.next()) {
            language = extractLanguage(resultSet);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return language;
    }

    @Override
    public Language readByPrefix(String prefix) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_LANG_BY_PREFIX);

        int k = 1;
        statement.setString(k++, prefix);

        ResultSet resultSet = statement.executeQuery();

        Language language = new Language();
        if (resultSet.next()) {
            language = extractLanguage(resultSet);
        }

        DBConnection.getInstance().close(connection, statement, resultSet);
        return language;
    }

    @Override
    public List<Language> readAll() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_LANGS);

        List<Language> languages = new ArrayList<>();
        while (resultSet.next()) {
            Language language = extractLanguage(resultSet);
            languages.add(language);
        }
        DBConnection.getInstance().close(connection, statement, resultSet);
        return languages;
    }

    private Language extractLanguage(ResultSet resultSet) throws Exception {
        Language language = new Language();
        language.setId(resultSet.getInt(ENTITY_ID));
        language.setName(resultSet.getString(LANG_NAME));
        language.setPrefix(resultSet.getString(LANG_PREFIX));
        return language;
    }
}
