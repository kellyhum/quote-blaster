package persistence;

import model.Quote;
import model.QuoteList;
import model.WordBlock;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JsonReader {
    private String sourceFile;

    public JsonReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    public QuoteList readQuoteList() throws IOException {
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuoteList(jsonObject);
    }

    public ArrayList<WordBlock> readWordList() throws IOException {
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWordList(jsonObject);
    }

    public int readAndParseScore() throws IOException {
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject.getInt("score");
    }

    private QuoteList parseQuoteList(JSONObject jsonObject) {
        QuoteList ql = new QuoteList();
        addQuoteToQuoteList(ql, jsonObject);
        return ql;
    }

    private void addQuoteToQuoteList(QuoteList ql, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("quoteDisplay");
        for (Object json : jsonArray) {
            JSONObject nextQuote = (JSONObject) json; // convert to json object
            initializeQuote(nextQuote, ql); // add quote to the quotelist
        }
    }

    private void initializeQuote(JSONObject jsonObject, QuoteList ql) {
        String name = jsonObject.getString("quote");
        Boolean isCurrLevel = jsonObject.getBoolean("isCurrLevel");

        Quote q = new Quote(name, isCurrLevel);
        ql.addQuote(q);
    }

    private ArrayList<WordBlock> parseWordList(JSONObject jsonObject) {
        ArrayList<WordBlock> wl = new ArrayList<>();
        addWordToWordList(wl, jsonObject);
        return wl;
    }

    private void addWordToWordList(ArrayList<WordBlock> wb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("words");
        for (Object json : jsonArray) {
            JSONObject nextWord = (JSONObject) json; // convert to json object
            initializeWord(nextWord, wb); // add quote to the quotelist
        }
    }

    private void initializeWord(JSONObject jsonObject, ArrayList<WordBlock> wb) {
        String word = jsonObject.getString("word");
        int x1 = jsonObject.getInt("x");
        int y1 = jsonObject.getInt("y");

        WordBlock w = new WordBlock(word, x1, y1);
        wb.add(w);
    }
}
