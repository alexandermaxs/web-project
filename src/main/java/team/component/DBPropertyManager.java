package team.component;

import java.util.ResourceBundle;

public class DBPropertyManager {
    private static final String DB_RESOURCE_LOCATION = "db";
    private final static DBPropertyManager instance = new DBPropertyManager();

    private ResourceBundle bundle = ResourceBundle.getBundle(DB_RESOURCE_LOCATION);

    public static DBPropertyManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key).trim();
    }

}