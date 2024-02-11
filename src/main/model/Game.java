package model;

/* The main game mechanism class,
   primarily for setup, updating and checking values
*/

import java.util.List;

public class Game {
    private List<WordBlock> currentlyPlayingQuote;

    // EFFECTS: constructs an empty game object
    public Game() {

    }

    // EFFECTS: initializes player, quote, and words
    public void setup() {
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

    // EFFECTS: shoots a bullet if the mouse is pressed
    public void checkMousePressed() {

    }

    // EFFECTS: adds aiming dots in response to the mouse's x y position
    public void checkMouseHover() {

    }

    // MODIFIES: this
    // EFFECTS: checks if the player has hit the wrong word; if so end game
    public void checkGameOver() {

    }
}
