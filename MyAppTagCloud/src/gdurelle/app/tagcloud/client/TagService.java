package gdurelle.app.tagcloud.client;

import gdurelle.app.tagcloud.client.tag.Tag;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tags")
public interface TagService extends RemoteService {
    public void addTag(String tag);
    public void removeTag(String tag);
    public List<Tag> getTags();
}
