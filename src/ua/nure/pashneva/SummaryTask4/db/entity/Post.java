package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this enum are strings from table posts.
 *
 * @author Anastasia Pashneva
 *
 */
public enum Post {
    PILOT,
    NAVIGATING_OFFICER,
    AIR_MECHANIC,
    STEWARDESS;

    /**
     * Method for obtaining object of enum by its ordinal number.
     *
     * @param ordinal ordinal number of object which must be obtained.
     * @return instance of enum with determined ordinal number.
     */
    public static Post getPost(int ordinal) {
        return Post.values()[ordinal - 1];
    }

    /**
     * Method for obtaining ordinal number of the enum object by its name.
     *
     * @param name name of object.
     * @return int value of ordinal number of object.
     */
    public static int getPostOrdinal(String name) {
        Post post = Post.valueOf(name) ;
        return post.ordinal() + 1;
    }

    /**
     * Method for obtaining ordinal number of the enum object by its instance.
     *
     * @param post object of enum which ordinal number must be obtained.
     * @return int value of ordinal number of object.
     */
    public static int getPostOrdinal(Post post) {
        return post.ordinal() + 1;
    }

    /**
     * Method for obtaining name of enum object.
     *
     * @return string value of the enum object name.
     */
    public String getName() {
        return name().toLowerCase();
    }
}
