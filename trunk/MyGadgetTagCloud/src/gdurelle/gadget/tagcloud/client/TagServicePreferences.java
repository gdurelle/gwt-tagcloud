package gdurelle.gadget.tagcloud.client;

import com.google.gwt.gadgets.client.BooleanPreference;
import com.google.gwt.gadgets.client.UserPreferences;

public interface TagServicePreferences extends UserPreferences {
    @PreferenceAttributes(display_name = "Used Cached XHR methods", default_value = "true")
    BooleanPreference useCachedXHR();
}
