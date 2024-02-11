package model;

import java.util.ArrayList;
import java.util.Scanner;

/* A list of quotes that the user can play */

public class QuoteList {
    private ArrayList<String> quoteList;
    private Scanner scan;

    // EFFECTS: constructs a new QuoteList object with two
    // quotes in the quoteList and initializes a scanner object for getting user input
    public QuoteList() {
    }

    // MODIFIES: this
    // EFFECTS: adds a user inputted quote to the end of the quote list
    public void addQuote(Quote q) {
    }

    // getter
    public ArrayList<String> getQuoteList() {
        return quoteList;
    }
}
