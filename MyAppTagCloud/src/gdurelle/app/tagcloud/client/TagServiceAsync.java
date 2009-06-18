package gdurelle.app.tagcloud.client;

import gdurelle.app.tagcloud.client.tag.Tag;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TagServiceAsync {
    public void addTag(String tag, AsyncCallback<Void> async);
    public void removeTag(String tag, AsyncCallback<Void> async);
    public void getTags(AsyncCallback<List<Tag>> async);
}
