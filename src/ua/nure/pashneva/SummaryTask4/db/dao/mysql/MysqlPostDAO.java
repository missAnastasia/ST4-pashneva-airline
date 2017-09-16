package ua.nure.pashneva.SummaryTask4.db.dao.mysql;

import ua.nure.pashneva.SummaryTask4.db.connection.DBConnection;
import ua.nure.pashneva.SummaryTask4.db.dao.PostDAO;
import ua.nure.pashneva.SummaryTask4.db.entity.Language;
import ua.nure.pashneva.SummaryTask4.db.entity.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements PostDAO interface and all its methods.
 * Implementation for MySQL DBMS.
 *
 * @author Anastasia Pashneva
 */
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
    public Post read(Language language, int id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_POST_BY_ID);

        int k = 1;
        statement.setInt(k++, id);
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        Post post = null;
        if (resultSet.next()) {
            post = extractPost(resultSet);
        }
        DBConnection.getInstance().close(connection, statement, resultSet);
        return post;
    }

    @Override
    public List<Post> readAll(Language language) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_POSTS);

        int k = 1;
        statement.setInt(k++, language.getId());

        ResultSet resultSet = statement.executeQuery();

        List<Post> posts = new ArrayList<>();
        while (resultSet.next()) {
            Post post = extractPost(resultSet);
            posts.add(post);
        }
        DBConnection.getInstance().close(connection, statement, resultSet);
        return posts;
    }

    /**
     * Private method for obtaining post data from ResultSet.
     *
     * @param resultSet instance of ResultSet which contains selected data from table posts.
     * @return object of Post which contains data obtained from ResultSet.
     * @throws Exception
     */
    private static Post extractPost(ResultSet resultSet) throws Exception {
        Post post = new Post();
        post.setId(resultSet.getInt(ENTITY_ID));
        post.setName(resultSet.getString(POST_NAME));
        return post;
    }
}
