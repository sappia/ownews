package ownews.bean;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Shreya Appia
 * bean representing user authorities and binds AUTHORITIES table
 */
@Entity
@Table(name = "AUTHORITIES")
public class Authorities implements Serializable {

    @Id
    @Column(name = "USERID", unique = true, nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters =
    @Parameter(name = "property", value = "register"))
    private int userId;
    @Column(name = "AUTHORITY")
    private String authority;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Register register;

    //Default Constructor
    public Authorities() {
    }

    public Authorities(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Register getRegister() {
        return register;
    }

    public int getUserId() {
        return userId;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAuthority(int userid, String authority) {
        this.userId = userid;
        this.authority = authority;
    }
}
