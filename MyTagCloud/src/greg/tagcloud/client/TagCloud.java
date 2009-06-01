package greg.tagcloud.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineHTML;

public class TagCloud extends Composite {

    private FlowPanel cloud;
    private ArrayList<Word> words;
    private int maxNumberOfWords;// the number of words shown in the cloud.
    private int minOccurences, maxOccurences, step;
    private boolean isColored;

    private static final int STEP_NUMBER = 10;

    public TagCloud() {
        cloud = new FlowPanel();
        words = new ArrayList<Word>();
        maxNumberOfWords = 20;
        minOccurences = 1;
        maxOccurences = 1;
        step = 1;// 'average' difference between each occurence
        DecoratorPanel dec = new DecoratorPanel();
        populate();
        dec.setWidget(cloud);
        dec.setWidth("400px");
        initWidget(dec);
    }

    /*
     * Dev method TO BE DELETED
     */
    private void populate() {

        for (int i = 0; i < 50; i++) {
            // words.add(new Word("abc" + i, "link" + i));
            addWord(new Word("abc" + i, "link" + i));
        }

        for (int i = 0; i < 100; i++) {
            double r = Math.random() * 10;
            int seed = (int) Math.floor(r) + 1;
            for (int j = 0; j < 50; j++) {
                if (j == seed) {
                    words.get(j).increaseNumberOfOccurences();
                }
            }
        }
        
        refresh();
    }

    /**
     * Add a word to the tagcloud list.
     * 
     * @param word
     */
    public void addWord(Word word) {
        boolean exist = false;
        for (Word w : words) {
            if (w.getWord().equalsIgnoreCase(word.getWord())) {
                w.increaseNumberOfOccurences();
                exist = true;
            }
        }
        if (!exist)
            words.add(word);
        refresh();
    }

    /**
     * Refresh the display of the tagcloud. Usually used after an adding of
     * word.
     */
    public void refresh() {
        cloud.clear();
        if (words != null && !words.isEmpty()) {
            // recalculate max and min of all occurences
            for (Word w : words) {
                if (w.getNumberOfOccurences() > maxOccurences)
                    maxOccurences = w.getNumberOfOccurences();
                if (w.getNumberOfOccurences() < minOccurences)
                    minOccurences = w.getNumberOfOccurences();
            }

            // a step correspond to a css style.
            step = (maxOccurences - minOccurences) / STEP_NUMBER;

            for (Word w : words) {
                InlineHTML inline = setInlineHTML(w);
                cloud.add(inline);
            }
        }
    }

    /**
     * Create the 'CSS' aspect of the given word thanks the whole minimum,
     * maximum, and average number of occurences of all words. It create a link
     * in a span with the appropriate font style/size
     * 
     * @param w
     *            The Word object to display
     * @return The InlinHTML object that fits in the cloud
     */
    private InlineHTML setInlineHTML(Word w) {
        int nboc = w.getNumberOfOccurences();

        InlineHTML inline = new InlineHTML(" <a href='" + w.getLink() + "'>" + w.getWord() + "</a>");
        inline.addStyleName("tag");

        // Apply the good style corersponding to the number of occurences
        if (nboc >= (maxOccurences - step)) {
            inline.addStyleName("tag10");
        } else if (nboc >= (maxOccurences - (step * 2))) {
            inline.addStyleName("tag9");
        } else if (nboc >= (maxOccurences - (step * 3))) {
            inline.addStyleName("tag8");
        } else if (nboc >= (maxOccurences - (step * 4))) {
            inline.addStyleName("tag7");
        } else if (nboc >= (maxOccurences - (step * 5))) {
            inline.addStyleName("tag6");
        } else if (nboc >= (maxOccurences - (step * 6))) {
            inline.addStyleName("tag5");
        } else if (nboc >= (maxOccurences - (step * 7))) {
            inline.addStyleName("tag4");
        } else if (nboc >= (maxOccurences - (step * 8))) {
            inline.addStyleName("tag3");
        } else if (nboc >= (maxOccurences - (step * 9))) {
            inline.addStyleName("tag2");
        } else if (nboc >= (maxOccurences - (step * 10))) {
            inline.addStyleName("tag1");
        }

        // applying color if needed
        if (isColored) {
            double r = Math.random() * 10;
            int seed = (int) Math.floor(r) + 1;
            switch (seed) {
            case 1:
                inline.addStyleName("red");
                break;
            case 2:
                inline.addStyleName("orange");
                break;
            case 3:
                inline.addStyleName("green");
                break;
            case 4:
                inline.addStyleName("lightblue");
                break;
            case 5:
                inline.addStyleName("purple");
                break;
            case 6:
                inline.addStyleName("blue");
                break;
            case 7:
                inline.addStyleName("pink");
                break;
            case 8:
                inline.addStyleName("brown");
                break;
            case 9:
                inline.addStyleName("blue");
                break;
            case 10:
                inline.addStyleName("grey");
                break;
            default:
                inline.addStyleName("black");
                break;
            }
        }

        return inline;
    }

    public int getMaxNumberOfWords() {
        return maxNumberOfWords;
    }

    public void setMaxNumberOfWords(int numberOfWords) {
        this.maxNumberOfWords = numberOfWords;
    }

    public boolean isColored() {
        return isColored;
    }

    /**
     * Set whither you wan the tags to be colored randomly.
     * 
     * @param isColored
     */
    public void setColored(boolean isColored) {
        this.isColored = isColored;
        if(this.isColored)
            refresh();
    }
}
