package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.List;

/**
 * The data manipulation interface in the table languages.
 *
 * @author Anastasia Pashneva
 *
 */
public interface LanguageDAO {

    /**
     *
     * @param language
     * @return
     * @throws DBException
     */
    boolean create(Language language) throws DBException;

    /**
     * Method of obtaining a language from the database.
     *
     * @param id language identifier (primary unique key)
     * @return object, which contains values of all fields from table languages.
     * @throws DBException
     */
    Language read(Integer id) throws DBException;

    /**
     * Method of obtaining a language from the database.
     *
     * @param name language name (e.g. ru, en)
     * @return object, which contains values of all fields from table languages.
     * @throws DBException
     */
    Language read(String name) throws DBException;

    /**
     * Method of obtaining all languages from the database.
     *
     * @return collection (List) of all languages.
     * @throws DBException
     */
    List<Language> readAll() throws DBException;

    /**
     *
     * @param language
     * @return
     * @throws DBException
     */
    boolean update(Language language) throws DBException;

    /**
     *
     * @param language
     * @return
     * @throws DBException
     */
    boolean delete(Language language) throws DBException;
}
