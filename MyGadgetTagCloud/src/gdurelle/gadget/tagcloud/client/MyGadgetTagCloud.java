package gdurelle.gadget.tagcloud.client;

import gdurelle.gadget.tagcloud.client.tag.Tag;
import gdurelle.gadget.tagcloud.client.tag.TagCloud;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.IntrinsicFeature;
import com.google.gwt.gadgets.client.NeedsIntrinsics;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.Gadget.ModulePrefs;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

@ModulePrefs(title = "Gadget TagCloud", author = "Gregory Durelle", author_email = "gregory.durelle@gmail.com")
public class MyGadgetTagCloud extends Gadget<UserPreferences> implements
		NeedsIntrinsics {

	private IntrinsicFeature intrinsicMethods = null;
	private TagServicePreferences prefs = null;
	private TagServiceAsync service;
	private TagCloud cloud ;

	@Override
	protected void init(UserPreferences preferences) {
		cloud = new TagCloud();
        cloud.setColored(true);
        cloud.setWidth("400px");
        service  = GWT.create(TagService.class);
        
        ServiceDefTarget serviceDef = (ServiceDefTarget) service;
        String rpcUrl = serviceDef.getServiceEntryPoint();
        if (prefs.useCachedXHR().getValue()) {
          rpcUrl = intrinsicMethods.getCachedUrl(rpcUrl);
          serviceDef.setServiceEntryPoint(rpcUrl);
        }
        refresh();
        RootPanel.get().add(cloud);
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

	@Override
	public void initializeFeature(IntrinsicFeature feature) {
		this.intrinsicMethods = feature;
	}

}
