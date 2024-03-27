package ui;

import model.Quote;
import model.Game;
import model.QuoteList;

import javax.swing.*;

/*
    Displays a JOptionPane that asks for user input: adding quotes, choosing a quote, or
    viewing the current list.
 */
public class QuoteDisplaySwing extends JOptionPane {
    Quote activeQuote;
    QuoteList ql;
    Game game;

    // EFFECTS: constructs a new QuoteDisplaySwing object that takes a Game parameter
    // sets the game and quote list fields to the game and the game's quote list.
    // also displays the option dialogue
    public QuoteDisplaySwing(Game g) {
        this.game = g;
        this.ql = g.getActiveQuoteList();

        String generalInstructions = "Add quotes, chose a quote, or view the list";
        String[] buttons = {"Add quote", "Choose a quote", "View the list", "Remove a quote"};

        int result = showOptionDialog(null, generalInstructions, "Change the quote", 0, 3, null, buttons, buttons[0]);
        checkResult(result);
    }

    // REQUIRES: result is either 0, 1, or 2
    // EFFECTS: adds a quote, chooses a quote, or views the current list
    // if result is 0, adds a quote and refreshes the words
    // if result is 1, chooses a quote and refreshes the words
    // if result is 2, views the list as a string
    // is result is 3, removes the quote from the quote list
    public void checkResult(int result) {
        if (result == 0) { // add quote
            String quoteInput = showInputDialog("enter a new quote");
            activeQuote = new Quote(quoteInput);
            game.addToActiveQuoteList(activeQuote);
            game.refreshActiveWords(activeQuote);

        } else if (result == 1) { // choose a quote
            String quoteInput = showInputDialog(
                    "the current playable quotes are\n" + displayQuoteListAsString()
                            + "\nenter the number of the quote you want to play (0 based indexing)");
            int quoteInputInteger = Integer.parseInt(quoteInput);
            activeQuote = ql.getQuoteAtIndex(quoteInputInteger);
            game.refreshActiveWords(activeQuote);

        } else if (result == 2) { // view the list
            showMessageDialog(null, "the current playable quotes are\n" + displayQuoteListAsString());
        } else if (result == 3) { // remove a quote
            String quoteInput = showInputDialog(
                    "the current playable quotes are\n" + displayQuoteListAsString()
                            + "\nenter the number of the quote you want to remove (0 based indexing)");
            int quoteInputInteger = Integer.parseInt(quoteInput);
            ql.removeQuote(quoteInputInteger);
            showMessageDialog(null, "quote removed");
        }
    }

    // EFFECTS: concat all quotes into one string with line breaks
    public String displayQuoteListAsString() {
        String quoteListInString = "";

        for (Quote q : ql.getQuoteList()) {
            quoteListInString = quoteListInString + q.getQuoteText() + "\n";
        }

        return quoteListInString;
    }
}
