package ui;

import model.Bullet;
import model.Game;
import model.WordBlock;

import javax.swing.*;
import java.awt.*;

public class GameDisplaySwing extends JPanel {
    private Game game;

    public GameDisplaySwing(Game game) {
        setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
        setBackground(new Color(40, 44, 84));
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

//        if (game.isOver()) {
//            gameOver(g);
//        }
    }

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


    // EFFECTS: render the bullets on screen at their x y coordinates
    public void drawBullets(Graphics g) {
        for (Bullet b : game.getActiveBullets()) {
            drawBullet(g, b);
        }
    }

    public void drawBullet(Graphics g, Bullet b) {
        Color bulletColor = new Color(255, 72, 144);
        g.setColor(bulletColor);
        g.fillRect(b.getX(), b.getY(), 10, 10);
    }

    // EFFECTS: render the words on screen at their x y coordinates
    public void drawWords(Graphics g) {
        for (WordBlock w : game.getActiveWords()) {
            drawWord(g, w);
        }
    }

    public void drawWord(Graphics g, WordBlock w) {
        Color wordColor = new Color(138, 255, 213);
        g.setColor(wordColor);
        g.fillRect(w.getX(), w.getY(), 15, 15);
        g.drawString(w.getWord(), w.getX() - 15 / 2, w.getY() - 15 / 2);
    }
}
