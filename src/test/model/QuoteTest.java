package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuoteTest {
    private Quote q;

    @BeforeEach
    public void setup() {
        q = new Quote("test", false);
    }

    @Test
    public void quoteTest() {
        assertFalse(q.getTheCurrentLevel());
        assertEquals("test", q.getQuoteText());
    }

    @Test
    public void setDeleteQuoteTest() {
        q.setDeleteQuote();
        assertTrue(q.getDeleteQuote());
    }

    @Test
    public void setIsTheCurrentLevelTest() {
        q.setIsTheCurrentLevel();
        assertTrue(q.getTheCurrentLevel());

        q.setIsTheCurrentLevel();
        assertFalse(q.getTheCurrentLevel());
    }
}
