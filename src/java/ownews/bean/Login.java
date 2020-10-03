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
 * bean representing a login object and binding the LOGIN_DETAILS table in Ownews database
 */
@Entity
@Table(name = "LOGIN_DETAILS")
public class Login implements Serializable {

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

    //Default Constructor
    public Login() {
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

    public void setUsername(String uname) {
        username = uname;
    }

    public void setPassword(String pwd) {
        password = pwd;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
