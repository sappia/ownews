package ownews.bean;

/**
 *
 * @author Shreya Appia
 * wrapper class which wraps the Login.java, Relationship.java and FriendSuggestions.java beans
 */
public class AddFriendForm {
    
    private Login login;
    
    private Relationship relationship;
    
    private FriendSuggestions friendsuggestions;

    //Default Constructor
    public AddFriendForm() {    
    }
    
    public Login getLogin() {
        return login;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public FriendSuggestions getFriendsuggestions() {
        return friendsuggestions;
    }


    public void setLogin(Login login) {
        this.login = login;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public void setFriendsuggestions(FriendSuggestions friendsuggestions) {
        this.friendsuggestions = friendsuggestions;
    }
    
}
