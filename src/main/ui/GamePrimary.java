package ui;

import model.Game;
import model.Quote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GamePrimary extends JFrame {
    private GameDisplaySwing gameDisplay;
    private ScoreDisplaySwing scoreDisplay;
    private Game game;

    public GamePrimary() throws InterruptedException {
        super("Project Starter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = new Game();

        gameDisplay = new GameDisplaySwing(game);
        scoreDisplay = new ScoreDisplaySwing(game);
        add(gameDisplay); // add panel to frame
        add(scoreDisplay, BorderLayout.NORTH); // add panel to top of the frame
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);

        while (true) {
            tick();
            Thread.sleep(75);
        }
    }

    public void tick() {
        game.update(game.WIDTH / 2 + 5, game.HEIGHT - 200); // bullet x y location
        scoreDisplay.updateScore();
        gameDisplay.repaint(); // re-render screen
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            try {
                game.keyPressed(e.getKeyCode());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new GamePrimary();
    }
}
