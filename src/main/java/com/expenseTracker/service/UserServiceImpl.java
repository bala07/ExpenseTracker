package com.expenseTracker.service;

import com.expenseTracker.domain.User;
import com.expenseTracker.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public User findUser(int id) {
        return userDao.findUser(id);
    }

    @Override
    public List<User> getUsersOfExpenseSheet(int expenseSheetId) {
        return userDao.getUsersOfExpenseSheet(expenseSheetId);
    }
}
