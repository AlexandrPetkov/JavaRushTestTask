package com.springapp.mvc.service;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Саша on 27.08.2016.
 */

@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> listUsers(String filter) {
        return userDao.listUsers(filter);
    }


    @Override
    @Transactional
    public void fillUsers() {
        userDao.fillUsers();
    }
}
