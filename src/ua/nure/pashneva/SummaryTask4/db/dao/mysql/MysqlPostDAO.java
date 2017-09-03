package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.PostDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Post;
import ua.nure.pashneva.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MysqlPostDAO implements PostDAO {

    /**
     * String fields which contain sql queries to tables
     * tables posts and posts_lang of MySQL database.
     */
    private static final String GET_POST_BY_ID = "SELECT * FROM (posts p INNER JOIN posts_lang p_l ON p.id=p_l.post_id) WHERE p.id=? AND p_l.lang_id=?";
    private static final String GET_ALL_POSTS = "SELECT * FROM (posts p INNER JOIN posts_lang p_l ON p.id=p_l.post_id) WHERE p_l.lang_id=?";

    /**
     * String fields which contain column names of tables posts and posts_lang.
     */
    private static final String ENTITY_ID = "p.id";
    private static final String POST_NAME = "p_l.name";

    @Override
    public Post read(Language language, Integer id) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Post post = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_POST_BY_ID);

            int k = 1;
            statement.setInt(k++, id);
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                post = extractPost(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return post;
    }

    @Override
    public List<Post> readAll(Language language) throws DBException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Post> posts = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GET_ALL_POSTS);

            int k = 1;
            statement.setInt(k++, language.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Post post = extractPost(resultSet);
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            DBConnection.getInstance().close(connection, statement, resultSet);
        }
        return posts;
    }

    private static Post extractPost(ResultSet resultSet) throws DBException {
        Post post = new Post();
        try {
            post.setId(resultSet.getInt(ENTITY_ID));
            post.setName(resultSet.getString(POST_NAME));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return post;
    }
}
