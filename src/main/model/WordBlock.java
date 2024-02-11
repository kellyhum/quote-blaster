package model;

/* A single word in a quote */

public class WordBlock {
    private String word;

    // EFFECTS: constructs a new WordBlock object with a word value of the
    // inputted word
    public WordBlock(String word) {
        this.word = word;
    }

    // getter
    public String getWord() {
        return this.word;
    }
}
