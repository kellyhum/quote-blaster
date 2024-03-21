/*
    Code referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

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

/*
    converts JSON into Java code
 */
public class JsonReader {
    private String sourceGame;
    private String sourceQuote;

    // EFFECTS: initializes a JsonReader object with sourceGame and sourceQuote fields set to the parameter inputs
    public JsonReader(String sourceGame, String sourceQuote) {
        this.sourceGame = sourceGame;
        this.sourceQuote = sourceQuote;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the quotelist from the file and returns the java QuoteList object
    public QuoteList readQuoteList() throws IOException {
        String jsonData = readFile(sourceQuote);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuoteList(jsonObject);
    }

    // EFFECTS: parses the words from the file and returns an arraylist of wordblocks
    public ArrayList<WordBlock> readWordList() throws IOException {
        String jsonData = readFile(sourceGame);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWordList(jsonObject);
    }

    // EFFECTS: parses the score from the file and returns the score in integer format
    public int readAndParseScore() throws IOException {
        String jsonData = readFile(sourceGame);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject.getInt("score");
    }

    // EFFECTS: parses the quotes from the json, add them to a quotelist object and return the quotelist
    private QuoteList parseQuoteList(JSONObject jsonObject) {
        QuoteList ql = new QuoteList();
        addQuoteToQuoteList(ql, jsonObject);
        return ql;
    }

    // MODIFIES: ql
    // EFFECTS: converts the quote jsons into Java, adds to the quotelist
    private void addQuoteToQuoteList(QuoteList ql, JSONObject jsonObject) {
        if (jsonObject.length() != 0) {
            JSONArray jsonArray = jsonObject.getJSONArray("quoteDisplay");
            for (Object json : jsonArray) {
                JSONObject nextQuote = (JSONObject) json; // convert to json object
                initializeQuote(nextQuote, ql); // add quote to the quotelist
            }
        }
    }

    // MODIFIES: ql
    // EFFECTS: parses the string data for the quote, makes a new quote and adds to the quotelist
    private void initializeQuote(JSONObject jsonObject, QuoteList ql) {
        String name = jsonObject.getString("quote");
        //Boolean isCurrLevel = jsonObject.getBoolean("isCurrLevel");

        Quote q = new Quote(name);
        ql.addQuote(q);
    }

    // EFFECTS: makes a new arraylist, adds the words into the list and return the list
    private ArrayList<WordBlock> parseWordList(JSONObject jsonObject) {
        ArrayList<WordBlock> wl = new ArrayList<>();
        addWordToWordList(wl, jsonObject);
        return wl;
    }

    // MODIFIES: wb
    // EFFECTS: loops through each word object in the json, initializes the word using the other method
    private void addWordToWordList(ArrayList<WordBlock> wb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("words");
        for (Object json : jsonArray) {
            JSONObject nextWord = (JSONObject) json; // convert to json object
            initializeWord(nextWord, wb); // add quote to the quotelist
        }
    }

    // MODIFIES: wb
    // EFFECTS: pulls the data for a WordBlock from the json, makes a new WordBlock and appends into list
    private void initializeWord(JSONObject jsonObject, ArrayList<WordBlock> wb) {
        String word = jsonObject.getString("word");
        int x1 = jsonObject.getInt("x");
        int y1 = jsonObject.getInt("y");

        WordBlock w = new WordBlock(word, x1, y1);
        wb.add(w);
    }
}
