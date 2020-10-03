package ownews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ownews.dao.LoginDao;

/**
 *
 * @author Shreya Appia
 * Provides the implementation of service function which calls DAO function from
 * Login DAO for getting user IDs of a username
 */
@Service("LoginService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    public LoginServiceImpl() {
    }

    @Override
     public int getUserIdFromName(String username) {
        return loginDao.getUserIdFromName(username);
                
    }
    
    
}