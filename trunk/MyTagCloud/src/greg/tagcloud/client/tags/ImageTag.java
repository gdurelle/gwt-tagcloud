package greg.tagcloud.client.tags;

import com.google.gwt.user.client.ui.Image;

public class ImageTag extends Tag {
    
    private Image image;

    public ImageTag(Image image) {
        this.image = image;
    }
    
    public ImageTag(Image image, String link) {
        this(image);
        this.link = link;
    }
    
    public ImageTag(String url, String link){
        this(new Image(url), link);
    }
    
    public ImageTag(String url){
        this(new Image(url));
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public void setUrl(String url){
        image.setUrl(url);
    }
    
    public String getUrl(){
        return image.getUrl();
    }
    
}
