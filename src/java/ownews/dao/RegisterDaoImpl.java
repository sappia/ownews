package ownews.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ownews.bean.Authorities;
import ownews.bean.Login;
import ownews.bean.Register;

/**
 *
 * @author Shreya Appia
 * provides implementation of functions for adding a user to LOGIN_DETAILS table in database
 */

@Repository("RegisterDao")
@SuppressWarnings("unchecked")
public class RegisterDaoImpl implements RegisterDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public boolean addUser(Register register) {
        List<Login> login_list = (List<Login>) sessionFactory.getCurrentSession().createCriteria(Login.class)
                .add(Restrictions.like("username", register.getUsername())).list();
        if (login_list.size() > 0) {
            return false;
        } else {
            Authorities a = new Authorities("ROLE_USER");
            register.setEnabled(true);
            register.setAuthorties(a);
            a.setRegister(register);
            sessionFactory.getCurrentSession().save(register);
            return true;
        }
    }
    
}
