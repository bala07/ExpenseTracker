package com.expenseTracker.service;

import com.expenseTracker.domain.User;
import com.expenseTracker.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public User findUser(int id) {
        return userDao.findUser(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }
}
