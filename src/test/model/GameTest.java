package model;

import org.junit.jupiter.api.*;

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
        g.setSpaceBarPressed();
        g.update(5, 5);

        assertEquals(1, g.getActiveBullets().size());
        assertFalse(g.getSpaceBarPressed());

        assertEquals(5, g.getActiveBullets().get(0).getX());
        assertEquals(4, g.getActiveBullets().get(0).getY());
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
        g.updateBullet();

        assertEquals(2, g.getActiveBullets().get(0).getX());
        assertEquals(1, g.getActiveBullets().get(0).getY());
    }

    @Test
    public void removeOffScreenTest() {
        g.setSpaceBarPressed();
        g.initializeBullet(2, 0);

        assertEquals(1, g.getActiveBullets().size());

        g.updateBullet();
        g.removeOffScreenBullets();

        assertEquals(0, g.getActiveBullets().size());
    }

    @Test
    public void bulletWordCollisionPassesTest() {
        WordBlock testWord = new WordBlock("test", 2, 1);
        g.getActiveWords().add(testWord);
        g.setSpaceBarPressed();
        g.initializeBullet(2, 1);

        assertEquals(0, g.getScore());
        assertFalse(g.getActiveWords().get(0).getHit());

        g.bulletWordCollision();

        assertEquals(1, g.getScore());
        assertTrue(g.getActiveWords().get(0).getHit());
    }

    @Test
    public void bulletWordCollisionDiffXTest() {
        WordBlock testWord = new WordBlock("test", 2, 1);
        g.getActiveWords().add(testWord);
        g.setSpaceBarPressed();
        g.initializeBullet(1, 1);

        assertEquals(0, g.getScore());
        assertFalse(g.getActiveWords().get(0).getHit());

        g.bulletWordCollision();

        assertEquals(0, g.getScore());
        assertFalse(g.getActiveWords().get(0).getHit());
    }

    @Test
    public void bulletWordCollisionDiffYTest() {
        WordBlock testWord = new WordBlock("test", 2, 1);
        g.getActiveWords().add(testWord);
        g.setSpaceBarPressed();
        g.initializeBullet(2, 6);

        assertEquals(0, g.getScore());
        assertFalse(g.getActiveWords().get(0).getHit());

        g.bulletWordCollision();

        assertEquals(0, g.getScore());
        assertFalse(g.getActiveWords().get(0).getHit());
    }

    @Test
    public void bulletWordCollisionHitTrueTest() {
        WordBlock testWord = new WordBlock("test", 2, 1);
        testWord.setHit(true);
        g.getActiveWords().add(testWord);
        g.setSpaceBarPressed();
        g.initializeBullet(2, 1);

        assertEquals(0, g.getScore());
        assertTrue(g.getActiveWords().get(0).getHit());

        g.bulletWordCollision();

        assertEquals(0, g.getScore());
        assertTrue(g.getActiveWords().get(0).getHit());
    }

    @Test
    public void splitQuoteTest() {
        g.setActiveQuote(new Quote("test quote", false));
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
        Quote k = new Quote("another test", false);
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
}
