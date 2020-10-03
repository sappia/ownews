package ownews.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Shreya Appia
 * bean representing a relationship object and binding the RELATIONSHIP table in Ownews database
 */
@Entity
@Table(name = "RELATIONSHIP")
public class Relationship implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "RELATIONID")
    private int relationid;
    @Column(name = "USERID")
    private int userid;
    @Column(name = "FRIENDID")
    private int friendid;
    @Column(name = "RELATION")
    private double relation;
    @Column(name = "RELATION_VOTES")
    private int relation_votes;
    @Column(name = "RELATION_FLAG")
    private int relation_flag;

    //Default Constructor
    public Relationship() {        
    }

    public int getRelationid() {
        return relationid;
    }
    
    public int getUserid() {
        return userid;
    }

    public int getFriendid() {
        return friendid;
    }

    public double getRelation() {
        return relation;
    }

    public int getRelation_votes() {
        return relation_votes;
    }

    public int getRelation_flag() {
        return relation_flag;
    }

    public void setRelationid(int relationid) {
        this.relationid = relationid;
    }
    
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setFriendid(int friendid) {
        this.friendid = friendid;
    }

    public void setRelation(double relation) {
        this.relation = relation;
    }

    public void setRelation_votes(int relation_votes) {
        this.relation_votes = relation_votes;
    }

    public void setRelation_flag(int relation_flag) {
        this.relation_flag = relation_flag;
    }
    
}
