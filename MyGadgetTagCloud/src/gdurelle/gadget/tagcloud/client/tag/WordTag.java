package gdurelle.gadget.tagcloud.client.tag;


@SuppressWarnings("serial")
public class WordTag extends Tag{
    private String word;
    
    public WordTag() {
        super();
    }

    public WordTag(String word) {
        this.word = word;
        numberOfOccurences = 1;
    }

    public WordTag(String word, String link) {
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
        if (!( obj instanceof Tag)){
            return false;
        }
        return word.equals(((WordTag)obj).getWord());
    }

}
