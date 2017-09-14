package ua.nure.pashneva.SummaryTask4.db.entity;

/**
 * Root of all entities which have identifier field.
 *
 * @author Anastasia Pashneva
 *
 */
public class Entity {
    protected int id;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;

        return getId() == entity.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
