package model;

import java.util.ArrayList;
import java.util.Scanner;

public class QuoteList {
    private ArrayList<String> quoteList;
    private Scanner scan;

    // EFFECTS: constructs a new QuoteList object with an empty
    // quote list and scanner object for getting user input
    public QuoteList() {
        // setup
        quoteList = new ArrayList<String>();
        scan = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: adds a user inputted quote to the end of the quote list
    public void addQuote(Quote q) {
        System.out.println("Type the quote that you want to add:");
        String inputQuote = scan.nextLine();

        quoteList.add(inputQuote);
    }

    public ArrayList<String> getQuoteList() {
        return quoteList;
    }
}
