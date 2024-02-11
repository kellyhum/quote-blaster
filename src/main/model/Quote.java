package model;

/* A quote in the quote list, where a quote represents
   a level of the game */

public class Quote {
    private String quote;
    private boolean isTheCurrentLevel;

    // EFFECTS: constructs a Quote object with the quote being the contents parameter value
    public Quote(String quote) {
        this.quote = quote;
        this.isTheCurrentLevel = false;
    }

    // MODIFIES: this
    // EFFECTS: sets the value of deleteQuote to true
    public void setDeleteQuote() {
    }

    // MODIFIES: this
    // EFFECTS: toggles the value of the currentlyPlayingQuote value
    public void setIsTheCurrentLevel() {

    }

    // getters
    public String getQuote() {
        return quote;
    }

    public boolean isTheCurrentLevel() {
        return isTheCurrentLevel;
    }
}
