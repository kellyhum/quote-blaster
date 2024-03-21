package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;

import model.*;

import persistence.JsonWriter;
import persistence.JsonReader;

import java.util.ArrayList;
import java.io.IOException;

/* holds all the console commands that show up on screen,
* also initiates the Lanterna game console */
public class GameDisplay {
    private static final String JSON_FILE = "./data/game.json";
    private static final String JSON_QUOTE = "./data/quoteList.json";

    private Screen screen;
    private Game game;
    private int middleOfScreen;
    private int playerYPos;

    private ArrayList<Bullet> activeBullets;
    private ArrayList<WordBlock> activeWords;
    private int score;
    private QuoteList ql;

    private JsonWriter writer;
    private JsonWriter writerQuote;
    private JsonReader reader;

    // EFFECTS: constructs a new GameDisplay object
    public void setupTextConsole() throws IOException, InterruptedException {
        writer = new JsonWriter(JSON_FILE);
        writerQuote = new JsonWriter(JSON_QUOTE);
        reader = new JsonReader(JSON_FILE, JSON_QUOTE);

        checkUserCommand();
    }

    // EFFECTS: creates specific objects based on which key the user pressed
    public void checkUserCommand() throws IOException, InterruptedException {
        QuoteDisplay qd = new QuoteDisplay();
        if (qd.setup()) {
            setupGameConsole(qd.getQuoteList());
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the lanterna interface, ticks every 250 milliseconds
    public void setupGameConsole(QuoteList ql) throws IOException, InterruptedException {
        System.out.println("Press s to save the current state of the game");
        System.out.println("Press l to load the last state of the game");

        game = new Game();
        game.setup();
        game.setActiveQuote(new Quote("test quote"));
        game.splitQuoteIntoWords(40, 10);

        this.ql = ql;

        screen = new DefaultTerminalFactory().createScreen();
        middleOfScreen = screen.getTerminalSize().getColumns() / 2;
        playerYPos = screen.getTerminalSize().getRows() - 8;
        screen.startScreen();

        while (true) {
            tick();
            Thread.sleep(75);
        }
    }

    // MODIFIES: this
    // EFFECTS: checks key commands, renders, and checks collisions
    public void tick() throws IOException {
        this.activeBullets = game.getActiveBullets();
        this.activeWords = (ArrayList<WordBlock>) game.getActiveWords();

        game.update(middleOfScreen, playerYPos);
        this.score = game.getScore();

        screen.clear();
        keyCommands();
        drawPlayer();
        drawBullets();
        drawWords();
        drawScore();
        screen.refresh();
    }

    // MODIFIES: this, game
    // EFFECTS: checks for user input. if space was pressed, make a new Bullet object; s to save; l to load;
    // v to print the quote list
    public void keyCommands() throws IOException {
        KeyStroke ks = screen.pollInput();

        if (ks == null) {
            return;
        }

        if (ks.getKeyType() == KeyType.Character) {
            if (ks.getCharacter() == ' ') {
                game.setSpaceBarPressed();
            } else if (ks.getCharacter() == 's') {
                writer.open();
                writerQuote.open();
                writer.writeGameStats(this.score, this.activeWords);
                writerQuote.writeQuoteList(this.ql);
                writer.close();
                writerQuote.close();
                System.out.println("saved");
            } else if (ks.getCharacter() == 'l') {
                this.ql = reader.readQuoteList();
                this.score = reader.readAndParseScore();
                game.setActiveWords(reader.readWordList());
            } else if (ks.getCharacter() == 'v') {
                System.out.println(this.ql.getQuoteList().toString());
            }
        }
    }

    // EFFECTS: render the player on screen
    public void drawPlayer() {
        TerminalPosition t = new TerminalPosition(middleOfScreen - 2, playerYPos);
        TerminalSize s = new TerminalSize(3, 1);
        TextGraphics player = screen.newTextGraphics();
        player.setBackgroundColor(TextColor.ANSI.CYAN);
        player.drawRectangle(t, s, ' ');
    }

    // EFFECTS: render the bullets on screen at their x y coordinates
    public void drawBullets() {
        for (Bullet b : activeBullets) {
            TerminalPosition t = new TerminalPosition(b.getX(), b.getY());
            TerminalSize s = new TerminalSize(1, 1);
            TextGraphics bullet = screen.newTextGraphics();
            bullet.setBackgroundColor(TextColor.ANSI.RED);
            bullet.drawRectangle(t, s, ' ');
        }
    }

    // EFFECTS: render the words on screen at their x y coordinates
    public void drawWords() {
        for (WordBlock w : activeWords) {
            TextGraphics tg = screen.newTextGraphics();
            tg.setBackgroundColor(TextColor.ANSI.GREEN);
            tg.putString(w.getX(), w.getY(), w.getWord());
        }
    }

    // EFFECTS: render the score on screen
    public void drawScore() {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.ANSI.YELLOW);
        tg.putString(1, 1, "Score: ");

        TextGraphics tg2 = screen.newTextGraphics();
        tg2.setBackgroundColor(TextColor.ANSI.YELLOW);
        tg2.putString(8, 1, Integer.toString(this.score));
    }
}
