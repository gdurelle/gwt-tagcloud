package gdurelle.tagcloud.client;

import gdurelle.tagcloud.client.tags.ImageTag;
import gdurelle.tagcloud.client.tags.TagCloud;
import gdurelle.tagcloud.client.tags.WordTag;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyTagCloud implements EntryPoint {

    public void onModuleLoad() {

    	VerticalPanel panel = new VerticalPanel();
        panel.setWidth("200px");
    	
        TagCloud cloud = new TagCloud();
        cloud.setColored(true);
        cloud.setWidth("200px");
        cloud.populate();
        
        TagCloud photoCloud = new TagCloud();
        Image im = new Image("/pdf.PNG");
        photoCloud.addImage(new ImageTag(im));
        photoCloud.addImage(new ImageTag("/odt.PNG"));
        photoCloud.addImage(new ImageTag("http://media.linkedin.com/mpr/mpr/shrink_80_80/p/3/000/01c/01d/2192909.jpg"));
        
        TagCloud mini = new TagCloud();
        mini.addWord(new WordTag("Google", "http://www.google.com"));
        mini.addWord(new WordTag("Google", "http://www.google.com"));
        mini.addWord(new WordTag("Google", "http://www.google.com"));
        mini.addWord(new WordTag("Google", "http://www.google.com"));
        mini.addWord(new WordTag("News", "http://cnn.com"));
        mini.addWord(new WordTag("News", "http://cnn.com"));
        mini.addWord(new WordTag("News", "http://cnn.com"));
        mini.addImage(new ImageTag("http://lh5.ggpht.com/s/v/51.28/img/logo/pwa-fr.gif"));
        mini.addImage(new ImageTag("http://lh5.ggpht.com/s/v/51.28/img/logo/pwa-fr.gif"));
        mini.addImage(new ImageTag("http://lh5.ggpht.com/s/v/51.28/img/logo/pwa-fr.gif"));   
        mini.addImage(new ImageTag("http://static.linkedin.com/img/pic/pic_logo_119x32.gif", "http://linkedin.com"));
        
        
        panel.add(cloud);
        panel.add(photoCloud);
        panel.add(mini);
        
        RootPanel.get("content").add(panel);
    }
}
