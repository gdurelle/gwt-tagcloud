package gdurelle.gadget.tagcloud.server.tags;


import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class WordTagBean extends TagBean{

    private static final long serialVersionUID = -723618637756771994L;
    @Persistent
    private String word;
    
    public WordTagBean() {
        super();
    }

    public WordTagBean(String word) {
        this.word = word;
        numberOfOccurences = 1;
    }

    public WordTagBean(String word, String link) {
        this(word);
        this.link = link;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    
    @Override
    public int hashCode() {
        return word.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!( obj instanceof TagBean)){
            return false;
        }
        return word.equals(((WordTagBean)obj).getWord());
    }

}
