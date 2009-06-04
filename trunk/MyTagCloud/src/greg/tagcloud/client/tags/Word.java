package greg.tagcloud.client.tags;

public class Word extends Tag{
    private String word;

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

}
