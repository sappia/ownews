package ownews.bean;

/**
 *
 * @author Shreya Appia
 * bean mapping the suggestion names shown on friends page to suggestion values
 */
public class FriendSuggestions {
    
    private String suggestionName;
    
    private String suggestionValue;
    
    //Default Constructor
    public FriendSuggestions() {        
    }

    public FriendSuggestions(String suggestionName, String suggestionValue) {
        this.suggestionName = suggestionName;
        this.suggestionValue = suggestionValue;
    }

    public String getSuggestionName() {
        return suggestionName;
    }

    public String getSuggestionValue() {
        return suggestionValue;
    }

    public void setSuggestionName(String suggestionName) {
        this.suggestionName = suggestionName;
    }

    public void setSuggestionValue(String suggestionValue) {
        this.suggestionValue = suggestionValue;
    }
    
    
}
