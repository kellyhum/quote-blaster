package model;

/* A quote in the quote list, where a quote represents
   a level of the game */

import org.json.JSONObject;

public class Quote {
    private final String quoteText;
    private boolean deleteQuote;

    // EFFECTS: constructs a Quote object with the quote being the quote parameter value, and
    // whether the user wants to play the quote
    public Quote(String quote) {
        quoteText = quote;
    }

    // MODIFIES: this
    // EFFECTS: sets the value of deleteQuote to true
    public void setDeleteQuote() {
        this.deleteQuote = true;
    }

    // EFFECTS: makes a new JSON object with a "quote" key, values of the Quote
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("quote", quoteText);
        return json;
    }

    // getters
    public String getQuoteText() {
        return quoteText;
    }

    public boolean getDeleteQuote() {
        return deleteQuote;
    }
}
