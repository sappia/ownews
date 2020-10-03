package ownews.service;

import java.util.List;
import ownews.bean.Login;
import ownews.bean.Relationship;

/**
 *
 * @author Shreya Appia
 * Provides the interface for Relationship Service functions
 */
public interface RelationshipService {

    public void updateUserId(int currentUser);
    
    public List<Login> getFriends();
    
    public List<Login> getFriendSuggestions(String suggestion);
    
    public void addFriend(Relationship relationship);
    
    public int needMoreFriends();
    
}
