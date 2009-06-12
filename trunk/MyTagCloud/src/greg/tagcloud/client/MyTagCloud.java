package greg.tagcloud.client;

import greg.tagcloud.client.tags.ImageTag;
import greg.tagcloud.client.tags.TagCloud;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyTagCloud implements EntryPoint {

    public void onModuleLoad() {
        TagCloud cloud = new TagCloud();
        cloud.setColored(true);
        cloud.populate();
        cloud.shuffle();
        cloud.setWidth("400px");
        RootPanel.get("content").add(cloud);

        TagCloud photoCloud = new TagCloud();
        Image im = new Image("/pdf.PNG");
        photoCloud.addImage(new ImageTag(im));
        photoCloud.addImage(new ImageTag("/odt.PNG"));
        photoCloud.addImage(new ImageTag("http://media.linkedin.com/mpr/mpr/shrink_80_80/p/3/000/01c/01d/2192909.jpg"));
        photoCloud.setWidth("20px");
        RootPanel.get("content").add(photoCloud);
    }
}
