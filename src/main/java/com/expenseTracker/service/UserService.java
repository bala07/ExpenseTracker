package com.expenseTracker.service;

import com.expenseTracker.domain.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(int id);

    public User findUser(int id);

    public List<User> findAllUsers();
}
