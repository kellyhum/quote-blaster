package persistence;

import model.Quote;
import model.QuoteList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String sourceFile;

    public JsonReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public QuoteList readQuoteList() throws IOException {
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuoteList(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private QuoteList parseQuoteList(JSONObject jsonObject) {
        QuoteList ql = new QuoteList();
        addQuoteToQuoteList(ql, jsonObject);
        return ql;
    }

    private void addQuoteToQuoteList(QuoteList ql, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("quoteDisplay");
        for (Object json : jsonArray) {
            JSONObject nextQuote = (JSONObject) json; // get the json of the quote
            initializeQuote(nextQuote, ql); // add quote to the quotelist
        }
    }

    private void initializeQuote(JSONObject jsonObject, QuoteList ql) {
        String name = jsonObject.getString("quote");
        Boolean isCurrLevel = jsonObject.getBoolean("isCurrLevel");

        Quote q = new Quote(name, isCurrLevel);
        ql.addQuote(q);
    }
}
