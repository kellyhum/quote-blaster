package ui;

import model.Quote;
import model.QuoteList;
import java.util.Scanner;
import java.util.ArrayList;

/* displays the quote list and quotes */

public class QuoteDisplay {
    private final QuoteList quoteList;
    private final Scanner scan;

    public QuoteDisplay() {
        quoteList = new QuoteList();
        scan = new Scanner(System.in);
    }

    public void setup() {
        printQuotes();
        quoteCommands();
    }

    public void printQuotes() {
        System.out.println("\nCurrent quote list:");

        for (Quote q : quoteList.getQuoteList()) {
            System.out.print(q.getQuoteText());
            System.out.print(", ");
            System.out.print(q.getTheCurrentLevel());
            System.out.println("");
        }
    }

    public void quoteCommands() {
        System.out.println("\nPress A to add a new quote");
        System.out.println("Press B to go back to the main menu");

        String userInput = scan.nextLine();
        checkUserCommand(userInput);
    }

    public Quote determineCurrentQuote(ArrayList<Quote> ql) {
        for (Quote q : ql) {
            if (q.getTheCurrentLevel()) {
                return q;
            }
        }

        return null;
    }

    public void checkUserCommand(String u) {
        if (u.equals("A")) {
            System.out.println("Enter your new quote here:");
            String userQuote = scan.nextLine();

            System.out.println("Do you want to play this quote? (true or false)");
            boolean userPlay = scan.nextBoolean();

            scan.nextLine();

            Quote q = new Quote(userQuote, userPlay);

            quoteList.addQuote(q);

            printQuotes();
            quoteCommands();
        } else if (u.equals("B")) {
            // todo!!!
            return;
        }
    }

    // getter
    public QuoteList getQuoteList() {
        return quoteList;
    }
}
