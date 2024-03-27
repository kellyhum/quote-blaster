package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuoteListTest {
    private QuoteList q;
    private Quote quote;

    @BeforeEach
    public void setup() {
        q = new QuoteList();
        quote = new Quote("test");
    }

    @Test
    public void quoteListTest() {
        assertEquals(0, q.getQuoteList().size());
    }

    @Test
    public void addQuoteTest() {
        q.addQuote(this.quote);
        assertEquals(1, q.getQuoteList().size());
    }

    @Test
    public void getQuoteTest() {
        q.addQuote(this.quote);
        assertEquals(this.quote, q.getQuoteAtIndex(0));
    }

    @Test
    public void removeQuoteTest() {
        q.addQuote(this.quote);
        q.removeQuote(0);
        assertEquals(0, q.getQuoteList().size());
    }
}
