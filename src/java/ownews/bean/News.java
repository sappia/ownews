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
 * bean representing a news object and binding the NEWS table in Ownews database
 */
@Entity
@Table(name = "NEWS")
@org.hibernate.annotations.Entity(
		dynamicUpdate = true
)
public class News implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "NEWSID")
    private int newsid;
    @Column(name = "USERID")
    private int userid;
    @Column(name = "NEWS_LINK")
    private String newslink;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "POSTED_TIME")
    private Calendar postedtime;
    @Column(name = "VOTES")
    private int votes;
    @Column(name = "POPULARITY")
    private double popularity;

    //Default Constructor
    public News() {
    }
    
    public int getNewsid() {
        return newsid;
    }

    public int getUserid() {
        return userid;
    }

    public String getNewslink() {
        return newslink;
    }

    public Calendar getPostedtime() {
        return postedtime;
    }

    public int getVotes() {
        return votes;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setNewslink(String newslink) {
        this.newslink = newslink;
    }

    public void setPostedtime(Calendar postedtime) {
        this.postedtime = postedtime;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
    
}
