package ownews.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ownews.bean.Login;

/**
 *
 * @author Shreya Appia 
 * Provides the implementation of function for getting user IDs of a username
 */
@Repository("LoginDao")
@SuppressWarnings("unchecked")
public class LoginDaoImpl implements LoginDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public int getUserIdFromName(String username) {
        int userId = 0;
        List<Login> logins = (List<Login>) sessionFactory.getCurrentSession().createCriteria(Login.class)
                .add(Restrictions.like("username", username)).list();

        if (logins.size() == 1) {
            userId = logins.get(0).getUserid();
        }
        return userId;
    }
}