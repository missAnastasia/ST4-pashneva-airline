package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.Language;

import java.util.List;

/**
 * The data manipulation interface in the table languages.
 *
 * @author Anastasia Pashneva
 */
public interface LanguageDAO {

    /**
     * Method of obtaining a language from the database.
     *
     * @param name language name (e.g. Russian, English)
     * @return object, which contains values of all fields from table languages.
     * @throws Exception
     */
    Language readByName(String name) throws Exception;

    /**
     * Method of obtaining a language from the database.
     *
     * @param prefix language prefix (e.g. ru, en)
     * @return object, which contains values of all fields from table languages.
     * @throws Exception
     */
    Language readByPrefix(String prefix) throws Exception;

    /**
     * Method of obtaining all languages from the database.
     *
     * @return collection (List) of all languages.
     * @throws Exception
     */
    List<Language> readAll() throws Exception;
}
