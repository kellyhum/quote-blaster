package ui;

import model.Game;
import model.Quote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreDisplaySwing extends JPanel {
    private Game game;
    private JLabel maxScore;
    private JButton quoteButton;

    public ScoreDisplaySwing(Game game) {
        setBackground(new Color(180, 180, 180));

        this.game = game;
        quoteButton = new JButton("change quotes");
        quoteButton.setFocusable(false);
        quoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("button clicked");
                QuoteDisplaySwing quoteDisplay = new QuoteDisplaySwing();
                game.setActiveQuoteList(quoteDisplay.getActiveQuoteList());
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
