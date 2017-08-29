package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Root of all entities which have identifier field.
 *
 * @author Anastasia Pashneva
 *
 */
public class Entity {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
