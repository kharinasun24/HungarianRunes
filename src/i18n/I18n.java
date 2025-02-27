package i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {
    private static volatile I18n instance;
    private static final Object LOCK = new Object();
    private final Locale locale;
    private ResourceBundle resourceBundle;

    @SuppressWarnings("deprecation")
    private I18n(Locale lcl) {
        locale = (lcl == null) ? new Locale("en", "GB") : lcl;
        resourceBundle = ResourceBundle.getBundle("i18n.messages", locale);
    }

    public static I18n getInstance(Locale locale) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new I18n(locale);
                }
            }
        }
        return instance;
    }

    public String getValue(String key) {
        if (resourceBundle == null) {
            return "";
        }
        return resourceBundle.getString(key);
    }
}
