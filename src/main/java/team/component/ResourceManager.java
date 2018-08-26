package team.component;

import java.util.ResourceBundle;

/**
 * This class provides an access to property-file
 */
public class ResourceManager {
    /**
     * This is the path to property-file
     */
    public static final String RESOURCE_PATH = "resource";

    /**
     * Resource bundles contain locale-specific objects
     */
    private static ResourceBundle resource = ResourceBundle.getBundle(RESOURCE_PATH);

    /**
     * This method return property by key
     *
     * @param key a name of property
     * @return value of property
     */
    public static String getValue(String key) {
        return resource.getString(key);
    }
}
