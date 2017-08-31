package ua.nure.pashneva.SummaryTask4.db.entity;

public class Aircraft extends Entity {
    private String typeName;

    public Aircraft() {
    }

    public Aircraft(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "typeName='" + typeName + '\'' +
                ", id=" + id +
                '}';
    }
}
