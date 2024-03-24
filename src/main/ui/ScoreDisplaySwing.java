package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    displays the score and button to change quotes
 */

public class ScoreDisplaySwing extends JPanel {
    private Game game;
    private JLabel maxScore;
    private JButton quoteButton;

    // EFFECTS: constructs a new ScoreDisplaySwing object
    // sets up the background and score;
    // displays a JButton for changing quotes, adds an event listener on it
    public ScoreDisplaySwing(Game g) {
        setBackground(new Color(89, 141, 133));
        this.game = g;
        quoteButton = new JButton("change quotes");
        quoteButton.setFocusable(false);
        quoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QuoteDisplaySwing(game);
            }
        });

        maxScore = new JLabel("Max Score: " + game.getScore());
        add(maxScore);
        add(quoteButton);
    }

    // MODIFIES: this
    // EFFECTS: reset and redraw the score
    public void updateScore() {
        maxScore.setText("Max Score: " + game.getScore());
        repaint();
    }
}
