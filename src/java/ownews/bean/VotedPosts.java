package ownews.bean;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Shreya Appia
 * bean representing rating details of a voted news and binds the VOTED_POSTS table in Ownews database
 */
@Entity
@Table(name = "VOTED_POSTS")
public class VotedPosts implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "POSTID")
    private int postid;
    @Column(name = "USERID")
    private int userid;
    @Column(name = "NEWSID")
    private int newsid;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "VOTED_TIME")
    private Calendar votedtime;
    @Column(name = "RATING")
    private int rating;

    //Default Constructor
    public VotedPosts() {
    }
    
    public int getPostid() {
        return postid;
    }

    public int getUserid() {
        return userid;
    }

    public int getNewsid() {
        return newsid;
    }

    public Calendar getVotedtime() {
        return votedtime;
    }

    public int getRating() {
        return rating;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    public void setVotedtime(Calendar votedtime) {
        this.votedtime = votedtime;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
}
