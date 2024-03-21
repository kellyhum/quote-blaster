package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuoteTest {
    private Quote q;

    @BeforeEach
    public void setup() {
        q = new Quote("test");
    }

    @Test
    public void quoteTest() {
        assertEquals("test", q.getQuoteText());
    }

    @Test
    public void setDeleteQuoteTest() {
        q.setDeleteQuote();
        assertTrue(q.getDeleteQuote());
    }
}
