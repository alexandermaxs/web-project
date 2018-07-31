package team.service;

import java.util.ResourceBundle;

public class DBPropertyManager {
	private final static DBPropertyManager instance = new DBPropertyManager();

	private ResourceBundle bundle = ResourceBundle.getBundle(DBLocationConstant.DB_RESOURCE_LOCATION);

	public static DBPropertyManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key).trim();
	}

}