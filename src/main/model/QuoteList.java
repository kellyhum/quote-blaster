package model;

import java.util.ArrayList;

/* A list of quotes that the user can play */

public class QuoteList {
    private final ArrayList<Quote> quoteList;

    // EFFECTS: constructs a new QuoteList object with two Quotes in the quoteList
    public QuoteList() {
        quoteList = new ArrayList<>();
        quoteList.add(new Quote("All's fair in love and war", false));
        quoteList.add(new Quote("Curiosity killed the cat", false));
    }

    // MODIFIES: this
    // EFFECTS: adds a user inputted quote to the end of the quote list
    public void addQuote(Quote q) {
        quoteList.add(q);
    }

    // getter
    public ArrayList<Quote> getQuoteList() {
        return quoteList;
    }
}
