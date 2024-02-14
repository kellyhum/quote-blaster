package model;

/* The main game mechanism class,
   primarily for setup, updating and checking values
*/

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Quote currentlyPlayingQuote;
    private List<WordBlock> currentlyPlayingQuoteWords;
    private int score;

    // EFFECTS: initializes player, quote, words, and score
    public void setup() {
        currentlyPlayingQuoteWords = new ArrayList<>();
        currentlyPlayingQuoteWords.add(new WordBlock("test", 40, 5));

        this.score = 0;
    }

    // MODIFIES: this
    // EFFECTS: updates the game every interval
    public void update() {
    }

    // MODIFIES: this
    // EFFECTS: checks if the bullet has hit the wordblock, removes
    // both bullet and wordblock
    public void checkBulletWordBlockCollision() {

    }

    // MODIFIES: this
    // EFFECTS: moves the bullets up the screen every update
    public void moveBullets() {

    }

    // MODIFIES: this
    // EFFECTS: removes the bullets when they go past the screen boundaries
    public void removeOffScreenBullets() {

    }

    // MODIFIES: this
    // EFFECTS: turns a quote into a list of wordblocks
    public List<WordBlock> splitQuoteIntoWords() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: checks if the player has hit the wrong word; if so end game
    public void checkGameOver() {

    }

    public void incScore() {
        score++;
    }

    // getters and setters
    public void setCurrentlyPlayingQuote(Quote q) {
        this.currentlyPlayingQuote = q;
    }

    public Quote getCurrentlyPlayingQuote() {
        return currentlyPlayingQuote;
    }

    public List<WordBlock> getCurrentlyPlayingQuoteWords() {
        return currentlyPlayingQuoteWords;
    }

    public int getScore() {
        return score;
    }
}
