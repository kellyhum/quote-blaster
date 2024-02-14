package model;

/* A single word in a quote */

public class WordBlock {
    private final String word;
    private int x1;
    private int y1;

    // EFFECTS: constructs a new WordBlock object with a word value of the
    // inputted word
    public WordBlock(String word, int x, int y) {
        this.word = word;
        this.x1 = x;
        this.y1 = y;
    }

    // getters
    public String getWord() {
        return this.word;
    }

    public int getX() {
        return this.x1;
    }

    public int getY() {
        return this.y1;
    }

    public void setX(int x1) {
        this.x1 = x1;
    }

    public void setY(int y) {
        this.y1 = y;
    }
}
