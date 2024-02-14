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

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

/* holds all the console commands that show up on screen,
* also initiates the Lanterna game console */
public class GameDisplay {
    private Screen screen;
    private Game game;
    private Bullet bullet;
    private WordBlock word;
    private Random random;
    private int middleOfScreen;
    private int playerYPos;


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
            game.setCurrentlyPlayingQuote(
                    qd.determineCurrentQuote(qd.getQuoteList().getQuoteList())
            );
            System.out.println("game curr quote:" + game.getCurrentlyPlayingQuote());

        } else if (u.equals("G")) {
            setupGameConsole();
        }
    }

    public void setupGameConsole() throws IOException, InterruptedException {
        random = new Random();

        game = new Game();
        game.setup();

        screen = new DefaultTerminalFactory().createScreen();
        middleOfScreen = screen.getTerminalSize().getColumns() / 2;
        playerYPos = screen.getTerminalSize().getRows() - 8;
        screen.startScreen();

        //b = new Bullet(middleOfScreen, playerYPos);
        word = game.getCurrentlyPlayingQuoteWords().get(0);

        // refresh page every 250 milliseconds
        while (true) {
            tick();
            Thread.sleep(250);
        }
    }

    public void tick() throws IOException {
        screen.clear();
        keyCommands();
        drawPlayer();
        bulletWordCollision();
        drawScore();
        screen.refresh();
    }

    public void keyCommands() throws IOException {
        KeyStroke ks = screen.pollInput();

        if (ks == null) {
            return;
        }

        KeyType keyInput = ks.getKeyType();

        if (keyInput == KeyType.Character) {
            if (ks.getCharacter() == ' ') {
                TextGraphics tg = screen.newTextGraphics();
                tg.putString(10, 10, "a was pressed");
                bullet = new Bullet(middleOfScreen, playerYPos);
            }
        }
    }

    public void bulletWordCollision() {
        if (((bullet.getY() == word.getY()) && (bullet.getX() == word.getX()))) {
            game.incScore();
            // reset so that this true block doesn't loop
            word.setY(random.nextInt(screen.getTerminalSize().getRows()));
            word.setX(random.nextInt(screen.getTerminalSize().getColumns()));
            bullet.setY(25);
            bullet.setX(22);

        } else {
            bullet.move();
            drawBullet(bullet.getX(), bullet.getY());
            drawWord(word.getX(), word.getY());
        }
    }

    public void drawPlayer() {
        TerminalPosition t = new TerminalPosition(middleOfScreen - 2, playerYPos);
        TerminalSize s = new TerminalSize(3, 1);
        TextGraphics player = screen.newTextGraphics();
        player.setBackgroundColor(TextColor.ANSI.CYAN);
        player.drawRectangle(t, s, ' ');
    }

    public void drawBullet(int x, int y) {
        TerminalPosition t = new TerminalPosition(x, y);
        TerminalSize s = new TerminalSize(1, 1);
        TextGraphics bullet = screen.newTextGraphics();
        bullet.setBackgroundColor(TextColor.ANSI.RED);
        bullet.drawRectangle(t, s, ' ');
    }

    public void drawWord(int x, int y) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.ANSI.GREEN);
        tg.putString(x, y, word.getWord());
    }

    public void drawScore() {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.ANSI.YELLOW);
        tg.putString(1, 1, "Score: ");

        TextGraphics tg2 = screen.newTextGraphics();
        tg2.setBackgroundColor(TextColor.ANSI.YELLOW);
        tg2.putString(8, 1, Integer.toString(game.getScore()));
    }
}
