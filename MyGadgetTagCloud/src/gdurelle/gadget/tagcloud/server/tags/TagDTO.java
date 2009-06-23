package gdurelle.gadget.tagcloud.server.tags;

import gdurelle.gadget.tagcloud.client.tag.Tag;
import gdurelle.gadget.tagcloud.client.tag.WordTag;

public class TagDTO {
    public static Tag convertToTag(TagBean tagbean){
        
        Tag tag = new WordTag(((WordTagBean)tagbean).getWord(), tagbean.getLink());
        tag.setNumberOfOccurences(tagbean.getNumberOfOccurences());
        tag.setId(tagbean.getId());
        
        return tag;
    }
}
