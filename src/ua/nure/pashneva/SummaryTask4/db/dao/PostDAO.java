package ua.nure.pashneva.SummaryTask4.db.dao;

import ua.nure.pashneva.SummaryTask4.db.entity.FlightStatus;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Post;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.util.List;

/**
 * The data manipulation interface in the tables posts and posts_lang.
 *
 * @author Anastasia Pashneva
 *
 */
public interface PostDAO {

    /**
     * Method of obtaining a post from the database.
     *
     * @param id post identifier (primary unique key)
     * @param language object of Language class which contains data of current locale.
     * @return object which contains values of all fields from tables posts and posts_lang.
     * @throws DBException
     */
    Post read(Language language, Integer id) throws DBException;

    /**
     * Method of obtaining all posts from the database.
     *
     * @param language object of Language class which contains data of current locale.
     * @return object which contains values of all fields from tables posts and posts_lang.
     * @throws DBException
     */
    List<Post> readAll(Language language) throws DBException;
}
