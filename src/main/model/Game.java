package model;

/* The main game mechanism class,
   primarily for setup, updating and changing values
*/

import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final String JSON_FILE = "./data/game.json";
    private static final String JSON_QUOTE = "./data/quoteList.json";

    private int score;
    private boolean spaceBarPressed;

    private ArrayList<Bullet> activeBullets;
    private Quote activeQuote;
    private ArrayList<WordBlock> activeWords;
    private QuoteList activeQuoteList;

    private Random random;

    private JsonWriter writer;
    private JsonWriter writerQuote;
    private JsonReader reader;

    public Game() {
        writer = new JsonWriter(JSON_FILE);
        writerQuote = new JsonWriter(JSON_QUOTE);
        reader = new JsonReader(JSON_FILE, JSON_QUOTE);
        setup();
    }

    // MODIFIES: this
    // EFFECTS: initializes player, space bar, quote, words, random, and score
    public void setup() {
        this.score = 0;
        this.spaceBarPressed = false;
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
        bulletWordCollision();
        removeOffScreenBullets();
    }

    // MODIFIES: this
    // EFFECTS: if the space bar was pressed, then make a new bullet, add the bullet
    // to the list and set the space bar to false
    public void initializeBullet(int x, int y) {
        if (spaceBarPressed) {
            Bullet b = new Bullet(x, y);
            activeBullets.add(b);
            spaceBarPressed = false;
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
    // EFFECTS: checks if the bullet has collided with the word. if so, increase the score
    // if not, move the bullet
    public void bulletWordCollision() {
        for (Bullet b : activeBullets) {
            for (WordBlock w : activeWords) {
                // is there a way to make the if condition better?
                if (new Rectangle(b.getX(), b.getY(), 10, 10).intersects(new Rectangle(w.getX(), w.getY(), 15, 15))) {
                    if (!w.getHit()) {
                        incScore();
                        w.setHit(true);
                    }
                }
//                if (((b.getY() == w.getY()) && (b.getX() == w.getX())))
            }
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
    // EFFECTS: turns a quote into a list of word blocks with random positions, set the activeWords
    // list to the resulting list
    public void splitQuoteIntoWords(int width, int height) {
        String[] splitQuote = this.activeQuote.getQuoteText().split(" ");

        for (String s : splitQuote) {
            int randomXPos = WIDTH / 2 + 5; // TESTING PURPOSES ONLY
            //int randomXPos = random.nextInt(width);
            int randomYPos = random.nextInt(height);

            activeWords.add(new WordBlock(s, randomXPos, randomYPos));
        }
    }

    public void keyPressed(int keyCode) throws IOException {
        if (keyCode == KeyEvent.VK_SPACE) {
            setSpaceBarPressed();
        } else if (keyCode == KeyEvent.VK_S) {
            writer.open();
            writerQuote.open();
            writer.writeGameStats(this.score, this.activeWords);
            writerQuote.writeQuoteList(activeQuoteList);
            writer.close();
            writerQuote.close();
            System.out.println("saved");
        } else if (keyCode == KeyEvent.VK_L) {
            activeQuoteList = reader.readQuoteList();
            score = reader.readAndParseScore();
            System.out.println("loaded");
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

    // MODIFIES: this
    // EFFECTS: sets the active quote field to the parameter value
    public void setActiveQuote(Quote q) {
        this.activeQuote = q;
    }

    // MODIFIES: this
    // EFFECTS: sets the active words field to the parameter value
    public void setActiveWords(ArrayList<WordBlock> a) {
        activeWords = a;
    }

    // MODIFIES: this
    // EFFECTS: sets the spacebar field to true
    public void setSpaceBarPressed() {
        spaceBarPressed = true;
    }

    public void setActiveQuoteList(QuoteList q) {
        activeQuoteList = q;
    }

    // getters
    public Quote getActiveQuote() {
        return activeQuote;
    }

    public List<WordBlock> getActiveWords() {
        return activeWords;
    }

    public int getScore() {
        return score;
    }

    public boolean getSpaceBarPressed() {
        return spaceBarPressed;
    }

    public ArrayList<Bullet> getActiveBullets() {
        return activeBullets;
    }
}
