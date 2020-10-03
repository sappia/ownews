package ownews.dao;

import java.util.List;
import ownews.bean.Login;
import ownews.bean.Relationship;

/**
 *
 * @author Shreya Appia
 * Provides the interface for Relationship DAO functions
 */
public interface RelationshipDao {

    public List<Login> getFriends(int currentUser);
    
    public List<Login> getFriendSuggestions(int currentUser, String suggestion);
    
    public void addFriend(int currentUser, Relationship relationship);
    
    public int needMoreFriends(int currentUser);
    
    public double getAverageRelation();
}
