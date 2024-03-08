/*
    Code referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

package persistence;

import model.Quote;
import model.QuoteList;
import model.WordBlock;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    @Test
    public void testInvalidFile() {
        try {
            Quote q = new Quote("test quote", false);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriteQuoteList() {
        try {
            QuoteList ql = new QuoteList();
            ql.addQuote(new Quote("test", true));

            JsonWriter writer = new JsonWriter("./data/emptyQuoteListWriter.json");
            writer.open();
            writer.writeQuoteList(ql);
            writer.close();

            JsonReader reader = new JsonReader("", "./data/emptyQuoteListWriter.json");
            QuoteList ql2 = reader.readQuoteList();
            assertFalse(ql2.getQuoteList().isEmpty());
            assertEquals(1, ql2.getQuoteList().size());
            checkQuoteValues("test", true, ql2.getQuoteList().get(0));

        } catch (IOException e) {
            fail("should not have been thrown");
        }
    }

    @Test
    public void testWriteGame() {
        try {
            int score = 3;
            ArrayList<WordBlock> wb = new ArrayList<>();
            wb.add(new WordBlock("testing word", 10, 5));

            JsonWriter writer = new JsonWriter("./data/noWordsOrScoreWriter.json");
            writer.open();
            writer.writeGameStats(score, wb);
            writer.close();

            JsonReader reader = new JsonReader("./data/noWordsOrScoreWriter.json", "");
            ArrayList<WordBlock> wb2 = reader.readWordList();
            int score2 = reader.readAndParseScore();
            assertFalse(wb2.isEmpty());
            assertEquals(1, wb2.size());
            assertEquals(3, score2);
            checkWordValues("testing word", 10, 5, wb2.get(0));

        } catch (IOException e) {
            fail("should not have been thrown");
        }
    }
}
