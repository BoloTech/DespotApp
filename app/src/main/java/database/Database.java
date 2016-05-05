package database;

/**
 * Created by Bojan on 5/2/2016.
 */
public enum Database {
    DATABASE_NAME("Wash.db"),
    TABLE_NAME("NewWash"),
    // names of columns
    REG_NUMBER("RegNumber"),
    PRICE("Price"),
    DATE("Date");


    public String getName() {
        return name;
    }

    private String name;

    Database(String name) {
        this.name = name;
    }
}
