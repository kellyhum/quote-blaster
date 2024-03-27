package model;

/*
    Code referenced from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase

    The main game mechanism class,
    primarily for setup, updating and changing values
*/

import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.KeyEvent;
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
    private double rotationAngle = -1.5;

    private ArrayList<Bullet> activeBullets;
    private Quote activeQuote;
    private ArrayList<WordBlock> activeWords;
    private QuoteList activeQuoteList;

    private Random random;

    private JsonWriter writer;
    private JsonWriter writerQuote;
    private JsonReader reader;

    // EFFECTS: initializes a new Game object with JsonWriter and JsonReader objects. also
    // calls the setup function
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
        activeQuoteList = new QuoteList();
        random = new Random();
    }

    // MODIFIES: this
    // EFFECTS: updates the game every interval
    public void update(int x, int y) {
        initializeBullet(x, y);
        updateBulletAndWords();
        bulletWordCollision();
        removeOffScreenBulletsAndWords();
    }

    // MODIFIES: this
    // EFFECTS: if the space bar was pressed, then make a new bullet, add the bullet
    // to the list and set the space bar to false
    public void initializeBullet(int x, int y) {
        if (spaceBarPressed) {
            Bullet b = new Bullet(x, y, rotationAngle);
            activeBullets.add(b);
            spaceBarPressed = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: moves the bullets up every tick
    public void updateBulletAndWords() {
        for (Bullet b : activeBullets) {
            b.move();
        }

        for (WordBlock w : activeWords) {
            w.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if the bullet has collided with the word. if so, increase the score
    // if not, move the bullet
    public void bulletWordCollision() {
        for (int i = 0; i < activeBullets.size(); i++) {
            for (WordBlock w : activeWords) {
                // is there a way to make the if condition better?
                if (new Rectangle(activeBullets.get(i).getX(), activeBullets.get(i).getY(), 10, 10).intersects(
                        new Rectangle(w.getX(), w.getY(), 15, 15))) {
                    if (!w.getHit()) {
                        incScore();
                        activeBullets.remove(activeBullets.get(i));
                        w.setHit(true);
                    }
                }
//                if (((b.getY() == w.getY()) && (b.getX() == w.getX())))
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the bullets when they go past the screen boundaries
    public void removeOffScreenBulletsAndWords() {
        for (int i = 0; i < activeBullets.size(); i++) {
            if (activeBullets.get(i).getY() < 0) {
                activeBullets.remove(activeBullets.get(i));
            }
        }

        for (int i = 0; i < activeWords.size(); i++) {
            if (activeWords.get(i).getY() > HEIGHT) {
                activeWords.remove(activeWords.get(i));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: turns a quote into a list of word blocks with random positions, set the activeWords
    // list to the resulting list
    public void splitQuoteIntoWords(int width, int height) {
        String[] splitQuote = this.activeQuote.getQuoteText().split(" ");

        for (String s : splitQuote) {
            //int randomXPos = WIDTH / 2 + 5; // TESTING PURPOSES ONLY
            int randomXPos = random.nextInt(width);
            int randomYPos = random.nextInt(100);

            activeWords.add(new WordBlock(s, randomXPos, randomYPos));
        }
    }

    // MODIFIES: this
    // EFFECTS: checks whether the key was press, and executes commands based on the specific value
    // if space was pressed, call the setSpaceBarPressed() method
    // if S was pressed, save the score, words, and quote list
    // if L was pressed, load the previous quote list, wordlist, and score
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
        } else if (keyCode == KeyEvent.VK_L) {
            activeQuoteList = reader.readQuoteList();
            activeWords = reader.readWordList();
            score = reader.readAndParseScore();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            rotationAngle -= 0.2;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rotationAngle += 0.2;
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
    // EFFECTS: resets active words to empty, sets the active quote, and
    // calls the splitQuoteIntoWords function
    public void refreshActiveWords(Quote q) {
        setActiveWords(new ArrayList<>()); // reset active words to empty
        setActiveQuote(q);
        splitQuoteIntoWords(WIDTH, HEIGHT - 200);
    }

    // MODIFIES: this
    // EFFECTS: sets the active quote field to the parameter value and
    // calls the splitQuoteIntoWords function
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

    // MODIFIES: this
    // EFFECTS: sets the quote list to the parameter value
    public void addToActiveQuoteList(Quote q) {
        activeQuoteList.addQuote(q);
    }

    // getters
    public QuoteList getActiveQuoteList() {
        return activeQuoteList;
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

    public boolean getSpaceBarPressed() {
        return spaceBarPressed;
    }

    public ArrayList<Bullet> getActiveBullets() {
        return activeBullets;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }
}
