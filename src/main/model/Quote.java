package model;

/* A quote in the quote list, where a quote represents
   a level of the game */

import org.json.JSONObject;

public class Quote {
    private final String quoteText;
    //private boolean isTheCurrentLevel;
    private boolean deleteQuote;

    // EFFECTS: constructs a Quote object with the quote being the quote parameter value, and
    // whether the user wants to play the quote
    public Quote(String quote) {
        quoteText = quote;
        //this.isTheCurrentLevel = isTheCurrentLevel;
    }

    // MODIFIES: this
    // EFFECTS: sets the value of deleteQuote to true
    public void setDeleteQuote() {
        this.deleteQuote = true;
    }

    // MODIFIES: this
    // EFFECTS: toggles the value of the currentlyPlayingQuote value
//    public void setIsTheCurrentLevel() {
//        this.isTheCurrentLevel = !this.isTheCurrentLevel;
//    }

    // EFFECTS: makes a new JSON object with "quote" and "isCurrLevel" keys, values of the Quote
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("quote", quoteText);
        //json.put("isCurrLevel", isTheCurrentLevel);
        return json;
    }

    // getters
    public String getQuoteText() {
        return quoteText;
    }

    public boolean getDeleteQuote() {
        return deleteQuote;
    }

//    public boolean getTheCurrentLevel() {
//        return isTheCurrentLevel;
//    }
}
