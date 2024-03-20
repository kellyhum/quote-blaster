package ui;

import model.QuoteList;

import javax.swing.*;

public class QuoteDisplaySwing extends JDialog {
    QuoteList activeQuoteList;
    JLabel generalInstructions;

    public QuoteDisplaySwing() {
        generalInstructions = new JLabel("Add quotes, chose a quote, view the list, or load a previous list");
        add(generalInstructions);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public QuoteList getActiveQuoteList() {
        return activeQuoteList;
    }
}
