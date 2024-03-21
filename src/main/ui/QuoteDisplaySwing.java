package ui;

import model.Quote;
import model.Game;
import model.QuoteList;

import javax.swing.*;
import java.util.ArrayList;

public class QuoteDisplaySwing extends JOptionPane {
    Quote activeQuote;
    QuoteList ql;
    Game game;

    public QuoteDisplaySwing(Game g) {
        this.game = g;
        this.ql = g.getActiveQuoteList();

        String generalInstructions = "Add quotes, chose a quote, or view the list";
        String[] buttons = {"Add quote", "Choose a quote", "View the list"};

        int result = showOptionDialog(null, generalInstructions, "Change the quote", 0, 3, null,
                buttons,
                buttons[0]
        );

        if (result == 0) { // add quote
            String quoteInput = showInputDialog("enter a new quote");
            activeQuote = new Quote(quoteInput);
            game.addToActiveQuoteList(activeQuote);
            game.refreshActiveWords(activeQuote);

        } else if (result == 1) { // choose a quote
            String quoteInput = showInputDialog(
                    "the current playable quotes are\n"
                            + displayQuoteListAsString()
                            + "\nenter the number of the quote you want to play");
            int quoteInputInteger = Integer.parseInt(quoteInput);
            activeQuote = ql.getQuoteAtIndex(quoteInputInteger);
            game.refreshActiveWords(activeQuote);

        } else if (result == 2) { // view the list
            showMessageDialog(null, "the current playable quotes are\n" + displayQuoteListAsString());
        } else {
            showMessageDialog(null, "you chose 3");
        }
    }

    public String displayQuoteListAsString() {
        String quoteListInString = "";

        for (Quote q : ql.getQuoteList()) {
            quoteListInString = quoteListInString + q.getQuoteText() + "\n";
        }

        return quoteListInString;
    }
}
