package model;

/* The main game mechanism class,
   primarily for setup, updating and checking values
*/

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private int score;
    private boolean spacebarPressed;
    private ArrayList<Bullet> activeBullets;
    private Quote activeQuote;
    private ArrayList<WordBlock> activeWords;
    private Random random;

    // EFFECTS: initializes player, quote, words, and score
    public void setup() {
        this.score = 0;
        this.spacebarPressed = false;
        activeBullets = new ArrayList<>();
        activeQuote = null;
        activeWords = new ArrayList<>();
        random = new Random();
    }

    // MODIFIES: this
    // EFFECTS: updates the game every interval
    public void update(int x, int y) {
        initializeBullet(x, y);
        updateBullet();
        removeOffScreenBullets();
    }

    // MODIFIES: this
    // EFFECTS: if the spacebar was pressed (True), then make a new bullet and set
    // the spacebar to false
    public void initializeBullet(int x, int y) {
        if (spacebarPressed) {
            Bullet b = new Bullet(x, y);
            activeBullets.add(b);
            spacebarPressed = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the bullets up every tick
    public void updateBullet() {
        for (Bullet b : activeBullets) {
            b.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the bullets when they go past the screen boundaries
    public void removeOffScreenBullets() {
        for (int i = 0; i < activeBullets.size(); i++) {
            if (activeBullets.get(i).getY() < 0) {
                activeBullets.remove(activeBullets.get(i));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: turns a quote into a list of word blocks, set the currentlyPlayingQuoteWords to the
    // resulting list
    public void splitQuoteIntoWords(int width, int height) {
        String[] splitQuote = this.activeQuote.getQuoteText().split(" ");

        for (String s : splitQuote) {
            int randomXPos = random.nextInt(width);
            int randomYPos = random.nextInt(height);

            activeWords.add(new WordBlock(s, randomXPos, randomYPos));
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if the player has hit the wrong word; if so end game
//    public void checkGameOver() {
//
//    }

    // MODIFIES: this
    // EFFECTS: increments the score by 1
    public void incScore() {
        score++;
    }

    // getters and setters
    public void setActiveQuote(Quote q) {
        this.activeQuote = q;
    }

    public Quote getActiveQuote() {
        return activeQuote;
    }

    public List<WordBlock> getActiveWords() {
        return activeWords;
    }

    public int getScore() {
        return score;
    }

    public boolean getSpacebarPressed() {
        return spacebarPressed;
    }

    public void setSpacebarPressed() {
        spacebarPressed = true;
    }

    public ArrayList<Bullet> getActiveBullets() {
        return activeBullets;
    }
}
