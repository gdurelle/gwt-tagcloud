package greg.tagcloud.client.tags;


import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;

public class TagCloud extends Composite {

    private FlowPanel cloud;
    private ArrayList<Tag> tags;
    private int maxNumberOfTags;// the number of tags shown in the cloud.
    private int minOccurences, maxOccurences, step;
    private boolean isColored;

    private static final int STEP_NUMBER = 10;

    public TagCloud() {
        cloud = new FlowPanel();
        tags = new ArrayList<Tag>();
        maxNumberOfTags = 20;
        minOccurences = 1;
        maxOccurences = 1;
        step = 1;// 'average' difference between each occurence
        DecoratorPanel dec = new DecoratorPanel();
        dec.setWidget(cloud);
        initWidget(dec);
    }

    /*
     * Dev method TO BE DELETED
     */
    public void populate() {

        for (int i = 0; i < 50; i++) {
            addWord(new Word("abc" + i, "link" + i));
        }

        for (int i = 0; i < 500; i++) {
            double r = Math.random() * 50;
            int seed = (int) Math.floor(r) + 1;
            for (int j = 0; j < 50; j++) {
                    if (j == seed) {
                        tags.get(j).increaseNumberOfOccurences();
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
        for (Tag w : tags) {
            if (w instanceof Word) {
                if (((Word) w).getWord().equalsIgnoreCase(word.getWord())) {
                    w.increaseNumberOfOccurences();
                    exist = true;
                }
            }
        }
        if (!exist)
            tags.add(word);
        refresh();
    }

    /**
     * Add an image to the tagcloud list
     * @param image
     */
    public void addImage(ImageTag image) {
        boolean exist = false;
        for (Tag w : tags) {
            if (w instanceof ImageTag) {
                if (((ImageTag) w).getUrl().equalsIgnoreCase(image.getUrl())) {
                    w.increaseNumberOfOccurences();
                    exist = true;
                }
            }
        }
        if (!exist)
            tags.add(image);
        refresh();
    }

    /**
     * Refresh the display of the tagcloud. Usually used after an adding of
     * word.
     */
    public void refresh() {
        cloud.clear();
        if (tags != null && !tags.isEmpty()) {
            // recalculate max and min of all occurences
            for (Tag w : tags) {
                if (w.getNumberOfOccurences() > maxOccurences)
                    maxOccurences = w.getNumberOfOccurences();
                if (w.getNumberOfOccurences() < minOccurences)
                    minOccurences = w.getNumberOfOccurences();
            }

            // a step correspond to a css style.
            step = (maxOccurences - minOccurences) / STEP_NUMBER;

            for (Tag w : tags) {
                InlineHTML inline = null;
                if (w instanceof Word)
                    inline = setInlineHTML((Word) w);
                else {
                    Image ima = ((ImageTag) w).getImage();
                    inline = new InlineHTML(" <a href='" + w.getLink() + "'><img src='" + ima.getUrl() + "'</a>");
                    inline.addStyleName("tag");
                }
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
        return maxNumberOfTags;
    }

    public void setMaxNumberOfWords(int numberOfWords) {
        this.maxNumberOfTags = numberOfWords;
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
        if (this.isColored)
            refresh();
    }
}
