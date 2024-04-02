package ui;

import model.EventLog;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

/*
    Code referenced from https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
    Window shutdown code reference from https://edstem.org/us/courses/50358/discussion/4674357 (#1485)

    Main entry into the program, ticks and initializes Game, GameDisplay, and ScoreDisplay Swing objects
 */

public class GamePrimary extends JFrame {
    private GameDisplaySwing gameDisplay;
    private ScoreDisplaySwing scoreDisplay;
    private Game game;

    // EFFECTS: constructs a new GamePrimary object, sets up fields with Game, GameDisplaySwing,
    // and ScoreDisplaySwing objects. initializes key listeners, centres window, begins ticking
    public GamePrimary() throws InterruptedException {
        super("Project Starter");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stops running on close
        game = new Game();
        gameDisplay = new GameDisplaySwing(game);
        scoreDisplay = new ScoreDisplaySwing(game);
        add(gameDisplay);
        add(scoreDisplay, BorderLayout.NORTH); // add panel to top of the frame
        addKeyListener(new KeyHandler());
        pack();
        setLocationRelativeTo(null); // centres on screen
        setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println(EventLog.getInstance().getEvents());
        }));

        while (true) {
            tick();
            Thread.sleep(75);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the game and scoreDisplay objects, also repaints the gameDisplay object
    public void tick() {
        game.update(game.WIDTH / 2 + 5, game.HEIGHT - 200); // bullet x y location
        scoreDisplay.updateScore();
        gameDisplay.repaint(); // re-render screen
    }

    // EFFECTS: a key handler to respond to key events. tries the game's implementation;
    // if an IOException is thrown, throw a new RuntimeException
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
