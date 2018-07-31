package team.service;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static DBResourceManager instance;
    private String location = "db";
    private ResourceBundle bundle = ResourceBundle.getBundle(location);

    public static DBResourceManager getInstance() {
        DBResourceManager localInstance = instance;
        if (localInstance == null) {
            instance = localInstance = new DBResourceManager();
        }
        return localInstance;
    }

    public String getValue(String key) {
        return bundle.getString(key).trim();
    }
}
