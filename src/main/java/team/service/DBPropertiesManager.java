package team.service;

import java.util.ResourceBundle;

public class DBPropertiesManager {
	private final static DBPropertiesManager instance = new DBPropertiesManager();

	private ResourceBundle bundle = ResourceBundle.getBundle(LocationConstant.DB_RESOURCE_LOCATION);

	public static DBPropertiesManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key).trim();
	}

}