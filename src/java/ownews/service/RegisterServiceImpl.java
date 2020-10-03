package ownews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ownews.bean.Register;
import ownews.dao.RegisterDao;

/**
 *
 * @author Shreya Appia
 * Provides the implementation of service functions which calls DAO functions 
 * from Register DAO for adding users to LOGIN_DETAILS table in database
 */

@Service("RegisterService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;
    
    public RegisterServiceImpl() {
    }
    
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    @Override
    public boolean addUser(Register register) {
        return registerDao.addUser(register);
    }

}
