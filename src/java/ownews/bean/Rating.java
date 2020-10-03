package ownews.bean;

/**
 *
 * @author Shreya Appia
 * bean mapping the rating names shown on viewnews page to rating values
 */
public class Rating {
    
    private String ratingName;
    //Rating of a news link
    private int ratingValue;
    
    //Default Constructor
    public Rating() {        
    }

    public Rating(String ratingName, int ratingValue) {
        this.ratingName = ratingName;
        this.ratingValue = ratingValue;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public String getRatingName() {
        return ratingName;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }
}
