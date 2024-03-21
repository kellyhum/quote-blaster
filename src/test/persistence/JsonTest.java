/*
    Code referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

package persistence;

import model.Quote;
import model.WordBlock;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkQuoteValues(String desc, Quote q) {
        assertEquals(desc, q.getQuoteText());
    }

    protected void checkWordValues(String desc, int x, int y, WordBlock w) {
        assertEquals(desc, w.getWord());
        assertEquals(x, w.getX());
        assertEquals(y, w.getY());
    }
}
