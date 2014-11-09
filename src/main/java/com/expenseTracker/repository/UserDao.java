package com.expenseTracker.repository;

import com.expenseTracker.domain.User;

import java.util.List;

public interface UserDao {
    public void addUser(User user);

    public void deleteUser(int id);

    public User findUser(int id);

    public List<User> findAllUsers();

    public void updateUser(User user);
}
