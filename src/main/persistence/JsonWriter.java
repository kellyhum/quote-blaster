/*
    Code referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

package persistence;

import model.Quote;
import model.QuoteList;
import model.WordBlock;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
    converts Java into JSON code, stores in a JSON file
 */
public class JsonWriter {
    private String destPath;
    private PrintWriter printer;
    private static final int TAB = 4;

    // EFFECTS: makes a new JsonWriter object with a destination path of the parameter
    public JsonWriter(String destPath) {
        this.destPath = destPath;
    }

    // EFFECTS: initialize printWriter
    public void open() throws FileNotFoundException {
        printer = new PrintWriter(destPath);
    }

    // EFFECTS: converts the Quotelist into JSON, puts the JSON into the file
    public void writeQuoteList(QuoteList quoteList) {
        JSONArray quoteArray = new JSONArray();
        JSONObject mainElement = new JSONObject();

        for (Quote q : quoteList.getQuoteList()) {
            JSONObject quote = q.convertToJson();
            quoteArray.put(quote); // add to array
        }

        mainElement.put("quoteDisplay", quoteArray);

        printer.print(mainElement.toString(TAB));
    }

    // EFFECTS: converts the ArrayList and int into JSON, puts the JSON into the file
    public void writeGameStats(int score, ArrayList<WordBlock> w) {
        JSONObject mainElement = new JSONObject();
        JSONArray wordList = new JSONArray();

        for (WordBlock word : w) {
            JSONObject wordObj = word.convertToJson();
            wordList.put(wordObj);
        }

        mainElement.put("score", score);
        mainElement.put("words", wordList);

        printer.print(mainElement.toString(TAB));
    }

    // EFFECTS: closes the printWriter
    public void close() {
        printer.close();
    }
}
