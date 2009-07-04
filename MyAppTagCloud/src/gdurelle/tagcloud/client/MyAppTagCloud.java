package gdurelle.tagcloud.client;

import gdurelle.tagcloud.client.tags.Tag;
import gdurelle.tagcloud.client.tags.TagCloud;

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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class MyAppTagCloud implements EntryPoint {
	 
    private TagServiceAsync service = GWT.create(TagService.class);
    private TagCloud cloud = new TagCloud();

    public void onModuleLoad() {
        cloud.setColored(true);
        cloud.setWidth("400px");
        
        service.getTags(new AsyncCallback<List<Tag>>(){

            @Override
            public void onFailure(Throwable caught) {
                
            }

            @Override
            public void onSuccess(List<Tag> result) {
                cloud.setTags(result);
                cloud.refresh();
            }
            
        });
        
      //to add tags
        final TextBox tagbox = new TextBox();
        tagbox.setHeight("30px");
        Button tagbtn = new Button("Add");
        HorizontalPanel addPanel = new HorizontalPanel();
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
        
        RootPanel.get("content").add(addPanel);
        RootPanel.get("content").add(cloud);
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
                cloud.refresh();
            }
            
        });
    }
    
//    private void removeTag(String tag){
//        service.removeTag(tag, new AsyncCallback<Void>(){
//            @Override
//            public void onFailure(Throwable caught) {
//                Window.alert("ERROR in tuto.client.MyAppEngine.removeTag(String tag) method");
//            }
//
//            @Override
//            public void onSuccess(Void result) {
//                refresh();
//            }
//        });
//    }
    
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
