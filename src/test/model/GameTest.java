package model;

import org.junit.jupiter.api.*;

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
        g.setSpacebarPressed();
        g.update(5, 5);

        assertEquals(1, g.getActiveBullets().size());
        assertFalse(g.getSpacebarPressed());

        assertEquals(5, g.getActiveBullets().get(0).getX());
        assertEquals(4, g.getActiveBullets().get(0).getY());
    }

    @Test
    public void initializeBulletTest() {
        g.setSpacebarPressed();
        g.initializeBullet(2, 2);

        assertEquals(1, g.getActiveBullets().size());
        assertFalse(g.getSpacebarPressed());
    }

    @Test
    public void updateBulletTest() {
        g.setSpacebarPressed();
        g.initializeBullet(2, 2);
        g.updateBullet();

        assertEquals(2, g.getActiveBullets().get(0).getX());
        assertEquals(1, g.getActiveBullets().get(0).getY());
    }

    @Test
    public void removeOffScreenTest() {
        g.setSpacebarPressed();
        g.initializeBullet(2, 0);

        assertEquals(1, g.getActiveBullets().size());

        g.updateBullet();
        g.removeOffScreenBullets();

        assertEquals(0, g.getActiveBullets().size());
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
        assertFalse(g.getSpacebarPressed());
        g.setSpacebarPressed();
        assertTrue(g.getSpacebarPressed());
    }

    @Test
    public void getActiveBullets() {
        assertEquals(0, g.getActiveBullets().size());
    }
}
