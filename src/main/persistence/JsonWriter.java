package persistence;

import model.Quote;
import model.QuoteList;
import model.WordBlock;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JsonWriter {
    private String destPath;
    private PrintWriter printer;
    private static final int TAB = 4;

    public JsonWriter(String destPath) {
        this.destPath = destPath;
    }

    // EFFECTS: initialize printWriter
    public void open() throws FileNotFoundException {
        printer = new PrintWriter(destPath);
    }

//    public void writeAll(QuoteList ql, int score, ArrayList<WordBlock> wb) {
//        JSONArray mainArray = new JSONArray();
//        mainArray.put(writeQuoteList(ql));
//
//        mainArray.put(writeGameStats(score, wb));
//    }

    public void writeQuoteList(QuoteList quoteList) {
        JSONArray quoteArray = new JSONArray();
        JSONObject mainElement = new JSONObject();

        for (Quote q : quoteList.getQuoteList()) {
            JSONObject quote = q.convertToJson();
            quoteArray.put(quote); // add to array
        }

        // need to add to the MAIN json object!
        mainElement.put("quoteDisplay", quoteArray);

        // convert array to string, print the output
        printer.print(mainElement.toString(TAB));
    }

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
