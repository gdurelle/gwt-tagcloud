package gdurelle.app.tagcloud.client.tag;

import java.io.Serializable;

public abstract class Tag implements Serializable{

    private static final long serialVersionUID = 2153391657389702774L;
    private String id;
    protected String link;
    protected int numberOfOccurences;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getNumberOfOccurences() {
        return numberOfOccurences;
    }
    
    public void setNumberOfOccurences(int occurency){
        numberOfOccurences = occurency;
    }

    /**
     * Increase the <code>numberOfOccurences</code> variable
     */
    public void increaseNumberOfOccurences() {
        numberOfOccurences++;
    }
    
    @Override
    public abstract int hashCode();
    
    @Override
    public abstract boolean equals(Object obj);

}
