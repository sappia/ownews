package ownews.bean;

/**
 *
 * @author Shreya Appia 
 * wraps a Login object and variables trust_score and popularity_score to calculate 
 * list of friend recommendations for categories 'By trustworthiness' and 'By popularity'
 */
public class FriendRecommender {

    private double trust_score;
    private double popularity_score;
    private Login login;

    //Default Constructor
    public FriendRecommender() {
    }

    public double getTrust_score() {
        return trust_score;
    }

    public double getPopularity_score() {
        return popularity_score;
    }

    public Login getLogin() {
        return login;
    }

    public void setTrust_score(double trust_score) {
        this.trust_score = trust_score;
    }

    public void setPopularity_score(double popularity_score) {
        this.popularity_score = popularity_score;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
