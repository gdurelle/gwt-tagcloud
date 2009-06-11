package greg.tagcloud.client;

import greg.tagcloud.client.tags.TagCloud;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyTagCloud implements EntryPoint {

    public void onModuleLoad() {
        TagCloud cloud = new TagCloud();
        cloud.setColored(true);
        RootPanel.get("content").add(cloud);
    }
}
