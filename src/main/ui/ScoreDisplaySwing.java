package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScoreDisplaySwing extends JPanel {
    private Game game;
    private JLabel maxScore;
    private JButton quoteButton;

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

    public void updateScore() {
        maxScore.setText("Max Score: " + game.getScore());
        repaint();
    }
}
