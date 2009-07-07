package gdurelle.tagcloud.client;

import gdurelle.tagcloud.client.tags.Tag;
import gdurelle.tagcloud.client.tags.TagCloud;
import gdurelle.tagcloud.client.tags.WordTag;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class MyAppTagCloud implements EntryPoint {
	 
    private TagServiceAsync service = GWT.create(TagService.class);
    private TagCloud cloud = new TagCloud();
    private HorizontalPanel delPanel;

    public void onModuleLoad() {
        cloud.setColored(true);
        cloud.setWidth("400px");
        
        refresh();
        
        /*
         * To add tags
         */
        final TextBox tagbox = new TextBox();
        tagbox.setHeight("20px");
        Button tagbtn = new Button("Add");
        HorizontalPanel addPanel = new HorizontalPanel();
        addPanel.add(new HTML("Add a tag :&nbsp;"));
        addPanel.add(tagbox);
        addPanel.add(tagbtn);
        
        //handlers for the button and its textbox
        ClickHandler click = new ClickHandler(){
          @Override
          public void onClick(ClickEvent event) {
              addTag(tagbox.getText());
              tagbox.setText("");
          }
        };
        
        KeyPressHandler press = new KeyPressHandler(){
          @Override
          public void onKeyPress(KeyPressEvent event) {
              if(event.getCharCode()== KeyCodes.KEY_ENTER){
                  addTag(tagbox.getText());
                  tagbox.setText("");
              }
          }  
        };
        
        tagbox.addKeyPressHandler(press);
        tagbtn.addClickHandler(click);
        
        /*
         * To remove tags
         */
        delPanel = new HorizontalPanel();
        delPanel.add(new HTML("Delete tag :&nbsp;"));
        
        RootPanel.get("content").add(addPanel);
        RootPanel.get("content").add(delPanel);
        RootPanel.get("content").add(cloud);
    }
    
    private void setWordColor(String word, String color){
    	List<Tag> tags = cloud.getTags();
    	if(tags!=null){
    		for(Tag t : tags){
    			if(((WordTag)t).getWord().equalsIgnoreCase(word))
    				((WordTag)t).setColor(color);
    		}
    	}
    }
    
    private void setWordOrientation (String word, int orientation){
        List<Tag> tags = cloud.getTags();
        if(tags!=null){
            for(Tag t : tags){
                if(((WordTag)t).getWord().equalsIgnoreCase(word))
                    ((WordTag)t).setOrientation(orientation);
            }
        }
    }
    
    private void refresh(){

        service.getTags(new AsyncCallback<List<Tag>>(){

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("ERROR in tuto.client.MyAppEngine.refresh() method");
            }

            @Override
            public void onSuccess(List<Tag> result) {
                cloud.setTags(result);
                
                delPanel.clear();
                delPanel.add(new HTML("Delete tags : "));
                for(final Tag t : cloud.getTags()){
                	Button b = new Button(((WordTag)t).getWord());
                	b.addClickHandler(new ClickHandler(){
        				@Override
        				public void onClick(ClickEvent event) {
        					removeTag(((WordTag)t).getWord());
        				}
                	});
                	delPanel.add(b);
                }
                
                setWordColor("gwt", "red");
                setWordColor("android", "green");
                setWordColor("appengine", "blue");
                setWordColor("eclipse", "purple");
                setWordColor("rails", "red");
                setWordColor("ruby", "red");
                setWordColor("apple", "lightgrey");
                
                setWordOrientation("gwt", Tag.VERTICAL_LEFT);
                setWordOrientation("rails", Tag.VERTICAL_RIGHT);
                setWordOrientation("apple", Tag.VERTICAL_LEFT);
                setWordOrientation("ruby", Tag.VERTICAL_LEFT);
                setWordOrientation("Eclipse", Tag.VERTICAL_LEFT);
                setWordOrientation("IT", Tag.VERTICAL_RIGHT);
                
                
                cloud.refresh();
            }
            
        });
    }
    
    private void removeTag(String tag){
        service.removeTag(tag, new AsyncCallback<Void>(){
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("ERROR in tuto.client.MyAppEngine.removeTag(String tag) method");
            }

            @Override
            public void onSuccess(Void result) {
                refresh();
            }
        });
    }
    
    private void addTag(String tag){
        service.addTag(tag, new AsyncCallback<Void>(){

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("ERROR in tuto.client.MyAppEngine.addTag(String tag) method");
            }

            @Override
            public void onSuccess(Void result) {
                refresh();
            }
        });
    }
}
