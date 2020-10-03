package ownews.bean;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Shreya Appia
 * bean representing a newly registered user and binds to the LOGIN_DETAILS table in Ownews database
 */
@Entity
@Table(name = "LOGIN_DETAILS")
public class Register implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "USERID")
    private int userid;
    
    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "EMAILID")
    private String emailid;
    
    @Column(name = "ENABLED")
    private boolean enabled;
    
    @OneToOne(mappedBy = "register", cascade = CascadeType.ALL)
    private Authorities authorties;
    
    //Default Constructor
    public Register () {
    }
    
    public Register (String username, String password, String emailid, boolean enabled) {
        this.username = username;
        this.password = password;
        this.emailid = emailid;
        this.enabled = enabled;
    }

    public Authorities getAuthorties() {
        return authorties;
    }

    public void setAuthorties(Authorities authorties) {
        this.authorties = authorties;
    }

    public int getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailid() {
        return emailid;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
   
}
