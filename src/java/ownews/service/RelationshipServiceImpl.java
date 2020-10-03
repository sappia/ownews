package ownews.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ownews.bean.Login;
import ownews.bean.Relationship;
import ownews.dao.RelationshipDao;

/**
 *
 * @author Shreya Appia
 * Provides the implementation of service functions which calls DAO functions from
 * Relationship DAO for getting list of friends and recommendations to add friends
 */
@Service("RelationshipService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RelationshipServiceImpl implements RelationshipService {
    
    private int currentUser;
    @Autowired
    private RelationshipDao relationshipDao;

    @Override
    public void updateUserId(int currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public List<Login> getFriends() {
        return relationshipDao.getFriends(currentUser);
    }
    
    @Override
    public List<Login> getFriendSuggestions(String suggestion) {
        return relationshipDao.getFriendSuggestions(currentUser, suggestion);
    }
    
    @Override
    public void addFriend(Relationship relationship) {
        relationshipDao.addFriend(currentUser, relationship);
    }
    
    @Override
    public int needMoreFriends() {
        return relationshipDao.needMoreFriends(currentUser);
    }
}
