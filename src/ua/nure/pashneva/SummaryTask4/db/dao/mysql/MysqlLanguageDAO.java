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

/**
 * Class that implements LanguageDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 */
public class MysqlLanguageDAO implements LanguageDAO {

    /**
     * String fields which contain sql queries to tables
     * table languages of MySQL database.
     */
    private static final String GET_ALL_LANGS = "SELECT * FROM languages";
    private static final String GET_LANG_BY_NAME = "SELECT * FROM languages WHERE name=?";
    private static final String GET_LANG_BY_PREFIX = "SELECT * FROM languages WHERE prefix=?";

    /**
     * String fields which contain column names of table languages.
     */
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

    /**
     * Private method for obtaining language data from ResultSet.
     *
     * @param resultSet instance of ResultSet which contains selected data from table languages.
     * @return object of Language which contains data obtained from ResultSet.
     * @throws Exception
     */
    private Language extractLanguage(ResultSet resultSet) throws Exception {
        Language language = new Language();
        language.setId(resultSet.getInt(ENTITY_ID));
        language.setName(resultSet.getString(LANG_NAME));
        language.setPrefix(resultSet.getString(LANG_PREFIX));
        return language;
    }
}
