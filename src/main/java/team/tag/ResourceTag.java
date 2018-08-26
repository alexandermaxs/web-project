package team.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This tag prints a property from resource file by key
 * This tag chooses language on attribute <code>language</code> keeping in sessions
 */
public class ResourceTag extends TagSupport {
    private static final String RESOURCE_PATH = "resource";
    private static final String PARAM_LANGUAGE = "language";
    private static final String NO_RESOURCE = "error.no.resource";
    /**
     * This is name of property in resource file
     */
    private String key;
    /**
     * This is logger which print some messages to log file
     */
    private static final Logger LOGGER = LogManager.getLogger(ResourceTag.class);

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * This method prints a property from resource file by key
     * This method chooses language on attribute <code>language</code> keeping in sessions
     * If attribute <code>language</code> not found it uses a default language
     *
     * @return SKIP_BODY
     */
    @Override
    public int doStartTag() {
        String language = (String) pageContext.getSession().getAttribute(PARAM_LANGUAGE);
        Locale locale;
        if (language != null) {
            locale = new Locale(language);
        } else {
            locale = pageContext.getRequest().getLocale();
        }
        ResourceBundle resource = ResourceBundle.getBundle(RESOURCE_PATH, locale);
        JspWriter writer = pageContext.getOut();
        try {
            try {
                writer.print(resource.getString(key));
            } catch (MissingResourceException ex) {
                LOGGER.error(ex);
                writer.print(resource.getString(NO_RESOURCE));
            }
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        return SKIP_BODY;
    }
}
