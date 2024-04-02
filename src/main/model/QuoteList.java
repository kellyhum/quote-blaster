package model;

import java.util.ArrayList;

/* A list of quotes that the user can play */

public class QuoteList {
    private final ArrayList<Quote> quoteList;

    // EFFECTS: constructs a new QuoteList object with no quotes in the quote list
    public QuoteList() {
        quoteList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a user inputted quote to the end of the quote list
    public void addQuote(Quote q) {
        quoteList.add(q);
    }

    // REQUITES: quoteList.size() >= 1
    // EFFECTS: returns the quote at the specified index
    public Quote getQuoteAtIndex(int index) {
        return quoteList.get(index);
    }

    // REQUITES: quoteList.size() >= 1
    // EFFECTS: removes the quote at the specified index
    public void removeQuote(int index) {
        EventLog.getInstance().logEvent(
                new Event("removed quote '"
                        + quoteList.get(index).getQuoteText() + "' from quote list"));
        quoteList.remove(index);
    }

    // getter
    public ArrayList<Quote> getQuoteList() {
        return quoteList;
    }
}
