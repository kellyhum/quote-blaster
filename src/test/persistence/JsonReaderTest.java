/*
    Code referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

package persistence;

import model.QuoteList;
import model.WordBlock;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @Test
    public void testNonExistentFileQuote() {
        JsonReader reader = new JsonReader("", "./data/noSuchFile.json");
        try {
            QuoteList ql = reader.readQuoteList();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testNonExistentFileGame() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json", "");
        try {
            ArrayList<WordBlock> wb = reader.readWordList();
            int score = reader.readAndParseScore();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testEmptyQuoteList() {
        JsonReader reader = new JsonReader("", "./data/emptyQuoteList.json");
        try {
            QuoteList ql = reader.readQuoteList();
            assertTrue(ql.getQuoteList().isEmpty());
        } catch (IOException e) {
            fail("not supposed to throw");
        }
    }

    @Test
    public void testEmptyJsonObject() {
        JsonReader reader = new JsonReader("", "./data/emptyJsonObject.json");
        try {
            QuoteList ql = reader.readQuoteList();
            assertTrue(ql.getQuoteList().isEmpty());
        } catch (IOException e) {
            fail("not supposed to throw");
        }
    }

    @Test
    public void testEmptyWordListAndScore() {
        JsonReader reader = new JsonReader("./data/noWordsOrScore.json", "");
        try {
            ArrayList<WordBlock> wb = reader.readWordList();
            int score = reader.readAndParseScore();
            assertTrue(wb.isEmpty());
            assertEquals(0, score);
        } catch (IOException e) {
            fail("not supposed to throw");
        }
    }

    @Test
    public void testQuoteList() {
        JsonReader reader = new JsonReader("", "./data/normalQuoteList.json");
        try {
            QuoteList ql = reader.readQuoteList();
            assertFalse(ql.getQuoteList().isEmpty());
            assertEquals(2, ql.getQuoteList().size());
            checkQuoteValues("lkjlkj", ql.getQuoteList().get(0));
            checkQuoteValues("lk", ql.getQuoteList().get(1));
        } catch (IOException e) {
            fail("not supposed to throw");
        }
    }

    @Test
    public void testWordListAndScore() {
        JsonReader reader = new JsonReader("./data/normalWordAndScore.json", "");
        try {
            ArrayList<WordBlock> wb = reader.readWordList();
            int score = reader.readAndParseScore();
            assertFalse(wb.isEmpty());
            assertEquals(3, wb.size());
            assertEquals(1, score);
            checkWordValues("testBlock", 40, 5, wb.get(0));
            checkWordValues("test", 23, 0, wb.get(1));
            checkWordValues("quote", 18, 9, wb.get(2));
        } catch (IOException e) {
            fail("not supposed to throw");
        }
    }
}
