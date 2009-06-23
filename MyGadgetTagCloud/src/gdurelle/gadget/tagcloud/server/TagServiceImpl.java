package gdurelle.gadget.tagcloud.server;

import gdurelle.gadget.tagcloud.client.TagService;
import gdurelle.gadget.tagcloud.client.tag.Tag;
import gdurelle.gadget.tagcloud.server.tags.TagBean;
import gdurelle.gadget.tagcloud.server.tags.TagDTO;
import gdurelle.gadget.tagcloud.server.tags.WordTagBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gwt.user.server.rpc.RPCServletUtils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TagServiceImpl extends RemoteServiceServlet implements TagService {

    private PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
    PersistenceManager pm = pmf.getPersistenceManager();

    /**
     * Do not validate HTTP headers - iGoogle munges them.
     */
    @Override
    protected String readContent(HttpServletRequest request)
        throws ServletException, IOException {
      return RPCServletUtils.readContentAsUtf8(request, false);
    }
    
    @Override
    public void addTag(String tag) {

        if (pm.isClosed())
            pm = pmf.getPersistenceManager();

        Extent<WordTagBean> extent = pm.getExtent(WordTagBean.class, false);
        try {
            boolean exists = false;
            for (TagBean tb : extent) {
                if (((WordTagBean) tb).getWord().equalsIgnoreCase(tag)) {
                    exists = true;
                    tb.increaseNumberOfOccurences();
                }
            }
            if (!exists) {
                TagBean t = new WordTagBean(tag);
                pm.makePersistent(t);
            }
        } finally {
            extent.closeAll();
            pm.close();
        }
    }

    @Override
    public List<Tag> getTags() {

        if (pm.isClosed())
            pm = pmf.getPersistenceManager();

        Extent<WordTagBean> extent = pm.getExtent(WordTagBean.class, false);
        List<Tag> tags = new ArrayList<Tag>();
        
        try {
            for (TagBean tb : extent) {
                tags.add(TagDTO.convertToTag(tb));
            }
        } finally {
            extent.closeAll();
        }

        return tags;
    }

    @Override
    public void removeTag(String tag) {

//        if (pm.isClosed())
//            pm = pmf.getPersistenceManager();
//
//        String query = "select from " + TagBean.class.getName();
//        List<TagBean> tags = (List<TagBean>) pm.newQuery(query).execute();
//
//        for (TagBean t : tags) {
//            if (((WordTagBean) t).getWord().equals(tag)) {
//                try {
//                    pm.deletePersistent(t);
//                } finally {
//                    pm.close();
//                }
//            }
//        }
    }

}
