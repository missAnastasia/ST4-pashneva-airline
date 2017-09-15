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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language)) return false;

        Language language = (Language) o;

        if (getId() != language.getId()) return false;
        if (!getName().equals(language.getName())) return false;
        return getPrefix().equals(language.getPrefix());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getPrefix().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}
