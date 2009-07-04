package gdurelle.tagcloud.client;

import gdurelle.tagcloud.client.tags.Tag;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tagService")
public interface TagService extends RemoteService {
    public void addTag(String tag);
    public void removeTag(String tag);
    public List<Tag> getTags();
}
