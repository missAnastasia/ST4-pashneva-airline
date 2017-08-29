package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Objects of this class are strings from the table languages.
 *
 * @author Anastasia Pashneva
 *
 */
public class Language {

    private int id;
    private String name;
    private String prefix;

    public Language() {
    }

    /**
     * Constructor of class for creating object without without an identifier.
     *
     * @param id
     * @param name
     * @param prefix
     */
    public Language(int id, String name, String prefix) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
