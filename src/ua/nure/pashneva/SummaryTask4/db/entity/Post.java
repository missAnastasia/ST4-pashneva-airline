package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this class are strings from table posts.
 *
 * @author Anastasia Pashneva
 */
public class Post extends Entity {

    private String name;

    public Post() {
    }

    public Post(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Post{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
