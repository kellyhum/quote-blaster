package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;

import model.Bullet;
import model.Game;
import model.WordBlock;
import model.Quote;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/* holds all the console commands that show up on screen,
* also initiates the Lanterna game console */
public class GameDisplay {
    private Screen screen;
    private Game game;
    private int middleOfScreen;
    private int playerYPos;
    private WordBlock testBlock;

    private ArrayList<Bullet> activeBullets;
    private ArrayList<WordBlock> activeWords;

    // EFFECTS: constructs a new GameDisplay object
    public void setupTextConsole() throws IOException, InterruptedException {
        Scanner s = new Scanner(System.in);

        textCommands();
        String userInput = s.nextLine();

        checkUserCommand(userInput);
    }

    // EFFECTS: shows the instruction commands on the console
    public void textCommands() {
        System.out.println("Press Q to view the quote list");
        System.out.println("Press G to start a new game");
    }

    // EFFECTS: creates specific objects based on which key the user pressed
    public void checkUserCommand(String u) throws IOException, InterruptedException {
        if (u.equals("Q")) {
            QuoteDisplay qd = new QuoteDisplay();
            qd.setup();

            // todo: connect quote display and game console functionalities
            game.setActiveQuote(
                    qd.determineCurrentQuote(qd.getQuoteList().getQuoteList())
            );

        } else if (u.equals("G")) {
            setupGameConsole();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the lanterna interface, ticks every 250 milliseconds
    public void setupGameConsole() throws IOException, InterruptedException {
        testBlock = new WordBlock("testBlock", 40, 5);

        game = new Game();
        game.setup();
        game.setActiveQuote(new Quote("test quote", false));
        game.splitQuoteIntoWords(40, 10);

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
        // todo: fix this later
        this.activeWords = (ArrayList<WordBlock>) game.getActiveWords();
        this.activeWords.add(testBlock);
        game.update(middleOfScreen, playerYPos);

        screen.clear();
        keyCommands();
        drawPlayer();
        drawBullets();
        drawWords();
        drawScore();
        screen.refresh();
    }

    // MODIFIES: game
    // EFFECTS: checks for user input. if space was pressed, make a new Bullet object
    public void keyCommands() throws IOException {
        KeyStroke ks = screen.pollInput();

        if (ks == null) {
            return;
        }

        if (ks.getKeyType() == KeyType.Character) {
            if (ks.getCharacter() == ' ') {
                game.setSpacebarPressed();
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
        tg2.putString(8, 1, Integer.toString(game.getScore()));
    }
}
