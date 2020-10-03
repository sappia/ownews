package ownews.bean;

/**
 *
 * @author Shreya Appia
 * wrapper class which wraps the News.java, VotedPosts.java and Rating.java beans
 */
public class VotingForm {
    
    private News news;
    
    private Rating rating;
    
    private VotedPosts votedposts;
    
    //Default Constructor
    public VotingForm() {       
    }
    
    public News getNews() {
        return news;
    }

    public Rating getRating() {
        return rating;
    }

    public VotedPosts getVotedposts() {
        return votedposts;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setVotedposts(VotedPosts votedposts) {
        this.votedposts = votedposts;
    }

}
