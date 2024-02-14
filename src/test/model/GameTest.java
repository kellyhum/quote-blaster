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
        assertNull(g.getCurrentlyPlayingQuote());
        assertEquals(0, g.getCurrentlyPlayingQuoteWords().size());
        assertEquals(0, g.getScore());
    }

    @Test
    public void incScoreTest() {
        g.incScore();
        assertEquals(1, g.getScore());
    }

    @Test
    public void getCurrentQuoteTest() {
        assertNull(g.getCurrentlyPlayingQuote());
    }

    @Test
    public void setCurrentQuoteTest() {
        Quote k = new Quote("another test", false);
        g.setCurrentlyPlayingQuote(k);

        assertEquals(k, g.getCurrentlyPlayingQuote());
    }

    @Test
    public void getCurrentQuoteWordsTest() {
        assertEquals(0, g.getCurrentlyPlayingQuoteWords().size());
    }

    @Test
    public void getScoreTest() {
        assertEquals(0, g.getScore());
    }
}
