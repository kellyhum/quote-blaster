package ui;

import model.Quote;
import model.QuoteList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/* displays the quote list and quotes */

public class QuoteDisplay {
    private static final String JSON_FILE = "./data/test.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private QuoteList quoteList;
    private final Scanner scan;

    // EFFECTS: intiialize Scanner, empty QuoteList, and both JsonWriter and JsonReader
    public QuoteDisplay() {
        quoteList = new QuoteList();
        scan = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
    }

    // EFFECTS: runs quote displaying and commands functions
    public void setup() {
        printQuotes();
        quoteCommands();
    }

    // EFFECTS: prints current quotelist
    public void printQuotes() {
        System.out.println("\nCurrent quote list:");

        for (Quote q : quoteList.getQuoteList()) {
            System.out.print(q.getQuoteText());
            System.out.print(", ");
            System.out.print(q.getTheCurrentLevel());
            System.out.println("");
        }
    }

    // EFFECTS: asks for user input
    public void quoteCommands() {
        System.out.println("\nPress A to add a new quote");
        System.out.println("Press S to save the current quotelist");
        System.out.println("Press L to load the previous quotelist");
        System.out.println("Press V to view the quotelist");
        System.out.println("Press B to go back to the main menu");

        String userInput = scan.nextLine();
        checkUserCommand(userInput);
    }

    // EFFECTS: returns the quote that has currentLevel = true
    public Quote determineCurrentQuote(ArrayList<Quote> ql) {
        for (Quote q : ql) {
            if (q.getTheCurrentLevel()) {
                return q;
            }
        }

        return null;
    }

    // EFFECTS: if user clicks a, let them add a new quote
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
        } else if (u.equals("S")) {
            saveQuoteList();
            quoteCommands();
        } else if (u.equals("L")) {
            loadQuoteList();
            quoteCommands();
        } else if (u.equals("V")) {
            printQuotes();
            quoteCommands();
        } else if (u.equals("B")) {
            // todo!!!
            return;
        }
    }

    public void saveQuoteList() {
        try {
            jsonWriter.open();
            jsonWriter.writeQuoteList(quoteList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILE);
        }
    }

    public void loadQuoteList() {
        try {
            this.quoteList = jsonReader.readQuoteList();
            System.out.println("Loaded from " + JSON_FILE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FILE);
        }
    }

    // getter
    public QuoteList getQuoteList() {
        return quoteList;
    }
}
