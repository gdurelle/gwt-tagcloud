package greg.tagcloud.client;

public class Word {
    private String word;
    private String link;
    private int numberOfOccurences;

    public Word(String word) {
        this.word = word;
        numberOfOccurences = 1;
    }

    public Word(String word, String link) {
        this(word);
        this.link = link;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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

    /**
     * Increase the <code>numberOfOccurences</code> variable
     */
    public void increaseNumberOfOccurences() {
        numberOfOccurences++;
    }
}
