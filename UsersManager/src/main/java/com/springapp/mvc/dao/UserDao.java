package com.springapp.mvc.dao;

import com.springapp.mvc.model.User;

import java.util.List;

/**
 * Created by Саша on 27.08.2016.
 */
public interface UserDao {
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
    public User getUserById(int id);
    public List<User> listUsers(String filter);
    public void fillUsers();
}
