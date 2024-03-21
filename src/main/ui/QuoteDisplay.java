package ui;

import model.Quote;
import model.QuoteList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/* displays the quote list and quotes */
public class QuoteDisplay {
    private static final String JSON_QUOTE = "./data/quoteList.json";
    private JsonReader jsonReader;
    private QuoteList quoteList;
    private final Scanner scan;
    private boolean startGame;

    // EFFECTS: initialize Scanner, empty QuoteList, and both JsonWriter and JsonReader
    public QuoteDisplay() {
        quoteList = new QuoteList();
        scan = new Scanner(System.in);
        jsonReader = new JsonReader("", JSON_QUOTE);
        startGame = false;
    }

    // EFFECTS: runs quote displaying and commands functions
    public boolean setup() {
        printQuotes();
        return quoteCommands();
    }

    // EFFECTS: prints current quotelist
    public void printQuotes() {
        System.out.println("\nCurrent quote list:");

        for (Quote q : quoteList.getQuoteList()) {
            System.out.print(q.getQuoteText());
            System.out.print(", ");
            //System.out.print(q.getTheCurrentLevel());
            System.out.println();
        }
    }

    // EFFECTS: asks for user input
    public boolean quoteCommands() {
        System.out.println("\nPress A to add a new quote");
        System.out.println("Press L to load the previous quotelist");
        System.out.println("Press V to view the quotelist");
        System.out.println("Press B to run the game");

        String userInput = scan.nextLine();
        checkUserCommand(userInput);

        if (startGame) {
            return true;
        }
        return false;
    }

    // EFFECTS: returns the quote that has currentLevel = true
//    public Quote determineCurrentQuote(ArrayList<Quote> ql) {
//        for (Quote q : ql) {
//            if (q.getTheCurrentLevel()) {
//                return q;
//            }
//        }
//
//        return null;
//    }

    // EFFECTS: if user clicks a, add a new quote; l, load prior version; v, print quote list; b, start the game
    public void checkUserCommand(String u) {
        if (u.equals("A")) {
            System.out.println("Enter your new quote here:");
            String userQuote = scan.nextLine();

            System.out.println("Do you want to play this quote? (true or false)");
            boolean userPlay = scan.nextBoolean();

            scan.nextLine();

            Quote q = new Quote(userQuote);

            quoteList.addQuote(q);

            printQuotes();
            quoteCommands();
        } else if (u.equals("L")) {
            loadQuoteList();
            quoteCommands();
        } else if (u.equals("V")) {
            printQuotes();
            quoteCommands();
        } else if (u.equals("B")) {
            startGame = true;
        }
    }

    // EFFECTS: read the quoteList from the json file and print "loaded from [file]", catch IOexception
    public void loadQuoteList() {
        try {
            this.quoteList = jsonReader.readQuoteList();
            System.out.println("Loaded from " + JSON_QUOTE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_QUOTE);
        }
    }

    // getter
    public QuoteList getQuoteList() {
        return quoteList;
    }
}
