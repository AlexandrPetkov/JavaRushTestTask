package com.springapp.mvc.dao;

import com.springapp.mvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User added successfully. User details: "+user);

    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User updated successfully. User details: "+user);
    }

    @Override
    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        if (user != null)
        {
            session.delete(user);
            logger.info("User deleted successfully. User details: "+user);
        } else logger.info("There is no such user");

    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User loaded successfully. User details: "+user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers(String filter) {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User where name like '"+filter+"%'").list();
        for (User user : userList){
            logger.info("User list: "+user);
        }
        return userList;
    }

    @Override
    public void fillUsers() {
        for (int i = 0; i < 15; i++) {
            addUser(new User("user"+i, 20+i, false));
        }
    }
}
