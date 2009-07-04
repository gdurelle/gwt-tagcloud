package gdurelle.tagcloud.server.tags;

import gdurelle.tagcloud.client.tags.Tag;
import gdurelle.tagcloud.client.tags.WordTag;

public class TagDTO {
    public static Tag convertToTag(TagBean tagbean){
        
        Tag tag = new WordTag(((WordTagBean)tagbean).getWord(), tagbean.getLink());
        tag.setNumberOfOccurences(tagbean.getNumberOfOccurences());
        tag.setId(tagbean.getId());
        
        return tag;
    }
}
