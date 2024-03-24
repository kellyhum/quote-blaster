package ui;

import model.Bullet;
import model.Game;
import model.WordBlock;

import javax.swing.*;
import java.awt.*;

/*
     Code referenced from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase

     Displays the game using the Java Swing library, contains methods for drawing objects
 */

public class GameDisplaySwing extends JPanel {
    private Game game;

    // EFFECTS: initializes a new GameDisplaySwing object that takes in a parameter of Game
    // sets the screen size and background, also sets the game field to the parameter
    public GameDisplaySwing(Game game) {
        setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
        setBackground(new Color(40, 44, 84));
        this.game = game;
    }

    // EFFECTS: override the JComponent's implementation of paintComponent
    // calls the drawGame method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);
        // add if game is over condition and rendering
    }

    // MODIFIES: this
    // EFFECTS: draws the player, bullets, and words on screen
    public void drawGame(Graphics g) {
        drawPlayer(g);
        drawBullets(g);
        drawWords(g);
    }

    // EFFECTS: render the player on screen
    public void drawPlayer(Graphics g) {
        Color playerColor = new Color(250, 128, 20);
        g.setColor(playerColor);
        g.fillRect(game.WIDTH / 2, game.HEIGHT - 200, 20, 20);
    }

    // EFFECTS: loops through the list and calls drawBullet on each
    public void drawBullets(Graphics g) {
        for (Bullet b : game.getActiveBullets()) {
            drawBullet(g, b);
        }
    }

    // EFFECTS: draws the bullet at the specified x y position
    public void drawBullet(Graphics g, Bullet b) {
        Color bulletColor = new Color(255, 72, 144);
        g.setColor(bulletColor);
        g.fillRect(b.getX(), b.getY(), 10, 10);
    }

    // EFFECTS: loops through each word and calls drawWord on each
    public void drawWords(Graphics g) {
        for (WordBlock w : game.getActiveWords()) {
            drawWord(g, w);
        }
    }

    // EFFECTS: render the word on screen at their x y coordinates
    public void drawWord(Graphics g, WordBlock w) {
        Color wordColor = new Color(138, 255, 213);
        g.setColor(wordColor);
        g.fillRect(w.getX(), w.getY(), 15, 15);
        g.drawString(w.getWord(), w.getX() - 15 / 2, w.getY() - 15 / 2);
    }
}
