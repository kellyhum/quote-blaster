package model;

import org.junit.jupiter.api.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game g;

    @BeforeEach
    public void setup() {
        g = new Game();
        g.setup();
    }

    @Test
    public void setupTest() {
        assertEquals(0, g.getActiveWords().size());
        assertEquals(0, g.getActiveBullets().size());
        assertNull(g.getActiveQuote());
        assertEquals(0, g.getActiveWords().size());
        assertEquals(0, g.getScore());
    }

    @Test
    public void updateTest() {
        WordBlock testWord = new WordBlock("test", 2, 1);
        ArrayList<WordBlock> testWords = new ArrayList<>();
        testWords.add(testWord);

        g.setActiveWords(testWords);
        g.setSpaceBarPressed();
        g.update(35, 35);

        assertEquals(1, g.getActiveWords().size());
        assertEquals(1, g.getActiveBullets().size());
        assertFalse(g.getSpaceBarPressed());

        assertEquals(35, g.getActiveBullets().get(0).getX());
        assertEquals(27, g.getActiveBullets().get(0).getY());
        assertEquals(2, g.getActiveWords().get(0).getX());
        assertEquals(6, g.getActiveWords().get(0).getY());
    }

    @Test
    public void initializeBulletSpacePressedTest() {
        g.setSpaceBarPressed();
        g.initializeBullet(2, 2);

        assertEquals(1, g.getActiveBullets().size());
        assertFalse(g.getSpaceBarPressed());
    }

    @Test
    public void initializeBulletSpaceNotPressedTest() {
        g.initializeBullet(2, 2);

        assertEquals(0, g.getActiveBullets().size());
        assertFalse(g.getSpaceBarPressed());
    }

    @Test
    public void updateBulletTest() {
        g.setSpaceBarPressed();
        g.initializeBullet(2, 2);
        g.updateBulletAndWords();

        assertEquals(2, g.getActiveBullets().get(0).getX());
        assertEquals(-5, g.getActiveBullets().get(0).getY());
    }

    @Test
    public void removeOffScreenTest() {
        ArrayList<WordBlock> testWords = new ArrayList<>();
        testWords.add(new WordBlock("word", 0, 601));

        g.setSpaceBarPressed();
        g.initializeBullet(2, 0);
        g.setActiveWords(testWords);

        assertEquals(1, g.getActiveBullets().size());
        assertEquals(1, g.getActiveWords().size());

        g.updateBulletAndWords();
        g.removeOffScreenBulletsAndWords();

        assertEquals(0, g.getActiveBullets().size());
        assertEquals(0, g.getActiveWords().size());
    }

    @Test
    public void bulletWordCollisionPassesTest() {
        Rectangle testBullet = new Rectangle(2, 1, 10, 10);
        Rectangle testWord = new Rectangle(2, 1, 15, 15);
        WordBlock testWordBlock = new WordBlock("test", 2, 1);
        g.getActiveWords().add(testWordBlock);
        g.setSpaceBarPressed();
        g.initializeBullet(2, 1);

        assertEquals(0, g.getScore());
        assertFalse(g.getActiveWords().get(0).getHit());

        g.bulletWordCollision();

        assertTrue(testBullet.intersects(testWord));
        assertEquals(1, g.getScore());
        assertTrue(g.getActiveWords().get(0).getHit());
    }

    @Test
    public void bulletWordCollisionDiffXTest() {
        Rectangle testBullet = new Rectangle(1, 1, 10, 10);
        Rectangle testWord = new Rectangle(2, 1, 15, 15);
        WordBlock testWordBlock = new WordBlock("test", 2, 1);

        g.getActiveWords().add(testWordBlock);
        g.setSpaceBarPressed();
        g.initializeBullet(1, 1);

        assertEquals(0, g.getScore());
        assertFalse(g.getActiveWords().get(0).getHit());

        g.bulletWordCollision();

        assertTrue(testBullet.intersects(testWord));
        assertEquals(1, g.getScore());
        assertTrue(g.getActiveWords().get(0).getHit());
    }

    @Test
    public void bulletWordCollisionDiffYTest() {
        Rectangle testBullet = new Rectangle(1, 1, 10, 10);
        Rectangle testWord = new Rectangle(2, 1, 15, 15);
        WordBlock testWordBlock = new WordBlock("test", 2, 1);
        g.getActiveWords().add(testWordBlock);
        g.setSpaceBarPressed();
        g.initializeBullet(2, 6);

        assertEquals(0, g.getScore());
        assertFalse(g.getActiveWords().get(0).getHit());

        g.bulletWordCollision();

        assertTrue(testBullet.intersects(testWord));
        assertEquals(1, g.getScore());
        assertTrue(g.getActiveWords().get(0).getHit());
    }

    @Test
    public void bulletWordCollisionHitTrueTest() {
        Rectangle testBullet = new Rectangle(2, 1, 10, 10);
        Rectangle testWord = new Rectangle(2, 1, 15, 15);
        WordBlock testWordBlock = new WordBlock("test", 2, 1);
        testWordBlock.setHit(true);
        g.getActiveWords().add(testWordBlock);
        g.setSpaceBarPressed();
        g.initializeBullet(2, 1);

        assertEquals(0, g.getScore());
        assertTrue(g.getActiveWords().get(0).getHit());

        g.bulletWordCollision();

        assertTrue(testBullet.intersects(testWord));
        assertEquals(0, g.getScore());
        assertTrue(g.getActiveWords().get(0).getHit());
    }

    @Test
    public void bulletWordTouchingNotIntersecting() {
        Rectangle testBullet = new Rectangle(2, 1, 10, 10);
        Rectangle testWord = new Rectangle(2, 12, 15, 15);

        g.bulletWordCollision();

        assertFalse(testBullet.intersects(testWord));
    }

    @Test
    public void bulletWordCompletelyNotTouching() {
        Rectangle testBullet = new Rectangle(200, 100, 10, 10);
        Rectangle testWord = new Rectangle(2, 12, 15, 15);

        g.bulletWordCollision();

        assertFalse(testBullet.intersects(testWord));
    }

    @Test
    public void bulletWordDiffYStillIntersecting() {
        Rectangle testBullet = new Rectangle(1, 5, 10, 10);
        Rectangle testWord = new Rectangle(1, 12, 15, 15);

        g.bulletWordCollision();

        assertTrue(testBullet.intersects(testWord));
    }

    @Test
    public void bulletWordDiffXStillIntersecting() {
        Rectangle testBullet = new Rectangle(4, 12, 10, 10);
        Rectangle testWord = new Rectangle(2, 12, 15, 15);

        g.bulletWordCollision();

        assertTrue(testBullet.intersects(testWord));
    }

    @Test
    public void splitQuoteTest() {
        g.setActiveQuote(new Quote("test quote"));
        g.splitQuoteIntoWords(4, 3);

        assertEquals(2, g.getActiveWords().size());
        assertEquals("test", g.getActiveWords().get(0).getWord());
        assertEquals("quote", g.getActiveWords().get(1).getWord());
    }

    @Test
    public void incScoreTest() {
        g.incScore();
        assertEquals(1, g.getScore());
    }

    @Test
    public void getCurrentQuoteTest() {
        assertNull(g.getActiveQuote());
    }

    @Test
    public void setCurrentQuoteTest() {
        Quote k = new Quote("another test");
        g.setActiveQuote(k);

        assertEquals(k, g.getActiveQuote());
    }

    @Test
    public void getActiveWordsTest() {
        assertEquals(0, g.getActiveWords().size());
    }

    @Test
    public void getScoreTest() {
        assertEquals(0, g.getScore());
    }

    @Test
    public void setSpacebarTest() {
        assertFalse(g.getSpaceBarPressed());
        g.setSpaceBarPressed();
        assertTrue(g.getSpaceBarPressed());
    }

    @Test
    public void getActiveBullets() {
        assertEquals(0, g.getActiveBullets().size());
    }

    @Test
    public void setWordsTest() {
        WordBlock b2 = new WordBlock("word2", 5, 10);
        ArrayList<WordBlock> j = new ArrayList<>();
        j.add(b2);

        g.setActiveWords(j);
        assertEquals(j, g.getActiveWords());
    }

    @Test
    public void getActiveQuoteListTest() {
        Quote temp = new Quote("test");
        ArrayList<Quote> testQuote = new ArrayList<>();
        testQuote.add(temp);

        assertEquals(0, g.getActiveQuoteList().getQuoteList().size());

        g.addToActiveQuoteList(temp);

        assertEquals(testQuote, g.getActiveQuoteList().getQuoteList());
    }

    @Test
    public void refreshWordsTest() {
        Quote q = new Quote("test quote");
        ArrayList<WordBlock> testWords = new ArrayList<>();
        WordBlock w = new WordBlock("ta", 5, 5);
        testWords.add(w);

        g.setActiveWords(testWords);

        assertEquals(1, g.getActiveWords().size());

        g.refreshActiveWords(q);
        assertEquals(q, g.getActiveQuote());
        assertEquals(2, g.getActiveWords().size());
        assertEquals("test", g.getActiveWords().get(0).getWord());
        assertEquals("quote", g.getActiveWords().get(1).getWord());

    }

    @Test
    public void testSKeyEvent() throws IOException {
        JsonReader readerGame = new JsonReader("./data/keySaveLoadTest.json", "");

        g.keyPressed(KeyEvent.VK_S);

        ArrayList<WordBlock> ql2 = readerGame.readWordList();
        assertFalse(ql2.isEmpty());
        assertEquals(2, ql2.size());

        int score2 = readerGame.readAndParseScore();
        assertEquals(2, score2);
    }

    @Test
    public void testNotValidKeyEvent() throws IOException {
        assertEquals(0, g.getScore());
        assertEquals(0, g.getActiveWords().size());
        assertEquals(0, g.getActiveQuoteList().getQuoteList().size());
        g.keyPressed(KeyEvent.VK_J);
        assertEquals(0, g.getScore());
        assertEquals(0, g.getActiveWords().size());
        assertEquals(0, g.getActiveQuoteList().getQuoteList().size());
    }

    @Test
    public void testLKeyEvent() throws IOException {
        g.keyPressed(KeyEvent.VK_L);
        assertEquals(0, g.getScore());
        assertEquals(0, g.getActiveWords().size());
        assertEquals(0, g.getActiveQuoteList().getQuoteList().size());
    }

    @Test
    public void testRightArrowKeyEvent() throws IOException {
        g.keyPressed(KeyEvent.VK_RIGHT);
        assertEquals(-1.3, g.getRotationAngle());
    }

    @Test
    public void testLeftArrowKeyEvent() throws IOException {
        g.keyPressed(KeyEvent.VK_LEFT);
        assertEquals(-1.7, g.getRotationAngle());
    }

    @Test
    public void testSpaceKeyEvent() throws IOException {
        g.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(g.getSpaceBarPressed());
        g.initializeBullet(5, 5);
        assertEquals(1, g.getActiveBullets().size());
        g.keyPressed(KeyEvent.VK_SPACE);
        g.initializeBullet(6, 6);
        g.keyPressed(KeyEvent.VK_SPACE);
        g.initializeBullet(7, 7);
        assertEquals(3, g.getActiveBullets().size());
    }
}
