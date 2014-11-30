package com.expenseTracker.repository;

import com.expenseTracker.domain.User;

import java.util.List;

public interface UserDao {
    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

    public User findUser(int id);

    public List<User> findAllUsers();

}
